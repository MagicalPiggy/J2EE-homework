package sc.water.ustc.dao;

import sc.ustc.dao.BaseDAO;
import sc.water.ustc.model.UserBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends BaseDAO {

    public UserDao() {
//        // mysql
//        Url = "jdbc:mysql://localhost:3306/e2_test?serverTimezone=GMT";//���ݿ����·��	
//        Driver = "com.mysql.cj.jdbc.Driver";//���ݿ�����
//        User = "root";//���ݿ��û���
//        Password = "...";//���ݿ��û�����

        // sqlite
        Url = "jdbc:sqlite://C:\\Users\\Administrator\\testDB.db";
        Driver = "org.sqlite.JDBC";
    }

    @Override
    public UserBean query(String sql) {//��ѯ���û��Ƿ����
        UserBean user = null;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                user = new UserBean();
                user.setUserId(resultSet.getInt("userId"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPass(resultSet.getString("userPass"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean insert(String sql) {
        return executeUpdate(sql);
    }

    @Override
    public boolean update(String sql) {
        return executeUpdate(sql);
    }

    @Override
    public boolean delete(String sql) {
        return executeUpdate(sql);
    }

    private boolean executeUpdate(String sql) {
        int result;
        try {
            result = statement.executeUpdate(sql);
        } catch (SQLException e) {
            result = 0;
            e.printStackTrace();
        }
        return result > 0;
    }
}
