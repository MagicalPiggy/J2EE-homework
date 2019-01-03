package sc.water.ustc.action;

import sc.water.ustc.dao.UserDao;
import sc.water.ustc.model.UserBean;
import sc.water.ustc.utils.CommonUtils;

public class LoginAction {

    private String userName;
    private String password;


    public String handleLogin() {
        String resultMsg = "failure";//Ĭ��ֵ
        if (CommonUtils.isNotEmpty(this.userName) && CommonUtils.isNotEmpty(this.password)) {
            UserBean userBean = new UserBean();//ʵ����
            userBean.setUserName(userName);
            userBean.setUserPass(password);
            resultMsg = userBean.signIn() ? "success" : "login_failure";//����userBean�е�signIn���������ж�
        }
        return resultMsg;
    }

}
