package com.asgab.service.client;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.core.pagination.Page;
import com.asgab.entity.Client;
import com.asgab.entity.ClientContact;
import com.asgab.entity.ClientShare;
import com.asgab.repository.ClientContactMapper;
import com.asgab.repository.ClientMapper;
import com.asgab.repository.ClientShareMapper;

@Component
@Transactional
public class ClientService {

  @Autowired
  private ClientMapper clientMapper;
  @Autowired
  private ClientContactMapper clientContactMapper;
  @Autowired
  private ClientShareMapper clientShareMapper;

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
    addClientShares(client.getUserIds(), client.getId());
  }

  public void update(Client client) {
    clientMapper.update(client);
    // 先删除销售人员,物理删除
    clientShareMapper.deleteByClientId(client.getId());
    // 再添加销售人员
    addClientShares(client.getUserIds(), client.getId());

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
}
