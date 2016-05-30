package com.asgab.web.advertiser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asgab.entity.Review;
import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "/advertiser")
public class AdvertiserController {



  @RequestMapping(value = "review", method = RequestMethod.GET)
  public String toReview(Model model) {
    // 构建数据
    Review view1 = new Review();
    view1.setId("100001");
    view1.setName("雅诗兰黛1");
    view1.setBrand("倩碧1");
    view1.setIndustry("化妆1");

    Review view2 = new Review();
    view2.setId("100002");
    view2.setName("雅诗兰黛2");
    view2.setBrand("芭比波朗2");
    view2.setIndustry("彩妆2");

    Review view3 = new Review();
    view3.setId("100003");
    view3.setName("雅诗兰黛3");
    view3.setBrand("汤姆福特3");
    view3.setIndustry("顶级限量彩妆3顶级限量彩妆3顶级限量彩妆3");



    Map<String, List<String>> map = new HashMap<>();
    map.put("id", Lists.newArrayList(view1.getId(), view2.getId(), view3.getId()));
    map.put("name", Lists.newArrayList(view1.getName(), view2.getName(), view3.getName()));
    map.put("brand", Lists.newArrayList(view1.getBrand(), view2.getBrand(), view3.getBrand()));
    map.put("industry", Lists.newArrayList(view1.getIndustry(), view2.getIndustry(), view3.getIndustry()));

    model.addAttribute("map", map);

    return "advertiser/advertiserReview";
  }
}
