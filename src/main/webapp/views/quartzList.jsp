<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>系统设置-用户管理</title>
 <link rel="stylesheet" href="/css/style.css">
 <style type="text/css">
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
 </style>
 <script type="text/javascript" src="/js/jquery-2.0.0.min.js"></script>
</head>
<body>
<div class="bs-example bs-navbar-top-example" data-example-id="navbar-fixed-to-top">
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid w1250">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-6">
          <ul class="nav navbar-nav">
          </ul>
          <div class="topright"></div>
        </div><!-- /.navbar-collapse -->
      </div>
    </nav>
  </div>
<div class="topmune">
	<ul>
		<li>
			<a href="/quartz/list" class="ative">定时任务管理</a>&nbsp;&nbsp;
			<a href="/quartz/data/init">生产数据库操作</a>&nbsp;&nbsp;
			<a href="/quartz/zookeep/list">zookeeper配置管理</a>
		</li>
		<li><a href="/login/out" >退出系统</a>&nbsp;&nbsp;</li>
	</ul>
	</div>
<div class="hmaxbox">
<div class="homebox">
<ul class="nav nav-tabss"><li class="active"><a href="/quartz/list" data-toggle="tab">任务列表</a></li></ul>
<div class="com_chaform">
<ul>
<li><a href="/quartz/initSave" class="btn btn btn-danger"> 新 增 </a></li></ul>
</div>
</div></div>

<div class="hmxbox">
<div class="homebox">
<table width="100%" border="1" class="gridtable">
 <thead>
  <tr class="tdb">
    <th>序号</th>
    <th>任务名称</th>
    <th>执行规则</th>
    <th>类包全名</th>
    <th>备注</th>
    <th>操  作</th>
  </tr>
   </thead>
   <tbody>
   <c:forEach items="${quartzList}" var="job" varStatus="xh">
  	<tr class="tdc">
	    <td>${xh.count}</td>
		<td>${job.jobName}</td>
		<td>${job.cron}</td>
		<td>${job.classPackage}</td>
		<td>${job.remarks}</td>
		<td>
			<a href='/quartz/delete/${job.id}'>删除</a>
		</td>
  	</tr>
  </c:forEach>
  </tbody>
</table>
</div></div>
  </body>
</html>