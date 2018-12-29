package sc.water.ustc.interceptor;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import sc.ustc.impl.InterceptorInterface;
import sc.ustc.model.Action;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogInterceptor implements InterceptorInterface {

	Document document;
	Element log;
	Element actionElement;
	File file = new File("F:\\e3-log\\log.xml");
	boolean isNewFile = false;
	
	
	public void preAction(Action action) {
		System.out.println("----------LogInterceptor preAction");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 璁剧疆鏃ユ湡鏍煎紡
		String SysTime = df.format(new Date()); // 鑾峰彇褰撳墠绯荤粺鏃堕棿,鍗充负璁块棶寮�濮嬫椂闂�
		// 娴嬭瘯鎵撳嵃System.out.println(df.format(new Date()));// new Date()涓鸿幏鍙栧綋鍓嶇郴缁熸椂闂�

		if (!file.exists()) { // 濡傛灉璇ユ棩蹇楁枃浠朵笉瀛樺湪鍒欏垱寤鸿鏂囦欢
			try {
				isNewFile = true;
				//System.out.println("is the new file:"+isNewFile);
				file.createNewFile();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();// 鍒涘缓鏂囦欢宸ュ巶
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			if(isNewFile ==true)
			{document = builder.newDocument();
			log = document.createElement("log");
			System.out.println("成功创建log元素");}
			
			
			
			
			else
			{document = builder.parse(file);
			
			
			NodeList logs =  document.getElementsByTagName("log");
			
			log = (Element) logs.item(0);
			System.out.println("成功获取log元素");
			}
			
			
			
			// 鍒涘缓瀛愯妭鐐癸紝骞惰缃睘鎬�
			actionElement = document.createElement("actionElement");
			// 涓篴ction娣诲姞瀛愯妭鐐�
			Element name = document.createElement("name");
			name.setTextContent(action.getName());
			actionElement.appendChild(name);
			Element stime = document.createElement("s-time");
			stime.setTextContent(SysTime);
			actionElement.appendChild(stime);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void afterAction(Action action) {
		System.out.println("----------LogInterceptor afterAction");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 璁剧疆鏃ユ湡鏍煎紡
		String SysTime = df.format(new Date()); // 鑾峰彇褰撳墠绯荤粺鏃堕棿,鍗充负璁块棶缁撴潫鏃堕棿

		String result = "success"; // 璁剧疆杩斿洖鍊�

		Element etime = document.createElement("e-time");
		etime.setTextContent(SysTime);
		actionElement.appendChild(etime);
		// 杩斿洖缁撴灉
		Element eresult = document.createElement("result");
		eresult.setTextContent(result);
		actionElement.appendChild(eresult);
		 

		// 涓烘牴鑺傜偣娣诲姞瀛愯妭鐐�
		log.appendChild(actionElement);
		// 灏嗘牴鑺傜偣娣诲姞鍒癉ocument涓�
		if(isNewFile == true)
		{document.appendChild(log);}
		// 鍒涘缓TransformerFactory瀵硅薄
		TransformerFactory tff = TransformerFactory.newInstance();
		// 鍒涘缓Transformer瀵硅薄
		Transformer tf;
		try {
			// 鐢熸垚xml
			tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			
			tf.setOutputProperty(OutputKeys.CDATA_SECTION_ELEMENTS, "yes");
			tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream(file)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
