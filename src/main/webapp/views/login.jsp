<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>系统设置-用户管理</title>
<link rel="stylesheet" href="/css/style.css">
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
<div class="hmaxbox">
      <form action="/login/login" method="post">
        <div class="com_chanew">
        	<table align="left">
        		<tr height="60px"><td align="right">登录账号：</td><td><input type="text" name="loginName" value=""/></td>
        		<td>&nbsp;&nbsp;规则：英文或数字</td></tr>
        		<tr height="60px"><td align="right">登录密码：</td><td><input tpe="password" name="password" value="" /></td>
        		<td>&nbsp;&nbsp;规则：英文或数字</td></tr>
        		<tr height="60px"><td><input type="submit" class="btn btn-primary" value="登录"/></td></tr>
        	</table>
        </div>
      </form>
</div>
  </body>
</html>