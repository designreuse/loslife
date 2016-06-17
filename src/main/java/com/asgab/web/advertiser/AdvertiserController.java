package com.asgab.web.advertiser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asgab.core.pagination.Page;
import com.asgab.entity.Client;
import com.asgab.service.client.ClientService;
import com.asgab.util.CommonUtil;
import com.asgab.util.Servlets;

@Controller
@RequestMapping(value = "/advertiser")
public class AdvertiserController {
  private static final String PAGE_SIZE = "10";

  @Autowired
  private ClientService clientService;

  @RequestMapping(value = "list", method = RequestMethod.GET)
  public String init(Model model) {
    Page<Client> pages = new Page<Client>(1, 10, "", new HashMap<String, Object>());
    pages.setContent(new ArrayList<Client>());
    pages.setTotal(-1);
    model.addAttribute("search", Servlets.encodeParameterString(new HashMap<String, Object>()));
    model.addAttribute("pages", pages);
    return "advertiser/advertiserList";
  }

  @RequestMapping(method = RequestMethod.GET)
  public String list(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
      @RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize, @RequestParam(
          value = "sort", defaultValue = "id desc") String sort, HttpServletRequest request,
      Model model) {
    Map<String, Object> params = new HashMap<String, Object>();
    if (StringUtils.isNotBlank(request.getParameter("clientname"))) {
      params.put("clientname", request.getParameter("clientname"));
    }
    if (StringUtils.isNotBlank(request.getParameter("brand"))) {
      params.put("brand", request.getParameter("brand"));
    }
    if (StringUtils.isNotBlank(request.getParameter("channel"))) {
      params.put("channel", request.getParameter("channel"));
      params.put("tempChannel_name", request.getParameter("tempChannel_name"));
    }
    if (StringUtils.isNotBlank(request.getParameter("saleIds"))) {
      params.put("saleIds", request.getParameter("saleIds"));
      params.put("tempSaleIds_name", request.getParameter("tempSaleIds_name"));
    }
    if (StringUtils.isNotBlank(request.getParameter("dateRange"))) {
      params.put("dateRange", request.getParameter("dateRange"));
      if (request.getParameter("dateRange").length() >= 10) {
        params.put("createDateStart", request.getParameter("dateRange").substring(0, 10)
            + " 00:00:00");
      }
      if (request.getParameter("dateRange").length() >= 23) {
        params.put("createDateEnd", request.getParameter("dateRange").substring(13, 23)
            + " 23:59:59");
      }
    }
    params.put("client_status", "Active");

    model.addAttribute("search", Servlets.encodeParameterString(params));
    params.put("sort", sort);
    Page<Client> page = new Page<Client>(pageNumber, pageSize, sort, params);
    Page<Client> pages = clientService.search(page);
    model.addAttribute("pages", pages);
    // 设置行业和货币
    clientService.setSelect(request);
    // 设置联系人
    clientService.resetClientContacts(pages.getContent());
    return "advertiser/advertiserList";
  }

  @RequestMapping(value = "merge", method = RequestMethod.GET)
  public String toMerge(Model model, HttpServletRequest request) {
    String checkIds = request.getParameter("checkIds");
    List<Long> idList = new ArrayList<Long>();
    for (String id : checkIds.split(",")) {
      idList.add(Long.parseLong(id));
    }
    List<Client> clients = clientService.getClientsByIdList(idList);
    for (Client client : clients) {
      clientService.resetClientContact(client);
    }
    List<Object> ids = new ArrayList<Object>();
    List<Object> names = new ArrayList<Object>();
    List<Object> brands = new ArrayList<Object>();
    List<Object> channels = new ArrayList<Object>();
    List<Object> industrys = new ArrayList<Object>();
    List<Object> addresses = new ArrayList<Object>();
    List<Object> qualifications = new ArrayList<Object>();
    List<Object> website_names = new ArrayList<Object>();
    List<Object> website_addresses = new ArrayList<Object>();
    List<Object> organization_codes = new ArrayList<Object>();
    List<Object> icps = new ArrayList<Object>();
    List<Object> business_licences = new ArrayList<Object>();
    List<Object> platforms = new ArrayList<Object>();
    List<Object> currencys = new ArrayList<Object>();
    List<Object> whether_cross_districts = new ArrayList<Object>();
    // 联系人
    List<Object> contacts = new ArrayList<Object>();

    for (Client client : clients) {
      ids.add(client.getId());
      names.add(client.getClientname());
      brands.add(client.getBrand());

      // 是否代理，代理商
      Map<String, Object> channelMap = new HashMap<String, Object>();
      channelMap.put("whether_channel", client.getWhether_channel());
      channelMap.put("channel", client.getChannel());
      channelMap.put("channel_name", client.getChannel_name());
      channels.add(channelMap);

      industrys.add(client.getIndustry_id());
      addresses.add(client.getAddress());
      qualifications.add(client.getCompany_name());
      website_names.add(client.getWebsite_name());
      website_addresses.add(client.getWebsite_address());
      organization_codes.add(client.getOrganization_code());
      icps.add(client.getIcp());
      business_licences.add(client.getBusiness_licence());
      contacts.add(client.getContacts());
      platforms.add(client.getPlatform());
      currencys.add(client.getCurrency_id());
      whether_cross_districts.add(client.getWhether_cross_district());
    }

    Map<String, Object> map = new HashMap<>();
    map.put("id", ids);
    map.put("clientname", names);
    map.put("brand", brands);
    map.put("industry_id", industrys);
    map.put("address", addresses);
    map.put("company_name", qualifications);
    map.put("website_name", website_names);
    map.put("website_address", website_addresses);
    map.put("organization_code", organization_codes);
    map.put("icp", icps);
    map.put("business_licence", business_licences);
    map.put("platform", platforms);
    map.put("currency_id", currencys);
    map.put("contacts", contacts);
    map.put("channels", channels);
    map.put("whether_cross_districts", whether_cross_districts);

    model.addAttribute("map", map);
    model.addAttribute("checkIds", checkIds);
    clientService.setSelect(request);

    return "advertiser/advertiserReview";
  }

  @RequestMapping(value = "merge", method = RequestMethod.POST)
  public String merge(Client client, HttpServletRequest request,
      RedirectAttributes redirectAttributes) {
    String mergeIds = request.getParameter("checkIds");
    // 获取要保留的广告主ID
    String keepId = request.getParameter("id");
    String copy_contact_client_id = request.getParameter("copy_contact_client_id");

    Client keepClient = clientService.get(Long.valueOf(keepId));
    if (keepClient != null && StringUtils.isNotBlank(mergeIds)) {
      keepClient.setClientname(request.getParameter("clientname"));
      keepClient.setBrand(request.getParameter("brand"));
      keepClient.setWhether_channel(Integer.valueOf(request.getParameter("whether_channel")));
      String chanel = request.getParameter("channel");
      keepClient.setChannel("".equals(chanel) ? null : chanel);
      keepClient.setIndustry_id(Integer.valueOf(request.getParameter("industry_id")));
      keepClient.setCurrency_id(Integer.valueOf(request.getParameter("currency_id")));
      keepClient.setAddress(request.getParameter("address"));
      keepClient.setWhether_cross_district(Integer.valueOf(request
          .getParameter("whether_cross_districts")));
      keepClient.setCompany_name(request.getParameter("company_name"));
      keepClient.setWebsite_name(request.getParameter("website_name"));
      keepClient.setWebsite_address(request.getParameter("website_address"));
      keepClient.setOrganization_code(request.getParameter("organization_code"));
      keepClient.setIcp(request.getParameter(request.getParameter("icp")));
      keepClient.setBusiness_licence(request.getParameter("business_licence"));
      keepClient.setPlatform(request.getParameter("platform"));

      Map<String, Object> mergeMap = new HashMap<String, Object>();
      mergeMap.put("mergeIds", mergeIds);// 待合并的广告主IDs
      mergeMap.put("keepId", keepId);// 保留的广告主ID
      mergeMap.put("keepClient", keepClient);// 保留的广告主，需要Update的
      mergeMap.put("copy_contact_client_id", copy_contact_client_id);// 合并联系人来源
      mergeMap.put("refreshDataRange", request.getParameter("refreshDataRange"));
      mergeMap.put("mergedData", request.getParameter("mergedData"));

      clientService.merge(mergeMap);

      redirectAttributes.addFlashAttribute("message",
          CommonUtil.getProperty(request, "message.review.success"));
    }

    return "redirect:/advertiser";
  }

}
