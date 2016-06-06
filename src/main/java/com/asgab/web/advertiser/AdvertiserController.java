package com.asgab.web.advertiser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.asgab.core.pagination.Page;
import com.asgab.entity.Client;
import com.asgab.service.client.ClientService;
import com.asgab.util.Servlets;

@Controller
@RequestMapping(value = "/advertiser")
public class AdvertiserController {
  private static final String PAGE_SIZE = "10";

  @Autowired
  private ClientService clientService;

  @RequestMapping(method = RequestMethod.GET)
  public String list(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
      @RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize, @RequestParam(value = "sort", defaultValue = "id desc") String sort,
      ServletRequest request, Model model) {
    Map<String, Object> params = new HashMap<String, Object>();
    if (StringUtils.isNotBlank(request.getParameter("name"))) {
      params.put("name", request.getParameter("name"));
    }
    if (StringUtils.isNotBlank(request.getParameter("brand"))) {
      params.put("brand", request.getParameter("brand"));
    }
    if (StringUtils.isNotBlank(request.getParameter("dateRange"))) {
      params.put("dateRange", request.getParameter("dateRange"));
      if (request.getParameter("dateRange").length() >= 10) {
        params.put("createDateStart", request.getParameter("dateRange").substring(0, 10) + " 00:00:00");
      }
      if (request.getParameter("dateRange").length() >= 23) {
        params.put("createDateEnd", request.getParameter("dateRange").substring(13, 23) + " 23:59:59");
      }
    }
    params.put("client_status", "Active");

    model.addAttribute("search", Servlets.encodeParameterString(params));
    params.put("sort", sort);
    Page<Client> page = new Page<Client>(pageNumber, pageSize, sort, params);
    Page<Client> pages = clientService.search(page);
    model.addAttribute("pages", pages);
    return "advertiser/advertiserList";
  }

  @RequestMapping(value = "merge", method = RequestMethod.GET)
  public String toMerge(Model model, HttpServletRequest request) {
    String checkIds = request.getParameter("checkIds");
    List<Long> idList = new ArrayList<Long>();
    for (String id : checkIds.split(",")) {
      idList.add(Long.parseLong(id));
    }
    List<Client> clients = clientService.getAdvertisersByIdList(idList);
    List<Object> ids = new ArrayList<Object>();
    List<Object> names = new ArrayList<Object>();
    List<Object> brands = new ArrayList<Object>();
    List<Object> industrys = new ArrayList<Object>();
    List<Object> addresses = new ArrayList<Object>();
    List<Object> qualifications = new ArrayList<Object>();
    List<Object> website_names = new ArrayList<Object>();
    List<Object> website_addresses = new ArrayList<Object>();
    List<Object> organization_codes = new ArrayList<Object>();
    List<Object> icps = new ArrayList<Object>();
    List<Object> business_licences = new ArrayList<Object>();
    for (Client client : clients) {
      ids.add(client.getId());
      names.add(client.getName());
      brands.add(client.getBrand());
      industrys.add(client.getIndustry_name());
      addresses.add(client.getAddress());
      qualifications.add(client.getQualification_name());
      website_names.add(client.getWebsite_name());
      website_addresses.add(client.getWebsite_address());
      organization_codes.add(client.getOrganization_code());
      icps.add(client.getIcp());
      business_licences.add(client.getBusiness_licence());
    }

    Map<String, Object> map = new HashMap<>();
    map.put("id", ids);
    map.put("name", names);
    map.put("brand", brands);
    map.put("industry", industrys);
    map.put("address", addresses);
    map.put("qualification", qualifications);
    map.put("website_name", website_names);
    map.put("website_addresse", website_addresses);
    map.put("organization_code", organization_codes);
    map.put("icp", icps);
    map.put("business_licence", business_licences);

    model.addAttribute("map", map);
    model.addAttribute("checkIds", checkIds);

    return "advertiser/advertiserReview";
  }

}
