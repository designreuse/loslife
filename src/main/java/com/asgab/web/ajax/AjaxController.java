package com.asgab.web.ajax;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asgab.core.pagination.Page;
import com.asgab.entity.Product;
import com.asgab.entity.User;
import com.asgab.entity.xmo.Advertiser;
import com.asgab.service.account.AccountService;
import com.asgab.service.advertiser.AdvertiserService;
import com.asgab.service.product.ProductService;
import com.asgab.util.SelectMapper;

@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {

  @Autowired
  private AdvertiserService advertiserService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private ProductService productService;

  @RequestMapping(value = "addProduct", method = RequestMethod.POST)
  public String addProduct(HttpServletRequest request, Model model) {
    List<SelectMapper> mappers = new ArrayList<SelectMapper>();
    mappers.add(new SelectMapper("CPC", "CPC"));
    mappers.add(new SelectMapper("CPM", "CPM"));
    model.addAttribute("index", request.getParameter("index"));
    model.addAttribute("saleModes", mappers);
    return "businessOpportunity/product";
  }

  @ResponseBody
  @RequestMapping(value = "getAdvertisers", method = RequestMethod.GET)
  public String getAdvertisers(@RequestParam("q") String name) {
    Map<String, Object> params = new HashMap<String, Object>();
    if (StringUtils.isNotBlank(name)) {
      params.put("name", name);
    }
    Page<Advertiser> page = new Page<Advertiser>(1, 10, null, params);
    Page<Advertiser> pages = advertiserService.search(page);

    JSONArray array = new JSONArray();
    for (int i = 0; i < pages.getContent().size(); i++) {
      JSONObject tmp = new JSONObject();
      tmp.put("id", pages.getContent().get(i).getId());
      tmp.put("text", pages.getContent().get(i).getName());
      array.add(tmp);
    }
    return array.toJSONString();
  }

  @ResponseBody
  @RequestMapping(value = "getSales", method = RequestMethod.GET)
  public String getSales(@RequestParam("q") String name) {

    Map<String, Object> params = new HashMap<String, Object>();
    if (StringUtils.isNotBlank(name)) {
      params.put("name", name);
    }
    Page<User> page = new Page<User>(1, 10, null, params);
    Page<User> pages = accountService.getAllUser(page);

    JSONArray array = new JSONArray();
    for (int i = 0; i < pages.getContent().size(); i++) {
      JSONObject tmp = new JSONObject();
      tmp.put("id", pages.getContent().get(i).getId());
      tmp.put("text", pages.getContent().get(i).getName());
      array.add(tmp);
    }
    return array.toJSONString();

  }

  @ResponseBody
  @RequestMapping(value = "getProducts", method = RequestMethod.GET)
  public String getProducts(@RequestParam("q") String name) {

    Map<String, Object> params = new HashMap<String, Object>();
    if (StringUtils.isNotBlank(name)) {
      params.put("name", name);
    }
    Page<Product> page = new Page<Product>(1, 10, null, params);
    Page<Product> pages = productService.search(page);

    JSONArray array = new JSONArray();
    for (int i = 0; i < pages.getContent().size(); i++) {
      JSONObject tmp = new JSONObject();
      tmp.put("id", pages.getContent().get(i).getId());
      tmp.put("text", pages.getContent().get(i).getName());
      array.add(tmp);
    }
    return array.toJSONString();

  }
  
  @RequestMapping(value = "addContact", method = RequestMethod.POST)
  public String addContact(HttpServletRequest request, Model model) {
    model.addAttribute("index", request.getParameter("index"));
    return "client/include/clientContact";
  }

}
