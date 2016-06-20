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

  private String reportDate_start;
  private String reportDate_end;
  private String progress_start;
  private String progress_end;


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

  public String getFmtBudgetSum() {
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

}
