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
		// 将request中的参数以字符串形式获取
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		LoginDao userDao = new LoginDao();// 实例化一个Dao对象，用来执行注册操作
		HttpSession session = request.getSession();
		if (userDao.select(id, password) != null && !userDao.select(id, password).isEmpty()) {
			// 若在数据库中查询到对应的记录，说明用户名已经被注册，则以字符串形式返回失败的结果
			session.setAttribute("registMessage", "用户名已存在！");
			return "failure";
		} else {
			// 否则在数据库中插入新的记录，并且返回成功的结果
			userDao.insert(id, password);
			session.setAttribute("id", id);
			return "success";
		}
	}
}
