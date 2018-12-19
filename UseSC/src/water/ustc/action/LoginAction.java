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
		// 将request中的参数以字符串形式获取
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		LoginDao userDao = new LoginDao();// 实例化一个Dao对象
		HttpSession session = request.getSession();// 创建一个session
		if (userDao.select(id, password) != null && !userDao.select(id, password).isEmpty()) {
			// 若在数据库中查询到对应的纪录，则以字符串形式返回成功的结果
			session.setAttribute("id", id);
			return "success";
		} else {
			// 否则返回失败的结果
			session.setAttribute("loginMessage", "用户名或密码错误！");
			return "failure";
		}
	}
}
