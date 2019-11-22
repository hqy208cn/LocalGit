<%@page import="cn.service.impl.GradeServiceimp"%>
<%@page import="cn.service.GradeService"%>
<%@page import="cn.entity.deal"%>
<%@page import="cn.entity.Student"%>
<%@page import="cn.service.StudentService"%>
<%@page import="cn.service.impl.StudentServiceimp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>运动详细信息</title>
<link href="<%=request.getContextPath()%>/css/Detail.css"
	rel="stylesheet" type="text/css">
</head>
<body>
<ul id="nav">
    <li><a href="\student\index.jsp">首页</a> </li>
    <li><a href="\student\register.jsp">注册</a></li>
    <li><a href="\student\login.jsp">登录</a></li>
    <li><a href="stuList.jsp">运动记录</a></li>
    <li><a href="out.jsp">排行榜</a></li>
</ul>
<div id="aa" style="margin-top: 120px;">
<h2>运动详细信息</h2>
<%

session.removeAttribute("deal");
int id = Integer.parseInt(request.getParameter("id"));
StudentService stuService = new StudentServiceimp();
deal de =new deal();
de.setId(id);
de = stuService.getDealMoreInfo(de);
session.setAttribute("deal", de);
%>
<table id="stuDetailList">
    <tr>
        <td>用户名</td>
        <td><%=de.getname()%></td>
    </tr>
    <tr>
        <td>运动项目</td>
        <td><%=de.getproject()%></td>
    </tr>
    <tr>
        <td>平均心率</td>
        <td><%=de.getheart()%></td>
    </tr>
    <tr>
        <td>最大心率</td>
        <td><%=de.getmaxheart()%></td>
    </tr>
    <tr>
        <td>最小心率</td>
        <td><%=de.getlimheart()%></td>
    <tr>
        <td>运动处方</td>
        <td><%=de.getdeal()%></td>
    </tr>
    <tr>
        <td>消耗卡路里</td>
        <td><%=de.getcal()%></td>
    </tr>
    <tr>
        <td>时间</td>
        <td><%=de.gettime()%></td>
    </tr>
</table></div>
</body>
</html>