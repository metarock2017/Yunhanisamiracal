package indi.yunhan.exam.mission.one.model;

/**
 * Created by asus on 2017/8/19.
 */
public class Question {
    private String content;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Question(String content, int status) {
        this.content = content;
        this.status = status;
    }

    public Question(){

    }
}
