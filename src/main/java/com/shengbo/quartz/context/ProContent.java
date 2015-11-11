package com.shengbo.quartz.context;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

public class ProContent {
	
	public final static String JOB_GROUP_NAME="SETTLE_JOB_GROUP_NAME";
	public final static String TRIGGER_GROUP_NAME="SETTLE_TRIGGER_GROUP_NAME";
	public final static String DAY_BEGIN_SUFFIX=" 00:00:00";
	public final static String DAY_END_SUFFIX=" 23:59:59";
	public final static String LOGIN_SESSION="LOGIN_SESSION";
	
	public static String ZOOKEEPER_ADDRESS = "";
	
	public static Map<String,String> loginUser= new HashMap<String,String>();
	static {
		loginUser.put("356f952e25f40ec282d381bf3d40b2be", "pan@321");//key值（用户名密码字符串的md5签名值）pan@321  pan@321
	}
	public static String SMS_URL="";
	public static String SMS_ACCOUNT="";
	public static String SMS_PASSWORD="";
	public static String COUPON_SMS_MESSAGE="";//
	public static int SMS_SEND_CYCLE=1;//优惠卷短信提醒发送周期(单位：天)
	public static void main(String args[]) {
		System.out.println(DigestUtils.md5Hex("linweih*387linweih*387"));
	}
}
