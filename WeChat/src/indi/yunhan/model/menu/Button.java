package indi.yunhan.model.menu;

import java.util.ArrayList;

/**
 * Created by asus on 2017/8/5.
 */
public class Button {
    private String type;
    private String name;
    private String key;

    private String url;
    private String appId;
    private String pagePath;
    private ArrayList<Button> sub_button;

    public ArrayList<Button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(ArrayList<Button> sub_butttton) {
        this.sub_button = sub_butttton;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public Button() {

    }

    public Button(boolean boo, String type, String name, String key) {
        if (boo) {
            sub_button = new ArrayList<>();
        }
        this.type = type;
        this.name = name;
        this.key = key;
    }

    public Button(boolean boo, String type, String name, String key, String url, String appId, String pagePath) {
        if (boo) {
            sub_button = new ArrayList<>();
        }
        this.type = type;
        this.name = name;
        this.key = key;
        this.url = url;
        this.appId = appId;
        this.pagePath = pagePath;
    }
}
