<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
        <base href="<%=basePath%>">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
    <link href="css/index.css" rel="stylesheet" type="text/css">
        <title>智能跑带</title>
</head>
<body>
<ul id="nav">
    <li><a href="index.jsp">首页</a> </li>
    <li><a href="register.jsp">注册</a></li>
    <li><a href="login.jsp">登录</a></li>
    <li><a href="/student/pages/stuList.jsp">运动记录</a></li>
    <li><a href="/student/pages/stuList.jsp">排行榜</a></li>
</ul>
<h1 style="margin-top: 120px; text-align:center;">欢迎使用智能跑带系统</h1>
</body>
</html>