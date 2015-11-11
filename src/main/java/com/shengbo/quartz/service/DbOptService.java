package com.shengbo.quartz.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shengbo.quartz.dao.DbOptMapper;
/**
 * 生产数据库增删改查基本操作
 * @author panguixiang
 *
 */
@Service
public class DbOptService {
	
	private static Logger logger = Logger.getLogger(DbOptService.class);
	
	@Autowired
	private DbOptMapper dbOptMapper;
	
	public void save(String insertSql, String optUser) throws Exception {
		dbOptMapper.save(insertSql);
		logger.info(optUser.concat("执行了插入sql:").concat(insertSql));
	}
	
	public void update(String updateSql, String optUser) throws Exception {
		dbOptMapper.update(updateSql);
		logger.info(optUser.concat("执行了修改sql:").concat(updateSql));
	}
	
	public void delete(String deleteSql, String optUser) throws Exception {
		dbOptMapper.delete(deleteSql);
		logger.info(optUser.concat("执行了删除sql:").concat(deleteSql));
	}
	
	public List<Map<String,Object>> select(String selectSql) throws Exception {
		return dbOptMapper.select(selectSql);
	}
	
}
