<%@page import="cn.service.impl.StudentServiceimp"%>
<%@page import="cn.service.StudentService"%>
<%@page import="cn.entity.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改学生信息</title>
<link href="<%=request.getContextPath()%>/css/common.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<h2>修改学生信息</h2>
	<form
		action="<%=request.getContextPath()%>/servlet/addStudent?opr=modifyStu"
		method="post">
		<table id="stuDetailList">
			<%
int id = Integer.parseInt(request.getParameter("id"));
StudentService studentService = new StudentServiceimp();
Student student = new Student();
student.setId(id);
//student = studentService.getStudentMoreInfo(student);

%>
			<tr>
				<td>姓名</td>
				<td><input type="text" name="stuName"
					value="<%=student.getName()%>"></td>
			</tr>
			<tr>
				<td>性别</td>
				<td><input type="radio" name="sex" value="男" checked="checked"><input
					type="radio" name="sex" value="女"></td>
			</tr>
			<tr>
				<td>年龄</td>
				<td><input type="text" name="stuAge"
					value="<%=student.getAge()%>">
			</tr>
			<tr>
				<td>年级</td>
				<td><select name="stuGrade">
						<option value="大一">大一</option>
						<option value="大二">大二</option>
						<option value="大三">大三</option>
						<option value="大四" selected="selected">大四</option>
				</select></td>
			</tr>
			<tr>
				<td>自我介绍</td>
				<td><textarea name="showProfile" cols="40" rows="6"><%=student.getProfile()%></textarea></td>
			</tr>
			<tr>
				<td><input type="hidden" name="stuId"
					value="<%=student.getId()%>"></td>
				<td colspan="2" style="text-align: center"><input type="submit"
					value="提交信息"></td>
			</tr>
		</table>
	</form>
</body>
</html>