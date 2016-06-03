package com.asgab.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.asgab.entity.xmo.Advertiser;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface AdvertiserMapper {


  List<Advertiser> search(Map<String, Object> parameters, RowBounds rowBounds);

  Advertiser get(Long id);
  
  List<Advertiser> getAdvertisersByIdList(List<Long> idList);

}
