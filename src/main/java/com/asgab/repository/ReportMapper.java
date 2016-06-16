package com.asgab.repository;

import java.util.List;
import java.util.Map;

import com.asgab.entity.Report;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface ReportMapper {

  List<Report> getOpportunitysByProduct(Map<String, Object> parameters);
  
  List<Report> getOrdersByProduct(Map<String, Object> parameters);

  List<Report> getReportBySaleTeam(Map<String, Object> parameters);

  List<Report> getReportBySaleRepresentative(Map<String, Object> parameters);

  List<Report> getReportByChannel(Map<String, Object> parameters);

}
