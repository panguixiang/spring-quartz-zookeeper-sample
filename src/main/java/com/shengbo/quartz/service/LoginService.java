package com.shengbo.quartz.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.shengbo.quartz.context.ProContent;

/**
 * 登录service
 * @author panguixiang
 *
 */
@Service
public class LoginService {

	/**
	 * 登录验证
	 * @param loginName
	 * @param password
	 * @return
	 */
	public boolean login(String loginName, String password) {
		String key = DigestUtils.md5Hex(loginName.concat(password));
		if(StringUtils.isBlank(ProContent.loginUser.get(key))) {
			return false;
		}
		return true;
	}
}
