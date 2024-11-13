package mainpackage;

import client.ClientDaoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        ClientDaoImpl clientDao = new ClientDaoImpl();
        Connection conn = clientDao.getConnection();
        if(conn != null) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS t1");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS t1(id INTEGER PRIMARY KEY, b TEXT)");
            statement.executeUpdate("INSERT INTO t1 VALUES(1, 'A')");
            statement.executeUpdate("INSERT INTO t1 VALUES(2, 'B')");
            statement.executeUpdate("INSERT INTO t1 VALUES(3, 'D')");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM t1");
            while(resultSet.next()) {
                System.out.println(resultSet.getString("id") + " " + resultSet.getString("b"));
            }
            statement.executeUpdate("UPDATE t1 SET b='C' WHERE id=3");
            resultSet = statement.executeQuery("SELECT * FROM t1");
            while(resultSet.next()) {
                System.out.println(resultSet.getString("id") + " " + resultSet.getString("b"));
            }

            statement.executeUpdate("DELETE FROM t1 WHERE id=1");

            statement.close();
            clientDao.closeConnection();
        }
    }
}
