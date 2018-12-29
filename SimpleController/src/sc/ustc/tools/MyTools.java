package sc.ustc.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sc.ustc.model.Action;
import sc.ustc.model.Interceptor;
import sc.ustc.model.Result;

public class MyTools {
	private List<Action> actionList;
	private List<Interceptor> interceptorList;
	private Action action;
	private Result result;
	private Interceptor interceptor;
	private String tagName;

	public List<Action> getActionList() {
		return actionList;
	}

	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public void readXml(String path) {
		this.actionList = new ArrayList<>();
		this.interceptorList = new ArrayList<>();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();// 创建工厂
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();// 得到DOM解析�?
			Document document = db.parse(path); // 3.解析xml文档，得到代表文档的document

			NodeList documentActionList = document.getElementsByTagName("action");// 获取�?�?"action"节点
			for (int i = 0; i < documentActionList.getLength(); i++) {// 遍历�?�?"action"节点
				Node actionNode = documentActionList.item(i);// 从list中取出一个节�?
				this.action = new Action();
				NamedNodeMap actionNodeMap = actionNode.getAttributes();// 获取该节点的�?有属性�??
				// 分别获取该action对应的名称�?�类、方�?
				this.action.setName(actionNodeMap.getNamedItem("name").getNodeValue());
				this.action.setClassPath(actionNodeMap.getNamedItem("class").getNodeValue());
				this.action.setMethod(actionNodeMap.getNamedItem("method").getNodeValue());

				// 再获取该节点中的子节�?
				NodeList actionChildNodes = actionNode.getChildNodes();
				for (int j = 0; j < actionChildNodes.getLength(); j++) {// 遍历�?有的子节�?
					if (actionChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {// 如果当前节点是元素节�?
						if (actionChildNodes.item(j).getNodeName().toString().equals("result")) {// 如果这是�?个result节点
							this.result = new Result();
							NamedNodeMap resultMap = actionChildNodes.item(j).getAttributes();// 获取该result节点的所有属�?
							this.result.setName(resultMap.getNamedItem("name").getNodeValue());
							this.result.setType(resultMap.getNamedItem("type").getNodeValue());
							this.result.setValue(resultMap.getNamedItem("value").getNodeValue());
							
							this.action.addResult(this.result);
							//this.result = null;
						}
						
						if (actionChildNodes.item(j).getNodeName().toString().equals("interceptor-ref")) {// 如果这是�?个interceptor-ref节点
							NamedNodeMap irefMap = actionChildNodes.item(j).getAttributes();// 获取该result节点的所有属�?
							System.out.println(irefMap.getNamedItem("name").getNodeValue());
							this.action.addInterceptorRef(irefMap.getNamedItem("name").getNodeValue());
						}

						// 将此action存入List�?
						this.actionList.add(this.action);
						//this.action = null;
					}
				}

			}

			NodeList documentInterceptorList = document.getElementsByTagName("interceptor");// 获取�?�?"interceptor"节点
			for (int k = 0; k < documentInterceptorList.getLength(); k++) {// 遍历�?�?"action"节点
				Node InterceptorNode = documentInterceptorList.item(k);// 从list中取出一个节�?
				this.interceptor = new Interceptor();
				NamedNodeMap interceptorNodeMap = InterceptorNode.getAttributes();// 获取该节点的�?有属性�??

				// 分别获取该interceptor对应的名称�?�类、方�?

				this.interceptor.setName(interceptorNodeMap.getNamedItem("name").getNodeValue());
				this.interceptor.setClassPath(interceptorNodeMap.getNamedItem("class").getNodeValue());
				this.interceptor.setPreDo(interceptorNodeMap.getNamedItem("predo").getNodeValue());
				this.interceptor.setAfterDo(interceptorNodeMap.getNamedItem("afterdo").getNodeValue());

				// 将此interceptor存入List�?
				this.interceptorList.add(this.interceptor);

			}
		} catch (ParserConfigurationException e1) {

			e1.printStackTrace();
		} catch (SAXException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public Interceptor getInterceptor() {
		return interceptor;
	}

	public void setInterceptor(Interceptor interceptor) {
		this.interceptor = interceptor;
	}

	public List<Interceptor> getInterceptorList() {
		return this.interceptorList;
	}

	public void setInterceptorList(List<Interceptor> interceptorList) {
		this.interceptorList = interceptorList;
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
