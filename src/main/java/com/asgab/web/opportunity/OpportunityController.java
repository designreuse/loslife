package com.asgab.web.opportunity;

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
import com.asgab.entity.Opportunity;
import com.asgab.entity.ProgressBar;
import com.asgab.service.opportunity.OpportunityService;
import com.asgab.util.Servlets;

@Controller
@RequestMapping(value = "/opportunity")
public class OpportunityController {
  private static final String PAGE_SIZE = "10";
  
  @Autowired
  private OpportunityService opportunityService;

  @RequestMapping(method = RequestMethod.GET)
  public String List(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
      @RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize, @RequestParam(value = "sort", defaultValue = "id desc") String sort,
      ServletRequest request, Model model) {
    Map<String, Object> params = new HashMap<String, Object>();
    if (StringUtils.isNotBlank(request.getParameter("task"))) {
      params.put("task", request.getParameter("task"));
    }
    
    model.addAttribute("search", Servlets.encodeParameterString(params));
    params.put("sort", sort);
    Page<Opportunity> page = new Page<Opportunity>(pageNumber, pageSize, sort, params);
    Page<Opportunity> pages = opportunityService.search(page);
    model.addAttribute("pages", pages);
    return "opportunity/opportunityList";
  }

  @RequestMapping(value = "create", method = RequestMethod.GET)
  public String toCreate(Model model, HttpServletRequest request) {

    return "opportunity/opportunityForm";
  }
}
