package com.asgab.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BusinessOpportunity {
  private Long id;
  private Long advertiser_id;
  private BigDecimal budget;
  private Long currency_id;
  private String deliver_start_date;
  private String deliver_end_date;
  private Long owner_sale;
  private String cooperate_sales;
  private Integer exist_msa;
  private Integer exist_service;
  private Integer status;
  private Integer progress;
  private String remark;

  private List<BusinessOpportunityProduct> businessOpportunityProducts = new ArrayList<BusinessOpportunityProduct>();

  private String deliver_date;

  private ProgressBar progressBar;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getAdvertiser_id() {
    return advertiser_id;
  }

  public void setAdvertiser_id(Long advertiser_id) {
    this.advertiser_id = advertiser_id;
  }

  public BigDecimal getBudget() {
    return budget;
  }

  public void setBudget(BigDecimal budget) {
    this.budget = budget;
  }

  public Long getCurrency_id() {
    return currency_id;
  }

  public void setCurrency_id(Long currency_id) {
    this.currency_id = currency_id;
  }

  public String getDeliver_start_date() {
    return deliver_start_date;
  }

  public void setDeliver_start_date(String deliver_start_date) {
    this.deliver_start_date = deliver_start_date;
  }

  public String getDeliver_end_date() {
    return deliver_end_date;
  }

  public void setDeliver_end_date(String deliver_end_date) {
    this.deliver_end_date = deliver_end_date;
  }

  public Long getOwner_sale() {
    return owner_sale;
  }

  public void setOwner_sale(Long owner_sale) {
    this.owner_sale = owner_sale;
  }

  public String getCooperate_sales() {
    return cooperate_sales;
  }

  public void setCooperate_sales(String cooperate_sales) {
    this.cooperate_sales = cooperate_sales;
  }

  public Integer getExist_msa() {
    return exist_msa;
  }

  public void setExist_msa(Integer exist_msa) {
    this.exist_msa = exist_msa;
  }

  public Integer getExist_service() {
    return exist_service;
  }

  public void setExist_service(Integer exist_service) {
    this.exist_service = exist_service;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getProgress() {
    return progress;
  }

  public void setProgress(Integer progress) {
    this.progress = progress;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public List<BusinessOpportunityProduct> getBusinessOpportunityProducts() {
    return businessOpportunityProducts;
  }

  public void setBusinessOpportunityProducts(List<BusinessOpportunityProduct> businessOpportunityProducts) {
    this.businessOpportunityProducts = businessOpportunityProducts;
  }

  public String getDeliver_date() {
    return deliver_date;
  }

  public void setDeliver_date(String deliver_date) {
    this.deliver_date = deliver_date;
  }

  public ProgressBar getProgressBar() {
    return progressBar;
  }

  public void setProgressBar(ProgressBar progressBar) {
    this.progressBar = progressBar;
  }

}

