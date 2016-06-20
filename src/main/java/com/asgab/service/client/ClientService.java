package com.asgab.service.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.asgab.core.pagination.Page;
import com.asgab.entity.BusinessOpportunity;
import com.asgab.entity.Client;
import com.asgab.entity.ClientContact;
import com.asgab.entity.ClientShare;
import com.asgab.repository.AgencyMapper;
import com.asgab.repository.BusinessOpportunityMapper;
import com.asgab.repository.ClientContactMapper;
import com.asgab.repository.ClientMapper;
import com.asgab.repository.ClientShareMapper;
import com.asgab.service.account.AccountService;
import com.asgab.service.management.CurrencyTypeService;
import com.asgab.service.management.IndustryTypeService;

@Component
@Transactional
public class ClientService {

  @Autowired
  private ClientMapper clientMapper;
  @Autowired
  private ClientContactMapper clientContactMapper;
  @Autowired
  private ClientShareMapper clientShareMapper;
  @Autowired
  private CurrencyTypeService currencyTypeService;
  @Autowired
  private IndustryTypeService industryTypeService;
  @Autowired
  private BusinessOpportunityMapper businessOpportunityMapper;
  @Autowired
  private AgencyMapper agencyService;
  @Autowired
  private AccountService accountService;

  public Page<Client> search(Page<Client> page) {
    List<Client> clients = clientMapper.search(page.getSearchMap(), page.getRowBounds());
    int count = clientMapper.count(page.getSearchMap());
    page.setContent(clients);
    page.setTotal(count);
    return page;
  }

  public Client get(Long id) {
    return clientMapper.get(id);
  }

  public void save(Client client) {
    clientMapper.save(client);

    // 添加广告主联系人
    addClientContacts(client.getContacts(), client.getId());

    // 添加销售人员
    addClientShares(client.getSaleIds(), client.getId());
  }

  public void update(Client client) {
    clientMapper.update(client);
    // 先删除销售人员,物理删除
    clientShareMapper.deleteByClientId(client.getId());
    // 再添加销售人员
    addClientShares(client.getSaleIds(), client.getId());

    if (client.getDeleteContactIds() != null && client.getDeleteContactIds().length > 0) {
      for (String deleteId : client.getDeleteContactIds()) {
        ClientContact cc_db = clientContactMapper.get(Long.valueOf(deleteId));
        if (cc_db != null) {
          cc_db.setIs_delete(0);
          cc_db.setUpdated_at(new Date());
          clientContactMapper.update(cc_db);
        }
      }
    }

    if (client.getContacts() != null && client.getContacts().size() > 0) {
      for (ClientContact cc : client.getContacts()) {
        if (cc.getId() == null) {
          if (cc.getContact_person() != null) {
            cc.setClient_id(client.getId());
            cc.setCreated_at(new Date());
            clientContactMapper.save(cc);
          }
        } else {
          ClientContact cc_db = clientContactMapper.get(cc.getId());
          if (cc_db != null) {
            cc_db.update(cc);
            clientContactMapper.update(cc_db);
          }
        }
      }
    }
  }

  /***
   * 添加广告主联系人
   * 
   * @param ccs 多个联系人信息
   * @param client_id 广告主ID
   */
  private void addClientContacts(List<ClientContact> ccs, long client_id) {
    if (ccs != null && ccs.size() > 0) {
      for (ClientContact cc : ccs) {
        cc.setClient_id(client_id);
        cc.setCreated_at(new Date());
        clientContactMapper.save(cc);
      }
    }
  }

  /***
   * 添加userId和clientId的关系
   * 
   * @param userIds 销售人员IDs
   * @param client_id 广告主ID
   */
  private void addClientShares(String userIds, long client_id) {
    ClientShare cs = null;
    if (StringUtils.isNotBlank(userIds)) {
      for (String share_id : userIds.split(",")) {
        if (!"0".equals(share_id)) {
          cs = new ClientShare();
          cs.setClient_id(client_id);
          cs.setShare_id(Long.parseLong(share_id));
          clientShareMapper.save(cs);
        }
      }
    }
  }

  public void delete(Long id) {
    clientMapper.delete(id);
  }

  public List<Client> getClientsByIdList(List<Long> idList) {
    return clientMapper.getClientsByIdList(idList);
  }

  public void setSelect(HttpServletRequest request) {
    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
    String lang = localeResolver.resolveLocale(request).getLanguage();
    Map<String, Object> searchMap = new HashMap<String, Object>();
    searchMap.put("lang", lang);
    request.setAttribute("currencyTypes", currencyTypeService.getOptions(null));
    request.setAttribute("industryTypes", industryTypeService.getOptions(searchMap));
    request.setAttribute("agencys", agencyService.search(null));
    request.setAttribute("users", accountService.getAllXMOUser());
  }

  public void resetClientContact(Client client) {
    Map<String, Object> searchMap = new HashMap<String, Object>();
    searchMap.put("client_id", client.getId());
    client.setContacts(clientContactMapper.search(searchMap));
  }

  public void resetClientContacts(List<Client> clients) {
    if (clients == null || (clients != null && clients.size() == 0)) {
      return;
    }
    List<Long> clientIds = new ArrayList<Long>();
    for (Client client : clients) {
      clientIds.add(client.getId());
    }
    List<ClientContact> clientContacts =
        clientContactMapper.getClientContactsByClientIdList(clientIds);
    if (clientContacts != null && clientContacts.size() > 0) {
      for (Client client : clients) {
        for (ClientContact contact : clientContacts) {
          if (client.getId().equals(contact.getClient_id())) {
            client.getContacts().add(contact);
          }
        }
      }
    }
  }

  /***
   * 合并广告主
   */
  public void merge(Map<String, Object> parameters) {
    Client keepClient = (Client) parameters.get("keepClient");
    String copy_contact_client_id = (String) parameters.get("copy_contact_client_id");
    String keepId = (String) parameters.get("keepId");
    String refreshDataRange = (String) parameters.get("refreshDataRange");
    Date now = new Date();

    // 更新保留下来广告主
    clientMapper.update(keepClient);

    // 废弃合并后的广告主
    for (String clientId : parameters.get("mergeIds").toString().split(",")) {
      if (!keepId.equals(clientId)) {
        Client abandonClient = clientMapper.get(Long.parseLong(clientId));
        if (abandonClient != null) {
          abandonClient.setStatus("Stop");
          abandonClient.setUpdated_at(now);
          clientMapper.update(abandonClient);
        }
      }
    }

    // 如果联系人需要合并
    if (!keepId.equals(copy_contact_client_id)) {
      List<Long> seach = new ArrayList<Long>();
      seach.add(Long.parseLong(keepId));
      seach.add(Long.parseLong(copy_contact_client_id));
      List<ClientContact> contactList = clientContactMapper.getClientContactsByClientIdList(seach);
      if (contactList != null) {
        for (ClientContact cc : contactList) {
          if (keepId.equals(cc.getClient_id().toString())) {
            cc.setIs_delete(0);
            cc.setUpdated_at(now);
            clientContactMapper.update(cc);
          } else if (copy_contact_client_id.equals(cc.getClient_id().toString())) {
            ClientContact copy_cc = new ClientContact();
            copy_cc.setClient_id(Long.parseLong(keepId));
            copy_cc.setContact_person(cc.getContact_person());
            copy_cc.setPhone(cc.getPhone());
            copy_cc.setEmail(cc.getEmail());
            copy_cc.setPosition(cc.getPosition());
            copy_cc.setIs_delete(1);
            copy_cc.setCreated_at(now);
            clientContactMapper.save(copy_cc);
          }
        }
      }
    }

    Map<String, Object> searchMap = new HashMap<String, Object>();
    searchMap.put("advertiserIds", parameters.get("mergeIds"));

    if (!"3".equals(refreshDataRange)) {
      // 按日期刷新
      if ("2".equals(refreshDataRange)) {
        searchMap.put("mergedData", parameters.get("mergedData"));
      }

      List<BusinessOpportunity> listBO = businessOpportunityMapper.getListByCondition(searchMap);
      for (BusinessOpportunity bo : listBO) {
        if (!keepId.equals(bo.getAdvertiser_id().toString())) {
          bo.setAdvertiser_id(Long.parseLong(keepId));
          bo.setUpdated_at(now);
          businessOpportunityMapper.update(bo);
        }
      }
    }
  }
}
