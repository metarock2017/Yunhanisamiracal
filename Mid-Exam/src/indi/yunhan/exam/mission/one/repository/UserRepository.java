package indi.yunhan.exam.mission.one.repository;

import com.google.gson.Gson;
import indi.yunhan.exam.mission.common.DBConnection;
import indi.yunhan.exam.mission.common.YhError;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by asus on 2017/8/19.
 */
public class UserRepository {
    private DBConnection dbConnection = new DBConnection();
    private Gson gson = new Gson();
    private YhError error = new YhError();

    public boolean checkUser(String username) {
        String getSql = "SELECT * FROM user WHERE username='" + username + "'";
        ResultSet rs = dbConnection.excuteSqlWithResult(getSql);

        try {
            rs.first();
            if (rs.getRow() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String login(String username, String password) {
        String getSql = "SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "'";
        ResultSet rs = dbConnection.excuteSqlWithResult(getSql);

        try {
            rs.first();
            if (rs.getRow() == 1) {
                error.setErrorcode(0);
                error.setStatus(200);
                error.setErrorMsg("Login success");
                return gson.toJson(error);
            } else {
                error.setErrorcode(1);
                error.setStatus(500);
                error.setErrorMsg("Database error");
                return gson.toJson(error);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        error.setErrorcode(1);
        error.setStatus(500);
        error.setErrorMsg("Service error");
        return gson.toJson(error);
    }

    public String register(String username, String password) {
        String insertSql = "INSERT INTO user (username,password) VALUES('" + username + "','" + password + "')";

        boolean checkFlag = checkUser(username);

        if (!checkFlag) {
            boolean insertFlag = dbConnection.excuteSqlWithoutResult(insertSql);
            if (insertFlag) {
                error.setErrorcode(0);
                error.setStatus(200);
                return gson.toJson(error);
            } else {
                error.setErrorcode(1);
                error.setStatus(500);
                error.setErrorMsg("Register fail");
                return gson.toJson(error);
            }
        } else {
            error.setErrorcode(0);
            error.setStatus(200);
            error.setErrorMsg("User already exit");
            return gson.toJson(error);
        }
    }

    public String reSetPsd(String username, String reply, String passwordNew) {
        String getIdSql = "SELECT * FROM user WHERE username='" + username + "'";
        ResultSet rsUser = dbConnection.excuteSqlWithResult(getIdSql);

        try {
            rsUser.first();
            if (rsUser.getRow() > 0) {
                int id = rsUser.getInt("question_id");
                dbConnection = new DBConnection();
                String getQuestionSql = "SELECT * FROM question WHERE id='" + id + "' AND reply='" + reply + "'";
                ResultSet rsQuestion = dbConnection.excuteSqlWithResult(getQuestionSql);
                rsQuestion.first();
                if (rsQuestion.getRow() == 1) {
                    dbConnection = new DBConnection();
                    String updateSql = "UPDATE user SET password='" + passwordNew + "' WHERE username='" + username + "'";

                    boolean updateFlag = dbConnection.excuteSqlWithoutResult(updateSql);
                    if (updateFlag) {
                        error.setErrorcode(0);
                        error.setStatus(200);
                        error.setErrorMsg("Update success");
                        return gson.toJson(error);
                    } else {
                        error.setErrorcode(1);
                        error.setStatus(200);
                        error.setErrorMsg("Update fail");
                        return gson.toJson(error);
                    }
                } else {
                    error.setErrorcode(-1);
                    error.setStatus(200);
                    error.setErrorMsg("User not set question");
                    return gson.toJson(error);
                }
            } else {
                error.setErrorcode(-1);
                error.setStatus(200);
                error.setErrorMsg("User not exit");
                return gson.toJson(error);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        error.setErrorcode(2);
        error.setStatus(500);
        error.setErrorMsg("Database error");
        return gson.toJson(error);
    }
}
