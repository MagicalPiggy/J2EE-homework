package sc.ustc.tools;

import org.xml.sax.SAXException;
import sc.ustc.model.Action;
import sc.ustc.model.Interceptor;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SCUtil {
    public static XmlResolveHelper getXmlResolveHelper(){
        try {
            // 鏋勫缓SAXParser
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // 瀹炰緥鍖�?  DefaultHandler瀵硅�?
            XmlResolveHelper parseXml=new XmlResolveHelper();
            // 鍔犺浇璧勬簮鏂囦�? 杞寲涓轰竴涓緭鍏ユ祦
            InputStream stream=XmlResolveHelper.class.getClassLoader().getResourceAsStream("controller.xml");
            // 璋冪敤parse()鏂规�?
            parser.parse(stream, parseXml);
            // 杩斿洖缁撴灉
            return parseXml;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Interceptor> getRefInterceptorList(Action action, List<Interceptor> interceptorList){
        List<Interceptor> refInterceptorList = new ArrayList<>();
        for(String ref: action.getInterceptorRefList()){
            for(Interceptor interceptor:interceptorList){
                if(ref.equals(interceptor.getName())){
                    refInterceptorList.add(interceptor);
                }
            }
        }
        return refInterceptorList;
    }
}
