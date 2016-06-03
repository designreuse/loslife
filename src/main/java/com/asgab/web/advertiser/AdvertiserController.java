package com.asgab.web.advertiser;

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
import com.asgab.entity.Review;
import com.asgab.service.review.ReviewService;
import com.asgab.util.Servlets;
import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "/advertiser")
public class AdvertiserController {
  private static final String PAGE_SIZE = "10";

  @Autowired
  private ReviewService reviewService;

  @RequestMapping(method = RequestMethod.GET)
  public String list(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
      @RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize, @RequestParam(value = "sort", defaultValue = "id desc") String sort,
      ServletRequest request, Model model) {
    Map<String, Object> params = new HashMap<String, Object>();
    if (StringUtils.isNotBlank(request.getParameter("advertiser"))) {
      params.put("advertiser", request.getParameter("advertiser"));
    }

    model.addAttribute("search", Servlets.encodeParameterString(params));
    params.put("sort", sort);
    Page<Review> page = new Page<Review>(pageNumber, pageSize, sort, params);
    Page<Review> pages = reviewService.search(page);
    model.addAttribute("pages", pages);
    return "advertiser/reviewList";
  }

  @RequestMapping(value = "review", method = RequestMethod.GET)
  public String toReview(Model model, HttpServletRequest request) {
    // 构建数据
    Review view1 = new Review();
    view1.setId("100001");
    view1.setClientname("雅诗兰黛1");
    view1.setClient_brand("倩碧1");
    view1.setIndustry("化妆1");
    view1.setCompany_adress("上海宝山区");
    view1.setQualification_name("abc公司");
    view1.setWebsite_name("百度");
    view1.setWebsite_address("www.baidu.com");
    view1.setOrganization_code("100011");
    view1.setIcp("ICP0001");
    view1.setBusiness_license("ISBN2234");

    Review view2 = new Review();
    view2.setId("100002");
    view2.setClientname("雅诗兰黛2");
    view2.setClient_brand("芭比波朗2");
    view2.setIndustry("彩妆2");
    view2.setCompany_adress("上海徐汇区");
    view2.setQualification_name("YSLD");
    view2.setWebsite_name("雅诗兰黛");
    view2.setWebsite_address("www.ysld.com");
    view2.setOrganization_code("12345");
    view2.setIcp("ICP002");
    view2.setBusiness_license("ISBN454R8");

    Review view3 = new Review();
    view3.setId("100003");
    view3.setClientname("雅诗兰黛3");
    view3.setClient_brand("汤姆福特3");
    view3.setIndustry("顶级限量彩妆3顶级限量彩妆3顶级限量彩妆3");
    view3.setCompany_adress("上海静安寺");
    view3.setQualification_name("TMTF");
    view3.setWebsite_name("汤姆福特");
    view3.setWebsite_address("www.tm.com");
    view3.setOrganization_code("10067");
    view3.setIcp("ICP3493");
    view3.setBusiness_license("ISBN3J4O");


    Map<String, List<String>> map = new HashMap<>();
    map.put("id", Lists.newArrayList(view1.getId(), view2.getId(), view3.getId()));
    map.put("clientname", Lists.newArrayList(view1.getClientname(), view2.getClientname(), view3.getClientname()));
    map.put("client_brand", Lists.newArrayList(view1.getClient_brand(), view2.getClient_brand(), view3.getClient_brand()));
    map.put("industry", Lists.newArrayList(view1.getIndustry(), view2.getIndustry(), view3.getIndustry()));
    map.put("company_adress", Lists.newArrayList(view1.getCompany_adress(), view2.getCompany_adress(), view3.getCompany_adress()));
    map.put("qualification_name", Lists.newArrayList(view1.getQualification_name(), view2.getQualification_name(), view3.getQualification_name()));
    map.put("website_name", Lists.newArrayList(view1.getWebsite_name(), view2.getWebsite_name(), view3.getWebsite_name()));
    map.put("website_address", Lists.newArrayList(view1.getWebsite_address(), view2.getWebsite_address(), view3.getWebsite_address()));
    map.put("organization_code", Lists.newArrayList(view1.getOrganization_code(), view2.getOrganization_code(), view3.getOrganization_code()));
    map.put("icp", Lists.newArrayList(view1.getIcp(), view2.getIcp(), view3.getIcp()));
    map.put("business_license", Lists.newArrayList(view1.getBusiness_license(), view2.getBusiness_license(), view3.getBusiness_license()));

    model.addAttribute("map", map);

    return "advertiser/advertiserReview";
  }

  @RequestMapping(value = "merge", method = RequestMethod.POST)
  public String merge(Review review) {
    reviewService.update(review);
    return "redirect:/advertiser/review";
  }

}
