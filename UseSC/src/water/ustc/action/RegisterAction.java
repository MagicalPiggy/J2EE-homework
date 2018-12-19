package water.ustc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import water.ustc.dao.LoginDao;

public class RegisterAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public String handleRegist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��request�еĲ������ַ�����ʽ��ȡ
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		LoginDao userDao = new LoginDao();// ʵ����һ��Dao��������ִ��ע�����
		HttpSession session = request.getSession();
		if (userDao.select(id, password) != null && !userDao.select(id, password).isEmpty()) {
			// �������ݿ��в�ѯ����Ӧ�ļ�¼��˵���û����Ѿ���ע�ᣬ�����ַ�����ʽ����ʧ�ܵĽ��
			session.setAttribute("registMessage", "�û����Ѵ��ڣ�");
			return "failure";
		} else {
			// ���������ݿ��в����µļ�¼�����ҷ��سɹ��Ľ��
			userDao.insert(id, password);
			session.setAttribute("id", id);
			return "success";
		}
	}
}
