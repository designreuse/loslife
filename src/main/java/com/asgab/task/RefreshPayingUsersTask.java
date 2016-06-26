package com.asgab.task;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.service.user.UserService;
import com.asgab.util.CommonUtil;

@Component
@Transactional
public class RefreshPayingUsersTask {

	Log log = LogFactory.getLog(RefreshPayingUsersTask.class);

	@Autowired
	private UserService userService;

	public void run() {
		log.info("----------sync paying enterprises start----------");
		log.info("----------" + CommonUtil.formatDate(new Date()) + "----------");

		userService.initPayingUsers();

		log.info("----------" + CommonUtil.formatDate(new Date()) + "----------");
		log.info("----------sync paying enterprises successs----------");
	}

}
