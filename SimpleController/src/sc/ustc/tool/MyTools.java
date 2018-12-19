package sc.ustc.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MyTools {

	public Map<String, String> readXml(String passActionName, String path) {
		Map<String, String> map = new HashMap<String, String>();// 创建一个Map集合
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();// 创建工厂
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();// 得到DOM解析器
			Document document = db.parse(path); // 3.解析xml文档，得到代表文档的document
			NodeList actionList = document.getElementsByTagName("action");// 获取所有"action"节点
			for (int i = 0; i < actionList.getLength(); i++) {// 遍历所有"action"节点
				Node actionNode = actionList.item(i);// 从list中取出一个节点
				NamedNodeMap actionNodeMap = actionNode.getAttributes();// 获取该节点的所有属性值
				// 分别获取该action对应的名称、类、方法
				String actionName = actionNodeMap.getNamedItem("name").getNodeValue();
				String actionClass = actionNodeMap.getNamedItem("class").getNodeValue();
				String actionMethod = actionNodeMap.getNamedItem("method").getNodeValue();

				if (actionName.equals(passActionName)) {// 如果此action与传入的action名称一致

					// 将此action对应的名称、类、方法存入Map中
					map.put("action", actionName);
					map.put("class", actionClass);
					map.put("method", actionMethod);
					// 再获取该节点中的子节点,也就是result节点
					NodeList actionChildNodes = actionNode.getChildNodes();
					for (int j = 0; j < actionChildNodes.getLength(); j++) {// 遍历所有的result节点
						if (actionChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {// 如果当前节点是元素节点
							if (actionChildNodes.item(j).getNodeName().toString().equals("result")) {// 如果这是一个result节点
								NamedNodeMap resultMap = actionChildNodes.item(j).getAttributes();// 获取该result节点的所有属性
								String resultName = resultMap.getNamedItem("name").getNodeValue();
								String resultType = resultMap.getNamedItem("type").getNodeValue();
								String resultValue = resultMap.getNamedItem("value").getNodeValue();
								String res = resultType + '-' + resultValue;
								map.put("result" + resultName, res);

							}

						}

					}

				}
			}

		} catch (ParserConfigurationException e1) {

			e1.printStackTrace();
		} catch (SAXException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return map;
	}

}
