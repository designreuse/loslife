package com.asgab.web.ajax;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {

	@RequestMapping(value = "view/{id}", method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request, @PathVariable("id") Long id) {

		return "user/modalDetail";
	}

}
