package com.shengbo.quartz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shengbo.quartz.context.ProContent;
import com.shengbo.quartz.entity.JobBean;
import com.shengbo.quartz.service.QuartzBeanService;

import net.sf.json.JSONObject;

/**
 * 管理quartz控制层
 * @author panguixiang
 *
 */
@Controller
@RequestMapping("/quartz")
public class ManagQuartzController {
	
	private static Logger logger = Logger.getLogger(ManagQuartzController.class);
	
	@Autowired
	private QuartzBeanService quartzBeanService;
	
	/**
	 * 查询job列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model) {
		List<JobBean> jobList = quartzBeanService.list();
		model.addAttribute("quartzList", jobList);
		return "quartzList";
	}

	
	/**
	 * 查询job列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "initSave", method = RequestMethod.GET)
	public String initSave(Model model) {
		return "save";
	}
	
	/**
	 * 新增job
	 * @param jobBean
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String add(@ModelAttribute("jobBean") @Valid JobBean jobBean, BindingResult result, HttpSession session) {
		
		String userName = (String)session.getAttribute(ProContent.LOGIN_SESSION);
		logger.info("====登录用户======".concat(userName).concat("===执行了  新增 Job操作")
				.concat(",任务Bean=").concat(JSONObject.fromObject(jobBean).toString()));
		
		if (result.hasErrors()) {
            return "save";  
        } 
		try {
			quartzBeanService.addJob(jobBean);
		} catch (Exception e) {
			logger.error("新增Job异常"+e);
		}
		return "redirect:/quartz/list";
	}

	/**
	 * 删除job
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET, produces = "application/json")
	public String delete(@PathVariable("id") String id, HttpSession session) {
		
		String userName = (String)session.getAttribute(ProContent.LOGIN_SESSION);
		
		JobBean bean = null;
		try {
			bean = quartzBeanService.getById(id);
			if(bean!=null) {
				quartzBeanService.remove(id);
			}
		} catch (Exception e) {
			logger.error("删除Job异常"+e);
		} finally {
			if(bean!=null) {
				logger.info("====登录用户======".concat(userName).concat("===执行了  删除 Job操作")
						.concat(",任务Bean=").concat(JSONObject.fromObject(bean).toString()));
			}
		}
		return "redirect:/quartz/list";
	}
	
}
