package water.ustc.dao;

import water.ustc.useMysql.Mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class LoginDao {
	Mysql mysql = new Mysql();
  

    //此方法用来查询
    public List<List<String>> select(String id, String password) {
    	//初始化sql语句
        String sql = "select * from userAccount where id = ? and password = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<List<String>> listlist = new ArrayList();
        List<String> list = new ArrayList();
        try {
            // 加载驱动
            Class.forName(mysql.driver);
            // 获得与数据库的连接
            conn = DriverManager.getConnection(mysql.url, mysql.userName, mysql.passWord);
            // 创建prepareStatement，将传入的实参填入sql语句中
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, password);
            // 执行sql
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
                list.add(rs.getString(2));
                listlist.add(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listlist;
    }
  
    //此方法用来插入
    public void insert(String id, String password) {

        String sql = "insert into userAccount(id,password) values(?,?)";

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 加载驱动
            Class.forName(mysql.driver);
            // 获得连接
            conn = DriverManager.getConnection(mysql.url, mysql.userName, mysql.passWord);
            // 创建prepareStatement
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, password);
            // 执行操作
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
