package com.asgab.entity;

import java.util.Date;

public class DataSharing {

  private Long id;
  private Long user_id;
  private Long parent_id;
  private int type;
  private Date created_at;
  private Long created_by;

  public DataSharing() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public Long getParent_id() {
    return parent_id;
  }

  public void setParent_id(Long parent_id) {
    this.parent_id = parent_id;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public Long getCreated_by() {
    return created_by;
  }

  public void setCreated_by(Long created_by) {
    this.created_by = created_by;
  }

}
