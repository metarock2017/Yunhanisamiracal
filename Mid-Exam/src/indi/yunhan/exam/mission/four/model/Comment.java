package indi.yunhan.exam.mission.four.model;

import java.util.LinkedList;

/**
 * Created by asus on 2017/8/19.
 */
public class Comment {
    private String id;
    private String content;
    private String user_id;
    private String status;

    private LinkedList<Comment> subComments = new LinkedList<>();

    public LinkedList<Comment> getSubComments() {
        return subComments;
    }

    public void setSubComments(LinkedList<Comment> subComments) {
        this.subComments = subComments;
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

    public Comment() {

    }

    public Comment(String id, String content, String user_id, String status) {
        this.id = id;
        this.content = content;
        this.user_id = user_id;
        this.status = status;
    }

    public void addSubComment(Comment comment){
        this.getSubComments().add(comment);
    }
}
