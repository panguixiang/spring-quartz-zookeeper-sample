package com.shengbo.quartz.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shengbo.quartz.context.ProContent;
import com.shengbo.quartz.service.LoginService;

/**
 * 
 * @author panguixiang
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	/**
	 * 登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "init", method = RequestMethod.GET)
	public String login() {
			return "login";
	}
	
	/**
	 * 登录
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam("loginName") String loginName, 
			@RequestParam("password") String password, HttpServletRequest request) {
		if(loginService.login(loginName.trim(), password.trim())) {
			request.getSession(true).setAttribute(ProContent.LOGIN_SESSION, loginName);
			return "redirect:/quartz/list";
		} else {
			return "login";
		}
	}
	
	/**
	 * 退出登录
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "out", method = RequestMethod.GET)
	public String out(HttpServletRequest request) {
		request.getSession(true).invalidate();
		return "redirect:/login/init";
	}
	
}
