package com.shengbo.quartz.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 操作数据表（CAID）
 * @author pangx
 *
 */
public interface DbOptMapper {

	public void save(@Param("insertSql") String insertSql) throws Exception;
	
	public void update(@Param("updateSql") String updateSql) throws Exception;
	
	public void delete(@Param("deleteSql") String deleteSql) throws Exception;
	
	public List<Map<String,Object>> select(@Param("selectSql") String selectSql) throws Exception;
	
}
