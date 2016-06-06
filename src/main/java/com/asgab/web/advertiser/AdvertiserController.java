package com.asgab.web.advertiser;

import java.util.HashMap;
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
import com.asgab.entity.xmo.Advertiser;
import com.asgab.service.advertiser.AdvertiserService;
import com.asgab.util.Servlets;
import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "/advertiser")
public class AdvertiserController {
  private static final String PAGE_SIZE = "10";

  @Autowired
  private AdvertiserService advertiserService;

  @RequestMapping(method = RequestMethod.GET)
  public String list(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
      @RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize, @RequestParam(value = "sort", defaultValue = "id desc") String sort,
      ServletRequest request, Model model) {
    Map<String, Object> params = new HashMap<String, Object>();
    if (StringUtils.isNotBlank(request.getParameter("clientname"))) {
      params.put("clientname", request.getParameter("clientname"));
    }
    if (StringUtils.isNotBlank(request.getParameter("client_brand"))) {
      params.put("client_brand", request.getParameter("client_brand"));
    }
    if (StringUtils.isNotBlank(request.getParameter("company_name"))) {
      params.put("company_name", request.getParameter("company_name"));
    }

    model.addAttribute("search", Servlets.encodeParameterString(params));
    params.put("sort", sort);
    Page<Advertiser> page = new Page<Advertiser>(pageNumber, pageSize, sort, params);
    Page<Advertiser> pages = advertiserService.search(page);
    model.addAttribute("pages", pages);
    return "advertiser/advertiserList";
  }

  @RequestMapping(value = "merge", method = RequestMethod.GET)
  public String toMerge(Model model, HttpServletRequest request) {
    String checkIds = request.getParameter("checkIds");
    System.out.println(checkIds);
    // 构建数据
    Advertiser advertiser1 = new Advertiser();
    advertiser1.setId(100001L);
    advertiser1.setClientname("雅诗兰黛1");
    advertiser1.setClient_brand("倩碧1");
    advertiser1.setIndustry("化妆1");
    advertiser1.setCompany_address("上海宝山区");
    advertiser1.setQualification_name("abc公司");
    advertiser1.setWebsite_name("百度");
    advertiser1.setWebsite_address("www.baidu.com");
    advertiser1.setOrganization_code("100011");
    advertiser1.setIcp("ICP0001");
    advertiser1.setBusiness_licence("ISBN2234");

    Advertiser advertiser2 = new Advertiser();
    advertiser2.setId(100002L);
    advertiser2.setClientname("雅诗兰黛2");
    advertiser2.setClient_brand("芭比波朗2");
    advertiser2.setIndustry("彩妆2");
    advertiser2.setCompany_address("上海徐汇区");
    advertiser2.setQualification_name("YSLD");
    advertiser2.setWebsite_name("雅诗兰黛");
    advertiser2.setWebsite_address("www.ysld.com");
    advertiser2.setOrganization_code("12345");
    advertiser2.setIcp("ICP002");
    advertiser2.setBusiness_licence("ISBN454R8");

    Advertiser advertiser3 = new Advertiser();
    advertiser3.setId(100003L);
    advertiser3.setClientname("雅诗兰黛3");
    advertiser3.setClient_brand("汤姆福特3");
    advertiser3.setIndustry("顶级限量彩妆3顶级限量彩妆3顶级限量彩妆3");
    advertiser3.setCompany_address("上海静安寺");
    advertiser3.setQualification_name("TMTF");
    advertiser3.setWebsite_name("汤姆福特");
    advertiser3.setWebsite_address("www.tm.com");
    advertiser3.setOrganization_code("10067");
    advertiser3.setIcp("ICP3493");
    advertiser3.setBusiness_licence("ISBN3J4O");


    Map<String, Object> map = new HashMap<>();
    map.put("id", Lists.newArrayList(advertiser1.getId(), advertiser2.getId(), advertiser3.getId()));
    map.put("clientname", Lists.newArrayList(advertiser1.getClientname(), advertiser2.getClientname(), advertiser3.getClientname()));
    map.put("client_brand", Lists.newArrayList(advertiser1.getClient_brand(), advertiser2.getClient_brand(), advertiser3.getClient_brand()));
    map.put("industry", Lists.newArrayList(advertiser1.getIndustry(), advertiser2.getIndustry(), advertiser3.getIndustry()));
    map.put("company_address", Lists.newArrayList(advertiser1.getCompany_address(), advertiser2.getCompany_address(), advertiser3.getCompany_address()));
    map.put("qualification_name", Lists.newArrayList(advertiser1.getQualification_name(), advertiser2.getQualification_name(), advertiser3.getQualification_name()));
    map.put("website_name", Lists.newArrayList(advertiser1.getWebsite_name(), advertiser2.getWebsite_name(), advertiser3.getWebsite_name()));
    map.put("website_address", Lists.newArrayList(advertiser1.getWebsite_address(), advertiser2.getWebsite_address(), advertiser3.getWebsite_address()));
    map.put("organization_code", Lists.newArrayList(advertiser1.getOrganization_code(), advertiser2.getOrganization_code(), advertiser3.getOrganization_code()));
    map.put("icp", Lists.newArrayList(advertiser1.getIcp(), advertiser2.getIcp(), advertiser3.getIcp()));
    map.put("business_licence", Lists.newArrayList(advertiser1.getBusiness_licence(), advertiser2.getBusiness_licence(), advertiser3.getBusiness_licence()));

    model.addAttribute("map", map);
    model.addAttribute("checkIds", checkIds);

    return "advertiser/advertiserReview";
  }

}
