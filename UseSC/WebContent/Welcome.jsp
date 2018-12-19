<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <body>
        <div>
            尊敬的${sessionScope.id }，您好！
        </div>
        <div>
            <a href="/UseSC/Login.jsp">退出账户</a>
        </div>
    </body>
</html>
