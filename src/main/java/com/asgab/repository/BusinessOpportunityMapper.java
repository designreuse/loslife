package com.asgab.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.asgab.entity.BusinessOpportunity;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface BusinessOpportunityMapper {

  BusinessOpportunity get(Long id);

  List<BusinessOpportunity> search(Map<String, Object> parameters);

  List<BusinessOpportunity> search(Map<String, Object> parameters, RowBounds rowBounds);

  int save(BusinessOpportunity businessOpportunity);

  int update(BusinessOpportunity businessOpportunity);

  int count(Map<String, Object> map);

  int delete(BusinessOpportunity businessOpportunity);

  List<BusinessOpportunity> getListByCondition(Map<String, Object> parameters);

}
