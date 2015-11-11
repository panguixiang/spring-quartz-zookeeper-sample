package com.shengbo.quartz.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shengbo.quartz.context.ProContent;
import com.shengbo.quartz.service.DbOptService;

/**
 *数据库增删改查操作
 * @author panguixiang
 *
 */
@Controller
@RequestMapping("/quartz/data")
public class DataController {

	@Autowired
	private DbOptService dbOptService;
	
	/**
	 * 查询job列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "init", method = RequestMethod.GET)
	public String initSave(Model model) {
		model.addAttribute("message", "");
		return "list";
	}
	
	@RequestMapping(value="save", method = RequestMethod.POST)
	public String save(@ModelAttribute("sql") String sql, Model model, HttpSession session) {
		model.addAttribute("message", "成功！");
		model.addAttribute("sql", sql);
		if(StringUtils.isBlank(sql)) {
			model.addAttribute("message", "失败！-sql为空！");
			return "list";
		}
		String userName = (String)session.getAttribute(ProContent.LOGIN_SESSION);
		try {
			dbOptService.save(sql,userName);
		} catch (Exception e) {
			model.addAttribute("message", "失败！异常！"+e);
		}
		return "list";
	}
	
	@RequestMapping(value="update", method = RequestMethod.POST)
	public String update(@ModelAttribute("sql") String sql, Model model, HttpSession session) {
		model.addAttribute("sql", sql);
		if(StringUtils.isBlank(sql)) {
			model.addAttribute("message", "失败！-sql为空！");
			return "list";
		}
		String userName = (String)session.getAttribute(ProContent.LOGIN_SESSION);
		try {
			dbOptService.update(sql,userName);
		} catch (Exception e) {
			model.addAttribute("message", "失败！异常！"+e);
		}
		return "list";
	}
	
	@RequestMapping(value="delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute("sql") String sql, Model model, HttpSession session) {
		model.addAttribute("sql", sql);
		if(StringUtils.isBlank(sql)) {
			model.addAttribute("message", "失败！-sql为空！");
			return "list";
		}
		String userName = (String)session.getAttribute(ProContent.LOGIN_SESSION);
		try {
			dbOptService.delete(sql,userName);
		} catch (Exception e) {
			model.addAttribute("message", "失败！异常！"+e);
		}
		return "list";
	}
	
	@RequestMapping(value="select", method = RequestMethod.POST)
	public String select(@ModelAttribute("sql") String sql,Model model) {
		model.addAttribute("sql", sql);
		if(StringUtils.isBlank(sql)) {
			model.addAttribute("message", "失败！-sql为空！");
			return "list";
		}
		List<Map<String,Object>> list = null;
		try {
			list = dbOptService.select(sql);
			if(list==null || list.size()==0) {
				model.addAttribute("message", "查无数据");
			} else {
				model.addAttribute("list", list);
			}
		} catch (Exception e) {
			model.addAttribute("message", "失败！异常！"+e);
		}
		return "list";
	}
	
}
