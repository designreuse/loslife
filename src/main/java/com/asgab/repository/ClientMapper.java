package com.asgab.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.asgab.entity.Client;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface ClientMapper {

  Client get(Long id);

  List<Client> search(Map<String, Object> parameters);

  List<Client> search(Map<String, Object> parameters, RowBounds rowBounds);

  void save(Client client);

  void update(Client client);

  void delete(Long id);

  int count(Map<String, Object> map);
  
  List<Client> getAdvertisersByIdList(List<Long> idList);

}
