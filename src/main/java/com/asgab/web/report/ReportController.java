package com.asgab.web.report;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/report")
public class ReportController {

  @RequestMapping(method = RequestMethod.GET)
  public String report(HttpServletRequest request, RedirectAttributes redirectAttributes) {

    
    return "report/report";
  }
}
