package Database;

import java.sql.*;

/**
 * Created by yunhan on 2017/5/16.
 */

public class DBConnection {

    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String dbAddress = "jdbc:mysql://localhost:3306/Redrock_java?autoReconnect=true&useSSL=false";

    private String userName = "admin";
    private String passWord = "admin";

    private Connection connection = null;
    private Statement statement = null;

    private static DBConnection instance = new DBConnection();

    private DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("connect...");
            connection = DriverManager.getConnection(dbAddress, userName, passWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getDBConnection(){
        return instance;
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

            // 关闭
            return rs;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    };


    public boolean excuteSqlWithResultAndBoo(String sql) {
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            rs.last();

            if (rs.getRow() == 1) {
                rs.close();
                statement.close();
                connection.close();
                return true;
            } else {
                rs.close();
                statement.close();
                connection.close();
                return false;
            }
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
            return false;
        }
    }


}