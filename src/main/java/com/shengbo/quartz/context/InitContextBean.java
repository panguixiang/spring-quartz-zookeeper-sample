package com.shengbo.quartz.context;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.shengbo.quartz.entity.JobBean;
import com.shengbo.quartz.service.QuartzBeanService;
import com.shengbo.quartz.util.ApplicationContextHelper;
import com.shengbo.quartz.util.QuartzUtil;


/**
 * 随spring容器启动而启动执行初始化数据
 * @author panguixiang
 *
 */
@SuppressWarnings("serial")
public class InitContextBean extends HttpServlet {

	private static Logger logger = Logger.getLogger(InitContextBean.class);
	
	public void init() throws ServletException {
		ProContent.ZOOKEEPER_ADDRESS = this.getServletContext().getInitParameter("zookeepersIp");
		initParam();//初始化项目配置参数
		initQuartz();//初始化quartz任务
	}
	
	/**
	 * 项目启动时将zookeeper里配置的节点数据获得
	 */
	private void initParam() {
		ZkClient zkClient = new ZkClient(ProContent.ZOOKEEPER_ADDRESS,2000);
		String node = zkClient.readData("/taxi/param",true);
		System.out.println("===得到zookeeper中配置的/taxi/param 节点数据 ======"+node);
		
		zkClient.subscribeDataChanges("/taxi/param", new IZkDataListener() {
			/**
			 * 删除/taxi/param节点数据后，回调此事件
			 */
			public void handleDataDeleted(String dataPath) throws Exception {
				logger.info("taxi param was deleted!");
			}
			/**
			 * 改变/taxi/param节点数据后，回调此事件
			 */
			public void handleDataChange(String dataPath, Object data) throws Exception {
				System.out.println("===zookeeper中配置的/taxi/param 节点数据 ======"+node);
			}
		});
		
	}
	/**
	 * 项目启动时从数据库里查询出已有的quartz规则，并添加到系统crob里
	 */
	private void initQuartz() {
		QuartzBeanService quartzBeanService =
				(QuartzBeanService) ApplicationContextHelper.getApplicationContext().getBean("quartzBeanService");
		List<JobBean> list = quartzBeanService.list();
		if(CollectionUtils.isNotEmpty(list)) {
		for(JobBean job : list) {
			try {
				QuartzUtil.addJob(job);//开启quartz job
			} catch (Exception e) {
				logger.error("初始化quartz job 信息异常=="+e);
			}
		}
		}
	}
	
}
