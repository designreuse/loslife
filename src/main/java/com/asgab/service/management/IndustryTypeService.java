package com.asgab.service.management;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.entity.IndustryType;
import com.asgab.repository.IndustryTypeMapper;

@Component
@Transactional
public class IndustryTypeService {

  @Autowired
  private IndustryTypeMapper industryTypeMapper;

  public List<IndustryType> getList(Map<String, Object> searchMap) {
    return industryTypeMapper.search(searchMap);
  }

}
