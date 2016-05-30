package com.asgab.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.core.pagination.Page;
import com.asgab.entity.xmo.Product;
import com.asgab.repository.xmo.ProductXMOMapper;

@Component
@Transactional
public class ProductService {

  @Autowired
  private ProductXMOMapper productXMOMapper;

  public Page<Product> search(Page<Product> page) {
    List<Product> products = productXMOMapper.search(page.getSearchMap(), page.getRowBounds());
    page.setContent(products);
    return page;
  }
}
