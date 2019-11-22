<%@page import="cn.entity.Project"%>
<%@page import="cn.service.GradeService"%>
<%@page import="cn.service.impl.GradeServiceimp"%>
<%@page import="cn.entity.Student"%>
<%@page import="java.util.List"%>
<%@page import="cn.service.ProService"%>
<%@page import="cn.service.impl.ProServiceimp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程信息</title>
<link href="<%=request.getContextPath()%>/css/common.css"
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
	<div align="right">
		欢迎你,${user.username} &nbsp;&nbsp;&nbsp;<a href="stuList.jsp">个人数据&nbsp;&nbsp;</a><a
			href="logout.jsp">退出</a>
	</div>
	<h2>学生列表</h2>
	<p id="addStu">
		<a href="addStu.jsp"><input type="button" value="添加学生"></a>
	</p>

	<table>
		<tr>
			<td class="listTable">编号</td>
			<td class="listTable">姓名</td>
			<td class="listTable">性别</td>
			<td class="listTable">年龄</td>
			<td class="listTable">年级</td>
			<td id="profile">自我介绍</td>
			<td id="operate">操作</td>
		</tr>
		<%
  session.removeAttribute("list");
ProService proService = new ProServiceimp();
  List<Project> list = proService.getPro();
  session.setAttribute("list", list);
  for(Project pro:list){


%>
		<tr>
			<td><%=pro.getproname() %></td>
			<td><%=pro.getranzhi() %></td>
			<td><%=pro.getprotime()%></td>
			<td><%=pro.getq1() %></td>
			<td><%=pro.getq2() %></td>
			<td><%=pro.getq3() %></td>
			<td><%=pro.getq4() %></td>
			<td><%=pro.getq5() %></td>
			<td><%=pro.getq6() %></td>
			<td><%=pro.getq7() %></td>
			<td><%=pro.getq8() %></td>
			<td><%=pro.getq9() %></td>
			<td><%=pro.getq10() %></td>
			<td><%=pro.getq11() %></td>
			<td><%=pro.getq12() %></td>



		</tr>
		<%} %>
	</table>
</body>
</html>