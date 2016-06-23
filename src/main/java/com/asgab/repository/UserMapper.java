package com.asgab.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.asgab.entity.User;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface UserMapper {

	User get(Long id);

	List<User> search(Map<String, Object> parameters);

	List<User> search(Map<String, Object> parameters, RowBounds rowBounds);

	int count(Map<String, Object> map);

}
