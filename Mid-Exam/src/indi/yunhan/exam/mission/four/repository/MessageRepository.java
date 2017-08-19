package indi.yunhan.exam.mission.four.repository;

import com.google.gson.Gson;
import indi.yunhan.exam.mission.common.DBConnection;
import indi.yunhan.exam.mission.common.YhError;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by asus on 2017/8/19.
 */
public class MessageRepository {
    private DBConnection dbConnection = new DBConnection();
    private Gson gson = new Gson();
    private YhError error = new YhError();

    public String getMessage() {
        String getMsgSql = "SELECT * FROM message WHERE user_id=1";
        String getComSql = "SELECT * FROM comment WHERE message_id=1";
        String getSubComSql = "SELECT * FROM comment WHERE father_id IN (SELECT id FROM comment WHERE message_id=1)";

        Set father_id = new HashSet();
        return null;
    }
}
