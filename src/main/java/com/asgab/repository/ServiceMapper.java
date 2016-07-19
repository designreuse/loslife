package com.asgab.repository;

import java.util.List;

import com.asgab.entity.Service;
import com.asgab.entity.ServiceAttrMap;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface ServiceMapper {

	List<Service> getServiceByEnterpriseId(String enterpriseId);

	List<ServiceAttrMap> getServiceAttrMapsByServiceIds(List<String> serviceIds);

}
