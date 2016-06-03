package com.asgab.service.review;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.core.pagination.Page;
import com.asgab.entity.Review;
import com.asgab.repository.ReviewMapper;

@Component
@Transactional
public class ReviewService {

  @Autowired
  private ReviewMapper reviewMapper;

  public Review get(Long id) {
    return reviewMapper.get(id);
  }

  public List<Review> search(Map<String, Object> parameters) {
    return reviewMapper.search(parameters);
  }

  public Page<Review> search(Page<Review> page) {
    List<Review> reviews = reviewMapper.search(page.getSearchMap(), page.getRowBounds());
    int count = reviewMapper.count(page.getSearchMap());
    page.setContent(reviews);
    page.setTotal(count);
    return page;
  }

  public int save(Review review) {
    return reviewMapper.save(review);
  }

  public int update(Review review) {
    return reviewMapper.update(review);
  }

}
