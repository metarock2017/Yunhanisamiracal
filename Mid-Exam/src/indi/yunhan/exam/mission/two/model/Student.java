package indi.yunhan.exam.mission.two.model;

/**
 * Created by asus on 2017/8/19.
 */
public class Student {
    private String id;
    private String stuId;
    private String name;
    private String gender;
    private String grade;
    private String college;
    private String major;
    private String classNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public Student(String id, String stuId, String name, String gender, String grade, String college, String major, String classNum) {
        this.id = id;
        this.stuId = stuId;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.college = college;
        this.major = major;
        this.classNum = classNum;
    }

    public Student(){

    };
}
