package com.asgab.repository.xmo;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.asgab.entity.Group;
import com.asgab.entity.User;
import com.asgab.entity.xmo.Currency;
import com.asgab.repository.mybatis.MyBatisRepositoryXMO;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现. 方法名称必须与Mapper.xml中保持一致.
 * 
 * @author calvin
 */
@MyBatisRepositoryXMO
public interface UserXMOMapper {

  User get(Long id);

  List<User> search(Map<String, Object> parameters);

  List<User> search(Map<String, Object> parameters, RowBounds rowBounds);

  void save(User user);

  void update(User user);

  void delete(Long id);

  int count(Map<String, Object> map);

  List<Group> findUserGroupByLoginName(Map<String, Object> parameters);

  List<User> findUsersByGroupName(Map<String, Object> parameters);

  List<Group> findGroupByGroupName(String groupName);
  
  List<Currency> getCurrencys();

}
