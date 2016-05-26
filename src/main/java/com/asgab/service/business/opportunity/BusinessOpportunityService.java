package com.asgab.service.business.opportunity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.core.pagination.Page;
import com.asgab.entity.BusinessOpportunity;
import com.asgab.entity.BusinessOpportunityProduct;
import com.asgab.entity.ProgressBar;
import com.asgab.entity.xmo.Currency;
import com.asgab.repository.BusinessOpportunityMapper;
import com.asgab.repository.BusinessOpportunityProductMapper;
import com.asgab.repository.xmo.UserXMOMapper;
import com.asgab.util.SelectMapper;

@Component
@Transactional
public class BusinessOpportunityService {

  @Autowired
  private BusinessOpportunityMapper businessOpportunityMapper;

  @Autowired
  private BusinessOpportunityProductMapper businessOpportunityProductMapper;

  @Autowired
  private UserXMOMapper userXMOMapper;

  private static Map<Integer, Integer> statusMap = new HashMap<Integer, Integer>();

  static {
    statusMap.put(0, 1);
    statusMap.put(30, 2);
    statusMap.put(50, 3);
    statusMap.put(70, 4);
    statusMap.put(90, 5);
    statusMap.put(100, 6);
  }

  public Page<BusinessOpportunity> search(Page<BusinessOpportunity> page) {
    List<BusinessOpportunity> businessOpportunitys = businessOpportunityMapper.search(page.getSearchMap(), page.getRowBounds());
    for (int i = 0; i < businessOpportunitys.size(); i++) {
      businessOpportunitys.get(i).setProgressBar(new ProgressBar(businessOpportunitys.get(i).getProgress()));
    }
    int count = businessOpportunityMapper.count(page.getSearchMap());
    page.setContent(businessOpportunitys);
    page.setTotal(count);
    return page;
  }

  public BusinessOpportunity get(Long id) {
    BusinessOpportunity businessOpportunity = businessOpportunityMapper.get(id);
    if (businessOpportunity != null) {
      businessOpportunity.setBusinessOpportunityProducts(businessOpportunityProductMapper.getByBusinessOpportunityId(id));
    }
    return businessOpportunity;
  }

  public void save(BusinessOpportunity businessOpportunity) {
    businessOpportunity.setStatus(statusMap.get(businessOpportunity.getProgress()));
    businessOpportunityMapper.save(businessOpportunity);
    for (BusinessOpportunityProduct businessOpportunityProduct : businessOpportunity.getBusinessOpportunityProducts()) {
      businessOpportunityProduct.setBusiness_opportunity_id(businessOpportunity.getId());
      businessOpportunityProductMapper.save(businessOpportunityProduct);
    }
  }

  public void update(BusinessOpportunity businessOpportunity) {
    businessOpportunityMapper.update(businessOpportunity);
    for (BusinessOpportunityProduct businessOpportunityProduct : businessOpportunity.getBusinessOpportunityProducts()) {
      businessOpportunityProductMapper.update(businessOpportunityProduct);
    }
  }

  public List<SelectMapper> getCurrencies() {
    List<Currency> currencys = userXMOMapper.getCurrencies();
    List<SelectMapper> mappers = new ArrayList<SelectMapper>();
    for (int i = 0; i < currencys.size(); i++) {
      mappers.add(new SelectMapper(currencys.get(i).getId() + "", currencys.get(i).getName()));
    }
    return mappers;
  }

}
