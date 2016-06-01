package com.asgab.service.business.opportunity;

import java.util.ArrayList;
import java.util.Date;
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
import com.asgab.service.product.ProductService;
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

  @Autowired
  private ProductService productService;

  public static Map<Integer, Integer> statusMap = new HashMap<Integer, Integer>();
  public static Map<Integer, String> statusZH = new HashMap<Integer, String>();
  public static Map<Integer, String> statusEN = new HashMap<Integer, String>();

  static {
    statusMap.put(0, 1);
    statusMap.put(10, 2);
    statusMap.put(30, 3);
    statusMap.put(50, 4);
    statusMap.put(70, 5);
    statusMap.put(90, 6);
    statusMap.put(100, 7);

    statusZH.put(1, "商机丢失");
    statusZH.put(2, "销售创建brief");
    statusZH.put(3, "转为订单");
    statusZH.put(4, "商机下任一订单审批通过");
    statusZH.put(5, "排期已分享给客户");
    statusZH.put(6, "客户已确认排期");
    statusZH.put(7, "商机下任一订单合同确认审批通过");

    statusEN.put(1, "sales opportunity lost");
    statusEN.put(2, "sales create a sales opportunity");
    statusEN.put(3, "sales opportunity kicks start internal process - booking");
    statusEN.put(4, "booking order approved");
    statusEN.put(5, "media plan shared to client");
    statusEN.put(6, "client confirmed media plan");
    statusEN.put(6, "IO signed");
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
      for (int i = 0; i < businessOpportunity.getBusinessOpportunityProducts().size(); i++) {
        BusinessOpportunityProduct businessOpportunityProduct = businessOpportunity.getBusinessOpportunityProducts().get(i);
        businessOpportunityProduct.setProduct(productService.get(businessOpportunityProduct.getProduct_id()));
      }
    }
    return businessOpportunity;
  }

  public void save(BusinessOpportunity businessOpportunity) {
    businessOpportunity.setStatus(statusMap.get(businessOpportunity.getProgress()));
    businessOpportunity.setCreated_at(new Date());
    businessOpportunityMapper.save(businessOpportunity);
    for (BusinessOpportunityProduct businessOpportunityProduct : businessOpportunity.getBusinessOpportunityProducts()) {
      if (businessOpportunityProduct != null && businessOpportunityProduct.getProduct_id() != null) {
        businessOpportunityProduct.setBusiness_opportunity_id(businessOpportunity.getId());
        businessOpportunityProduct.setCreated_at(new Date());
        businessOpportunityProductMapper.save(businessOpportunityProduct);
      }
    }
  }

  public void update(BusinessOpportunity businessOpportunity) {
    businessOpportunity.setUpdated_at(new Date());
    businessOpportunityMapper.update(businessOpportunity);
    for (BusinessOpportunityProduct businessOpportunityProduct : businessOpportunity.getBusinessOpportunityProducts()) {
      if (businessOpportunityProduct.getId() != null) {
        businessOpportunityProduct.setUpdated_at(new Date());
        businessOpportunityProductMapper.update(businessOpportunityProduct);
      } else {
        businessOpportunityProduct.setBusiness_opportunity_id(businessOpportunity.getId());
        businessOpportunityProduct.setCreated_at(new Date());
        businessOpportunityProductMapper.save(businessOpportunityProduct);
      }
    }
  }

  public List<Currency> getCurrencys() {
    return userXMOMapper.getCurrencys();
  }

  public List<SelectMapper> getCurrencyMappers() {
    List<Currency> currencys = userXMOMapper.getCurrencys();
    List<SelectMapper> mappers = new ArrayList<SelectMapper>();
    for (int i = 0; i < currencys.size(); i++) {
      mappers.add(new SelectMapper(currencys.get(i).getId() + "", currencys.get(i).getName()));
    }
    return mappers;
  }

  public int delete(Long id) {
    List<BusinessOpportunityProduct> products = businessOpportunityProductMapper.getByBusinessOpportunityId(id);
    for (BusinessOpportunityProduct p : products) {
      p.setDeleted_at(new Date());
      businessOpportunityProductMapper.delete(p);
    }
    BusinessOpportunity delOpportunity = new BusinessOpportunity();
    delOpportunity.setId(id);
    delOpportunity.setDeleted_at(new Date());
    return businessOpportunityMapper.delete(delOpportunity);
  }

}
