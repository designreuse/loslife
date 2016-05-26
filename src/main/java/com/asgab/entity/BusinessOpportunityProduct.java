package com.asgab.entity;

import java.math.BigDecimal;

public class BusinessOpportunityProduct {
  private Long id;
  private Long business_opportunity_id;
  private Long product_id;
  private String sale_mode;
  private BigDecimal budget;
  
  private Integer deleted;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getBusiness_opportunity_id() {
    return business_opportunity_id;
  }

  public void setBusiness_opportunity_id(Long business_opportunity_id) {
    this.business_opportunity_id = business_opportunity_id;
  }

  public Long getProduct_id() {
    return product_id;
  }

  public void setProduct_id(Long product_id) {
    this.product_id = product_id;
  }

  public String getSale_mode() {
    return sale_mode;
  }

  public void setSale_mode(String sale_mode) {
    this.sale_mode = sale_mode;
  }

  public BigDecimal getBudget() {
    return budget;
  }

  public void setBudget(BigDecimal budget) {
    this.budget = budget;
  }

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }
  
}
