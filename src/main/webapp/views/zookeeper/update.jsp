<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="/css/style.css">
 <title>系统设置-zookeeper配置管理</title>
</head>
<body>
<div class="topmune">
	<ul>
		<li>
			<a href="/quartz/list" >定时任务管理</a>&nbsp;&nbsp;
			<a href="/quartz/data/init">生产数据库操作</a>&nbsp;&nbsp;
			<a href="/quartz/zookeep/list" class="ative">zookeeper配置管理</a>
		</li>
		<li><a href="/login/out" >退出系统</a>&nbsp;&nbsp;</li>
	</ul>
</div>
<div class="hmxbox">
<div class="homebox">
     <h1 style="color:red;">${message}</h1>
      <form action="/quartz/zookeep/update" method="post">
       	<input type="hidden" name="nodeName" value="${nodeName}"/>
                 节点名称：<input type="text" value="${nodeName}" />&nbsp;&nbsp;格式:/a/b/c，必须‘/’符号开头<br/><br/>
                 节点数据： <textarea rows="4" cols="60" name="nodeValue">${nodeValue}</textarea><br/><br/>
                 子节点描述： <input type="text" name="nodeDesc" value="${nodeDesc}"/>
       	<input type="submit" value="submit" />
      </form><br/><br/>
       	<span style="color: red;">${errorMsg}</span>
       	</div>
       	</div>
  </body>
</html>