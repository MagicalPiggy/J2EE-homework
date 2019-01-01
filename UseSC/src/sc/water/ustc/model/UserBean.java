package sc.water.ustc.model;

import sc.water.ustc.dao.UserDao;

import java.sql.ResultSet;

public class UserBean {
	private int userId;
	private String userName;
	private String userPass;

	public boolean signIn() {// 此方法用来处理登录业务
		UserDao userDao = new UserDao();
		String loginInSql = "SELECT * FROM user WHERE userName='" + this.userName + "'";// 登录时执行的sql语句
		userDao.openDBConnection();// 连接数据库
		UserBean user = userDao.query(loginInSql);// 查询用户是否存在，并构造一个UserBean对象
		userDao.closeDBConnection();// 关闭连接
		if (user == null)
			return false;
		else
			return this.userPass.equals(user.userPass);// 将返回结果对象的pass与当前对象的进行对比
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
}
