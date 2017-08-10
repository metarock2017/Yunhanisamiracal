package indi.yunhan.service;

import indi.yunhan.model.message.TextMessage;
import indi.yunhan.util.Encoding;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Created by asus on 2017/8/4.
 */
public class MessageService {

    public String replyMsg(javax.servlet.http.HttpServletRequest request) {
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String msgSignature = request.getParameter("msg_signature");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try {
            builder = factory.newDocumentBuilder();
            Document reqDocument = builder.parse(inputStream);
            Element reqRootNode = reqDocument.getDocumentElement();
            NodeList allNode = reqRootNode.getChildNodes();

            String tUserName = allNode.item(1).getTextContent();
            String encrypt = allNode.item(3).getTextContent();

            String format = "<xml><ToUserName><![CDATA[%s]]></ToUserName><Encrypt><![CDATA[%s]]></Encrypt></xml>";
            String fromXML = String.format(format, tUserName, encrypt);
            String clearText = Encoding.decrypt(msgSignature, timestamp, nonce, fromXML);

            /*
            * 初始化文本消息对象
            * */
            System.out.println(clearText);
            System.out.println(getTypeOfMsg(clearText));

            TextMessage textMessage = new TextMessage(clearText);
            textMessage.getKeyValueFromClearText();
            textMessage.exchangeUser();

            return Encoding.encrypt(textMessage.toString(), nonce);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error!";
    }

    public String getTypeOfMsg(String clearText) {
        try {
            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = df.newDocumentBuilder();

            StringReader stringReader = new StringReader(clearText);
            InputSource inputSource = new InputSource(stringReader);

            Document document = db.parse(inputSource);
            Element element = document.getDocumentElement();

            NodeList nodeList = element.getElementsByTagName("MsgType");

            return nodeList.item(0).getTextContent();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
