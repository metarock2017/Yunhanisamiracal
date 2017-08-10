package XmlAnalysis;

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
import java.util.ArrayList;

/**
 * Created by asus on 2017/8/4.
 */
public class LlpScore {
    public static void main(String[] args) {
        String xmlData = "<jwData><errorCode>0</errorCode><errorInfo>正常</errorInfo><dataNums>15</dataNums><data><item><课类>理论</课类><学号>2015211878</学号><姓名>李立平</姓名><班级>04121513</班级><学期>20162</学期><课程号>000022  </课程号><课程名>健康教育2</课程名><考试性质>正常考试</考试性质><成绩>85</成绩></item><item><课类>理论</课类><学号>2015211878</学号><姓名>李立平</姓名><班级>04121513</班级><学期>20162</学期><课程号>040123  </课程号><课程名>面向对象程序设计-C++</课程名><考试性质>正常考试</考试性质><成绩>85</成绩></item><item><课类>理论</课类><学号>2015211878</学号><姓名>李立平</姓名><班级>04121513</班级><学期>20162</学期><课程号>040157  </课程号><课程名>数据库原理</课程名><考试性质>正常考试</考试性质><成绩>81</成绩></item><item><课类>理论</课类><学号>2015211878</学号><姓名>李立平</姓名><班级>04121513</班级><学期>20162</学期><课程号>040200  </课程号><课程名>计算机网络</课程名><考试性质>正常考试</考试性质><成绩>68</成绩></item><item><课类>理论</课类><学号>2015211878</学号><姓名>李立平</姓名><班级>04121513</班级><学期>20162</学期><课程号>040319 </课程号><课程名>操作系统</课程名><考试性质>正常考试</考试性质><成绩>68</成绩></item><item><课类>理论</课类><学号>2015211878</学号><姓名>李立平</姓名><班级>04121513</班级><学期>20162</学期><课程号>040433  </课程号><课程名>数值计算方法</课程名><考试性质>正常考试</考试性质><成绩>72</成绩></item><item><课类>理论</课类><学号>2015211878</学号><姓名>李立平</姓名><班级>04121513</班级><学期>20162</学期><课程号>050093  </课程号><课程名>外贸实务英语</课程名><考试性质>正常考试</考试性质><成绩>69</成绩></item><item><课类>理论</课类><学号>2015211878</学号><姓名>李立平</姓名><班级>04121513</班级><学期>20162</学期><课程号>070107  </课程号><课程名>形势与政策</课程名><考试性质>正常考试</考试性质><成绩>83</成绩></item><item><课类>理论</课类><学号>2015211878</学号><姓名>李立平</姓名><班级>04121513</班级><学期>20162</学期><课程号>A1035020</课程号><课程名>礼仪与社会交往</课程名><考试性质>正常考试</考试性质><成绩>82</成绩></item><item><课类>理论</课类><学号>2015211878</学号><姓名>李立平</姓名><班级>04121513</班级><学期>20162</学期><课程号>A1075250</课程号><课程名>计算机犯罪及取证</课程名><考试性质>正常考试</考试性质><成绩>77</成绩></item><item><课类>理论</课类><学号>2015211878</学号><姓名>李立平</姓名><班级>04121513</班级><学期>20162</学期><课程号>090113  </课程号><课程名>体育(俱乐部)</课程名><考试性质>正常考试</考试性质><成绩>88</成绩></item><item><课类>实验实践</课类><学号>2015211878</学号><姓名>李立平</姓名><班级>04121513</班级><学期>20162</学期><课程号>041114  </课程号><课程名>集中上机(C++/Java)</课程名><考试性质>正常考试</考试性质><成绩>84</成绩></item><item><课类>实验实践</课类><学号>2015211878</学号><姓名>李立平</姓名><班级>04121513</班级><学期>20162</学期><课程号>040123  </课程号><课程名>面向对象程序设计-C++</课程名><考试性质>正常考试</考试性质><成绩>79</成绩></item><item><课类>实验实践</课类><学号>2015211878</学号><姓名>李立平</姓名><班级>04121513</班级><学期>20162</学期><课程号>040157  </课程号><课程名>数据库原理</课程名><考试性质>正常考试</考试性质><成绩>91</成绩></item><item><课类>实验实践</课类><学号>2015211878</学号><姓名>李立平</姓名><班级>04121513</班级><学期>20162</学期><课程号>040200  </课程号><课程名>计算机网络</课程名><考试性质>正常考试</考试性质><成绩>84</成绩></item></data></jwData>";

        try {
            // 读取xml文档
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(xmlData));

            Document document = builder.parse(inputSource);

            Element dataNode = (Element) document.getDocumentElement().getLastChild();

            NodeList nodeList = dataNode.getChildNodes();

            // ArrayList储存节点List
            ArrayList<NodeList> arrayListNodeList = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                arrayListNodeList.add(nodeList.item(i).getChildNodes());
            }

            // 遍历计算
            int count = 0;
            int score = 0;
            for (int i = 0; i < arrayListNodeList.size(); i++) {
                count++;
                score = score + Integer.parseInt(arrayListNodeList.get(i).item(8).getTextContent());
            }

            if (score/count>0){
                System.out.println("好学生!");
            }
            else {
                System.out.println("好学生!!!");
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
