
package water.ustc.useMysql;

//Mysql类保存了数据库相关的字段
public class Mysql {
	    public String driver = "com.mysql.cj.jdbc.Driver";//驱动的完整名称
	    public String dbName = "e2_test";//数据库的名称
	    public String passWord = "Zz19941012";//mysql的用户密码
	    public String userName = "root";//mysql的用户名称
	    //拼接成为完整的url
	    public String url = "jdbc:mysql://localhost:3306/" + dbName;
	    //+"?serverTimezone=GMT"
}
