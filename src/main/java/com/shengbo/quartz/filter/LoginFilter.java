package com.shengbo.quartz.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shengbo.quartz.context.ProContent;
/**
 * 登录校验拦截器
 * @author panguixiang
 *
 */
public class LoginFilter extends HandlerInterceptorAdapter{
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	    Object obj = request.getSession(true).getAttribute(ProContent.LOGIN_SESSION);
	    if(obj==null) {
	    	response.sendRedirect("/login/init");
	    	return false;
	    }
		return true;
	}
	
}