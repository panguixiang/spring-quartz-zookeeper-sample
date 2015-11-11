<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>系统设置-zookeeper配置管理</title>
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
      <form action="/quartz/zookeep/list" method="get" id="formIddd">
       	节点名称：<input type="text" name="nodeName" value="${nodeName}"/>&nbsp;&nbsp;格式:/a/b/c，必须‘/’符号开头
       	<input type="submit" value="submit" />
      </form>
      <a href="/quartz/zookeep/initAdd">新增节点</a>&nbsp;&nbsp;<span style="color:red;">特别注意，不要删除别人任何的节点数据，除非你明确知道你在干什么！</span>
       	<br/><br/>
       	<table border="1" class="gridtable" width="90%">
       		<tr class="tdb"><th>节点名称</th><th>节点数据</th><th colspan="3">操作</th></tr>
       		<c:forEach var="item" items="${zkNodesMap}"> 
       		<c:if test="${item.key!='/activemq'}">
       			<tr class="tdb">
       				<td width="15%"><a href="/quartz/zookeep/list?nodeName=${item.key}">${item.key}</a></td>
       				<td width="40%"><textarea rows="4" cols="60">${item.value}</textarea></td>
       				<td width="10%"><a href="/quartz/zookeep/initUpdate?nodeName=${item.key}">修改节点</a></td>
       				<td width="10%"><a href="/quartz/zookeep/initAdd?nodeName=${item.key}">新增子节点</a></td>
       				<td width="10%"><a href="javascript:;" onclick="deleteNode('${item.key}');">删除节点</a></td>
       			</tr>
       		</c:if>
			</c:forEach>
       	</table><br/><br/>
       	<span style="color: red;">${errorMsg}</span>
       	</div>
       	</div>
  </body>
  <script type="text/javascript">
  	function deleteNode(nodeName) {
  		if(confirm("将删除本节点及其所有子节点，请慎重！")) {
  			location.href="/quartz/zookeep/delete?nodeName="+nodeName;
  		}
  	}
  </script>
</html>