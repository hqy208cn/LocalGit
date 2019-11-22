<%@page import="cn.entity.deal"%>
<%@page import="cn.entity.User"%>
<%@page import="cn.service.GradeService"%>
<%@page import="cn.service.impl.GradeServiceimp"%>
<%@page import="cn.entity.Student"%>
<%@page import="java.util.List"%>
<%@page import="cn.service.StudentService"%>
<%@page import="cn.service.impl.StudentServiceimp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link href="<%=request.getContextPath()%>/css/List.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript">

  function check(){
	 var mymessage = confirm("确认要删除吗？");
	 if (mymessage = true){
		 return true;
	 }
     if (mymessage = false){
         return false;
     }	 	 
  }
</script>
</head>
<body>
<ul id="nav">
    <li><a href="\student\index.jsp">首页</a> </li>
    <li><a href="\student\register.jsp">注册</a></li>
    <li><a href="\student\login.jsp">登录</a></li>
    <li><a href="stuList.jsp">运动记录</a></li>
    <li><a href="out.jsp">排行榜</a></li>
</ul>
<div align="right" style="margin-top: 120px;">
    <p id="dd">欢迎你,${user.username}</p>
  <a href="logout.jsp" id="cc">退出</a>
</div>
<div id="ee">
<h2>排行榜</h2>
<!-- <p id = "addStu"><a href = "addStu.jsp"><input type = "button" value = "添加学生"></a></p> -->

<table>
    <tr>
    <td class="listTable">排名</td>
        <td class="listTable">用户名</td>
        <td class="listTable">消耗卡路里总计</td>
    </tr>
    <%
    session.removeAttribute("list");
    StudentService stuService = new StudentServiceimp();
    List<deal> list = stuService.getPaihang();
    session.setAttribute("list", list);
    int i =1;
    for(deal de:list){
    %>
    <tr>
        <td><%=i%></td>
        <td><%=de.getname() %></td>
        <td><%=de.getcal() %></td>     
    </tr>    
    <%i++;} %>
</table>
</div>
</body>
</html>