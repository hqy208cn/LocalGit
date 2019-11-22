<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <a href="showUser?id=2">ShowUser page</a><br>
    <a href="insertUser">insertUser </a><br>
    <a href=""id="a" >Delete page</a><br>
    <input type="text" id="input" value="" />
</body>
<script type="text/javascript">
    input.onkeyup=function(){
        a.setAttribute("href","deleteUser?id="+input.value); 
    }
</script>
</html>