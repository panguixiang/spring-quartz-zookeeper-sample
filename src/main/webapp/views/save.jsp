<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>系统设置-用户管理</title>
<link rel="stylesheet" href="/css/style.css">
<script type="text/javascript" src="/js/jquery-2.0.0.min.js"></script>
</head>
<body>
<div class="bs-example bs-navbar-top-example" data-example-id="navbar-fixed-to-top">
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid w1250">
        <div class="navbar-header logo">
          <a href="#" ><img src="/images/easy_logo.png" width="258" height="43"></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-6">
          <ul class="nav navbar-nav">
          </ul>
          <div class="topright"></div>
        </div>
      </div>
    </nav>
  </div>
<div class="topmune">
	<ul>
		<li>
			<a href="/quartz/list" class="ative">定时任务管理</a>&nbsp;&nbsp;
			<a href="/quartz/data/init" >生产数据库操作</a>&nbsp;&nbsp;
			<a href="/quartz/zookeep/list">zookeeper配置管理</a>
		</li>
		<li><a href="/login/out" >退出系统</a>&nbsp;&nbsp;</li>
	</ul>
	</div>
<div class="hmaxbox">
        <ul class="nav nav-tabss">
            <li class="active"><a href="#demo01" data-toggle="tab">新增任务</a></li>
        </ul>
      <form action="/quartz/save" method="post">
        <div class="com_chanew">
        	<table align="left">
        		<tr height="60px"><td align="right">任务名称：</td><td><input type="text" name="jobName" value=""/></td><td>&nbsp;&nbsp;规则：英文或数字</td></tr>
        		<tr height="60px"><td align="right">触发器名称：</td><td><input tpe="text" name="triggerName" value="" /></td><td>&nbsp;&nbsp;规则：英文或数字</td></tr>
        		<tr height="60px"><td align="right">任务实现类全名：</td>
        		<td><input type="text" name="classPackage" value="" /></td>
        		<td>&nbsp;继承CronJob类的类全名&nbsp;例如:com.abc.job.SampleJob</td></tr>
        		<tr height="60px"><td align="right">备注：</td><td><input tpe="text" name="remarks" value="" /></td><td>&nbsp;&nbsp;规则：50个字符以内</td></tr>
        		<tr height="60px"><td align="right">执行规则：</td>
        		<td><input type="text" name="cron" value="" /></td>
        		<td><input type="button" value="查看规则" id="cronButtonId" size="10" />&nbsp;&nbsp;
        		<input type="submit" class="btn btn-primary" value="提交任务"/></td></tr>
        	</table>
        </div>
        <div class="btntitle">
            　　	<div id="dispDiv" style="display:none;color:#F00;">
		<table align="left">
			<tr><td align="left">0/5 * * * * ?   每5秒钟执行一次</td></tr>
			<tr><td align="left">0 0 12 * * ?	每天12点触发</td></tr>
			<tr><td align="left">0 15 10 ? * *	每天10点15分触发</td></tr>
			<tr><td align="left">0 15 10 * * ?	每天10点15分触发</td></tr>
			<tr><td align="left">0 15 10 * * ? *	每天10点15分触发</td></tr>
			<tr><td align="left">0 15 10 * * ? 2005	2005年每天10点15分触发</td></tr>
			<tr><td align="left">0 * 14 * * ?	每天下午的 2点到2点59分每分触发</td></tr>
			<tr><td align="left">0 0/5 14 * * ?	每天下午的 2点到2点59分(整点开始，每隔5分触发)</td></tr>
			<tr><td align="left">0 0/5 14,18 * * ?	每天下午的 2点到2点59分(整点开始，每隔5分触发)</td></tr>
			<tr><td align="left">每天下午的 18点到18点59分(整点开始，每隔5分触发)</td></tr>
			<tr><td align="left">0 0-5 14 * * ?	每天下午的 2点到2点05分每分触发</td></tr>
			<tr><td align="left">0 10,44 14 ? 3 WED	3月分每周三下午的 2点10分和2点44分触发</td></tr>
			<tr><td align="left">0 15 10 ? * MON-FRI	从周一到周五每天上午的10点15分触发</td></tr>
			<tr><td align="left">0 15 10 15 * ?	每月15号上午10点15分触发</td></tr>
			<tr><td align="left">0 15 10 L * ?	每月最后一天的10点15分触发</td></tr>
			<tr><td align="left">0 15 10 ? * 6L	每月最后一周的星期五的10点15分触发</td></tr>
			<tr><td align="left">0 15 10 ? * 6L 2002-2005	从2002年到2005年每月最后一周的星期五的10点15分触发</td></tr>
			<tr><td align="left">0 15 10 ? * 6#3	每月的第三周的星期五开始触发</td></tr>
			<tr><td align="left">0 0 12 1/5 * ?	每月的第一个中午开始每隔5天触发一次</td></tr>
			<tr><td align="left">0 11 11 11 11 ?	每年的11月11号 11点11分触发(光棍节)</td></tr>
		</table>
			</div>
        </div>
      </form>
      </br>
      
</div>

  </body>
  <script type="text/javascript">
	$(document).ready(function(){
		$("#cronButtonId").click(function(){
			if($("#dispDiv").css("display")=="none") {
				$("#dispDiv").css("display","");
				$("#cronButtonId").val("收起规则");
			} else {
				$("#dispDiv").css("display","none");
				$("#cronButtonId").val("查看规则"); 
			}
		});
	});
</script>
</html>