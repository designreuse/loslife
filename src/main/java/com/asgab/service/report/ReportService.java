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
    boolean calGP = "2".equalsIgnoreCase(report.getMetric());
    opportunityReports = mergeReport(opportunityReports, calGP);
    orderReports = mergeReport(orderReports, calGP);
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
    boolean calGP = "2".equalsIgnoreCase(report.getMetric());
    opportunityReports = mergeReport(opportunityReports, calGP);
    orderReports = mergeReport(orderReports, calGP);
    sumReport(opportunityReports, exchangeRates, "channel");
    sumReport(orderReports, exchangeRates, "channel");
    return formatChannelReport(orderReports, opportunityReports);
  }

  private void exchangeRates(List<Report> reports, List<ExchangeRate> exchangeRates) {
    for (Report o : reports) {
      for (ExchangeRate e : exchangeRates) {
        // 如果找到汇率就转换
        if (o.getCurrency().equalsIgnoreCase(e.getBase_currency()) && "RMB".equalsIgnoreCase(e.getCurrency())) {
          o.setBudget(new BigDecimal(e.getRate()).multiply(o.getBudget()));
          break;
        }
      }
    }
  }

  // 同一个订单/商机 合并
  private List<Report> mergeReport(List<Report> reports, boolean calGP) {
    List<Report> targetReport = new ArrayList<Report>();
    // 首先按照order_id , opportunity_id分组. 同类合并. 以订单/商机为单位
    List<String> orderIds = new ArrayList<String>();
    List<String> opportunityIds = new ArrayList<String>();
    for (Report report : reports) {
      if (report.getOrder_id() != null) {
        orderIds.add(String.valueOf(report.getOrder_id()));
      } else if (report.getOpportunity_id() != null) {
        opportunityIds.add(String.valueOf(report.getOpportunity_id()));
      }
    }
    CommonUtil.distinctList(orderIds);
    CommonUtil.distinctList(opportunityIds);

    for (String orderId : orderIds) {
      Report report = null;
      for (int i = 0; i < reports.size(); i++) {
        if (orderId.equals(String.valueOf(reports.get(i).getOrder_id()))) {
          // 计算GP
          if (calGP) {
            calGP(reports.get(i));
          }
          // 订单合并
          if (report == null) {
            report = reports.get(i);
          } else {
            report.setBudget(report.getBudget().add(reports.get(i).getBudget()));
          }
        }
      }
      if (report != null) {
        // 计算GP 后面一部分 减去
        if (calGP) {
          calGP2(report);
        }
        targetReport.add(report);
      }
    }

    for (String opportunityId : opportunityIds) {
      Report report = null;
      for (int i = 0; i < reports.size(); i++) {
        if (opportunityId.equals(String.valueOf(reports.get(i).getOpportunity_id()))) {
          // 计算GP
          if (calGP) {
            calGP(reports.get(i));
          }
          // 订单合并
          if (report == null) {
            report = reports.get(i);
          } else {
            report.setBudget(report.getBudget().add(reports.get(i).getBudget()));
          }
        }
      }
      // 计算GP 后面一部分 减去
      if (report != null) {
        if (calGP) {
          calGP2(report);
        }
        targetReport.add(report);
      }
    }
    return targetReport;
  }

  // 汇率转换 & 产品合并
  private void sumReport(List<Report> reports, List<ExchangeRate> exchangeRates, String type) {
    // 汇率转换
    exchangeRates(reports, exchangeRates);
    // 合并
    for (int i = reports.size() - 1; i >= 0; i--) {
      Report ri = reports.get(i);
      for (int j = i - 1; j >= 0; j--) {
        Report rj = reports.get(j);
        if ("product".equalsIgnoreCase(type)) {
          if (ri.getProduct_id().equals(rj.getProduct_id())) {
            rj.setBudget(rj.getBudget().add(ri.getBudget()));
            // 删掉ri
            reports.remove(ri);
            break;
          }
        } else if ("channel".equalsIgnoreCase(type)) {
          if (ri.getChannel().equals(rj.getChannel())) {
            rj.setBudget(rj.getBudget().add(ri.getBudget()));
            // 删掉ri
            reports.remove(ri);
            break;
          }
        }
      }
    }

  }

  private void calGP(Report report) {
    String[] locals = {"CN", "HK"};
    if (report.getWhether_service().equals(1)) {
      // 服务类 服务类在calGP2 计算
      report.setCalGP(1);
    } else {
      // 非服务类
      boolean isCN = false;
      if (StringUtils.isBlank(report.getLocal())) {
        isCN = true;
      } else {
        for (String local : locals) {
          if (report.getLocal().toLowerCase().contains(local.toLowerCase())) {
            isCN = true;
            break;
          }
        }
      }
      // 如果是国内
      if (isCN) {
        // 1. 国内单 Total GP＝（产品1 budget×产品1 GP%+产品2 budget×产品2 GP%+…+产品n budget×产品n
        // GP%）－总budget×当前日期下返点%）
        report.setBudget(report.getBudget().multiply(new BigDecimal(report.getGp() / 100)));
        // 非服务类 国内
        report.setCalGP(2);
      } else {
        // 2. 海外单 Total GP＝（产品1售价-产品1底价）x （产品1预算÷产品1售价）+（产品2售价-产品2底价）x （产品2预算÷产品2售价）+…+
        // （产品n售价-产品n底价）x （产品n预算÷产品n售价）－总budget×当前日期下返点%
        // TODO opportunity没有
        // 非服务类 国外 售价=cost 底价public_price*floor_discount 预算=budget
        if (report.getOrder_id() != null) {
          BigDecimal val1 = report.getCost().subtract(report.getPublic_price().multiply(report.getFloor_discount()));
          BigDecimal val2 = report.getBudget().divide(report.getCost());
          report.setBudget(val1.multiply(val2));
        }
        report.setCalGP(3);
      }
    }
  }

  // 1:服务类
  // 2:非服务类 国内
  // 3:非服务类 国外
  private void calGP2(Report report) {
    if (report.getCalGP() == 1) {
      // b. 当订单有总体预算，又提供服务费率百分比时，收入以总体预算表示，GP以（总体预算*服务费率）表示
      report.setBudget(report.getTotalBudget().multiply(new BigDecimal(report.getService_charges_scale())));
    } else if (report.getCalGP() == 2) {
      // -总budget×当前日期下返点%
      report.setBudget(report.getBudget().subtract(report.getTotalBudget().multiply(new BigDecimal(report.getRebate()))));
    } else if (report.getCalGP() == 3) {
      // －总budget×当前日期下返点%
      report.setBudget(report.getBudget().subtract(report.getTotalBudget().multiply(new BigDecimal(report.getRebate()))));
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

  // 是否是国内
  public boolean isInternal(String regional) {
    String[] internalArray = {"SPECIAL", "OTHER", "NATION"};
    for (String internal : internalArray) {
      if (internal.equalsIgnoreCase(regional)) {
        return true;
      }
    }
    return false;
  }


}
