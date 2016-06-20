package com.asgab.repository;

import java.util.List;
import java.util.Map;

import com.asgab.entity.Report;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface ReportMapper {
  // product
  List<Report> getOpportunitysByProduct(Report report);
  
  List<Report> getOrdersByProduct(Report report);

  List<Report> getReportBySaleTeam(Map<String, Object> parameters);

  List<Report> getReportBySaleRepresentative(Map<String, Object> parameters);
  
  // channel
  List<Report> getOpportunitysByChannel(Report report);
  List<Report> getOrdersByChannel(Report report);

}
