package com.asgab.repository.xmo;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.asgab.entity.xmo.Advertiser;
import com.asgab.repository.mybatis.MyBatisRepositoryXMO;

@MyBatisRepositoryXMO
public interface AdvertiserXMOMapper {


  List<Advertiser> search(Map<String, Object> parameters, RowBounds rowBounds);

}
