package com.asgab.entity;

import java.math.BigDecimal;

import com.asgab.util.CommonUtil;

public class Report {
  // 数据维度
  private String dataRight;
  private String reportDate;
  private String progress;
  private String saleTeam;
  private String saleRepresentative;
  private String budget;
  private String orderType;
  private String gp;
  private String incomeType;
  private String currency;
  
  private BigDecimal budgetSum;
  private Long product_id;
  private String product_name;
  
  private String channel;
  private String channel_name;

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

  public String getBudget() {
    return budget;
  }

  public void setBudget(String budget) {
    this.budget = budget;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public String getGp() {
    return gp;
  }

  public void setGp(String gp) {
    this.gp = gp;
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

  public BigDecimal getBudgetSum() {
    return budgetSum;
  }
  
  public String getFmtBudgetSum(){
    return CommonUtil.digSeg(getBudgetSum().doubleValue());
  }

  public void setBudgetSum(BigDecimal budgetSum) {
    this.budgetSum = budgetSum;
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

}
