package indi.yunhan.model.gameroom;

/**
 * Created by asus on 2017/8/23.
 */
public class TypeAndId {
    private String type;
    private String openId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public TypeAndId(String type, String openId) {
        this.type = type;
        this.openId = openId;
    }
}
