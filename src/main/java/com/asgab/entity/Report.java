package com.asgab.entity;

import java.util.Date;

public class Report {
  // 数据维度
  private String dataRight;
  private Date reportDate;
  private Integer progress;
  private String saleTeam;
  private String saleRepresentative;
  private String budget;
  private String billType;
  private String gp;
  private String incomeType;
  private String currency;

  public String getDataRight() {
    return dataRight;
  }

  public void setDataRight(String dataRight) {
    this.dataRight = dataRight;
  }

  public Date getReportDate() {
    return reportDate;
  }

  public void setReportDate(Date reportDate) {
    this.reportDate = reportDate;
  }

  public Integer getProgress() {
    return progress;
  }

  public void setProgress(Integer progress) {
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

  public String getBillType() {
    return billType;
  }

  public void setBillType(String billType) {
    this.billType = billType;
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

}
