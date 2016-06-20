package com.asgab.repository;

import java.util.List;

import com.asgab.entity.DataSharing;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface DataSharingMapper {

  void save(DataSharing dataSharing);

  void update(DataSharing dataSharing);

  DataSharing get(Long id);

  List<DataSharing> getByUserId(Long user_id);

}
