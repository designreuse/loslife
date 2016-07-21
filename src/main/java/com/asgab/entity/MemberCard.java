package com.asgab.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asgab.util.CommonUtil;

public class MemberCard {
  private String id;
  private String memberNo;
  private String cardNo;
  private String memberCardCategoryName;
  private BigDecimal currentMoney;
  private Long create_date;
  private String baseInfo_type;
  private List<MemberCardAttrMap> memberCardAttrMaps = new ArrayList<MemberCardAttrMap>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMemberNo() {
    return memberNo;
  }

  public void setMemberNo(String memberNo) {
    this.memberNo = memberNo;
  }

  public String getCardNo() {
    return cardNo;
  }

  public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
  }

  public String getMemberCardCategoryName() {
    return memberCardCategoryName;
  }

  public void setMemberCardCategoryName(String memberCardCategoryName) {
    this.memberCardCategoryName = memberCardCategoryName;
  }

  public BigDecimal getCurrentMoney() {
    return currentMoney;
  }

  public void setCurrentMoney(BigDecimal currentMoney) {
    this.currentMoney = currentMoney;
  }

  public String getFmtCreate_date() {
    return CommonUtil.formatDate(create_date, "yyyy-MM-dd");
  }

  public Long getCreate_date() {
    return create_date;
  }

  public void setCreate_date(Long create_date) {
    this.create_date = create_date;
  }

  public List<MemberCardAttrMap> getMemberCardAttrMaps() {
    return memberCardAttrMaps;
  }

  public void setMemberCardAttrMaps(List<MemberCardAttrMap> memberCardAttrMaps) {
    this.memberCardAttrMaps = memberCardAttrMaps;
  }

  public String getBaseInfo_type() {
    return baseInfo_type;
  }

  public void setBaseInfo_type(String baseInfo_type) {
    this.baseInfo_type = baseInfo_type;
  }

  public Double getCardValue() {
    if ("recordTimeCard".equalsIgnoreCase(baseInfo_type)) {
      return new Double(getRecordTimesCardValue());
    } else {
      return currentMoney != null ? currentMoney.doubleValue() : 0;
    }
  }

  public int getRecordTimesCardValue() {
    int count = 0;
    for (MemberCardAttrMap map : memberCardAttrMaps) {
      count = count + Integer.parseInt(map.getValue());
    }
    return count;
  }

}
