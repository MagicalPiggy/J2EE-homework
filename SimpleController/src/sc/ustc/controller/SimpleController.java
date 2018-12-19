package sc.ustc.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sc.ustc.tool.MyTools;

public class SimpleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);// 对于Get请求也按照Post请求来处理
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html,charset=utf-8");// 指定浏览器解码方式
		response.setCharacterEncoding("utf-8");// 设置response的编码
		request.setCharacterEncoding("utf-8");// 设置request的编码

		String actionName = request.getServletPath().toString();// 将请求的url转换为字符串进行处理
		System.out.println(actionName);
		String[] actionUrl = actionName.split("/");// 分割为数组，以'/'为分隔符
		actionName = actionUrl[actionUrl.length - 1];// 获取action的名称
		String path = this.getServletContext().getRealPath("WEB-INF/classes/controller.xml");// 获取controller.xml这个web资源的路径
		MyTools mt = new MyTools();// 实例化一个MyTool对象

		// 以下调用MyTool的readXml方法进行该xml文件的解析，返回Map结果
		Map<String, String> actionMap = mt.readXml(actionName.substring(0, actionName.indexOf(".")), path);
		
		//对结果进行判断
		if (!actionMap.isEmpty()) {//如果action存在

			String classN = actionMap.get("class");//取得类名

			String methodN = actionMap.get("method");//取得方法名

			//以下实现反射
			try {
				Class cl = Class.forName(classN);//获取与类名对应的类对象
				Method m = cl.getDeclaredMethod(methodN, HttpServletRequest.class, HttpServletResponse.class);//获取对应的带参方法
				String result = (String) m.invoke(cl.newInstance(), request, response);//根据传入的类实例，通过配置的实参调用对应的方法

				String resN = actionMap.get("result" + result);//根据请求结果查找此action中<result>结点的name属性
				String resT = resN.substring(0, resN.indexOf("-"));//获取type定义的方式
				String resV = resN.substring(resN.indexOf("-") + 1);//获取vaule指向的资源

				if (resT.equals("foward")) {//请求转发
					request.getRequestDispatcher(resV).forward(request, response);
				} else if (resT.equals("redirect")) {//重定向
					response.sendRedirect(resV);
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("/UseSC/Login.jsp");//如果action不存在，重定向到登录页面
		}
	}

}
