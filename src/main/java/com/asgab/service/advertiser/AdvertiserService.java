package com.asgab.service.advertiser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.core.pagination.Page;
import com.asgab.entity.xmo.Advertiser;
import com.asgab.repository.xmo.AdvertiserXMOMapper;

@Component
@Transactional
public class AdvertiserService {

  @Autowired
  private AdvertiserXMOMapper advertiserXMOMapper;

  public Page<Advertiser> search(Page<Advertiser> page) {
    List<Advertiser> advertisers = advertiserXMOMapper.search(page.getSearchMap(), page.getRowBounds());
    page.setContent(advertisers);
    return page;
  }
}
