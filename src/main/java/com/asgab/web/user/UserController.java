package com.asgab.web.user;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.asgab.core.pagination.Page;
import com.asgab.entity.User;
import com.asgab.service.user.UserService;
import com.asgab.thread.SyncPayingUsersThread;
import com.asgab.util.ApplicationContext;
import com.asgab.util.CommonUtil;
import com.asgab.util.Servlets;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static final String PAGE_SIZE = "10";

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model) {
		Page<User> pages = new Page<User>(0, 0, "", null);
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("yearMonth", "2016-06");
		pages.setSearchMap(searchMap);
		model.addAttribute("pages", pages);
		model.addAttribute("init", "init");
		return "user/userList";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber, @RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sort", defaultValue = "id desc") String sort, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 如果超过10分钟没有更新payingUsers .先更新
		if ((CommonUtil.getCurrentDateTime() - ApplicationContext.lastSyncTime) > 10 * 60 * 1000) {
			// userService.initPayingUsers();
			// 用线程去同步
			SyncPayingUsersThread syncPayingUsersThread = new SyncPayingUsersThread(userService);
			syncPayingUsersThread.start();
		}

		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(request.getParameter("admin_account"))) {
			params.put("admin_account", request.getParameter("admin_account"));
		}
		if (StringUtils.isNotBlank(request.getParameter("searchType"))) {
			params.put("searchType", request.getParameter("searchType"));
		}
		if (StringUtils.isNotBlank(request.getParameter("yearMonth"))) {
			params.put("yearMonth", request.getParameter("yearMonth"));
		}

		model.addAttribute("search", Servlets.encodeParameterString(params));
		// 非页面数据不传入
		setParameters(params, request.getParameter("searchType"));

		params.put("sort", sort);
		Page<User> page = new Page<User>(pageNumber, pageSize, sort, params);

		// 如果是下载
		if (StringUtils.isNotBlank(request.getParameter("download"))) {
			XSSFWorkbook workbook = userService.download(userService.searchAll(page), request);
			// 往客户端传送文件流
			response.setContentType("application/x-msdownload");
			// 解决文件中文名下载问题
			String fileName = request.getParameter("fileName");
			try {
				response.setHeader("Content-Disposition", "attachment;filename=" + new String(URLDecoder.decode(fileName + ".xlsx", "UTF-8").getBytes(), "iso-8859-1"));
				// 最终写入文件
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} else {
			Page<User> pages = userService.search(page);
			model.addAttribute("pages", pages);
			return "user/userList";
		}
	}

	private void setParameters(Map<String, Object> params, String searchType) {
		if (StringUtils.isBlank(searchType)) {
			return;
		}
		params.put("searchIn", 1);
		switch (Integer.parseInt(searchType)) {
		case 1:
			// 当月新增付费用户
			String[] yearMonth = String.valueOf(params.get("yearMonth")).split("-");
			int year = Integer.parseInt(yearMonth[0]);
			int month = Integer.parseInt(yearMonth[1]) - 1;
			params.put("enterpriseIds", userService.getIncreasePayingUser(CommonUtil.getFistDayOfMonth(year, month), CommonUtil.getLastDayOfMonth(year, month)));
			break;
		case 2:
			// 过期3天内付费用户
			params.put("enterpriseIds", userService.getExpireNDaysPayingUser(CommonUtil.getGapNDaysStart(-3), CommonUtil.getGapNDaysEnd(-1)));
			break;
		case 3:
			// 过期3天内试用用户
			params.put("enterpriseIds", userService.getAllPayingUser());
			params.put("searchIn", 0);
			params.put("search_start", CommonUtil.getGapNDaysStart(-3));
			params.put("search_end", CommonUtil.getGapNDaysEnd(-1));

			break;
		case 4:
			// 不足5天使用期付费用户
			params.put("enterpriseIds", userService.getExpireNDaysPayingUser(CommonUtil.getGapNDaysStart(0), CommonUtil.getGapNDaysEnd(5)));
			break;
		case 5:
			// 还剩5天过期试用用户
			params.put("enterpriseIds", userService.getAllPayingUser());
			params.put("searchIn", 0);
			params.put("search_start", CommonUtil.getGapNDaysStart(0));
			params.put("search_end", CommonUtil.getGapNDaysEnd(5));
			break;
		case 6:
			// 在使用的付费
			params.put("enterpriseIds", userService.getInUsingPayingUser(CommonUtil.getCurrentDateTime()));
			break;
		case 7:
			// 过期付费用户
			params.put("enterpriseIds", userService.getExpirePayingUser(CommonUtil.getCurrentDateTime()));
			break;
		case 8:
			// 付费未过期 3天未备份 (和6一样. 但是需要加上未备份)
			params.put("enterpriseIds", userService.getInUsingPayingUser(CommonUtil.getCurrentDateTime()));
			params.put("backup_date", CommonUtil.getGapNDaysEnd(-4));
			break;
		case 9:
			// 试用未过期 3天未备份
			params.put("enterpriseIds", userService.getAllPayingUser());
			params.put("searchIn", 0);
			params.put("search_start", CommonUtil.getCurrentDateTime());
			params.put("backup_date", CommonUtil.getGapNDaysEnd(-4));
			break;

		default:
			break;
		}
	}

	@ModelAttribute
	public void getUser(@RequestParam(value = "id", defaultValue = "-1") String id, Model model) {
		if ("-1".equalsIgnoreCase(id)) {
			model.addAttribute("user", userService.get(id));
		}
	}

}
