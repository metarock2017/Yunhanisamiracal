package resource.set;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by wang on 2017/8/6.
 */
public class Parcel8 {
    public static void main(String[] args) {
        Set<String> words = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        //这里的路径需要自己找一个文件替换

        /*
        * 正则表达式匹配字母一次或者多次
        * */
        words.addAll(new MyFileReader("C:\\Users\\asus\\Desktop\\test.txt", "\\w+").readFromFile());
        System.out.println(words);

        //作业：选择一个文件，利用Set容器的特性，计算该文件中有多少个不同的单词

        /*
        * add()时会自动去重
        * 文件内容
        * hello the fucking world world world
        * */

        System.out.println("共有 " + words.size() + " 个单词");
    }
}
