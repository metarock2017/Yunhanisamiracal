package indi.yunhan.exam.mission.one.repository;

import com.google.gson.Gson;
import indi.yunhan.exam.mission.common.YhError;
import indi.yunhan.exam.mission.one.model.Question;
import indi.yunhan.exam.mission.common.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by asus on 2017/8/19.
 */
public class QuestionRepository {
    private DBConnection dbConnection = new DBConnection();
    private Gson gson = new Gson();
    private YhError error = new YhError();

    public boolean hasQuestion() {
        return false;
    }

    public boolean hasQuestion(String username) {
        String getSql = "SELECT * FROM user WHERE username='" + username + "'";
        ResultSet rs = dbConnection.excuteSqlWithResult(getSql);

        try {
            rs.first();
            if (rs.getRow() == 1) {
                int id = rs.getInt("question_id");
                if (id <= 0) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getQuestion(String username) {
        String getIdSql = "SELECT * FROM user WHERE username='" + username + "'";
        ResultSet rsId = dbConnection.excuteSqlWithResult(getIdSql);
        try {
            rsId.first();
            if (rsId.getRow() == 1) {
                int question_id = rsId.getInt("question_id");
                String getQuestionSql = "SELECT * FROM question WHERE id='" + question_id + "'";

                ResultSet rsQuestion = dbConnection.excuteSqlWithResult(getQuestionSql);
                rsQuestion.first();
                if (rsQuestion.getRow() == 1) {
                    Question question = new Question();
                    question.setContent(rsQuestion.getString("question"));
                    question.setStatus(1);
                    return gson.toJson(question);
                } else {
                    error.setErrorcode(1);
                    error.setStatus(500);
                    error.setErrorMsg("Database error");
                    return gson.toJson(error);
                }
            } else {
                error.setErrorcode(0);
                error.setStatus(200);
                error.setErrorMsg("Couldn't find question");
                return gson.toJson(error);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        error.setErrorcode(1);
        error.setStatus(500);
        error.setErrorMsg("Database error");
        return gson.toJson(error);
    }

    public String setQuestion(String username, String content, String reply) {
        if (!hasQuestion(username)) {
            String setQuestionSql = "INSERT INTO question (question,reply) VALUES ('" + content + "','" + reply + "')";

            boolean insertFlag = dbConnection.excuteSqlWithoutResult(setQuestionSql);

            if (insertFlag) {
                String getQuestionSql = "SELECT * FROM question WHERE question='" + content + "' AND reply='" + reply + "'";
                try {
                    dbConnection = new DBConnection();
                    ResultSet rsQuestion = dbConnection.excuteSqlWithResult(getQuestionSql);
                    rsQuestion.first();
                    int count = rsQuestion.getRow();
                    if (count > 0) {
                        int id = rsQuestion.getInt("id");
                        String updateSql = "UPDATE user SET question_id='" + id + "' WHERE username='" + username + "'";

                        boolean updateFlag = dbConnection.excuteSqlWithoutResult(updateSql);
                        if (updateFlag) {
                            error.setErrorcode(0);
                            error.setStatus(200);
                            error.setErrorMsg("ok");
                            return gson.toJson(error);
                        }
                    } else {
                        error.setStatus(1);
                        error.setStatus(500);
                        error.setErrorMsg("Database error");
                        return gson.toJson(error);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                error.setErrorcode(1);
                error.setStatus(500);
                error.setErrorMsg("Suddenly stop");
                return gson.toJson(error);
            } else {
                error.setStatus(1);
                error.setStatus(500);
                error.setErrorMsg("Database error");
                return gson.toJson(error);
            }
        } else {
            error.setErrorcode(0);
            error.setStatus(200);
            error.setErrorMsg("Already has question");
            return gson.toJson(error);
        }
    }
}
