package com.asgab.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.asgab.entity.xmo.Currency;
import com.asgab.service.business.opportunity.BusinessOpportunityService;
import com.asgab.util.CommonUtil;

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
  private Date deleted_at;
  private Date created_at;
  private Date updated_at;

  private List<BusinessOpportunityProduct> businessOpportunityProducts = new ArrayList<BusinessOpportunityProduct>();

  private String deliver_date;

  private ProgressBar progressBar;

  // for app
  private String advertiser;
  private String owner_sale_name;
  private List<Currency> currencys = new ArrayList<Currency>();
  private List<User> cooperate_sale_list = new ArrayList<User>();

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

  public String getDecodeDeliver_date() {
    if (StringUtils.isNotBlank(getDeliver_start_date()) && StringUtils.isNotBlank(getDeliver_end_date())) {
      return getDeliver_start_date() + " - " + getDeliver_end_date();
    }
    return "";
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

  public String getDecodeExist_msa(String lang) {
    if (getExist_msa().intValue() == 1) {
      return CommonUtil.getProperty(lang, "business.opportunity.yes");
    }
    return CommonUtil.getProperty(lang, "business.opportunity.no");
  }

  public void setExist_msa(Integer exist_msa) {
    this.exist_msa = exist_msa;
  }

  public Integer getExist_service() {
    return exist_service;
  }

  public String getDecodeExist_service(String lang) {
    if (getExist_service().intValue() == 1) {
      return CommonUtil.getProperty(lang, "business.opportunity.service");
    }
    return CommonUtil.getProperty(lang, "business.opportunity.exec");
  }

  public void setExist_service(Integer exist_service) {
    this.exist_service = exist_service;
  }

  public Integer getStatus() {
    return BusinessOpportunityService.statusMap.get(progress);
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

  public String getAdvertiser() {
    return advertiser;
  }

  public void setAdvertiser(String advertiser) {
    this.advertiser = advertiser;
  }

  public Date getDeleted_at() {
    return deleted_at;
  }

  public void setDeleted_at(Date deleted_at) {
    this.deleted_at = deleted_at;
  }

  public List<Currency> getCurrencys() {
    return currencys;
  }

  public void setCurrencys(List<Currency> currencys) {
    this.currencys = currencys;
  }

  public String getDecodeCurrency() {
    for (Currency c : currencys) {
      if (c.getId().longValue() == this.getCurrency_id().longValue()) {
        return c.getName();
      }
    }
    return "";
  }

  public String getDecodeStatus(String lang) {
    Map<Integer, String> statuses;
    if ("zh".equalsIgnoreCase(lang)) {
      statuses = BusinessOpportunityService.statusZH;
    } else {
      statuses = BusinessOpportunityService.statusEN;
    }
    return statuses.get(this.status);
  }

  public String getOwner_sale_name() {
    return owner_sale_name;
  }

  public void setOwner_sale_name(String owner_sale_name) {
    this.owner_sale_name = owner_sale_name;
  }

  public List<User> getCooperate_sale_list() {
    return cooperate_sale_list;
  }

  public void setCooperate_sale_list(List<User> cooperate_sale_list) {
    this.cooperate_sale_list = cooperate_sale_list;
  }

  public String getDecodeCooperate_sale() {
    String result = "";
    for (int i = 0; i < cooperate_sale_list.size(); i++) {
      if (i != 0) {
        result += ",&nbsp;&nbsp;&nbsp;&nbsp;";
      }
      result += cooperate_sale_list.get(i).getName();
    }
    return result;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public Date getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(Date updated_at) {
    this.updated_at = updated_at;
  }

}

