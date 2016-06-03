package com.asgab.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.asgab.entity.Review;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface ReviewMapper {
  Review get(Long id);

  List<Review> search(Map<String, Object> parameters);

  List<Review> search(Map<String, Object> parameters, RowBounds rowBounds);

  int save(Review review);

  int update(Review review);

  int count(Map<String, Object> map);

}
