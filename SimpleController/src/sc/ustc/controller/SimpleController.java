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
		doPost(request, response);// ����Get����Ҳ����Post����������
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html,charset=utf-8");// ָ����������뷽ʽ
		response.setCharacterEncoding("utf-8");// ����response�ı���
		request.setCharacterEncoding("utf-8");// ����request�ı���

		String actionName = request.getServletPath().toString();// �������urlת��Ϊ�ַ������д���
		System.out.println(actionName);
		String[] actionUrl = actionName.split("/");// �ָ�Ϊ���飬��'/'Ϊ�ָ���
		actionName = actionUrl[actionUrl.length - 1];// ��ȡaction������
		String path = this.getServletContext().getRealPath("WEB-INF/classes/controller.xml");// ��ȡcontroller.xml���web��Դ��·��
		MyTools mt = new MyTools();// ʵ����һ��MyTool����

		// ���µ���MyTool��readXml�������и�xml�ļ��Ľ���������Map���
		Map<String, String> actionMap = mt.readXml(actionName.substring(0, actionName.indexOf(".")), path);
		
		//�Խ�������ж�
		if (!actionMap.isEmpty()) {//���action����

			String classN = actionMap.get("class");//ȡ������

			String methodN = actionMap.get("method");//ȡ�÷�����

			//����ʵ�ַ���
			try {
				Class cl = Class.forName(classN);//��ȡ��������Ӧ�������
				Method m = cl.getDeclaredMethod(methodN, HttpServletRequest.class, HttpServletResponse.class);//��ȡ��Ӧ�Ĵ��η���
				String result = (String) m.invoke(cl.newInstance(), request, response);//���ݴ������ʵ����ͨ�����õ�ʵ�ε��ö�Ӧ�ķ���

				String resN = actionMap.get("result" + result);//�������������Ҵ�action��<result>����name����
				String resT = resN.substring(0, resN.indexOf("-"));//��ȡtype����ķ�ʽ
				String resV = resN.substring(resN.indexOf("-") + 1);//��ȡvauleָ�����Դ

				if (resT.equals("foward")) {//����ת��
					request.getRequestDispatcher(resV).forward(request, response);
				} else if (resT.equals("redirect")) {//�ض���
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
			response.sendRedirect("/UseSC/Login.jsp");//���action�����ڣ��ض��򵽵�¼ҳ��
		}
	}

}
