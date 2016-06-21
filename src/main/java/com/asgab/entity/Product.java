package com.asgab.entity;

public class Product {
  private Long id;
  private String name;
  private Long product_category_id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getProduct_category_id() {
    return product_category_id;
  }

  public void setProduct_category_id(Long product_category_id) {
    this.product_category_id = product_category_id;
  }



}
