package com.asgab.service.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.entity.ExchangeRate;
import com.asgab.entity.Report;
import com.asgab.repository.ReportMapper;
import com.asgab.service.exchange.ExchangeRateService;
import com.asgab.util.CommonUtil;


@Component
@Transactional
public class ReportService {

  @Autowired
  ReportMapper reportMapper;

  @Autowired
  ExchangeRateService exchangeRateService;

  public Map<String, Object> getReport(Report report) {
    List<ExchangeRate> exchangeRates = exchangeRateService.getAllAvailExchangeRates();
    String val1 = report.getProgress().split(";")[0];
    String val2 = report.getProgress().split(";")[1];
    boolean searchOpportunity = false;
    boolean searchOrder = false;
    if (val2.equalsIgnoreCase("100")) {
      searchOrder = true;
    }
    if (!val1.equalsIgnoreCase("100")) {
      searchOpportunity = true;
    }
    Map<String, Object> map = null;
    if ("1".equalsIgnoreCase(report.getDataRight())) {
      map = getReportByProduct(report, exchangeRates, searchOpportunity, searchOrder);
    } else if ("4".equalsIgnoreCase(report.getDataRight())) {
      map = getReportByChannel(report, exchangeRates, searchOpportunity, searchOrder);
    }
    return map;
  }

  public Map<String, Object> getReportByProduct(Report report, List<ExchangeRate> exchangeRates, boolean searchOpportunity, boolean searchOrder) {
    List<Report> opportunityReports = new ArrayList<Report>();
    List<Report> orderReports = new ArrayList<Report>();
    if (searchOpportunity) {
      opportunityReports = reportMapper.getOpportunitysByProduct(report);
    }
    if (searchOrder) {
      orderReports = reportMapper.getOrdersByProduct(report);
    }
    sumReport(opportunityReports, exchangeRates, "product");
    sumReport(orderReports, exchangeRates, "product");
    return formatProductReport(orderReports, opportunityReports);
  }

  public List<Report> getReportBySaleTeam(Map<String, Object> parameters) {
    return reportMapper.getReportBySaleTeam(parameters);
  }

  public List<Report> getReportBySaleRepresentative(Map<String, Object> parameters) {
    return reportMapper.getReportBySaleRepresentative(parameters);
  }

  public Map<String, Object> getReportByChannel(Report report, List<ExchangeRate> exchangeRates, boolean searchOpportunity, boolean searchOrder) {
    List<Report> opportunityReports = new ArrayList<Report>();
    List<Report> orderReports = new ArrayList<Report>();
    if (searchOpportunity) {
      opportunityReports = reportMapper.getOpportunitysByChannel(report);
    }
    if (searchOrder) {
      orderReports = reportMapper.getOpportunitysByChannel(report);
    }
    sumReport(opportunityReports, exchangeRates, "channel");
    sumReport(orderReports, exchangeRates, "channel");
    return formatChannelReport(orderReports, opportunityReports);
  }

  private void exchangeRates(List<Report> reports, List<ExchangeRate> exchangeRates) {
    for (Report o : reports) {
      for (ExchangeRate e : exchangeRates) {
        // 如果找到汇率就转换
        if (o.getCurrency().equalsIgnoreCase(e.getBase_currency()) && "RMB".equalsIgnoreCase(e.getCurrency())) {
          o.setBudgetSum(new BigDecimal(e.getRate()).multiply(o.getBudgetSum()));
          break;
        }
      }
    }
  }

  // 汇率转换 & 产品合并
  private void sumReport(List<Report> reports, List<ExchangeRate> exchangeRates, String type) {
    // 汇率转换
    exchangeRates(reports, exchangeRates);
    // 合并产品
    for (int i = reports.size() - 1; i >= 0; i--) {
      Report ri = reports.get(i);
      for (int j = i - 1; j >= 0; j--) {
        Report rj = reports.get(j);
        if ("product".equalsIgnoreCase(type)) {
          if (ri.getProduct_id().equals(rj.getProduct_id())) {
            rj.setBudgetSum(rj.getBudgetSum().add(ri.getBudgetSum()));
            // 删掉ri
            reports.remove(ri);
            break;
          }
        } else if ("channel".equalsIgnoreCase(type)) {
          if (ri.getChannel().equals(rj.getChannel())) {
            rj.setBudgetSum(rj.getBudgetSum().add(ri.getBudgetSum()));
            // 删掉ri
            reports.remove(ri);
            break;
          }
        }
      }
    }
  }

  private Map<String, Object> formatProductReport(List<Report> listA, List<Report> listB) {
    Map<String, Object> map = new TreeMap<String, Object>();
    // 获取
    List<String> productList = new ArrayList<String>();
    for (Report tmp : listA) {
      if (StringUtils.isNoneBlank(tmp.getProduct_name())) {
        productList.add(tmp.getProduct_name());
      }
    }
    for (Report tmp : listB) {
      if (StringUtils.isNoneBlank(tmp.getProduct_name())) {
        productList.add(tmp.getProduct_name());
      }
    }
    // 去重复
    CommonUtil.distinctList(productList);
    List<Report> targetReportOpportunity = new ArrayList<Report>();
    List<Report> targetReportOrder = new ArrayList<Report>();
    for (String product_name : productList) {
      boolean findA = false;
      boolean findB = false;
      for (Report tmp : listA) {
        if (product_name.equalsIgnoreCase(tmp.getProduct_name())) {
          targetReportOpportunity.add(tmp);
          findA = true;
          break;
        }
      }
      if (!findA) {
        targetReportOpportunity.add(null);
      }

      for (Report tmp : listB) {
        if (product_name.equalsIgnoreCase(tmp.getProduct_name())) {
          targetReportOrder.add(tmp);
          findB = true;
          break;
        }
      }
      if (!findB) {
        targetReportOrder.add(null);
      }
    }
    map.put("names", productList);
    map.put("opportunityReports", targetReportOpportunity);
    map.put("orderReports", targetReportOrder);

    return map;
  }


  private Map<String, Object> formatChannelReport(List<Report> listA, List<Report> listB) {
    Map<String, Object> map = new TreeMap<String, Object>();
    // 获取
    List<String> channelList = new ArrayList<String>();
    for (Report tmp : listA) {
      if (StringUtils.isNoneBlank(tmp.getChannel_name())) {
        channelList.add(tmp.getChannel_name());
      }
    }
    for (Report tmp : listB) {
      if (StringUtils.isNoneBlank(tmp.getChannel_name())) {
        channelList.add(tmp.getChannel_name());
      }
    }
    // 去重复
    CommonUtil.distinctList(channelList);
    List<Report> targetReportOpportunity = new ArrayList<Report>();
    List<Report> targetReportOrder = new ArrayList<Report>();
    for (String channel_name : channelList) {
      boolean findA = false;
      boolean findB = false;
      for (Report tmp : listA) {
        if (channel_name.equalsIgnoreCase(tmp.getChannel_name())) {
          targetReportOpportunity.add(tmp);
          findA = true;
          break;
        }
      }
      if (!findA) {
        targetReportOpportunity.add(null);
      }

      for (Report tmp : listB) {
        if (channel_name.equalsIgnoreCase(tmp.getChannel_name())) {
          targetReportOrder.add(tmp);
          findB = true;
          break;
        }
      }
      if (!findB) {
        targetReportOrder.add(null);
      }
    }
    map.put("names", channelList);
    map.put("opportunityReports", targetReportOpportunity);
    map.put("orderReports", targetReportOrder);

    return map;
  }


}
