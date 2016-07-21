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

@Component
@Transactional
public class ServiceService {

  @Autowired
  private ServiceMapper serviceMapper;

  public List<Service> getServiceByEnterpriseId(String enterpriseId) {
    List<Service> services = serviceMapper.getServiceByEnterpriseId(enterpriseId);
    if (services != null && services.size() > 0) {
      List<ServiceAttrMap> serviceAttrMaps = getServiceAttrMapsByEnterpriseId(enterpriseId);
      // 如果有服务
      setServiceName(serviceAttrMaps, services);
      if (serviceAttrMaps != null) {
        for (Service service : services) {
          setServiceAttrMaps(service, serviceAttrMaps);
        }
      }
    }
    return services;
  }

  private void setServiceName(List<ServiceAttrMap> serviceAttrMaps, List<Service> services) {
    for (ServiceAttrMap map : serviceAttrMaps) {
      // ����Ƿ���
      if (StringUtils.isNoneBlank(map.getKeyName()) && map.getKeyName().equalsIgnoreCase(ServiceAttrMap.service)) {
        for (Service service : services) {
          if (map.getValue().equalsIgnoreCase(service.getId())) {
            map.setServiceName(service.getName());
          }
        }
      }
    }

  }

  private void setServiceAttrMaps(Service service, List<ServiceAttrMap> serviceAttrMaps) {
    for (ServiceAttrMap map : serviceAttrMaps) {
      if (map.getService_id().equalsIgnoreCase(service.getId())) {
        service.getServiceAttrMaps().add(map);
      }
    }
  }

  public List<ServiceAttrMap> getServiceAttrMapsByEnterpriseId(String enterpriseId) {
    if (enterpriseId != null) {
      return serviceMapper.getServiceAttrMapsByServiceIds(enterpriseId);
    }
    return new ArrayList<ServiceAttrMap>();
  }
}
