/**
 */
package com.shengbo.quartz.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

public class StringUtil {

	public static String strFirstCut(String user_acct, String string) {
		if(StringUtil.isEmpty(user_acct)||StringUtil.isEmpty(string))return user_acct;
		if(user_acct.indexOf(string)==0)user_acct = user_acct.substring(string.length(),user_acct.length());
		return user_acct;
	}
	
	public static String addQuotationMarks(String str) {
		return "'"+str.replace(",", "','")+"'";
	}
	
	/**替换json格式中的节点字母
	 * @param xml
	 * @return
	 */
	public static String upZMSG(String xml) {
		if(StringUtils.isEmpty(xml))return xml;
		return xml.replaceAll("zMSG", "ZMSG").replaceAll("zHEAD", "ZHEAD").replaceAll("zBODY", "ZBODY");
	}
	
	/**
	 * 数组判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInStr(String[] strs ,String str) {
		if(StringUtils.isEmpty(str))return false;
		for (String string : strs) {
			if(string.equals(str))return true;
		}
		return false;
	}
	/**
	 * 数组判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInStr(List<String> strs ,String str) {
		if(StringUtils.isEmpty(str))return false;
		if(null==strs)return false;
		for (String string : strs) {
			if(string.equals(str))return true;
		}
		return false;
	}
	/**
	 * 传入字符串去除左右框
	 * 
	 * @param str
	 * @return
	 */
	public static String[] replaceStr(String str) {
		return str.replace("[", "").replace("]", "").split(",");
	}

	/**
	 * 判断对象是否空转字符串
	 * 
	 * @param o
	 * @return
	 */
	public static String isEmptyToString(Object o) {
		if (isEmpty(o)) {
			return "无";
		} else {
			return o.toString();
		}
	}

	/**
	 * 剔除字符串中的换行回车，主要用于JSON
	 * 
	 * @param str
	 * @return
	 */
	public static String getStringNoBlank(String str) {
		if (str != null && !"".equals(str)) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			String strNoBlank = m.replaceAll("");
			return strNoBlank;
		} else {
			return str;
		}
	}

	/**
	 * 
	 * 说明： 根据url 获取指定位置的参数
	 * 
	 * @param url
	 *            字符串url
	 * @param num
	 *            指定的位置
	 * @return 参数字符串
	 */
	public static String getParamter(String url, int num) {

		String[] str = url.replaceAll("http://", "").split("/");
		if (str != null && str.length >= num)
			return str[num - 1];
		else
			return "";
	}

	/**
	 * @explanation 获取参数并转换字符串编码
	 * @param req
	 *            str
	 * @return
	 * @throws UnsupportedEncodingException
	 * @Date 2010-1-15
	 * @author 刘贝
	 */
	public static String getParamEncode(HttpServletRequest req, String str)
			throws UnsupportedEncodingException {
		String param = req.getParameter(str);
		if (param != null && !"".equals(param)) {
			return new String(param.getBytes("ISO-8859-1"), "UTF-8");
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 说明：获取url末尾的参数值
	 * 
	 * @param url
	 *            网站url
	 * @return 返回url最后的一个参数字符串 否则返回空字符串
	 */
	public static String getLastParameter(String url) {
		String str = url.replaceAll("\\\\", "/");
		int i = str.lastIndexOf('/');

		if (i == -1)
			return "";
		return str.substring(i + 1).trim().replaceAll("\\..*", "");
	}

	/**
	 * 
	 * 说明：判断字符串是否是整数和0
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @return 是整数\0返回true，否则返回false
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("^-?[\\d]+");
		if (str == null || str.equals(""))
			return false;
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * 说明：判断字符串中是否包含中文
	 * 
	 * @param str
	 *            需要验证的字符串
	 * @return 包含汉字返回true，否则返回false
	 */
	public static boolean isIncludeChinese(String str) {
		if (str == null || str.trim().equals(""))
			return false;

		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher matcher = p.matcher(str);

		return matcher.find();

	}

	// 分页
	public static String pageshow(int curPage, int totalPages, String url) {
		int s = (int) (Math.floor((float) curPage / 10) * 10);
		int s2 = (int) Math.ceil((float) curPage / 10) * 10;
		int s3 = (int) Math.ceil((float) (s2 - 10));
		int s4 = (int) Math.ceil((float) (s2 + 1));
		int s5 = (s2 > totalPages) ? totalPages - s : 10;
		if (s5 == 0)
			s5 = 10;
		if (totalPages < 10)
			s5 = totalPages;
		if (s4 > totalPages)
			s4 = totalPages;
		StringBuffer sb = new StringBuffer("");
		if (s2 > 10) {
			sb.append("<a href='" + url
					+ "1' title='首页' class=\"number\">&lt;&lt;</a>");
			sb.append("<a href='" + url + s3
					+ "' title='上10页' class=\"number\">&lt;</a>");
		}

		for (int i = 0; i < s5; i++) {

			int x = i + s2 + 1 - 10;
			String link = "";
			if (curPage == x) {
				link = "<a href='" + url + x + "' title='第" + x
						+ "页' class=\"before_info\">" + x + "</a>";
			} else {
				link = "<a href='" + url + x + "' title='第" + x
						+ "页' class=\"number\">" + x + "</a>";
			}

			sb.append("" + link + "");
		}
		if (s2 < totalPages) {
			sb.append("<a href='" + url + s4
					+ "' title='下10页' class=\"number\">&gt</a>");
			sb.append("<a href='" + url + totalPages
					+ "' title='尾页' class=\"number\">&gt;&gt;</a>");
		}
		sb.append("<span>" + curPage + "/" + totalPages + "页</span>");
		return sb.toString();

	}

	// 判断字符串是否为空
	public static boolean isEmpty(String str) {
		return str == null || str.equals("");
	}

	public static boolean isEmpty(Object o) {
		return o == null;
	}

	public static String getString(String str) {
		if (isEmpty(str)) {
			return "";
		} else {
			return str;
		}
	}

	/**
	 * 
	 * 说明：转换html代码
	 * 
	 * @param str
	 *            需要进行转换的字符串
	 * @return 返回转换后的字符串
	 */
	public static String htmlEncode(String str) {
		if (str == null || "".equals(str))
			return "";

		str = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		return str;
	}

	public static String htmlDecode(String str) {
		if (str == null || "".equals(str))
			return "";

		str = str.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
		return str;
	}

	/**
	 * 对字符串当中的JS、IFRAME代码 进行过滤,全部替换为""
	 * 
	 * @param str
	 *            要过滤的字符串
	 * @return 过滤后的字符串
	 */
	public static String doFilterJS(String str) {
		if (str == null || "".equals(str))
			return "";
		str = Pattern
				.compile("<script.*?>.*?</script>", Pattern.CASE_INSENSITIVE)
				.matcher(str).replaceAll("");
		str = Pattern
				.compile("<iframe.*?>.*?</iframe>", Pattern.CASE_INSENSITIVE)
				.matcher(str).replaceAll("");

		str = Pattern.compile("<meta.*?>", Pattern.CASE_INSENSITIVE)
				.matcher(str).replaceAll("");
		str = Pattern.compile("<link.*?>", Pattern.CASE_INSENSITIVE)
				.matcher(str).replaceAll("");
		str = Pattern.compile("<!.*?>", Pattern.CASE_INSENSITIVE).matcher(str)
				.replaceAll("");

		return str;
	}

	/**
	 * 删除input字符串中的html格式
	 * 
	 * @param input
	 * @param length
	 * @return
	 */
	public static String filterString(String input) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
				"<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");

		return str;
	}

	// 将str中重复的去掉
	public static String strDistinct(String str) {
		String[] strArr = str.split(",");
		String strAim = ",";
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i].equals(""))
				continue;
			if (strAim.indexOf("," + strArr[i] + ",") == -1) {
				strAim = strAim + strArr[i] + ",";
			}
		}
		if (!strAim.equals(","))
			strAim = strAim.substring(1, strAim.length() - 1);
		else
			strAim = "";
		return strAim;
	}

	/**
	 * 判断参数是否包含在数组内
	 * 
	 * @param ps
	 * @param array
	 * @return
	 */
	public static boolean in_array(String ps, String[] array) {
		boolean rs = false;
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(ps)) {
				rs = true;
				continue;
			}
		}
		return rs;
	}

	/**
	 * 
	 * 说明：删除字符串中相同的字符
	 * 
	 * @param s
	 *            以逗号分隔的原字符串
	 * @param same
	 *            需要被删除的字符串
	 * @return
	 */
	public static String removeSame(String s, String same) {

		if (s == null || "".equals(s))
			return "";
		if (same == null || "".equals(same))
			return s;

		String[] ss = s.split(",");
		String returnStr = "";
		for (int i = 0; i < ss.length; i++) {
			if (!ss[i].equals(same))
				returnStr += "," + ss[i];
		}
		if (!"".equals(returnStr))
			returnStr = returnStr.substring(1, returnStr.length());

		return returnStr;
	}

	// 打印结果
	public static String showResult(String result, String menu) {
		String s = "<table width='80%' border='0' align='center' cellpadding='0' cellspacing='1'  class='border' style='margin-top:15px;'>";
		s += "<tr height='22' class='tdbg' >";
		s += "<td height='106' align='center' valign='middle'>";
		s += "<div style='width:75%; text-align:left; margin-top:15px; margin-bottom:15px; '>"
				+ result;
		s += "</div>   " + menu + "<br /><br />";
		s += "</td>  </tr></table>";

		return s;
	}

	// 比较字符串
	public static boolean compareList(String par, String args) {
		String[] b = args.split(",");
		boolean s = false;
		if (b.length == 0) {
			s = false;
		} else {
			for (int i = 0; i < b.length; i++) {
				if (par.equals(b[i])) {
					s = true;
					break;
				}
			}
		}
		return s;
	}

	public static String URLEncoderStr(String str) {
		String returnStr = null;
		try {
			if (str != null && !str.equals("")) {
				returnStr = java.net.URLEncoder.encode(str, "UTF-8");
			} else {
				returnStr = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnStr;
	}

	// 获得菜单树结构

	public static String URLDecoderStr(String str) {
		String returnStr = null;
		try {
			returnStr = java.net.URLDecoder.decode(str, "UTF-8");
		} catch (Exception e) {

			e.printStackTrace();
		}
		return returnStr;
	}

	public static String getChinaCharacter(String str) {
		String returnStr = null;
		if (str != null) {
			try {
				returnStr = new String(str.getBytes("ISO-8859-1"), "UTF-8");
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return returnStr;
	}

	public static String strNullCheck(Object str) {
		String returnStr = null;
		if (str != null) {
			returnStr = (String) str;
		} else {
			returnStr = "";
		}
		return returnStr;
	}

	// 分页
	public static String pageshow(int curPage, int totalPages, String url,
			String key) {
		int s = (int) (Math.floor((float) curPage / 10) * 10);
		int s2 = (int) Math.ceil((float) curPage / 10) * 10;
		int s3 = (int) Math.ceil((float) (s2 - 10));
		int s4 = (int) Math.ceil((float) (s2 + 1));
		int s5 = (s2 > totalPages) ? totalPages - s : 10;
		if (s5 == 0)
			s5 = 10;
		if (totalPages < 10)
			s5 = totalPages;
		if (s4 > totalPages)
			s4 = totalPages;
		StringBuffer sb = new StringBuffer("");
		if (s2 > 10) {
			sb.append("<a href='" + url
					+ "1' title='首页' class=\"number\">&lt;&lt;</a>");
			sb.append("<a href='" + url + s3 + key
					+ "' title='上10页' class=\"number\">&lt;</a>");
		}

		for (int i = 0; i < s5; i++) {

			int x = i + s2 + 1 - 10;
			String link = "";
			if (curPage == x) {
				link = "<a href='" + url + x + key + "' title='第" + x
						+ "页' class=\"number\"><b style=\"color:#FF9933\">" + x
						+ "</b></a>";
			} else {
				link = "<a href='" + url + x + key + "' title='第" + x
						+ "页' class=\"number\">" + x + "</a>";
			}

			sb.append("" + link + "");
		}
		if (s2 < totalPages) {
			sb.append("<a href='" + url + s4 + key
					+ "' title='下10页' class=\"number\">&gt</a>");
			sb.append("<a href='" + url + totalPages + key
					+ "' title='尾页' class=\"number\">&gt;&gt;</a>");
		}
		sb.append(curPage + "/" + totalPages + "页");
		return sb.toString();

	}

	/**
	 * 数组转换为字符串
	 * 
	 * @param strings
	 * @return
	 */
	public static String arrayToString(String[] strings) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strings.length; i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(strings[i]);
		}
		return sb.toString();
	}

	// 添加字符串到数组
	public static String[] addArray(String args[], String ps) {
		String results[] = new String[args.length + 1];
		System.arraycopy(args, 0, results, 0, args.length);
		results[args.length] = ps;
		return results;
	}

	// 添加INTEGER到数组
	public static Integer[] addArray(Integer args[], Integer ps) {
		Integer results[] = new Integer[args.length + 1];
		System.arraycopy(args, 0, results, 0, args.length);
		results[args.length] = ps;
		return results;
	}

	// 添加任意类型数据到数组
	public static Object[] addArray(Object args[], Object ps) {
		Object results[] = new Object[args.length + 1];
		System.arraycopy(args, 0, results, 0, args.length);
		results[args.length] = ps;
		return results;
	}

	// 添加任意类型数据到数组
	public static Object[] addArray(int index, Object args[], Object ps) {
		if (isEmpty(args)) {
			return null;
		}
		Object results[] = new Object[args.length + 1];
		for (int i = 0, j = 0; i < results.length; i++) {
			if (index != i) {
				results[i] = args[j];
				j++;
			} else {
				results[i] = ps;
			}
		}
		return results;
	}

	// 添加任意类型数据到数组
	public static Object[] getArray(Object args[], Object ps) {
		Object results[] = new Object[args.length + 1];
		System.arraycopy(args, 0, results, 0, args.length);
		results[args.length] = ps;
		return results;
	}

	/**
	 * 判断时间的正确性
	 */
	public static boolean checkTime(String str) {
		if (!isEmpty(str)) {
			String[] s = str.split(":");
			if (s.length > 0) {
				// 小时不可以大于24
				if (Integer.parseInt(s[0]) < 24) {
					return true;
				}
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * 创建下拉框年份与月份
	 * 
	 * @author LiCheng
	 * @create 2011-3-11 下午01:47:23
	 * @since
	 * @param yearSelectID
	 * @param monthSelectID
	 * @return
	 */
	public static String createSelect(int year, int month) {

		if (year == 0 || month == 0) {
			Calendar calendar = Calendar.getInstance();
			// 获取当前年份 当前月份
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH) + 1;
		}

		String yselect = "<select id=\"year\">";
		for (int i = year - 5; i < year + 6; i++) {
			if (i == year) {
				yselect += "<option value=\"" + i + "\" selected>" + i
						+ "年</option>";
				continue;
			}
			yselect += "<option value=\"" + i + "\">" + i + "年</option>";
		}
		yselect += "</select>";

		String mselect = "<select id=\"month\">";
		for (int i = 1; i < 13; i++) {
			if (i == month) {
				mselect += "<option value=\"" + i + "\" selected>" + i
						+ "月</option>";
				continue;
			}
			mselect += "<option value=\"" + i + "\">" + i + "月</option>";
		}
		mselect += "</select>";

		return yselect + mselect;
	}

	/**
	 * 创建下拉框
	 * 
	 * @author LiCheng
	 * @create 2011-3-11 下午02:57:47
	 * @since
	 * @param identify
	 * @return
	 */
	public static String createSelect(String selectID, int num) {
		String select = "<select id=\"" + selectID + "\">";
		for (int i = 1; i < 4; i++) {
			if (i == num) {
				select += "<option value=\"" + i + "\" selected>"
						+ getIdentify(i) + "</option>";
				continue;
			}
			select += "<option value=\"" + i + "\">" + getIdentify(i)
					+ "</option>";
		}
		select += "</select>";
		return select;
	}

	/**
	 * 创建文本框
	 * 
	 * @author LiCheng
	 * @create 2011-3-22 下午02:35:58
	 * @since
	 * @param str
	 * @return
	 */
	public static String createInput(String str) {
		String[] s = str.split("-");
		if (s.length == 1) {
			return str
					+ " <img src='/images/icons/clock_end.gif' style='height: 12px;cursor: pointer;' onclick='doClick(this)' />";
		}
		if (s.length == 2) {
			if ("".equals(s[0].trim())) {
				return "<img src='/images/icons/clock_start.gif' style='height: 12px;cursor: pointer;' onclick='doClick(this)' /> - ";
			}
			if ("".equals(s[1].trim())) {
				return str
						+ " <img src='/images/icons/clock_end.gif' style='height: 12px;cursor: pointer;' onclick='doClick(this)' />";
			}
		}
		return str;
	}

	public static String getIdentify(int identify) {
		String s = "";
		switch (identify) {
		case 1:
			s = "工作日";
			break;
		case 2:
			s = "休假";
			break;
		case 3:
			s = "工作日(半天)";
			break;
		}
		return s;
	}

	public static boolean isNull(Object object) {
		return object == null;
	}

	public static Object null2String(Object object) {
		if (isNull(object)) {
			return "";
		}
		return object.toString();
	}

	// 判断是否为Integer
	public static boolean isInteger(String str) {
		if (isEmpty(str))
			return false;
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean isInteger(Object str) {
		if ((str == null) || (str.equals("")))
			return false;
		try {
			Integer.parseInt(str.toString());
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	// 判断是否为Double
	public static boolean isDouble(Object str) {
		if ((str == null) || (str.equals("")))
			return false;
		try {
			Double.parseDouble(str.toString());
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	// 判断是否为Double
	public static boolean isDouble(String str) {
		if ((str == null) || (str.equals("")))
			return false;
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	// 判断是否为Float
	public static boolean isFloat(String str) {
		if (StringUtil.isEmpty(str))return false;
		try {
			Float.parseFloat(str);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	// 判断是否为字母
	public static boolean isLetter(String str) {
		return str.matches("^[a-zA-Z]*");
	}

	// 自动补位
	public static String g(int fl, String p) {
		String res = "";
		for (int i = 0; i < fl - p.length(); i++) {
			res += "0";
		}
		return res + p;
	}

	// 获取流水单号
	public static String getRandCode() {
		long l = System.currentTimeMillis();
		Random rand = new Random();
		long r = rand.nextInt(9999999);
		int s = (int) (l / r);
		String b = StringUtil.gl(8, s + "");
		return b;
	}

	// 随机自动补位
	public static String gl(int fl, String p) {
		String res = "";
		Random rand = new Random();

		for (int i = 0; i < fl - p.length(); i++) {
			long r = rand.nextInt(9);
			res += r;
		}
		return res + p;
	}

	public static String getRandLetter() {
		Random r = new Random();
		String code = "";
		int s = Math.abs(Long.valueOf((System.currentTimeMillis() / 9))
				.intValue());
		if (s == 0)
			s = 1;
		int temp = r.nextInt(s);
		char x = (char) (temp < 26 ? temp + 97 : (temp % 26) + 65);
		code = StringUtil.valueOf(x).toUpperCase();
		return code;

	}

	/**
	 * HttpServletRequest 传参不能为空效验
	 * 
	 * @author bin ko
	 * @create 2011-8-17 下午12:54:36
	 * @since
	 * @param req
	 * @param parName
	 * @param rs
	 * @return
	 */
	public static String parameterValidator(HttpServletRequest req,
			String[] parName, String[] rs) {

		String rets = "";
		boolean bo = false;
		for (Enumeration<?> e = req.getParameterNames(); e.hasMoreElements();) {
			String name = e.nextElement().toString();
			for (int i = 0; i < parName.length; i++) {
				if (name.equals(parName[i])) {
					bo = isEmpty(req.getParameter(parName[i]));

					if (bo) {
						rets = "ERROR MSG:[" + rs[i] + "]不能为空！";
						break;
					}
				}
			}
			if (bo)
				break;
			;

		}
		return rets;
	}

	/**
	 * 反转数组字符串
	 * 
	 * @author bin ko
	 * @create 2012-4-17 下午2:31:22
	 * @since
	 * @param arrays
	 * @param qz
	 * @return
	 */
	public static String inversionArray(String arrays, String qz) {
		String fd[] = arrays.split(qz);
		String rs = "";
		for (int i = fd.length; i > 0; i--) {
			rs += fd[i - 1] + qz;
		}
		return rs;
	}

	public static String inversionArray(String arrays, String qz, int ks) {
		String fd[] = arrays.split(qz);
		String rs = "";
		for (int i = fd.length; i > 0; i--) {
			if (i != fd.length) {
				rs += fd[i - 1] + qz;
			}
		}
		return rs;
	}

	/**
	 * 重组数组字符串
	 * 
	 * @author bin ko
	 * @create 2012-4-17 下午2:31:22
	 * @since
	 * @param arrays
	 * @param qz
	 * @return
	 */
	public static String reArray(String arrays, String qz, String m) {
		String fg[] = arrays.split(qz);
		String did = "";
		for (int i = 1; i < fg.length; i++) {
			did += "'" + fg[i] + "'" + ((i < fg.length - 1) ? m : "");
		}
		return did;
	}

	public static String entKey(String s, int b) {
		String r = "9IJHF1V7ZPWS2XBYGQAC4RL6ONU8TKME3D5";
		int m1 = r.indexOf(s.substring(1, 2)) + 1;
		int m2 = r.indexOf(s.substring(2, 3)) + 1;

		int m3 = (b - m1 - m2) % 35;
		String s1 = s.substring(3, s.length() - 3);
		int slen = s1.length();
		String rs = "";
		for (int i = 0; i < slen - 1; i++) {
			char k = s1.charAt(i);
			if (k > 127) {
				rs += k + "";
			} else {
				int d1 = (r.indexOf(s1.charAt(i) + "")) * 35
						+ (r.indexOf(s1.charAt(i + 1) + ""));
				if ((d1 - m3) > 45 && (d1 - m3) < 58) {
					char c1 = (char) (d1 - m3);
					rs += c1 + "";
				}
			}
		}

		return rs;
	}

	/**
	 * 从老系统获取KEY，登录者信息
	 * 
	 * @author bin ko
	 * @create 2012-4-20 下午7:34:58
	 * @since
	 * @param s
	 * @param b
	 * @return
	 */
	public static String getKey(String s, int b) {
		String r = "9IJHF1V7ZPWS2XBYGQAC4RL6ONU8TKME3D5";
		int m1 = r.indexOf(s.substring(1, 2)) + 1;
		int m2 = r.indexOf(s.substring(2, 3)) + 1;
		int m3 = (b - m1 - m2) % 35;
		String s1 = s.substring(3, s.length() - 3);
		int slen = s1.length();
		String rs = "";
		for (int i = 0; i < slen - 1; i++) {
			char k = s1.charAt(i);
			if (k > 127) {
				rs += k + "";
			} else {
				int d1 = (r.indexOf(s1.charAt(i) + "")) * 35
						+ (r.indexOf(s1.charAt(i + 1) + ""));
				if ((d1 - m3) > 45 && (d1 - m3) < 58) {
					char c1 = (char) (d1 - m3);
					rs += c1 + "";
				}
			}
		}

		return rs;
	}

	/**
	 * @param string 判断字符串是否合法
	 * @return
	 */
	public static boolean isJson(String str) {
		if(isEmpty(str))return false;
		try {
			JSONObject.fromObject(str);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**是否为email格式
	 * @param 
	 * @return
	 */
	public static boolean isEmail(String str) {
		if(isEmpty(str))return false;
		try {
			return Pattern.compile("^\\w+@\\w+\\.(com|cn|net|org)").matcher(str).matches();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**是否为mobile格式
	 * @param 
	 * @return
	 */
	public static boolean isMobile(String str) {
		if(isEmpty(str))return false;
		try {
			return Pattern.compile("1\\d{10}").matcher(str).matches();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**该数据格式以 fcode  与scode  值对进行字符串提取
	 * @param str
	 * @param fcode
	 * @param scode
	 * @return
	 */
	public static List<String> getBetween(String str,String fcode,String scode){
		String sb = new String(str);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 1000; i++) {
			int start = sb.indexOf(fcode);
			int end = sb.indexOf(scode);
			if(start>=0&&end>start){
				list.add(sb.substring(start+fcode.length(), end));
				sb = sb.substring(end+scode.length());
			}
			else break;
		}
		return list;
	}
	
	/**该数据格式以 fcode  与scode  值对进行字符串提取
	 * @param str
	 * @param fcode
	 * @param scode
	 * @return
	 */
	public static String getBetweenStr(String str,String fcode,String scode){
		StringBuffer sb = new StringBuffer(str);
		int start = sb.indexOf(fcode);
		int end = sb.indexOf(scode);
		if(start>=0&&end>start)return sb.substring(start+fcode.length(), end);
		return null;
	}
	/**该数据格式以 fcode  与scode  值对进行字符串提取
	 * @param str
	 * @param fcode
	 * @param scode
	 * @return
	 */
	public static String getBetweenStr(StringBuffer sb,String fcode,String scode){
		int start = sb.indexOf(fcode);
		int end = sb.indexOf(scode);
		if(start>=0&&end>start)return sb.substring(start+fcode.length(), end);
		return null;
	}
	
	/**特定场景  按照参数标签提取数值
	 * @param str
	 * @param class1
	 * @return
	 */
	public static Map<String, String> getBetweenStrToMap(String str,
			Class<?> class1) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if (null == class1) {
				return map;
			}
			Field[] f = class1.getDeclaredFields();
			for (Field field : f) {
				field.setAccessible(true); // 将private里面的参数 设为可获取
				String fieldName = field.getName();
				StringBuffer sb = new StringBuffer(str);
				String value = getBetweenStr(sb, "<"+fieldName+">", "</"+fieldName+">");
				if(StringUtils.isEmpty(value))continue;
				map.put(fieldName, value);
			}
			return map;
		} catch (Exception e) {
			return map;
		}
	}
	
	public static String valueOf(Object object) {
		return (object == null) ? "" : object.toString();
	}

	public static int parseInt(Object object) {
		int i = 0;
        if (object != null) {
            try {
                i = Integer.parseInt(valueOf(object));
            } catch (NumberFormatException e) {
                i = 0;
            }
        }
        return i;
	}
}
