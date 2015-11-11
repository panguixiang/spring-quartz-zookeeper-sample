package com.shengbo.quartz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shengbo.quartz.entity.JobBean;

public interface QuartzBeanMapper {

	public List<JobBean> list();
	
	public void add(@Param("job") JobBean jobBean);
	
	public void remove(@Param("id") String id);
	
	public JobBean getById(@Param("id") String id);
	
}
