package sc.water.ustc.model;

import sc.water.ustc.dao.UserDao;

import java.sql.ResultSet;

public class UserBean {
	private int userId;
	private String userName;
	private String userPass;

	public boolean signIn() {// �˷������������¼ҵ��
		UserDao userDao = new UserDao();
		String loginInSql = "SELECT * FROM user WHERE userName='" + this.userName + "'";// ��¼ʱִ�е�sql���
		userDao.openDBConnection();// �������ݿ�
		UserBean user = userDao.query(loginInSql);// ��ѯ�û��Ƿ���ڣ�������һ��UserBean����
		userDao.closeDBConnection();// �ر�����
		if (user == null)
			return false;
		else
			return this.userPass.equals(user.userPass);// �����ؽ�������pass�뵱ǰ����Ľ��жԱ�
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
