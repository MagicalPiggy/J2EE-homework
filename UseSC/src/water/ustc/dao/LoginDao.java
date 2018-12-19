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
  

    //�˷���������ѯ
    public List<List<String>> select(String id, String password) {
    	//��ʼ��sql���
        String sql = "select * from userAccount where id = ? and password = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<List<String>> listlist = new ArrayList();
        List<String> list = new ArrayList();
        try {
            // ��������
            Class.forName(mysql.driver);
            // ��������ݿ������
            conn = DriverManager.getConnection(mysql.url, mysql.userName, mysql.passWord);
            // ����prepareStatement���������ʵ������sql�����
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, password);
            // ִ��sql
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
  
    //�˷�����������
    public void insert(String id, String password) {

        String sql = "insert into userAccount(id,password) values(?,?)";

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // ��������
            Class.forName(mysql.driver);
            // �������
            conn = DriverManager.getConnection(mysql.url, mysql.userName, mysql.passWord);
            // ����prepareStatement
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, password);
            // ִ�в���
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
