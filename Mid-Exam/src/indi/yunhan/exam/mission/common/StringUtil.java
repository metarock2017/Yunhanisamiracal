package indi.yunhan.exam.mission.common;

import java.util.Map;

/**
 * Created by asus on 2017/8/19.
 */
public class StringUtil {
    public String getAttr(Map<String, String> data, String field, String defaultStr) {
        return data.get(field) == null ? defaultStr : data.get(field);
    }
}
