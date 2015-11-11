package com.shengbo.quartz.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.shengbo.quartz.context.ProContent;
import com.shengbo.quartz.cron.CronJob;
import com.shengbo.quartz.entity.JobBean;

/**
 * 操作 quartz数据的util工具类
 * @author panguixiang
 *
 */
public class QuartzUtil {

	// 存储执行任务
	private static List<JobBean> quartzList = new ArrayList<JobBean>();
	// 获得Scheduler实例
	private static Scheduler scheduler = (Scheduler)ApplicationContextHelper.getApplicationContext().getBean("scheduler");

	/**
	 * 获得 执行任务列表
	 * 
	 * @return
	 */
	public static List<JobBean> getJobBeanList() {
		return quartzList;
	}

	/**
	 * 新增执行任务
	 * 
	 * @param jobBean
	 * @throws Exception
	 */
	public static void addJob(JobBean jobBean) throws Exception {
		if(StringUtils.isBlank(jobBean.getId())) {
			jobBean.setId(UUIDUtil.getUUID());
		}
		CronJob dd = (CronJob) Class.forName(jobBean.getClassPackage()).newInstance();
		JobDetail jobDetail = JobBuilder.newJob(dd.getClass())
				.withIdentity(jobBean.getJobName(), ProContent.JOB_GROUP_NAME).build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(
				jobBean.getTriggerName(), ProContent.TRIGGER_GROUP_NAME)
				.withSchedule(CronScheduleBuilder.cronSchedule(jobBean.getCron())).build();
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
		jobBean.setJobKey(jobDetail.getKey());
		jobBean.setTrigerKey(trigger.getKey());
		quartzList.add(jobBean);
	}

	/**
	 * 根据JobBean ID 删除单个执行任务
	 * 
	 * @param id
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean deleteJob(String id) throws SchedulerException {
		if (StringUtils.isBlank(id)) {
			return false;
		}
		JobBean jobBean = getJobBeanById(id);
		scheduler.pauseTrigger(jobBean.getTrigerKey());//停止任务
		scheduler.unscheduleJob(jobBean.getTrigerKey());//删除trigger
		scheduler.deleteJob(jobBean.getJobKey());// 删除任务
		quartzList.remove(jobBean);
		return true;
	}

	/**
	 * 根据JobBean ID 获得单个执行任务
	 * 
	 * @param id
	 * @return
	 */
	private static JobBean getJobBeanById(String id) {
		for (JobBean bean : quartzList) {
			if (bean != null && StringUtils.equals(bean.getId(), id)) {
				return bean;
			}
		}
		return null;
	}
	
}
