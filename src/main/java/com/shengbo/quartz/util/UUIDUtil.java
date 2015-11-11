package com.shengbo.quartz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class UUIDUtil {

	/**
	 * 随机生成UUID 32位字符串
	 * 
	 * @return
	 */
	public static String getUUID() {
		return (UUID.randomUUID().toString()).replace("-", "");
	}

	/**
	 * 生成随机数6位
	 * 短信验证码
	 * @param passLength 长度 
	 * @param type 类型
	 * @return
	 */
	public static String getCode() {
		Date dt= new Date();
		Long time= dt.getTime();
		String str = String.valueOf(time);
		String res =  str.substring(str.length()-7, str.length()-1);
		return res;
	}

	/**
	 * 获得当前系统时间后的 nextMinute 分钟时刻 
	 * @param nextMinute
	 * @return
	 */
	public static Date getLastMinuteString(int nextMinute) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, +nextMinute);
		return calendar.getTime();
	}
	
	
	/**
	 * 
	 * @param checkDate 校验的时刻
	 * @param nowDate 
	 * @param minuteDifference 分钟数
	 * @return
	 */
	public static boolean compareDateTime(Date checkDate, Date nowDate, int minuteDifference) {
		 long l=checkDate.getTime()-nowDate.getTime();
		 if(l>=0 && l<=(minuteDifference*60*1000)) {//
			return true;
		 }
		 return false;
	}
	
	public static void getLastDay(Date begin, int lastDayNum) {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd"); //字符串转换
		  Calendar c = Calendar.getInstance();  
		  c.setTimeInMillis(begin.getTime());
		  c.add(Calendar.DATE, lastDayNum);//lastDayNum天后的日期
		  Date date= new Date(c.getTimeInMillis()); //将c转换成Date
		  System.out.println(formatDate.format(date));
	}
	
	public static void main(String args[]) throws ParseException {
		getNowLastDate(-10);
	}
	
	/**
	 * 获得当前日期的n的日期（n为负数表示n天前)
	 * @return
	 */
	public static String getNowLastDate(int n) {
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_MONTH, n);
		Date fu = ca.getTime();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		return formatDate.format(fu);
	}
	
	

}
