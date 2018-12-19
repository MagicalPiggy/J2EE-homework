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
		Map<String, String> map = new HashMap<String, String>();// ����һ��Map����
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();// ��������
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();// �õ�DOM������
			Document document = db.parse(path); // 3.����xml�ĵ����õ������ĵ���document
			NodeList actionList = document.getElementsByTagName("action");// ��ȡ����"action"�ڵ�
			for (int i = 0; i < actionList.getLength(); i++) {// ��������"action"�ڵ�
				Node actionNode = actionList.item(i);// ��list��ȡ��һ���ڵ�
				NamedNodeMap actionNodeMap = actionNode.getAttributes();// ��ȡ�ýڵ����������ֵ
				// �ֱ��ȡ��action��Ӧ�����ơ��ࡢ����
				String actionName = actionNodeMap.getNamedItem("name").getNodeValue();
				String actionClass = actionNodeMap.getNamedItem("class").getNodeValue();
				String actionMethod = actionNodeMap.getNamedItem("method").getNodeValue();

				if (actionName.equals(passActionName)) {// �����action�봫���action����һ��

					// ����action��Ӧ�����ơ��ࡢ��������Map��
					map.put("action", actionName);
					map.put("class", actionClass);
					map.put("method", actionMethod);
					// �ٻ�ȡ�ýڵ��е��ӽڵ�,Ҳ����result�ڵ�
					NodeList actionChildNodes = actionNode.getChildNodes();
					for (int j = 0; j < actionChildNodes.getLength(); j++) {// �������е�result�ڵ�
						if (actionChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {// �����ǰ�ڵ���Ԫ�ؽڵ�
							if (actionChildNodes.item(j).getNodeName().toString().equals("result")) {// �������һ��result�ڵ�
								NamedNodeMap resultMap = actionChildNodes.item(j).getAttributes();// ��ȡ��result�ڵ����������
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
