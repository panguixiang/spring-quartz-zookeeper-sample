package com.shengbo.quartz.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.springframework.stereotype.Service;

import com.shengbo.quartz.context.ProContent;

import net.sf.json.JSONObject;

/**
 * 操作zookeeper服务service层
 * @author panguixiang
 *
 */
@Service
public class ZookeService {
	
	private static Logger logger = Logger.getLogger(ZookeService.class);
	
	/**
	 * 根据nodeName查询子节点列表
	 * @param nodeName
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> nodesMap(String nodeName) throws RuntimeException{
		List<String> list = null;
		ZkClient zkClient = new ZkClient(ProContent.ZOOKEEPER_ADDRESS);
		Map<String,Object> zkNodesMap = new TreeMap<String,Object>();
		RuntimeException ex = null;
		try{
			if(StringUtils.isBlank(nodeName)) {
				list = zkClient.getChildren("/");//zookeeper最高级节点为/,其下面的子节点默认有一个zookeeper
				nodeName="";
			} else {
				if(zkClient.exists(nodeName)) {
					zkNodesMap.put(nodeName, zkClient.readData(nodeName, true));
					list = zkClient.getChildren(nodeName.trim());//zookeeper最高级节点为/,其下面的子节点默认有一个zookeeper
				}
			}
			if(list==null) {
				zkClient.close();
				return zkNodesMap;
			}
			String childNodeFullName = "";
			for(String node : list) {
				if(!node.equals("zookeeper")) {
					childNodeFullName = nodeName.concat("/").concat(node);
					zkNodesMap.put(childNodeFullName, zkClient.readData(childNodeFullName, true));
				}
			}
		}catch(RuntimeException e){//异常必须在这捕获，因为最终都需要关闭zkClient的链接，其他方法雷同
			logger.error("根据节点".concat(nodeName).concat("查询子节点列表").concat("系统异常!==>")+e);
			ex = e;
		} finally {
			if(zkClient!=null) {
				zkClient.close();
			}
			if(ex!=null) {
				throw new RuntimeException(ex);
			}
		}
		return zkNodesMap;
	}
	
	/**
	 * 根据nodeName查询此节点对应的数据
	 * @param nodeName
	 * @return
	 * @throws Exception
	 */
	public Object getNodeValue(String nodeName) throws RuntimeException{
		ZkClient zkClient = new ZkClient(ProContent.ZOOKEEPER_ADDRESS);
		Object obj = null;
		RuntimeException ex = null;
		try{
			obj = zkClient.readData(nodeName.trim(), true);
		}catch(RuntimeException e){//异常必须在这捕获，因为最终都需要关闭zkClient的链接，其他方法雷同
			logger.error("根据节点".concat(nodeName).concat("查询此节点对应的数据").concat("系统异常!==>")+e);
			ex = e;
		} finally {
			if(zkClient!=null) {
				zkClient.close();
			}
			if(ex!=null) {
				throw new RuntimeException(ex);
			}
		}
		return obj;
	}
	
	/**
	 * 新增，修改节点数据
	 * @param nodeName
	 * @param nodeValue
	 * @param nodeDesc
	 * @throws Exception
	 */
	public void opt(String nodeName, String nodeValue, String nodeDesc) throws RuntimeException{
		ZkClient zkClient = new ZkClient(ProContent.ZOOKEEPER_ADDRESS);
		RuntimeException ex = null;
		try{
			if (!zkClient.exists(nodeName)) {
				zkClient.create(nodeName, new Long(System.currentTimeMillis()), CreateMode.PERSISTENT);
			}
			JSONObject json = new JSONObject();
			json.put("nodeValue", nodeValue);
			json.put("nodeDesc", nodeDesc);
			zkClient.writeData(nodeName.trim(), json.toString());
		}catch(RuntimeException e){//异常必须在这捕获，因为最终都需要关闭zkClient的链接，其他方法雷同
			logger.error("新增，修改节点数据".concat(nodeName).concat("系统异常!==>")+e);
			ex = e;
		} finally {
			if(zkClient!=null) {
				zkClient.close();
			}
			if(ex!=null) {
				throw new RuntimeException(ex);
			}
		}
	}
	/**
	 * 删除节点数据
	 * @param nodeName
	 * @throws Exception
	 */
	public void deleteNode(String nodeName) throws RuntimeException{
		List<String> list = null;
		ZkClient zkClient = new ZkClient(ProContent.ZOOKEEPER_ADDRESS);
		RuntimeException ex = null;
		try {
			if(StringUtils.isBlank(nodeName)) {
				list = zkClient.getChildren("/");//zookeeper最高级节点为/,其下面的子节点默认有一个zookeeper
				nodeName="";
			} else {
				if(zkClient.exists(nodeName)) {
					list = zkClient.getChildren(nodeName.trim());//zookeeper最高级节点为/,其下面的子节点默认有一个zookeeper
				}
			}
			if(list!=null) {
				for(String node : list) {
					if(!node.equals("zookeeper")) {
						zkClient.delete(nodeName.concat("/").concat(node));
					}
				}
			}
			zkClient.delete(nodeName);
		} catch(RuntimeException e) {//异常必须在这捕获，因为最终都需要关闭zkClient的链接，其他方法雷同
			logger.error("删除节点".concat(nodeName).concat("系统异常!==>")+e);
			ex = e;
		} finally {
			if(zkClient!=null) {
				zkClient.close();
			}
			if(ex!=null) {
				throw new RuntimeException(ex);
			}
		}
	}
}
