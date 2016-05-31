package com.asgab.repository.xmo;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.asgab.entity.xmo.Product;
import com.asgab.repository.mybatis.MyBatisRepositoryXMO;

@MyBatisRepositoryXMO
public interface ProductXMOMapper {


  List<Product> search(Map<String, Object> parameters, RowBounds rowBounds);

  Product get(Long id);

}
