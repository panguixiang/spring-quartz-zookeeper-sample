package com.shengbo.quartz.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shengbo.quartz.context.ProContent;
import com.shengbo.quartz.service.ZookeService;

import net.sf.json.JSONObject;

/**
 * 操作zookeeper服务控制器
 * 
 * @author panguixiang
 *
 */
@Controller
@RequestMapping("/quartz/zookeep")
public class ZookeController {

	private static Logger logger = Logger.getLogger(ZookeController.class);
	
	@Autowired
	private ZookeService zookeService;
	
	/**
	 * 查询节点列表
	 * @param nodeName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(@RequestParam(value = "nodeName", required = false) String nodeName, Model model) {
		model.addAttribute("nodeName", nodeName);
		Map<String, Object> zkNodesMap = null;
		try {
			zkNodesMap = zookeService.nodesMap(nodeName);
		} catch (RuntimeException e) {
			model.addAttribute("errorMsg", e);
		}
		model.addAttribute("zkNodesMap", zkNodesMap);
		return "zookeeper/list";
	}
	/**
	 * 初始化新增节点页面
	 * @param nodeName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "initAdd", method = RequestMethod.GET)
	public String initAdd(@RequestParam(value = "nodeName", required = false) String nodeName, Model model) {
		model.addAttribute("nodeName", nodeName);
		return "zookeeper/save";
	}
	/**
	 * 执行新增操作
	 * @param nodeName
	 * @param sonNodeName
	 * @param sonNodeValue
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@RequestParam(value = "nodeName",required = false) String nodeName,
			@RequestParam(value = "sonNodeName") String sonNodeName,
			@RequestParam(value = "sonNodeValue") String sonNodeValue,
			@RequestParam(value = "nodeDesc") String nodeDesc,HttpSession session,
			Model model) {
		String userName = (String)session.getAttribute(ProContent.LOGIN_SESSION);
		logger.info("====登录用户======".concat(userName).concat("===执行了  新增  节点操作")
				.concat(",节点名称sonNodeName=").concat(sonNodeName).concat(",节点值sonNodeValue=").concat(sonNodeValue).concat(",节点描述nodeDesc=").concat(nodeDesc));
		try {
			zookeService.opt(nodeName+sonNodeName,sonNodeValue,nodeDesc);
		} catch (RuntimeException e) {
			model.addAttribute("errorMsg", e);
			return "zookeeper/save";
		}
		return "redirect:/quartz/zookeep/list?nodeName="+nodeName;
	}
	/**
	 * 初始化修改页面
	 * @param nodeName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "initUpdate", method = RequestMethod.GET)
	public String initUpdate(@RequestParam(value = "nodeName") String nodeName, Model model) {
		model.addAttribute("nodeName", nodeName);
		Object obj = null;
		try {
			obj = zookeService.getNodeValue(nodeName);
		} catch (RuntimeException e) {
			model.addAttribute("errorMsg", e);
		}
		if(obj!=null) {
			JSONObject json = JSONObject.fromObject(obj);
			model.addAttribute("nodeValue", json.get("nodeValue"));
			model.addAttribute("nodeDesc", json.get("nodeDesc"));
		}
		return "zookeeper/update";
	}
	/**
	 * 执行修改操作
	 * @param nodeName
	 * @param nodeValue
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@RequestParam(value = "nodeName") String nodeName,
			@RequestParam(value = "nodeValue") String nodeValue,
			@RequestParam(value = "nodeDesc") String nodeDesc,
			Model model,HttpSession seesion) {
		String userName = (String)seesion.getAttribute(ProContent.LOGIN_SESSION);
		logger.info("====登录用户======".concat(userName).concat("===执行了 修改  节点操作")
				.concat(",节点名称nodeName=").concat(nodeName).concat(",节点值nodeValue=").concat(nodeValue).concat(",节点描述nodeDesc=").concat(nodeDesc));
		try {
			zookeService.opt(nodeName,nodeValue,nodeDesc);
		} catch (RuntimeException e) {
			model.addAttribute("errorMsg", e);
			return "zookeeper/update";
		}
		return "redirect:/quartz/zookeep/list?nodeName="+nodeName;
	}
	
	/**
	 * 执行删除操作
	 * @param nodeName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "nodeName") String nodeName, Model model, HttpSession session) {
		model.addAttribute("nodeName", nodeName);
		String userName = (String)session.getAttribute(ProContent.LOGIN_SESSION);
		logger.info("====登录用户======".concat(userName).concat("===执行了 删除  节点操作")
				.concat(",节点名称nodeName=").concat(nodeName));
		try {
			 zookeService.deleteNode(nodeName);
		} catch (RuntimeException e) {
			model.addAttribute("errorMsg", e);
			return "zookeeper/list";
		}
		return "redirect:/quartz/zookeep/list";
	}
}
