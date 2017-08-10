package indi.yunhan.model.QRCode;

/**
 * Created by asus on 2017/8/6.
 */
public class Scene {
    private int scene_id;

    private String scene_str;

    public int getScene_id() {
        return scene_id;
    }

    public void setScene_id(int scene_id) {
        this.scene_id = scene_id;
    }

    public String getScene_str() {
        return scene_str;
    }

    public void setScene_str(String scene_str) {
        this.scene_str = scene_str;
    }

    public Scene(int scene_id, String scene_str) {
        this.scene_id = scene_id;
        this.scene_str = scene_str;
    }
}
