package indi.yunhan.exam.mission.four.model;

import java.util.LinkedList;

/**
 * Created by asus on 2017/8/19.
 */
public class Message {
    private String id;
    private String content;
    private String user_id;
    private String status;

    private LinkedList<Comment> comments = new LinkedList<>();

    public LinkedList<Comment> getComments() {
        return comments;
    }

    public void setComments(LinkedList<Comment> comments) {
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Message(){

    }

    public Message(String id, String content, String user_id, String status) {
        this.id = id;
        this.content = content;
        this.user_id = user_id;
        this.status = status;
    }

    public void addComment(Comment comment){
        this.getComments().add(comment);
    }
}
