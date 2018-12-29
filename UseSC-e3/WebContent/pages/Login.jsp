

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>please login!</title>
</head>
<body>
	<div>
		<h3>请登录：</h3>
		<br>
		<form action="login.sc" method="post">
			<input type="text" name="userName" placeholder="请输入用户名" /> <input
				type="text" name="password" placeholder="请输入密码" /> <input
				type="submit" value="登录">
		</form>
	</div>
	<div>
                如果您还不是我们的用户，请<a href="pages/Register.jsp">点击注册</a>
            </div>
</body>
</html>
