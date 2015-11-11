package com.shengbo.quartz.cron;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.shengbo.quartz.service.ChannelSettleJobService;
import com.shengbo.quartz.util.ApplicationContextHelper;

/**
 * 渠道结算任务类，必须继承 CronJob 类
 * @author panguixiang
 *
 */
public class ChannelSettleCronJob extends CronJob {

	/**
	 * 执行渠道结算任务(但本身并不处理业务，业务交由spring的service层)
	 */
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		ChannelSettleJobService channelSettleService =
				(ChannelSettleJobService)ApplicationContextHelper.getApplicationContext().getBean("channelSettleJobService");
		channelSettleService.doWorkChannelSettle();
	}

}
