package com.asgab.service.exchange;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.core.pagination.Page;
import com.asgab.entity.ExchangeRate;
import com.asgab.repository.ExchangeRateMapper;

// Spring Service Bean的标识.
@Component
@Transactional
public class ExchangeRateService {

  @Resource
  private ExchangeRateMapper exchangeRateMapper;

  public List<ExchangeRate> getAllAvailExchangeRates() {
    return (List<ExchangeRate>) exchangeRateMapper.search(null);
  }

  public Page<ExchangeRate> getAllAvailExchangeRates(Page<ExchangeRate> page) {
    List<ExchangeRate> list = exchangeRateMapper.search(page.getSearchMap(), page.getRowBounds());
    page.setContent(list);
    page.setTotal(getAllAvailExchangeRates().size());
    return page;
  }

  public List<ExchangeRate> search(Map<String, Object> map) {
    return exchangeRateMapper.search(map);
  }

  public ExchangeRate getExchangeRate(Long id) {
    return exchangeRateMapper.get(id);
  }

}
