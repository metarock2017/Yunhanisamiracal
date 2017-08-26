package indi.yunhan.service;

import indi.yunhan.model.message.ImageMessage;
import indi.yunhan.model.message.TextMessage;
import indi.yunhan.util.Encoding;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Created by asus on 2017/8/4.
 */
public class MessageService {

    public static String encrypt = null;

    public static String timestamp;
    public static String nonce;
    public static String msgSignature;

    public String replyMsg(javax.servlet.http.HttpServletRequest request) {

        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        String timestamp = request.getParameter("timestamp");
//        String nonce = request.getParameter("nonce");
//        String msgSignature = request.getParameter("msg_signature");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            Element element = document.getDocumentElement();
            NodeList nodeList = element.getChildNodes();

            //731229
            String msgTemplate = "" +
                    "<xml>" +
                    "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
                    "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
                    "<CreateTime>%s</CreateTime>\n" +
                    "<MsgType><![CDATA[text]]></MsgType>\n" +
                    "<Content><![CDATA[%s]]></Content>\n" +
                    "</xml>";
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(i + nodeList.item(i).getNodeName());
            }
            String resp = String.format(msgTemplate,
                    nodeList.item(2).getTextContent(),
                    nodeList.item(0).getTextContent(),
                    System.currentTimeMillis() / 1000 + "",
                    "http://ghan.s1.natapp.link/undercover/gameview/create.html");
            return resp;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            builder = factory.newDocumentBuilder();
//            Document reqDocument = builder.parse(inputStream);
//            Element reqRootNode = reqDocument.getDocumentElement();
//            NodeList allNode = reqRootNode.getChildNodes();

//            String tUserName = allNode.item(1).getTextContent();
//            String encrypt = allNode.item(3).getTextContent();
//
//            String format = "<xml><ToUserName><![CDATA[%s]]></ToUserName><Encrypt><![CDATA[%s]]></Encrypt></xml>";
//            String fromXML = String.format(format, tUserName, encrypt);
//            String clearText = Encoding.decrypt(msgSignature, timestamp, nonce, fromXML);

            /*
            * 初始化文本消息对象
            * */
//            System.out.println(clearText);
//            System.out.println(getTypeOfMsg(clearText));
//
//            if (getTypeOfMsg(clearText).equals("text")) {
//                TextMessage textMessage = new TextMessage(clearText);
//                textMessage.setKeyValue(clearText);
//                textMessage.exchangeUser();
//
//                return Encoding.encrypt(textMessage.toString(), nonce);
//
//            } else if (getTypeOfMsg(clearText).equals("image")) {
//                ImageMessage imageMessage = new ImageMessage(clearText);
//                imageMessage.setKeyValue(clearText);
//                imageMessage.exchangeUser();
//
//                System.out.println(imageMessage.toString());
//
//                return Encoding.encrypt(imageMessage.toString(), nonce);
//            } else {
//                return null;
//            }
//
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        ?
        return null;
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

    public boolean isEncryptMsg(HttpServletRequest request) {
        encrypt = request.getParameter("encrypt_type");
        System.out.println(encrypt);
        if ("aes".equals(encrypt)) {
            return true;
        }
        return false;
    }
}
