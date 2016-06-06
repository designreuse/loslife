package com.asgab.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.core.pagination.Page;
import com.asgab.entity.Product;
import com.asgab.repository.ProductMapper;

@Component
@Transactional
public class ProductService {

  @Autowired
  private ProductMapper productXMOMapper;

  public Page<Product> search(Page<Product> page) {
    List<Product> products = productXMOMapper.search(page.getSearchMap(), page.getRowBounds());
    page.setContent(products);
    return page;
  }

  public Product get(Long id) {
    return productXMOMapper.get(id);
  }
}