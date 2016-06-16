package com.asgab.repository;

import java.util.List;
import java.util.Map;

import com.asgab.entity.Report;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface ReportMapper {
  // product
  List<Report> getOpportunitysByProduct(Map<String, Object> parameters);
  
  List<Report> getOrdersByProduct(Map<String, Object> parameters);

  List<Report> getReportBySaleTeam(Map<String, Object> parameters);

  List<Report> getReportBySaleRepresentative(Map<String, Object> parameters);
  
  // channel
  List<Report> getOpportunitysByChannel(Map<String, Object> parameters);
  List<Report> getOrdersByChannel(Map<String, Object> parameters);

}
