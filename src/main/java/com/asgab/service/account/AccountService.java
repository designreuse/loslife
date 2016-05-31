package com.asgab.service.account;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.core.pagination.Page;
import com.asgab.entity.Group;
import com.asgab.entity.User;
import com.asgab.repository.xmo.UserXMOMapper;
import com.asgab.util.Digests;
import com.asgab.util.Encodes;
import com.google.common.collect.Maps;

/**
 * 用户管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class AccountService {

  public static final String HASH_ALGORITHM = "SHA-1";
  public static final int HASH_INTERATIONS = 1024;

  @Resource
  private UserXMOMapper userXMOMapper;

  public Page<User> getAllUser(Page<User> page) {
    List<User> list = userXMOMapper.search(page.getSearchMap(), page.getRowBounds());
    int count = userXMOMapper.count(page.getSearchMap());
    page.setContent(list);
    page.setTotal(count);
    return page;
  }

  public User findUserByLoginName(String loginName) {
    // return userDao.findByLoginName(loginName);
    Map<String, Object> map = Maps.newHashMap();
    map.put("loginName", loginName);
    // List<User> users = userMapper.search(map);
    List<User> users = userXMOMapper.search(map);
    if (users != null && users.size() > 0) {

      if (users.size() == 1) {
        return users.get(0);
      } else {
        for (User user : users) {
          if (loginName.equals(user.getLoginName())) {
            return user;
          }
        }
      }
    }
    return null;
  }

  public List<Group> findUserGroupByLoginName(String loginName) {
    Map<String, Object> map = Maps.newHashMap();
    map.put("loginName", loginName);
    return userXMOMapper.findUserGroupByLoginName(map);
  }
  
  public User get(Long id) {
    return userXMOMapper.get(id);
  }

  public static void main(String[] args) {
    byte[] hashPassword = Digests.sha1("0123456789".getBytes());

    System.out.println(Encodes.encodeHex(hashPassword));
  }
  
  public List<User> getAllXMOUser() {
    return (List<User>) userXMOMapper.search(null);
  }

}
