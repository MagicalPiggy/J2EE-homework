package sc.ustc.utils;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sc.ustc.model.Action;
import sc.ustc.model.BaseBean;
import sc.ustc.model.Interceptor;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;


public class SCUtil {
    public static <T extends DefaultHandler> T getXmlResolveHelper(T handler, String path, ServletContext
            servletContext) {
        try {
            // ����SAXParser
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // ������Դ�ļ� ת��Ϊһ��������
            InputStream stream = servletContext.getResourceAsStream(path);
            // ����parse()����
            parser.parse(stream, handler);
            stream.close();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return handler;
    }

    public static List<Interceptor> getRefInterceptorList(Action action, List<Interceptor> interceptorList) {
        List<Interceptor> refInterceptorList = new ArrayList<>();
        for (String ref : action.getInterceptorRefList()) {
            for (Interceptor interceptor : interceptorList) {
                if (ref.equals(interceptor.getName())) {
                    refInterceptorList.add(interceptor);
                }
            }
        }
        return refInterceptorList;
    }

    /**
     * author:      zhuchongliang
     * description: resultSetת��Ϊ����
     */
    @SuppressWarnings("unchecked")
    public static <T extends BaseBean> T resultSetToBean(ResultSet rs, T proxy) throws Exception {
        //ȡ��Method
        Method[] methods = proxy.getClass().getDeclaredMethods();
        // ���ڻ�ȡ����������������
        ResultSetMetaData meta = rs.getMetaData();
        if (rs.next()) {
            // ѭ����ȡָ���е�ÿһ�е���Ϣ
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                //  ��ȡ���������÷�����
                String colName = meta.getColumnName(i);
                String setMethodName = "set" + colName;
                // ����Method
                for (Method method : methods) {
                    if (method.getName().equalsIgnoreCase(setMethodName)) {
                        setMethodName = method.getName();
                        // ��ȡ��ǰλ�õ�ֵ������Object����
                        Object value = rs.getObject(colName);
                        if (value == null) {
                            continue;
                        }
                        try {
                            // ʵ��Set����
                            Method setMethod = proxy.getClass().getMethod(
                                    setMethodName, value.getClass());
                            setMethod.invoke(proxy, value);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return proxy;
    }
}
