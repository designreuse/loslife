package com.asgab.entity;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.asgab.util.CommonUtil;

public class Report {
  // 数据维度
  private String dataRight;
  private String reportDate;
  private String progress;
  private String saleTeam;
  private String saleRepresentative;
  private BigDecimal budget;
  private BigDecimal budgetRMB;
  private String orderType;
  private Double gp;
  private String incomeType;
  private String currency;
  // 是否服务类
  private Integer whether_service;
  // 地区
  private String regional;
  private BigDecimal totalBudget;
  private Long product_id;
  private String product_name;

  private Long order_id;
  private Long opportunity_id;
  private String local;
  private Double rebate;
  //售价
  private BigDecimal cost;
  // 底价 = public_price*floor_discount 底价 
  private BigDecimal public_price;
  private BigDecimal floor_discount;
  
  // 服务费率
  private Double service_charges_scale;
  private String metric;
  private String channel;
  private String channel_name;

  private String reportDate_start;
  private String reportDate_end;
  private String progress_start;
  private String progress_end;

  // 默认不算GP,
  // 1:服务类
  // 2:非服务类 国内
  // 3:非服务类 国外
  private int calGP = 0;

  public String getDataRight() {
    return dataRight;
  }

  public void setDataRight(String dataRight) {
    this.dataRight = dataRight;
  }

  public String getReportDate() {
    return reportDate;
  }

  public void setReportDate(String reportDate) {
    this.reportDate = reportDate;
  }

  public String getProgress() {
    return progress;
  }

  public void setProgress(String progress) {
    this.progress = progress;
  }

  public String getSaleTeam() {
    return saleTeam;
  }

  public void setSaleTeam(String saleTeam) {
    this.saleTeam = saleTeam;
  }

  public String getSaleRepresentative() {
    return saleRepresentative;
  }

  public void setSaleRepresentative(String saleRepresentative) {
    this.saleRepresentative = saleRepresentative;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public String getIncomeType() {
    return incomeType;
  }

  public void setIncomeType(String incomeType) {
    this.incomeType = incomeType;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getFmtBudget() {
    return CommonUtil.digSeg(getBudget().doubleValue());
  }

  public Long getProduct_id() {
    return product_id;
  }

  public void setProduct_id(Long product_id) {
    this.product_id = product_id;
  }

  public String getProduct_name() {
    return product_name;
  }

  public void setProduct_name(String product_name) {
    this.product_name = product_name;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public String getChannel_name() {
    return channel_name;
  }

  public void setChannel_name(String channel_name) {
    this.channel_name = channel_name;
  }

  public String getReportDate_start() {
    if (StringUtils.isNotBlank(reportDate) && reportDate.length() >= 10) {
      this.reportDate_start = reportDate.substring(0, 10);
    }
    return reportDate_start;
  }

  public void setReportDate_start(String reportDate_start) {
    this.reportDate_start = reportDate_start;
  }

  public String getReportDate_end() {
    if (StringUtils.isNotBlank(reportDate) && reportDate.length() >= 23) {
      this.reportDate_end = reportDate.substring(13, 23);
    }
    return reportDate_end;
  }

  public void setReportDate_end(String reportDate_end) {
    this.reportDate_end = reportDate_end;
  }

  public String getProgress_start() {
    if (StringUtils.isNotBlank(progress)) {
      this.progress_start = progress.split(";")[0];
    }
    return this.progress_start;
  }

  public void setProgress_start(String progress_start) {
    this.progress_start = progress_start;
  }

  public String getProgress_end() {
    if (StringUtils.isNotBlank(progress)) {
      this.progress_end = progress.split(";")[1];
    }
    return this.progress_end;
  }

  public void setProgress_end(String progress_end) {
    this.progress_end = progress_end;
  }

  public Integer getWhether_service() {
    return whether_service;
  }

  public void setWhether_service(Integer whether_service) {
    this.whether_service = whether_service;
  }

  public String getRegional() {
    return regional;
  }

  public void setRegional(String regional) {
    this.regional = regional;
  }

  public BigDecimal getBudget() {
    return budget;
  }

  public void setBudget(BigDecimal budget) {
    this.budget = budget;
  }

  public Double getGp() {
    return gp;
  }

  public void setGp(Double gp) {
    this.gp = gp;
  }

  public BigDecimal getTotalBudget() {
    return totalBudget;
  }

  public void setTotalBudget(BigDecimal totalBudget) {
    this.totalBudget = totalBudget;
  }

  public Long getOrder_id() {
    return order_id;
  }

  public void setOrder_id(Long order_id) {
    this.order_id = order_id;
  }

  public String getLocal() {
    return local;
  }

  public void setLocal(String local) {
    this.local = local;
  }

  public Double getRebate() {
    return rebate;
  }

  public void setRebate(Double rebate) {
    this.rebate = rebate;
  }

  public Double getService_charges_scale() {
    return service_charges_scale;
  }

  public void setService_charges_scale(Double service_charges_scale) {
    this.service_charges_scale = service_charges_scale;
  }

  public BigDecimal getBudgetRMB() {
    return budgetRMB;
  }

  public void setBudgetRMB(BigDecimal budgetRMB) {
    this.budgetRMB = budgetRMB;
  }

  public Long getOpportunity_id() {
    return opportunity_id;
  }

  public void setOpportunity_id(Long opportunity_id) {
    this.opportunity_id = opportunity_id;
  }

  public int getCalGP() {
    return calGP;
  }

  public void setCalGP(int calGP) {
    this.calGP = calGP;
  }

  public String getMetric() {
    return metric;
  }

  public void setMetric(String metric) {
    this.metric = metric;
  }

  public BigDecimal getCost() {
    return cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  public BigDecimal getPublic_price() {
    return public_price;
  }

  public void setPublic_price(BigDecimal public_price) {
    this.public_price = public_price;
  }

  public BigDecimal getFloor_discount() {
    return floor_discount;
  }

  public void setFloor_discount(BigDecimal floor_discount) {
    this.floor_discount = floor_discount;
  }

}
