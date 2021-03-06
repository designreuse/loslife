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

import com.alibaba.fastjson.JSONObject;
import com.asgab.core.pagination.Page;
import com.asgab.entity.BusinessOpportunity;
import com.asgab.service.business.opportunity.BusinessOpportunityService;
import com.asgab.util.CommonUtil;
import com.asgab.util.Servlets;

@Controller
@RequestMapping(value = "/businessOpportunity")
public class BusinessOpportunityController {
	private static final String PAGE_SIZE = "10";

	@Autowired
	private BusinessOpportunityService businessOpportunityService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sort", defaultValue = "id desc") String sort, ServletRequest request, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		String number = request.getParameter("number");
		if (StringUtils.isNotBlank(number)) {
			params.put("number", number);
			params.put("fmt_number", number.replace("SO", "").replaceFirst("^0*", ""));
		}
		if (StringUtils.isNotBlank(request.getParameter("advertiser"))) {
			params.put("advertiser", request.getParameter("advertiser"));
		}
		if (StringUtils.isNotBlank(request.getParameter("name"))) {
			params.put("name", request.getParameter("name"));
		}
		if (StringUtils.isNotBlank(request.getParameter("status"))) {
			params.put("status", request.getParameter("status"));
		}

		model.addAttribute("search", Servlets.encodeParameterString(params));
		params.put("sort", sort);
		Page<BusinessOpportunity> page = new Page<BusinessOpportunity>(pageNumber, pageSize, sort, params);
		Page<BusinessOpportunity> pages = businessOpportunityService.search(page);
		model.addAttribute("pages", pages);
		model.addAttribute("statusesMap", BusinessOpportunityService.statusMap);
		model.addAttribute("statusesZH", BusinessOpportunityService.statusZH);
		model.addAttribute("statusesEN", BusinessOpportunityService.statusEN);
		return "businessOpportunity/businessOpportunityList";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String toCreate(Model model, HttpServletRequest request) {
		BusinessOpportunity businessOpportunity = new BusinessOpportunity();
		businessOpportunity.setProgress(10);
		businessOpportunity.setExist_msa(1);
		businessOpportunity.setExist_service(1);
		model.addAttribute("businessOpportunity", businessOpportunity);
		model.addAttribute("action", "create");
		return "businessOpportunity/businessOpportunityForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(BusinessOpportunity businessOpportunity, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		businessOpportunity.setDeliver_start_date(businessOpportunity.getDeliver_date().substring(0, 10));
		businessOpportunity.setDeliver_end_date(businessOpportunity.getDeliver_date().substring(13, 23));
		businessOpportunityService.save(businessOpportunity);
		redirectAttributes.addFlashAttribute("message", CommonUtil.getProperty(request, "message.create.success"));
		return "redirect:/businessOpportunity";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("businessOpportunity") BusinessOpportunity businessOpportunity,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String result = businessOpportunityService.update(businessOpportunity);
		if (result != null) {
			String orderMessage = "";
			JSONObject jsonObject = JSONObject.parseObject(result);
			if (jsonObject.containsKey("success")) {
				if (jsonObject.getBoolean("success")) {
					orderMessage = CommonUtil.getProperty(request, "message.create.order.success") + ", ";
					orderMessage += CommonUtil.getProperty(request, "message.create.order.id") + ": "
							+ jsonObject.getInteger("order_id");
				} else {
					orderMessage = CommonUtil.getProperty(request, "message.create.order.error");
				}
			} else {
				// token access denied
				orderMessage = CommonUtil.getProperty(request, "message.token.denied");
			}
			redirectAttributes.addFlashAttribute("orderMessage", orderMessage);
		}
		redirectAttributes.addFlashAttribute("message", CommonUtil.getProperty(request, "message.update.success"));
		return "redirect:/businessOpportunity";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
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
