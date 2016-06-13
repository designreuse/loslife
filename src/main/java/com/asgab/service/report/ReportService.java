package com.asgab.service.report;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.entity.Report;
import com.asgab.repository.ReportMapper;

@Component
@Transactional
public class ReportService {

  @Autowired
  ReportMapper reportMapper;

  public List<Report> getReportByProduct(Map<String, Object> parameters) {
    return reportMapper.getReportByProduct(parameters);
  }

  public List<Report> getReportBySaleTeam(Map<String, Object> parameters) {
    return reportMapper.getReportBySaleTeam(parameters);
  }

  public List<Report> getReportBySaleRepresentative(Map<String, Object> parameters) {
    return reportMapper.getReportBySaleRepresentative(parameters);
  }

  public List<Report> getReportByChannel(Map<String, Object> parameters) {
    return reportMapper.getReportByChannel(parameters);
  }
}
