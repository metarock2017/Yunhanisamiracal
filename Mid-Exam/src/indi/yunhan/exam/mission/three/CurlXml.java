package indi.yunhan.exam.mission.three;

/**
 * Created by asus on 2017/8/19.
 *
 *  java.io.IOException: Server returned HTTP response code: 500 for URL: http://172.20.2.52:84/userpostservice.asmx
 *           at sun.net.www.protocol.http.HttpURLConnection.getInputStream0(HttpURLConnection.java:1876)
 *           at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1474)
 *           at indi.yunhan.exam.mission.three.CurlXml.Post(CurlXml.java:40)
 *           at indi.yunhan.exam.mission.three.CurlXml.main(CurlXml.java:54)
 *
 *
 *  500错误 没法请求 只能封装一个函数了 只针对题目所给xml格式 进行解析 返回json数组
 *  拿走即用 :D
 *  右键运行测试
 *
 */

import com.google.gson.Gson;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.LinkedList;
/*
*
*
* */

public class CurlXml {
    public static String encodingXml(String xml) {

        try {
            Gson gson = new Gson();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader stringReader = new StringReader(xml);
            InputSource inputSource = new InputSource(stringReader);

            Document document = db.parse(inputSource);
            NodeList nodeList = document.getElementsByTagName("GetUserPostTypesResult").item(0).getChildNodes();

            LinkedList<UserPostType> userPostTypes = new LinkedList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {

                if (nodeList.item(i).getNodeName().equals("UserPostTypes")) {
                    NodeList subNodelist = nodeList.item(i).getChildNodes();
                    UserPostType userPostType = new UserPostType();

                    for (int j = 0; j < subNodelist.getLength(); j++) {

                        if (!(subNodelist.item(j).getNodeName().equals("#text"))) {
                            if (subNodelist.item(j).getNodeName().equals("TypeId")) {
                                userPostType.setTypeId(subNodelist.item(j).getTextContent());
                            }
                            if (subNodelist.item(j).getNodeName().equals("TypeName")) {
                                userPostType.setTypeName(subNodelist.item(j).getTextContent());
                            }
                        }
                    }//For end

                    userPostTypes.add(userPostType);
                }
            }

            return gson.toJson(userPostTypes);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(CurlXml.encodingXml("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <GetUserPostTypesResponse xmlns=\"http://172.20.2.52:84/\">\n" +
                "      <GetUserPostTypesResult>\n" +
                "        <UserPostTypes>\n" +
                "          <TypeId>string</TypeId>\n" +
                "          <TypeName>string</TypeName>\n" +
                "        </UserPostTypes>\n" +
                "        <UserPostTypes>\n" +
                "          <TypeId>string</TypeId>\n" +
                "          <TypeName>string</TypeName>\n" +
                "        </UserPostTypes>\n" +
                "      </GetUserPostTypesResult>\n" +
                "    </GetUserPostTypesResponse>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>"));
    }
}