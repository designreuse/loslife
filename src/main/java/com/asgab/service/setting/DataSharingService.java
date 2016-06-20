package com.asgab.service.setting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.entity.DataSharing;
import com.asgab.repository.DataSharingMapper;

@Component
@Transactional
public class DataSharingService {

  @Autowired
  private DataSharingMapper dataSharingMapper;

  public void save(DataSharing dataSharing) {
    dataSharingMapper.save(dataSharing);
  }

  public void update(DataSharing dataSharing) {
    dataSharingMapper.update(dataSharing);
  }

  public DataSharing get(Long id) {
    return dataSharingMapper.get(id);
  }

  public DataSharing getByUserId(Long user_id) {
    List<DataSharing> list = dataSharingMapper.getByUserId(user_id);
    if (list == null || list.size() == 0)
      return null;
    return list.get(0);
  }

}
