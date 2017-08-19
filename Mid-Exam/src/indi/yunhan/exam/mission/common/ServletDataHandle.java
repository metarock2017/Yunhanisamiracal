package indi.yunhan.exam.mission.common;

import com.google.gson.Gson;

import javax.servlet.ServletInputStream;
import java.io.*;
import java.util.Map;

public class ServletDataHandle {
    public static Map<String, String> jsonToMap(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Map.class);
    }

    public static String getJsonFromStream(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((ServletInputStream) inputStream, "utf-8"));
            StringBuffer stringBuffer = new StringBuffer("");
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuffer.append(temp);
            }
            bufferedReader.close();
            return stringBuffer.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}