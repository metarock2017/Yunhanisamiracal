package indi.yunhan.model.message.messagehandle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 2017/8/10.
 */
public class MessageHandle {
    public static Map<String, String> getKeyValueFromClearText(String clearText) {
        Map<String, String> map = new HashMap<>();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            StringReader stringReader = new StringReader(clearText);
            InputSource inputSource = new InputSource(stringReader);

            Document document = db.parse(inputSource);
            Element element = document.getDocumentElement();

            NodeList nodeList = element.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeName().equals("#text")) {
                    continue;
                } else {
                    map.put(nodeList.item(i).getNodeName(), nodeList.item(i).getTextContent());
                }
            }
            return map;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("fail");
        return null;
    }
}
