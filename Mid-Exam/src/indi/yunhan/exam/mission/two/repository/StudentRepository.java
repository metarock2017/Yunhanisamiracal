package indi.yunhan.exam.mission.two.repository;

import com.google.gson.Gson;
import indi.yunhan.exam.mission.common.DBConnection;
import indi.yunhan.exam.mission.common.YhError;
import indi.yunhan.exam.mission.two.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by asus on 2017/8/19.
 */
public class StudentRepository {
    private DBConnection dbConnection = new DBConnection();
    private Gson gson = new Gson();
    private YhError error = new YhError();

    public String show() {
        String showSql = "SELECT * FROM student";

        LinkedList<Student> students = new LinkedList<>();
        ResultSet rs = dbConnection.excuteSqlWithResult(showSql);

        try {
            rs.first();
            while (rs.next()) {
                Student student = new Student(
                        rs.getString("id"),
                        rs.getString("stuId"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("grade"),
                        rs.getString("college"),
                        rs.getString("major"),
                        rs.getString("class"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gson.toJson(students);
    }

    public String search(int page, int per_page, String sortby, String sort, String name, String stuId) {
        if (page <= 0) {
            error.setErrorcode(-1);
            error.setStatus(200);
            error.setErrorMsg("Please input page");
            return gson.toJson(error);
        }
        if (per_page <= 0) {
            error.setErrorcode(-1);
            error.setStatus(200);
            error.setErrorMsg("Please input per_page");
            return gson.toJson(error);
        }
        if (sort.equals("")) {
            sort = "ASC";
        }

        String getSql = "SELECT * FROM student WHERE name LIKE '%" + name +
                "%' AND stuId LIKE '%" + stuId +
                "%' ORDER BY " + sort +
                " " + sortby +
                " LIMIT " + (page - 1) * per_page +
                "," + per_page;
        System.out.println(getSql);
        LinkedList<Student> students = new LinkedList<>();

        ResultSet rs = dbConnection.excuteSqlWithResult(getSql);

        try {
            rs.first();
            while (rs.next()) {
                Student student = new Student(
                        rs.getString("id"),
                        rs.getString("stuId"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("grade"),
                        rs.getString("college"),
                        rs.getString("major"),
                        rs.getString("class"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gson.toJson(students);
    }

    public boolean checkStu(String stuId) {
        String getSql = "SELECT * FROM student WHERE stuId='" + stuId + "'";
        ResultSet rs = dbConnection.excuteSqlWithResult(getSql);

        try {
            rs.first();
            int count = rs.getRow();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String create(String stuId, String name, int gender, int grade, String college, String major, String classNum) {
        String createSql = "INSERT INTO student " +
                "(stuId,name,gender,grade,college,major,classNum) VALUES " +
                "('" + stuId + "','" + name + "','" + gender + "','" + grade + "','" + college + "','" + major + "','" + classNum + "')";
        boolean hasStu = checkStu(stuId);

        if (!hasStu) {
            dbConnection = new DBConnection();
            boolean insertFlag = dbConnection.excuteSqlWithoutResult(createSql);

            if (insertFlag) {
                error.setErrorcode(0);
                error.setStatus(200);
                error.setErrorMsg("Create success");
                return gson.toJson(error);
            } else {
                error.setErrorcode(1);
                error.setStatus(500);
                error.setErrorMsg("Create fail");
                return gson.toJson(error);
            }
        } else {
            error.setErrorcode(-1);
            error.setStatus(200);
            error.setErrorMsg("Student already exit");
            return gson.toJson(error);
        }
    }

    public String delete(String stuId) {
        String deleteSql = "UPDATE student SET status=0 WHERE stuId='" + stuId + "'";
        boolean deleteFlag = dbConnection.excuteSqlWithoutResult(deleteSql);

        if (deleteFlag) {
            error.setErrorcode(0);
            error.setStatus(200);
            error.setErrorMsg("Delete success");
            return gson.toJson(error);
        } else {
            error.setErrorcode(1);
            error.setStatus(500);
            error.setErrorMsg("Delete fail");
            return gson.toJson(error);
        }
    }
}
