package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClientDaoImpl implements ClientDao {

    private Connection connection;

    @Override
    public void addClient(Client client) {

    }

    public ClientDaoImpl(){
        String url = "jdbc:sqlite:src/main/resources/client/client.db";
        try{
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to database");

        }catch(SQLException e){
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }
}
