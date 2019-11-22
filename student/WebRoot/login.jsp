<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户登陆</title>
    <link href="css/login.css"
          rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/login.js"></script>

</head>
<body  background="1.jpg">
<ul id="nav">
    <li><a href="index.jsp">首页</a> </li>
    <li><a href="register.jsp">注册</a></li>
    <li><a href="login.jsp">登录</a></li>
    <li><a href="/student/pages/stuList.jsp">运动记录</a></li>
    <li><a href="/student/pages/out.jsp">排行榜</a></li>
</ul>

<div id="myDiv">
<form action="/student/servlet/doLogin"
      onsubmit="return validate_form()" method="post" id="myForm">

        <div id="usernameDiv">

            <span class="s">用户名：</span>

            <input type="text" name="username" id="username" placeholder="请输入用户名" />


        </div>



        <div id="passwordDiv">

            <span class="s">密&nbsp;&nbsp;&nbsp;码：</span>

            <input type="password" name="password" id="password" placeholder="请输入密码" />


        </div>

        <div id="submitDiv">
            <input type=submit value="登录">
        </div>
<p>
        <span id="loginHit"> <%=session.getAttribute("msg")==null?"":session.getAttribute("msg") %>
            <% session.removeAttribute("msg"); %></span>
</p>
</form>
</div>
</body>
</html>