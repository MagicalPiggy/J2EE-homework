package sc.ustc.dao;

import sc.ustc.model.jdbc.JDBCClass;
import sc.ustc.model.jdbc.JDBCConfig;
import sc.ustc.utils.ConfigResolveHelper;
import sc.ustc.utils.SCUtil;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Author        zhuchongliang
 * Class:        Configuration
 * Date:         2019/1/1 20：25
 * Description: or_mapping.xml配置文件解析类
 */
public class Configuration {

    public void config(ServletContext servletContext) {
        ConfigResolveHelper helper = SCUtil.getXmlResolveHelper(new ConfigResolveHelper(),
                "/WEB-INF/classes/or_mapping.xml", servletContext);//调用工具类进行解析
        JDBCConfig jdbcConfig = helper.getJdbcConfig();
        List<JDBCClass> jdbcClassList = helper.getJdbcClassList();

        // 配置Conversation属性
        Conversation.setJdbcClassList(jdbcClassList);
        Conversation.setJdbcConfig(jdbcConfig);
    }
}

