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
<div class="topmune">
	<ul>
		<li>
			<a href="/quartz/list" >定时任务管理</a>&nbsp;&nbsp;
			<a href="/quartz/data/init" class="ative">生产数据库操作</a>&nbsp;&nbsp;
			<a href="/quartz/zookeep/list">zookeeper配置管理</a>
		</li>
		<li><a href="/login/out" >退出系统</a>&nbsp;&nbsp;</li>
	</ul>
</div>
<div class="hmxbox">
<div class="homebox">
     <h1 style="color:red;">${message}</h1>
      <form action="" method="post" id="formIddd">
       	<textarea rows="30" cols="90%" name="sql">${sql}</textarea>
       	<input type="button" value="submit" id="buttonSubId"/>&nbsp;&nbsp;<span style=" color: red;">查询操作建议执行分页查询sql(生产数据可能会很多)。</span>
      </form><br/>
       	请选择操作类型：<select name="opt" id="optSelectId">
       		<option value="">--请选择操作类型--</option>
       		<option value="save">save</option>
       		<option value="update">update</option>
       		<option value="delete">delete</option>
       		<option value="select">select</option>
       	</select>
       	<br/><br/>
       	<table border="1" class="gridtable" width="400px">
       	<c:set var="indes" value="0"/>  
       	 <c:forEach items="${list}" var="map">
	       	 <c:choose>
	       	 	<c:when test="${indes==0}">
		       	 	<tr class="tdb">
		       	 		<c:forEach var="item" items="${map}"> 
							<th>${item.key}</th>
			       		</c:forEach>
			       	</tr>
			       	<tr class="tdb">
			       	<c:forEach var="item" items="${map}"> 
							<td>${item.value}</td>
			       		</c:forEach>
			       	</tr>
	       	 	</c:when>
	       	 	<c:otherwise>
	       	 		<tr class="tdb">
	       	 		<c:forEach var="item" items="${map}"> 
						<td>${item.value}</td>
		       		</c:forEach>
	       	 		</tr>
	       	 	</c:otherwise>
	       	 </c:choose>
	       	 <c:if test="${indes==0}">
	       	 	<c:set var="indes" value="1"/>
	       	 </c:if>
       	</c:forEach>
       	</table>
       	</div>
       	</div>
  </body>
  <script type="text/javascript">
	$(document).ready(function(){
		$("#buttonSubId").click(function(){
			var opt = $("#optSelectId").val();
			if(opt.length==0) {
				alert("请选择操作类型");
			} else {
				var url = "/quartz/data/"+opt;
				 $('#formIddd').attr("action", url).submit();;  
			}
		});
	});
</script>
</html>