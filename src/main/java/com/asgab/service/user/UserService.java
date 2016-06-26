package com.asgab.service.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.core.pagination.Page;
import com.asgab.entity.BackupHistory;
import com.asgab.entity.RechargeRecord;
import com.asgab.entity.User;
import com.asgab.repository.UserMapper;
import com.asgab.service.recharge.RechargeRecordService;
import com.asgab.util.ApplicationContext;
import com.asgab.util.CommonUtil;

@Component
@Transactional
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RechargeRecordService rechargeRecordService;

	public Page<User> search(Page<User> page) {
		List<User> businessOpportunitys = userMapper.search(page.getSearchMap(), page.getRowBounds());
		int count = userMapper.count(page.getSearchMap());
		page.setContent(businessOpportunitys);
		page.setTotal(count);
		return page;
	}

	public List<User> searchAll(Page<User> page) {
		return userMapper.search(page.getSearchMap());
	}

	public User getDB(String id) {
		return userMapper.get(id);
	}

	public User get(String id) {
		User user = userMapper.get(id);
		if (user != null) {
			user.getRechargeRecords().addAll(rechargeRecordService.getByEnterpriseId(id));

		}
		return user;
	}

	public List<User> getPayingUsers() {
		return userMapper.getPayingUsers();
	}

	// 当月新增用户
	public List<String> getIncreasePayingUser(Long startDate, Long endDate) {
		List<User> payingUsers = ApplicationContext.payingUsers;
		List<String> enterpriseIds = new ArrayList<String>();
		enterpriseIds.add("$");
		for (User user : payingUsers) {
			for (RechargeRecord tmp : user.getRechargeRecords()) {
				// 第一个充值记录
				if (tmp.getMoney().compareTo(BigDecimal.ZERO) > 0) {
					if (tmp.getPay_date() >= startDate && tmp.getPay_date() <= endDate) {
						enterpriseIds.add(user.getId());
					}
					break;
				}
			}
		}
		return enterpriseIds;
	}

	// N天内用户
	public List<String> getExpireNDaysPayingUser(Long startDate, Long endDate) {
		List<User> payingUsers = ApplicationContext.payingUsers;
		List<String> enterpriseIds = new ArrayList<String>();
		enterpriseIds.add("$");
		for (User user : payingUsers) {
			if (user.getExpires_time() >= startDate && user.getExpires_time() <= endDate) {
				enterpriseIds.add(user.getId());
			}
		}
		return enterpriseIds;
	}

	// 在使用的付费用户 ( 没有过期的 )
	public List<String> getInUsingPayingUser(Long currentDate) {
		List<User> payingUsers = ApplicationContext.payingUsers;
		List<String> enterpriseIds = new ArrayList<String>();
		enterpriseIds.add("$");
		for (User user : payingUsers) {
			if (user.getExpires_time() > currentDate) {
				enterpriseIds.add(user.getId());
			}
		}
		return enterpriseIds;
	}

	// 过期付费用户
	public List<String> getExpirePayingUser(Long currentDate) {
		List<User> payingUsers = ApplicationContext.payingUsers;
		List<String> enterpriseIds = new ArrayList<String>();
		enterpriseIds.add("$");
		for (User user : payingUsers) {
			if (user.getExpires_time() < currentDate) {
				enterpriseIds.add(user.getId());
			}
		}
		return enterpriseIds;
	}

	// 所有付费用户( 包括过期 )
	public List<String> getAllPayingUser() {
		List<User> payingUsers = ApplicationContext.payingUsers;
		List<String> enterpriseIds = new ArrayList<String>();
		enterpriseIds.add("$");
		for (User user : payingUsers) {
			enterpriseIds.add(user.getId());
		}
		return enterpriseIds;
	}

	// 初始化所有的付费用户
	public void initPayingUsers() {
		List<User> targetUsers = new ArrayList<User>();
		List<User> users = getPayingUsers();
		User tmpUser = new User();
		for (int i = users.size() - 1; i >= 0; i--) {
			boolean diff = false;
			// 如果和上一个User是一样的
			if (users.get(i).getId().equalsIgnoreCase(tmpUser.getId())) {
				// 添加到本身recharge信息 因为是倒序.
				tmpUser.getRechargeRecords().add(0, new RechargeRecord(users.get(i)));
			} else {
				// 不一样就另建一个
				diff = true;
				tmpUser = users.get(i);
				// 添加本身
				tmpUser.getRechargeRecords().add(0, new RechargeRecord(users.get(i)));
			}

			if (diff) {
				targetUsers.add(tmpUser);
			}

			// 移除
			users.remove(i);
		}
		ApplicationContext.payingUsers.clear();
		ApplicationContext.payingUsers.addAll(targetUsers);
		ApplicationContext.lastSyncTime = CommonUtil.getCurrentDateTime();
	}

	/**
	 * 下载
	 * 
	 * @param users
	 */
	public XSSFWorkbook download(List<User> users, HttpServletRequest request) {
		final XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");
		Row rowHeader = sheet.createRow(0);
		String[] rowHeaderStrs = { "注册手机", "版本", "注册时间", "过期时间", "最后备份时间", "备份次数" };
		int headerIndex = 0;
		for (String s : rowHeaderStrs) {
			Cell cell = rowHeader.createCell(headerIndex++);
			cell.setCellValue(s);
		}
		int rowIndex = 1;
		for (User user : users) {
			int bodyColumnIndex = 0;
			Row rowBody = sheet.createRow(rowIndex++);
			Cell cell = rowBody.createCell(bodyColumnIndex++);
			cell.setCellValue(user.getAdmin_account());

			cell = rowBody.createCell(bodyColumnIndex++);
			cell.setCellValue(user.getVersion_type());

			cell = rowBody.createCell(bodyColumnIndex++);
			cell.setCellValue(user.getFmtDate_register());

			cell = rowBody.createCell(bodyColumnIndex++);
			cell.setCellValue(user.getFmtExpires_time());

			cell = rowBody.createCell(bodyColumnIndex++);
			cell.setCellValue(user.getFmtLastBackup_date());

			cell = rowBody.createCell(bodyColumnIndex++);
			cell.setCellValue(user.getBackup_count());
		}

		return workbook;
	}

	public String countBackupHistorysByEnterpriseId(String enterprise_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enterprise_id", enterprise_id);
		map.put("time1", CommonUtil.getGapNDaysStart(-1));
		map.put("time2", CommonUtil.getGapNDaysEnd(-1));
		map.put("time3", CommonUtil.getGapNDaysStart(-2));
		map.put("time4", CommonUtil.getGapNDaysEnd(-2));
		map.put("time5", CommonUtil.getGapNDaysStart(-3));
		map.put("time6", CommonUtil.getGapNDaysEnd(-3));
		List<Integer> countList = userMapper.countBackupHistorysByEnterpriseId(map);
		String str = "";
		for (int i = 0; i < countList.size(); i++) {
			if (i != 0) {
				str += "/";
			}
			str += countList.get(i);
		}
		return str;
	}

	public int unlockDevice(String admin_account) {
		int result = 0;
		List<String> ids = userMapper.getUserLoginCount(admin_account);
		if (ids.size() > 1) {
			// 超过1个. 可能有问题
			result = -1;
		} else if (ids.size() == 1) {
			for (String id : ids) {
				result = userMapper.deleteUserLoginCount(id);
			}
		}
		return result;
	}

	public List<BackupHistory> getBackupHistory(String id) {
		return userMapper.getBackupHistory(id);
	}
}
