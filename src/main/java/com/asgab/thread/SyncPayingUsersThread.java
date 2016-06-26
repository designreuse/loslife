package com.asgab.thread;

import com.asgab.service.user.UserService;

public class SyncPayingUsersThread extends Thread {

	private UserService userService;

	public SyncPayingUsersThread(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void run() {
		System.out.println("Sync Paying Users start");
		userService.initPayingUsers();
		System.out.println("Sync Paying Users end");
	}

}
