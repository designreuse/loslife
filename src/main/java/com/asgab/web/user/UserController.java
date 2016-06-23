package com.asgab.web.user;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.asgab.util.CommonUtil;
import com.asgab.util.Servlets;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static final String PAGE_SIZE = "10";

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sort", defaultValue = "id desc") String sort, ServletRequest request, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(request.getParameter("admin_account"))) {
			params.put("admin_account", request.getParameter("admin_account"));
		}
		if (StringUtils.isNotBlank(request.getParameter("searchType"))) {
			params.put("searchType", request.getParameter("searchType"));
		}
		setParameters(params, request.getParameter("searchType"));

		model.addAttribute("search", Servlets.encodeParameterString(params));
		params.put("sort", sort);
		Page<User> page = new Page<User>(pageNumber, pageSize, sort, params);
		Page<User> pages = userService.search(page);
		model.addAttribute("pages", pages);
		return "user/userList";
	}

	private void setParameters(Map<String, Object> params, String searchType) {
		if (StringUtils.isBlank(searchType)) {
			return;
		}
		switch (Integer.parseInt(searchType)) {
		case 1:
			// 当月新增付费用户
			params.put("search_start", CommonUtil.getFistDayOfMonth());
			params.put("search_end", CommonUtil.getLastDayOfMonth());
			break;
		case 2:
			// 过期3天付费用户
			params.put("search_start", CommonUtil.getGapNDaysEnd(-3));
			params.put("search_end", CommonUtil.getGapNDaysEnd(-3));
			break;
		case 3:
			// 还剩5天使用期付费用户
			params.put("search_start", CommonUtil.getGapNDaysEnd(5));
			params.put("search_end", CommonUtil.getGapNDaysEnd(5));
			break;
		case 4:
			// 还剩5天过期试用用户
			params.put("search_start", CommonUtil.getGapNDaysEnd(5));
			params.put("search_end", CommonUtil.getGapNDaysEnd(5));
			break;
		case 5:
			// 在使用的付费
			params.put("search_start", Calendar.getInstance().getTimeInMillis());
			break;
		case 6:
			// 付费3天未备份
			params.put("search_start", CommonUtil.getGapNDaysEnd(-3));
			break;
		case 7:
			// 试用3天未备份
			params.put("search_start", CommonUtil.getGapNDaysEnd(-3));
			break;

		default:
			break;
		}
	}

	@ModelAttribute
	public void getUser(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("user", userService.get(id));
		}
	}

}
