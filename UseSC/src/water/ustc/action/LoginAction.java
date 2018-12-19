package water.ustc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import water.ustc.dao.LoginDao;

public class LoginAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public String handleLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��request�еĲ������ַ�����ʽ��ȡ
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		LoginDao userDao = new LoginDao();// ʵ����һ��Dao����
		HttpSession session = request.getSession();// ����һ��session
		if (userDao.select(id, password) != null && !userDao.select(id, password).isEmpty()) {
			// �������ݿ��в�ѯ����Ӧ�ļ�¼�������ַ�����ʽ���سɹ��Ľ��
			session.setAttribute("id", id);
			return "success";
		} else {
			// ���򷵻�ʧ�ܵĽ��
			session.setAttribute("loginMessage", "�û������������");
			return "failure";
		}
	}
}
