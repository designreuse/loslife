package com.asgab.service.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.entity.Service;
import com.asgab.entity.ServiceAttrMap;
import com.asgab.repository.ServiceMapper;
import com.asgab.util.CommonUtil;

@Component
@Transactional
public class ServiceService {

	@Autowired
	private ServiceMapper serviceMapper;

	public List<Service> getServiceByEnterpriseId(String enterpriseId) {
		List<Service> services = serviceMapper.getServiceByEnterpriseId(enterpriseId);
		if (services != null && services.size() > 0) {
			List<String> serviceIds = new ArrayList<String>();
			for (Service service : services) {
				serviceIds.add(service.getId());
			}
			CommonUtil.distinctList(serviceIds);
			List<ServiceAttrMap> serviceAttrMaps = getServiceAttrMapsByServiceIds(serviceIds);
			// 如果存在服务， 则设置服务名字serviceName
			setServiceName(serviceAttrMaps,services);
			if (serviceAttrMaps != null) {
				for (Service service : services) {
					// 设置从表数据
					setServiceAttrMaps(service, serviceAttrMaps);
				}
			}
		}
		return services;
	}

	private void setServiceName(List<ServiceAttrMap> serviceAttrMaps, List<Service> services) {
		for(ServiceAttrMap map:serviceAttrMaps){
			// 如果是服务
			if(StringUtils.isNoneBlank(map.getKeyName())&& map.getKeyName().equalsIgnoreCase(ServiceAttrMap.service)){
				for(Service service:services){
					if(map.getValue().equalsIgnoreCase(service.getId())){
						map.setServiceName(service.getName());
					}
				}
			}
		}
		
	}

	// 設置從表數據
	private void setServiceAttrMaps(Service service, List<ServiceAttrMap> serviceAttrMaps) {
		for(ServiceAttrMap map: serviceAttrMaps){
			if(map.getService_id().equalsIgnoreCase(service.getId())){
				service.getServiceAttrMaps().add(map);
			}
		}
	}

	public List<ServiceAttrMap> getServiceAttrMapsByServiceIds(List<String> serviceIds) {
		if (serviceIds != null && serviceIds.size() > 0) {
			return serviceMapper.getServiceAttrMapsByServiceIds(serviceIds);
		}
		return new ArrayList<ServiceAttrMap>();
	}
}
