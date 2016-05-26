package com.asgab.web.business.opportunity;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.asgab.core.pagination.Page;
import com.asgab.entity.BusinessOpportunity;
import com.asgab.entity.BusinessOpportunityProduct;
import com.asgab.repository.xmo.UserXMOMapper;
import com.asgab.service.business.opportunity.BusinessOpportunityService;
import com.asgab.util.CommonUtil;
import com.asgab.util.Servlets;

@Controller
@RequestMapping(value = "/businessOpportunity")
public class BusinessOpportunityController {
  private static final String PAGE_SIZE = "10";

  @Autowired
  private BusinessOpportunityService businessOpportunityService;

  @Autowired
  private UserXMOMapper userXMOMapper;

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
    Page<BusinessOpportunity> page = new Page<BusinessOpportunity>(pageNumber, pageSize, sort, params);
    Page<BusinessOpportunity> pages = businessOpportunityService.search(page);
    model.addAttribute("pages", pages);
    return "businessOpportunity/businessOpportunityList";
  }

  @RequestMapping(value = "create", method = RequestMethod.GET)
  public String toCreate(Model model, HttpServletRequest request) {
    BusinessOpportunity businessOpportunity = new BusinessOpportunity();
    businessOpportunity.setExist_msa(0);
    businessOpportunity.setExist_service(0);
    model.addAttribute("businessOpportunity", businessOpportunity);
    model.addAttribute("currencys", businessOpportunityService.getCurrencyMappers());
    model.addAttribute("action", "create");
    return "businessOpportunity/businessOpportunityForm";
  }

  @RequestMapping(value = "create", method = RequestMethod.POST)
  public String create(BusinessOpportunity businessOpportunity, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    String productArray = request.getParameter("hiddenProductArray");
    JSONArray jsonArray = JSONArray.parseArray(productArray);
    for (int i = 0; i < jsonArray.size(); i++) {
      JSONArray tmpArray = jsonArray.getJSONArray(i);
      BusinessOpportunityProduct tmpProduct = new BusinessOpportunityProduct();
      tmpProduct.setProduct_id(tmpArray.getLong(0));
      tmpProduct.setSale_mode(tmpArray.getString(1));
      tmpProduct.setBudget(tmpArray.getBigDecimal(2));
      businessOpportunity.getBusinessOpportunityProducts().add(tmpProduct);
    }

    businessOpportunity.setCooperate_sales(CommonUtil.array2String(request.getParameterValues("cooperate_sales")));
    businessOpportunity.setDeliver_start_date(businessOpportunity.getDeliver_date().substring(0, 10));
    businessOpportunity.setDeliver_end_date(businessOpportunity.getDeliver_date().substring(13, 23));
    businessOpportunityService.save(businessOpportunity);
    redirectAttributes.addFlashAttribute("message", CommonUtil.getProperty(request, "message.create.success"));
    return "redirect:/businessOpportunity";
  }

  @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
  public String view(@PathVariable("id") Long id, Model model) {
    BusinessOpportunity businessOpportunity = businessOpportunityService.get(id);
    businessOpportunity.setCurrencys(businessOpportunityService.getCurrencys());
    // 设置own_sale
    businessOpportunity.setOwner_sale_name(userXMOMapper.get(businessOpportunity.getOwner_sale()).getName());
    // 设置coop_sale
    String coopSales = businessOpportunity.getCooperate_sales();
    String[] userIds = CommonUtil.string2Array(coopSales);
    for (int i = 0; i < userIds.length; i++) {
      businessOpportunity.getCooperate_sale_list().add(userXMOMapper.get(Long.parseLong(userIds[i])));
    }
    model.addAttribute("businessOpportunity", businessOpportunity);
    return "businessOpportunity/businessOpportunityView";
  }

  @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
  public String toUpdate(@PathVariable("id") Long id, Model model) {
    model.addAttribute("businessOpportunity", businessOpportunityService.get(id));
    model.addAttribute("action", "update");
    return "businessOpportunity/businessOpportunityForm";
  }

  @RequestMapping(value = "update", method = RequestMethod.POST)
  public String update(@ModelAttribute("businessOpportunity") BusinessOpportunity businessOpportunity, HttpServletRequest request,
      RedirectAttributes redirectAttributes) {
    businessOpportunityService.update(businessOpportunity);
    redirectAttributes.addFlashAttribute("message", CommonUtil.getProperty(request, "message.update.success"));
    return "redirect:/businessOpportunity";
  }

  @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
  public String delete(@PathVariable("id") Long id, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    businessOpportunityService.delete(id);
    redirectAttributes.addFlashAttribute("message", CommonUtil.getProperty(request, "message.delete.success"));
    return "redirect:/businessOpportunity";
  }

  @RequestMapping(value = "addProduct", method = RequestMethod.POST)
  public String addProduct(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("index", request.getParameter("index"));
    return "businessOpportunity/product";
  }

  @ModelAttribute
  public void getCustMaster(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
    if (id != -1) {
      model.addAttribute("businessOpportunity", businessOpportunityService.get(id));
    }
  }

}
