package sc.ustc.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public abstract class BaseDAO {
    protected static String Url = "";//���ݿ����·��
    protected static String Driver = "";//���ݿ�����
    protected static String User = "";//���ݿ��û���
    protected static String Password = "";//���ݿ��û�����

    private Connection conn = null;
    protected Statement statement = null;

    public void openDBConnection() {
        try {
            Class.forName(Driver);// ָ����������
            conn = DriverManager.getConnection(Url, User, Password);// ��ȡ����
            statement = conn.createStatement();// ��ȡִ��sql����statement����
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDBConnection() {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract Object query(String sql);

    public abstract boolean insert(String sql);

    public abstract boolean update(String sql);

    public abstract boolean delete(String sql);
}
