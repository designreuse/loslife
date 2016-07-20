package com.asgab.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class MemberCardCategory {
	private String id;
	// 会员卡名称
	private String name;
	// 开卡金额（元）
	private BigDecimal baseInfo_minMoney;
	// 充值卡 计次卡 年卡/季卡
	private String baseInfo_type;
	private Double discounts_goodsDiscount;
	private Double discounts_serviceDiscount;
	// 有效期（天）
	private Integer cardValid;
	private String cardNoGenRule_cardNoPrefix;
	private BigDecimal activeCardPresentMoney;
	private Double activeCardPresentScore;
	private Double rechargePresentScore;
	private Double consumePresentScore;
	private Double inverse_ratio;
	
	private static Map<String,String> baseInfo_types = new HashMap<String,String>();
	static{
		baseInfo_types.put("recharge", "充值卡");
		baseInfo_types.put("recordTimeCard", "计次卡");
		baseInfo_types.put("quarter", "年卡/季卡");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getBaseInfo_minMoney() {
		return baseInfo_minMoney;
	}

	public void setBaseInfo_minMoney(BigDecimal baseInfo_minMoney) {
		this.baseInfo_minMoney = baseInfo_minMoney;
	}

	public String getBaseInfo_type() {
		return baseInfo_type;
	}

	public void setBaseInfo_type(String baseInfo_type) {
		this.baseInfo_type = baseInfo_type;
	}

	public Double getDiscounts_goodsDiscount() {
		return discounts_goodsDiscount;
	}

	public void setDiscounts_goodsDiscount(Double discounts_goodsDiscount) {
		this.discounts_goodsDiscount = discounts_goodsDiscount;
	}

	public Double getDiscounts_serviceDiscount() {
		return discounts_serviceDiscount;
	}

	public void setDiscounts_serviceDiscount(Double discounts_serviceDiscount) {
		this.discounts_serviceDiscount = discounts_serviceDiscount;
	}

	public Integer getCardValid() {
		return cardValid;
	}

	public void setCardValid(Integer cardValid) {
		this.cardValid = cardValid;
	}

	public String getCardNoGenRule_cardNoPrefix() {
		return StringUtils.isBlank(cardNoGenRule_cardNoPrefix)?"":cardNoGenRule_cardNoPrefix;
	}

	public void setCardNoGenRule_cardNoPrefix(String cardNoGenRule_cardNoPrefix) {
		this.cardNoGenRule_cardNoPrefix = cardNoGenRule_cardNoPrefix;
	}

	public BigDecimal getActiveCardPresentMoney() {
		return activeCardPresentMoney;
	}

	public void setActiveCardPresentMoney(BigDecimal activeCardPresentMoney) {
		this.activeCardPresentMoney = activeCardPresentMoney;
	}

	public Double getActiveCardPresentScore() {
		return activeCardPresentScore;
	}

	public void setActiveCardPresentScore(Double activeCardPresentScore) {
		this.activeCardPresentScore = activeCardPresentScore;
	}

	public Double getRechargePresentScore() {
		return rechargePresentScore;
	}

	public void setRechargePresentScore(Double rechargePresentScore) {
		this.rechargePresentScore = rechargePresentScore;
	}

	public Double getConsumePresentScore() {
		return consumePresentScore;
	}

	public void setConsumePresentScore(Double consumePresentScore) {
		this.consumePresentScore = consumePresentScore;
	}

	public Double getInverse_ratio() {
		return inverse_ratio;
	}

	public void setInverse_ratio(Double inverse_ratio) {
		this.inverse_ratio = inverse_ratio;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDecodeBaseInfo_type(){
		if(StringUtils.isBlank(baseInfo_type)){
			return "";
		}
		if(baseInfo_types.containsKey(baseInfo_type)){
			return baseInfo_types.get(baseInfo_type);
		}
		return "";
	}
	
	public Double getDiscount(){
		if(discounts_goodsDiscount==discounts_serviceDiscount){
			return discounts_goodsDiscount;
		}
		//TODO
		return discounts_goodsDiscount;
	}

}
