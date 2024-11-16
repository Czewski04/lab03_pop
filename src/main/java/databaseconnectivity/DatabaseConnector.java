package databaseconnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private Connection connection;

    public DatabaseConnector(){
        String url = "jdbc:sqlite:src/database.db";
        try{
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to database");

        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Error occured while connecting to database");
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void closeConnection(){
        try{
            if(connection != null){
                connection.close();
            }
            System.out.println("Connection closed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
