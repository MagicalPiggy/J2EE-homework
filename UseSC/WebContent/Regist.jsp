<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
    <form action="regist.sc" name="loginForm" accept-charset="utf-8">
        <div>账&nbsp&nbsp号：<input type="text" name="id" /></div>
        <div>密&nbsp&nbsp码：<input type="text" name="password" /></div>
        <div><input type="submit" name="regist" value="注册" /></div>
    </form>
    <div>
        <font color="red">${sessionScope.registMessage }</font> 
    </div>
    <%
        session.removeAttribute("registMessage");
    %>
  </body>
</html>
