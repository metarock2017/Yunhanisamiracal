package indi.yunhan.model.gameroom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by asus on 2017/8/9.
 */
public class GameRoom {
    private String id;
    private String word_id;
    private String md5;

    private String max_num;
    private String now_num;

    private String word_one;
    private String word_two;

    private String undercover;

    public String getUndercover() {
        return undercover;
    }

    public void setUndercover(String undercover) {
        this.undercover = undercover;
    }

    private boolean hasUndercover = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord_id() {
        return word_id;
    }

    public void setWord_id(String word_id) {
        this.word_id = word_id;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getMax_num() {
        return max_num;
    }

    public void setMax_num(String max_num) {
        this.max_num = max_num;
    }

    public String getNow_num() {
        return now_num;
    }

    public void setNow_num(String now_num) {
        this.now_num = now_num;
    }

    public String getWord_one() {
        return word_one;
    }

    public void setWord_one(String word_one) {
        this.word_one = word_one;
    }

    public String getWord_two() {
        return word_two;
    }

    public void setWord_two(String word_two) {
        this.word_two = word_two;
    }

    public boolean isHasUndercover() {
        return hasUndercover;
    }

    public void setHasUndercover(boolean hasUndercover) {
        this.hasUndercover = hasUndercover;
    }

    public GameRoom(String id, String word_id, String md5, String max_num, String now_num) {
        this.id = id;
        this.word_id = word_id;
        this.md5 = md5;
        this.max_num = max_num;
        this.now_num = now_num;
    }

    public GameRoom(ResultSet rs) {
        try {
            this.id = rs.getString("id");
            this.word_id = rs.getString("word_id");
            this.md5 = rs.getString("md5");
            this.max_num = rs.getString("max_num");
            this.now_num = rs.getString("now_num");
            this.undercover = rs.getString("undercover");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public GameRoom(Map<String, String> map) {
        this.id = map.get("id");
        this.word_id = map.get("word_id");
        this.md5 = map.get("md5");
        this.max_num = map.get("max_num");
        this.now_num = map.get("now_num");
        this.undercover = map.get("undercover");
    }

    public void setWord(String[] words) {
        this.setWord_one(words[0]);
        this.setWord_two(words[1]);
    }
}
