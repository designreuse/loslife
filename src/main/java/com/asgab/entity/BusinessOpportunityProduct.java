package com.asgab.entity;

import java.math.BigDecimal;

import com.asgab.entity.xmo.Product;

public class BusinessOpportunityProduct {
  private Long id;
  private Long business_opportunity_id;
  private Long product_id;
  private String sale_mode;
  private BigDecimal product_budget;

  private Integer deleted;

  private Product product;

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

  public BigDecimal getProduct_budget() {
    return product_budget;
  }

  public void setProduct_budget(BigDecimal product_budget) {
    this.product_budget = product_budget;
  }

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public String getDecodeProductId() {
    if (product != null) {
      return product.getName();
    }
    return "";
  }
}
