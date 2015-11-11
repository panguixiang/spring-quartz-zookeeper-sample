package com.shengbo.quartz.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class ToolUtils {
	
	/**
	 * 获取订单流水号  算法     流水号产生时间(8位)+行业编号(4位)+uuid hashcode(10位)+随机码(4位)  共计26位数字
	 * @return
	 */
	public static String getPipelineNum(String businessNum){
		return format(new Date(), "yyyyMMdd") + businessNum + findNextVal();
	}
	
	/**唯一码生成器  返回值14位  10位uuid  hashcode  +  4位随机码
	 * @param name
	 * @return
	 */
	/**
	 * @return
	 */
	public static String findNextVal(){
		String uuidcode = String.valueOf(Math.abs(UUID.randomUUID().hashCode()));
		int k = uuidcode.length();
		for (int i = 0; i < 10-k; i++) {
			uuidcode = uuidcode+"0";
		}
		if(uuidcode.length()>10){
			uuidcode = uuidcode.substring(0, 10);
		}
		return String.valueOf(uuidcode+Math.round(Math.random()*8999+1000));
	}
	
	/**
	 * 格式化
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		DateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * 依据当前时间增加时间
	 * @param time
	 * @return
	 */
	public static Date addSecond(Date date,int second){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, second); 
		return calendar.getTime();
	}

	/**
	 * 获取UUID by jdk
	 * @return
	 */
	public static String getUuidByJdk(boolean is32bit){
		String uuid = UUID.randomUUID().toString();
		if(is32bit){
			return uuid.toString().replace("-", "");
		}
		return uuid;
	}
	/**
	 * 获得date日期后n天的日期
	 * @param date
	 * @param n
	 * @return
	 */
	public static String getNextNDay(Date date,int n) {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();  
		c.setTimeInMillis(new Date().getTime());
		c.add(Calendar.DATE, n);//n天后的日期
		return formatDate.format(new Date(c.getTimeInMillis()));
	}
	
}
