package com.asgab.web.opportunity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.asgab.core.pagination.Page;
import com.asgab.util.CommonUtil;
import com.asgab.util.Servlets;

@Controller
@RequestMapping(value = "/opportunity")
public class opportunityController {
  private static final String PAGE_SIZE = "10";

  @RequestMapping(method = RequestMethod.GET)
  public String List(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
      @RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize, @RequestParam(value = "sort", defaultValue = "id desc") String sort,
      ServletRequest request, Model model) {


    return "opportunity/opportunityList";
  }

  @RequestMapping(value = "create", method = RequestMethod.GET)
  public String toCreate(Model model, HttpServletRequest request) {

    return "opportunity/opportunityForm";
  }
}
