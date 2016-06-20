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
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asgab.core.pagination.Page;
import com.asgab.entity.Agency;
import com.asgab.entity.Client;
import com.asgab.entity.Product;
import com.asgab.entity.Report;
import com.asgab.entity.User;
import com.asgab.service.account.AccountService;
import com.asgab.service.agency.AgencyService;
import com.asgab.service.client.ClientService;
import com.asgab.service.exchange.ExchangeRateService;
import com.asgab.service.product.ProductService;
import com.asgab.service.report.ReportService;
import com.asgab.util.CommonUtil;
import com.asgab.util.SelectMapper;

@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {

  @Autowired
  private ClientService clientService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private ProductService productService;

  @Autowired
  private AgencyService agencyService;

  @Autowired
  private ReportService reportService;

  @Autowired
  private ExchangeRateService exchangeRateService;

  @RequestMapping(value = "addProduct", method = RequestMethod.POST)
  public String addProduct(HttpServletRequest request, Model model) {
    List<SelectMapper> mappers = new ArrayList<SelectMapper>();
    mappers.add(new SelectMapper("CPC", "CPC"));
    mappers.add(new SelectMapper("CPM", "CPM"));
    model.addAttribute("index", request.getParameter("index"));
    model.addAttribute("saleModes", mappers);
    List<Product> products_data = new ArrayList<Product>();
    products_data.addAll(productService.getAllProduct());
    model.addAttribute("products_data", products_data);
    return "businessOpportunity/product";
  }

  @ResponseBody
  @RequestMapping(value = "getAdvertisers", method = RequestMethod.GET)
  public String getAdvertisers(@RequestParam("q") String name) {
    Map<String, Object> params = new HashMap<String, Object>();
    if (StringUtils.isNotBlank(name)) {
      params.put("clientname", name);
    }
    Page<Client> page = new Page<Client>(1, 10, null, params);
    Page<Client> pages = clientService.search(page);

    JSONArray array = new JSONArray();
    for (int i = 0; i < pages.getContent().size(); i++) {
      JSONObject tmp = new JSONObject();
      tmp.put("id", pages.getContent().get(i).getId());
      tmp.put("text", pages.getContent().get(i).getClientname());
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

  @ResponseBody
  @RequestMapping(value = "getAgencys", method = RequestMethod.GET)
  public String getAgencys(@RequestParam("q") String name) {

    Map<String, Object> params = new HashMap<String, Object>();
    if (StringUtils.isNotBlank(name)) {
      params.put("name", name);
    }
    Page<Agency> page = new Page<Agency>(1, 10, null, params);
    Page<Agency> pages = agencyService.search(page);

    JSONArray array = new JSONArray();
    for (int i = 0; i < pages.getContent().size(); i++) {
      JSONObject tmp = new JSONObject();
      tmp.put("id", pages.getContent().get(i).getId());
      tmp.put("text", pages.getContent().get(i).getChannel_name());
      array.add(tmp);
    }

    return array.toJSONString();
  }

  @RequestMapping(value = "report/list", method = RequestMethod.POST)
  public String list(Report report, Model model, HttpServletRequest request) {
    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
    String lang = localeResolver.resolveLocale(request).getLanguage();
    String[] dataRightColumns = {"report.product", "report.sale.team", "report.sale.representative", "report.channel.company"};
    Map<String, Object> map = reportService.getReport(report);
    model.addAttribute("names", map.get("names"));
    model.addAttribute("opportunityReports", map.get("opportunityReports"));
    model.addAttribute("orderReports", map.get("orderReports"));
    Map<String, Object> exchangeRateMap = new HashMap<String, Object>();
    exchangeRateMap.put("base_currency", "RMB");
    model.addAttribute("exchangeRates", exchangeRateService.search(exchangeRateMap));

    model.addAttribute("dataRightColumn", CommonUtil.getProperty(lang, dataRightColumns[Integer.parseInt(report.getDataRight()) - 1]));
    return "report/reportResultList";
  }

}
