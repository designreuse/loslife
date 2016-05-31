package com.asgab.service.management;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.entity.CurrencyType;
import com.asgab.repository.CurrencyTypeMapper;

@Component
@Transactional
public class CurrencyTypeService {

  @Autowired
  private CurrencyTypeMapper currencyTypeMapper;

  public List<CurrencyType> getList(Map<String, Object> searchMap) {
    return currencyTypeMapper.search(searchMap);
  }
}
