package com.shengbo.quartz.service;

import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.shengbo.quartz.util.ToolUtils;

/**
 * 处理渠道结算业务类
 * @author panguixiang
 *
 */
@Service
public class ChannelSettleJobService {

	private static Logger logger = Logger.getLogger(ChannelSettleJobService.class);
			
	/**
	 * 调用存储过程执行清算业务
	 * @return
	 */
	public boolean doWorkChannelSettle() {
		logger.info("--------Begin-------------进入执行渠道结算Job业务-------执行时间-===="+ToolUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		logger.info("--------End-------------完成执行渠道结算Job业务----结束时间-===="+ToolUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		return true;
	}
}
