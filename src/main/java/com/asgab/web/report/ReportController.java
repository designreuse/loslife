package com.asgab.web.report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.asgab.service.management.CurrencyTypeService;
import com.asgab.util.CommonUtil;
import com.asgab.util.SelectMapper;

@Controller
@RequestMapping(value = "/report")
public class ReportController {

  @Autowired
  private CurrencyTypeService currencyTypeService;

  @RequestMapping(method = RequestMethod.GET)
  public String report(Model model, HttpServletRequest request) {
    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
    String lang = localeResolver.resolveLocale(request).getLanguage();

    List<SelectMapper> currencys = currencyTypeService.getOptions(null);
    currencys.add(0, new SelectMapper("0", CommonUtil.getProperty(lang, "report.all")));
    model.addAttribute("currencys", currencys);
    return "report/report";
  }
}
