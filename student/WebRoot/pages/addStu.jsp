<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加学生</title>
<link href="<%=request.getContextPath()%>/css/common.css"
	rel="stylesheet" type="text/css" />

<script type=text/javascript>
function check(){
	for(var i = 0;i<document.form1.elements.length-1;i++){
		if(document.form1.elements[i]==""){
			alert("当前表单不能有空项");
			document.form1.elements[i].focus();
			return false;
		}		
	}
	return true;
}
</script>

</head>
<body>
	<h2>学生信息</h2>
	<form
		action="<%=request.getContextPath()%>/servlet/addStudent?opr=addStu"
		name="form1" method="post" onsubmit="return check()">
		<table id="stuDetailList">
			<tr>
				<td>姓名</td>
				<td><input type="text" name="stuName"></td>
			</tr>
			<tr>
				<td>性别</td>
				<td><input type="radio" name="sex" value="男" checked="checked"><input
					type="radio" name="sex" value="女"></td>
			</tr>
			<tr>
				<td>年龄</td>
				<td><input type="text" name="stuAge">
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
				<td><textarea name="showProfile" cols="40" rows="6"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center"><input type="submit"
					value="提交信息"></td>
			</tr>
		</table>
	</form>
</body>
</html>