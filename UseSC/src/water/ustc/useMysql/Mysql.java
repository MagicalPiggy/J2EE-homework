
package water.ustc.useMysql;

//Mysql�ౣ�������ݿ���ص��ֶ�
public class Mysql {
	    public String driver = "com.mysql.cj.jdbc.Driver";//��������������
	    public String dbName = "e2_test";//���ݿ������
	    public String passWord = "Zz19941012";//mysql���û�����
	    public String userName = "root";//mysql���û�����
	    //ƴ�ӳ�Ϊ������url
	    public String url = "jdbc:mysql://localhost:3306/" + dbName;
	    //+"?serverTimezone=GMT"
}
