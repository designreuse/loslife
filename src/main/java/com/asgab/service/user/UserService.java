package com.asgab.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.core.pagination.Page;
import com.asgab.entity.User;
import com.asgab.repository.UserMapper;

@Component
@Transactional
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public Page<User> search(Page<User> page) {
		List<User> businessOpportunitys = userMapper.search(page.getSearchMap(), page.getRowBounds());
		int count = userMapper.count(page.getSearchMap());
		page.setContent(businessOpportunitys);
		page.setTotal(count);
		return page;
	}

	public User get(Long id) {
		return userMapper.get(id);
	}

}
