package com.asgab.service.advertiser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.core.pagination.Page;
import com.asgab.entity.xmo.Advertiser;
import com.asgab.repository.xmo.AdvertiserMapper;

@Component
@Transactional
public class AdvertiserService {

  @Autowired
  private AdvertiserMapper advertiserMapper;

  public Page<Advertiser> search(Page<Advertiser> page) {
    List<Advertiser> advertisers = advertiserMapper.search(page.getSearchMap(), page.getRowBounds());
    int count = advertiserMapper.count(page.getSearchMap());
    page.setTotal(count);
    page.setContent(advertisers);
    return page;
  }

  public Advertiser get(Long id) {
    return advertiserMapper.get(id);
  }

  public List<Advertiser> getAdvertisersByIdList(List<Long> idList) {
    return advertiserMapper.getAdvertisersByIdList(idList);
  }

}
