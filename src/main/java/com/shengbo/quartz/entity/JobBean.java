package com.shengbo.quartz.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.quartz.JobKey;
import org.quartz.TriggerKey;

/**
 * quartz 实体参数bean
 * @author panguixiang
 *
 */
public class JobBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4258809306128807097L;

	private String id;
	
	@NotNull(message="任务名称不能为空")
	@Size(max=200, message="最大为80个字符")
	@Pattern(regexp="^[A-Za-z0-9-]+$", message="只能为英文或数字组合")
	private String jobName;//任务名称
	
	@NotNull(message="触发器名称不能为空")
	@Size(max=200, message="最大为80个字符")
	@Pattern(regexp="^[A-Za-z0-9-]+$", message="只能为英文或数字组合")
	private String triggerName;//触发器名称
	
	private JobKey jobKey;//任务Key
	private String classPackage;//实现Job接口的类全名
	private TriggerKey trigerKey;//触发器Key
	
	@NotNull(message="执行规则不能为空")
	@Size(max=50, message="最大为50个字符")
	private String cron;//0/2 * * * * ?
	/*备注*/
	@Size(max=50, message="最大为50个字符")
	private String remarks="";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public JobKey getJobKey() {
		return jobKey;
	}
	public void setJobKey(JobKey jobKey) {
		this.jobKey = jobKey;
	}
	public String getClassPackage() {
		return classPackage;
	}
	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}
	public TriggerKey getTrigerKey() {
		return trigerKey;
	}
	public void setTrigerKey(TriggerKey trigerKey) {
		this.trigerKey = trigerKey;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
