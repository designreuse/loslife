package com.asgab.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Service {
	private String id;
	private String name;
	private Integer baseInfo_code;
	private BigDecimal prices_salesPrice;
	private String serviceCategory_id;
	private Long create_date;
	private String enterprise_id;
	private Integer sort_no;
	private String comment;

	private String cateName;
	private Integer type;

	private List<ServiceAttrMap> serviceAttrMaps = new ArrayList<ServiceAttrMap>();

	private static Map<Integer, String> types = new HashMap<Integer, String>();

	static {
		types.put(1, "服务");
		// 如果是卖品， 则可以包含服务。value是服务ID
		types.put(2, "卖品");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBaseInfo_code() {
		return baseInfo_code;
	}

	public void setBaseInfo_code(Integer baseInfo_code) {
		this.baseInfo_code = baseInfo_code;
	}

	public BigDecimal getPrices_salesPrice() {
		return prices_salesPrice;
	}

	public void setPrices_salesPrice(BigDecimal prices_salesPrice) {
		this.prices_salesPrice = prices_salesPrice;
	}

	public String getServiceCategory_id() {
		return serviceCategory_id;
	}

	public void setServiceCategory_id(String serviceCategory_id) {
		this.serviceCategory_id = serviceCategory_id;
	}

	public Long getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Long create_date) {
		this.create_date = create_date;
	}

	public String getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(String enterprise_id) {
		this.enterprise_id = enterprise_id;
	}

	public Integer getSort_no() {
		return sort_no;
	}

	public void setSort_no(Integer sort_no) {
		this.sort_no = sort_no;
	}

	public List<ServiceAttrMap> getServiceAttrMaps() {
		return serviceAttrMaps;
	}

	public void setServiceAttrMaps(List<ServiceAttrMap> serviceAttrMaps) {
		this.serviceAttrMaps = serviceAttrMaps;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public Integer getType() {
		return type;
	}

	public String getDecodedType() {
		if (type != null && types.containsKey(type)) {
			return types.get(type);
		}
		return "";
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<ServiceAttrMap> getServiceAttrMapByKey(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		List<ServiceAttrMap> maps = new ArrayList<ServiceAttrMap>();
		for (ServiceAttrMap map : serviceAttrMaps) {
			if (StringUtils.isNotBlank(map.getKeyName())&& map.getKeyName().equalsIgnoreCase(key)) {
				maps.add(map);
			}
		}
		return maps;
	}
	
	public String getKeyValue(String key) {
		List<ServiceAttrMap> serviceAttrMaps=getServiceAttrMapByKey(key);
		if(serviceAttrMaps == null ||  (serviceAttrMaps !=null && serviceAttrMaps.size()==0)){
			return "";
		}else{
			if(type.equals(1)){
				return serviceAttrMaps.get(0).getValue();
			}else{
				// 如果是卖品， 则可以包含服务
				return getStringByList(serviceAttrMaps);
			}
		}
	}
	
	private String getStringByList(List<ServiceAttrMap> serviceAttrMaps){
		String result = "";
		if(serviceAttrMaps!=null && serviceAttrMaps.size()>0){
			for(int i = 0;i<serviceAttrMaps.size();i++){
				if(i!=0){
					result+=",";
				}
				result+=serviceAttrMaps.get(i).getServiceName();
			}
		}
		return result;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
