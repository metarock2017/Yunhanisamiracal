package indi.yunhan.model.message;

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
 * Created by asus on 2017/8/8.
 */
public class TextMessage extends MessageProto {
    private String clearText;
    private Map<String, String> keyValue;

    private final String msgTemplate = "" +
            "<xml><ToUserName><![CDATA[%s]]></ToUserName>\n" +
            "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
            "<CreateTime>%s</CreateTime>\n" +
            "<MsgType><![CDATA[text]]></MsgType>\n" +
            "<Content><![CDATA[%s]]></Content>\n" +
            "<MsgId>%s</MsgId>\n" +
            "</xml>";
    private String msgXml;

    public TextMessage(String clearText) {
        this.clearText = clearText;
        this.msgType = "test";
    }

    public TextMessage(String toUserName, String fromUserName, String content, String msgId) {
        this.msgXml = String.format(
                this.msgTemplate,
                toUserName,
                fromUserName,
                System.currentTimeMillis() / 1000 + "",
                content,
                msgId);
        this.msgType = "text";
    }

    public Map<String, String> getKeyValueFromClearText() {
        Map<String, String> map = new HashMap<>();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            StringReader stringReader = new StringReader(this.clearText);
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
            this.keyValue = map;
            return map;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void exchangeUser() {
        String toUserName = this.keyValue.get("ToUserName");
        String fromUserName = this.keyValue.get("FromUserName");

        this.keyValue.put("ToUserName", fromUserName);
        this.keyValue.put("FromUserName", toUserName);
    }

    public boolean hasDate() {
        if (this.keyValue.size() >= 1 && !(this.clearText.equals(""))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format(
                this.msgTemplate,
                this.keyValue.get("ToUserName"),
                this.keyValue.get("FromUserName"),
                this.keyValue.get("CreateTime"),
                this.keyValue.get("Content"),
                this.keyValue.get("MsgId"));
    }
}
