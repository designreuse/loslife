package com.asgab.repository;

import java.util.List;

import com.asgab.entity.BusinessOpportunityProduct;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface BusinessOpportunityProductMapper {

  BusinessOpportunityProduct get(Long id);

  List<BusinessOpportunityProduct> getByBusinessOpportunityId(Long businessOpportunityId);

  void save(BusinessOpportunityProduct businessOpportunityProduct);

  void update(BusinessOpportunityProduct businessOpportunityProduct);


}
