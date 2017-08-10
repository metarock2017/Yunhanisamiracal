package Database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by asus on 2017/8/2.
 */
public class DBController {
    DBConnection dbConnection = null;

    public DBController(){
        dbConnection = DBConnection.getDBConnection();
    }

    public boolean add(){
        String sql = "INSERT INTO test (name) VALUES ('test')";

        return dbConnection.excuteSqlWithoutResult(sql);
    }

    public void select(){
        String sql = "SELECT * FROM test";

        ResultSet rs = dbConnection.excuteSqlWithResult(sql);

        try {
            while (rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");

                System.out.println("id : " + id);
                System.out.println("name : " + name);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean update(int id){
        String sql = "UPDATE test SET name='new' WHERE id='" + id + "'";

        return dbConnection.excuteSqlWithoutResult(sql);
    }

    public boolean delete(int id){
        String sql = "UPDATE test SET name='null' WHERE id='" + id + "'";

        return dbConnection.excuteSqlWithoutResult(sql);
    }

    public static void main(String[] args) {
        new DBController().add();
        new DBController().select();
        new DBController().update(1);
        new DBController().delete(1);

    }
}
