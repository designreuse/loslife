package com.asgab.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.entity.User;
import com.asgab.service.user.UserService;

@Component
@Transactional
public class ApplicationContext {

	@Autowired
	private UserService userService;

	public static List<User> payingUsers = new ArrayList<User>();

	public static Long lastSyncTime = CommonUtil.getCurrentDateTime();

	public synchronized void init() {

		userService.initPayingUsers();

		System.out.println("init end");
	}

}
