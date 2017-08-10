package resource.set;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by asus on 2017/8/8.
 */
public class MyFileReader {
    private String path;
    private Pattern pattern;
    private Set<String> wordSet;

    public MyFileReader(String path, String pattern){
        this.path = path;
        this.pattern = Pattern.compile(pattern);
        this.wordSet = new HashSet<>();
    }

    public Set<String> readFromFile(){
        try {
            FileInputStream fileInputStream = new FileInputStream(this.path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                this.parse(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.wordSet;
    }

    private void parse(String line) {
        Matcher matcher = this.pattern.matcher(line);

        /*
        * 每当匹配到就放入集合
        * */
        while (matcher.find()) {
            this.wordSet.add(matcher.group());
        }
    }
}
