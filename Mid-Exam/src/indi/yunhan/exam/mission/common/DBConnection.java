package indi.yunhan.exam.mission.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by yunhan on 2017/8/19.
 */

public class DBConnection {

    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String dbAddress = "jdbc:mysql://localhost:3306/mid_exam?autoReconnect=true&useSSL=false";

    private String userName = "admin";
    private String passWord = "admin";

    private Connection connection = null;
    private Statement statement = null;

    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbAddress, userName, passWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean excuteSqlWithoutResult(String sql) {
        try {
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);

            this.statement.close();
            connection.close();
            if (rows != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public ResultSet excuteSqlWithResult(String sql){
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            return rs;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}