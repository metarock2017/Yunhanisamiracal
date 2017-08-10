package indi.yunhan.model.QRCode;

/**
 * Created by asus on 2017/8/6.
 */
public class QRCode {
    private int expire_seconds;
    private String action_name;

    private ActionInfo action_info;


    public int getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(int expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public ActionInfo getAction_info() {
        return action_info;
    }

    public void setAction_info(ActionInfo action_info) {
        this.action_info = action_info;
    }

    public QRCode(int expire_seconds, String action_name, Scene scene) {
        this.expire_seconds = expire_seconds;
        this.action_name = action_name;

        this.action_info = new ActionInfo(scene);
    }

}
