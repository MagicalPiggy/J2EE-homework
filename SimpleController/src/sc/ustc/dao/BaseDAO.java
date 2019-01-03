package sc.ustc.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public abstract class BaseDAO {
    protected static String Url = "";//数据库访问路径
    protected static String Driver = "";//数据库驱动
    protected static String User = "";//数据库用户名
    protected static String Password = "";//数据库用户密码
    protected static void setUrl(String url) {
		Url = url;
	}

	protected static void setDriver(String driver) {
		Driver = driver;
	}

	protected static void setUser(String user) {
		User = user;
	}

	protected static void setPassword(String password) {
		Password = password;
	}


	

    private Connection conn = null;
    protected Statement statement = null;

    public void openDBConnection() {//打开数据库连接
        try {
            Class.forName(Driver);// 指定连接类型
            conn = DriverManager.getConnection(Url, User, Password);// 获取连接
            statement = conn.createStatement();// 获取执行sql语句的statement对象
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDBConnection() {//关闭数据库连接
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract Object query(String sql);//执行sql语句，并返回结果对象

    public abstract boolean insert(String sql);//执行sql语句，并返回执行结果

    public abstract boolean update(String sql);//执行sql语句，并返回执行结果


    public abstract boolean delete(String sql);//执行sql语句，并返回执行结果

}
