package com.shengbo.quartz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shengbo.quartz.dao.QuartzBeanMapper;
import com.shengbo.quartz.entity.JobBean;
import com.shengbo.quartz.util.QuartzUtil;
import com.shengbo.quartz.util.UUIDUtil;

/**
 * quartz任务注册表
 * @author panguixiang
 *
 */
@Service("quartzBeanService")
public class QuartzBeanService {
	
	@Autowired
	private QuartzBeanMapper quartzBeanMapper;
	
	public List<JobBean> list() {
		return quartzBeanMapper.list();
	}
	
	public void addJob(JobBean jobBean) throws Exception{
		jobBean.setId(UUIDUtil.getUUID());
		quartzBeanMapper.add(jobBean);
		QuartzUtil.addJob(jobBean);
	}
	
	public void remove(String id) throws Exception{
		id=id.trim();
		quartzBeanMapper.remove(id);
		QuartzUtil.deleteJob(id);
	}
	
	public JobBean getById(String id) throws Exception{
		return quartzBeanMapper.getById(id);
	}
	
}
