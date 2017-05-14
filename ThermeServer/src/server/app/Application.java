/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import server.connection.ServerManager;
import server.database.DatabaseConnection;
import static server.database.DatabaseConnection.connection;

/**
 *
 * @author fernando
 */
public class Application {
    public static void main(String[] args) throws IOException {
	//ServerManager.runServer();
        DatabaseConnection.connect();
        
        String queryCustomers = "select * from customer;";
        String queryProducts = "select * from product";
        
        Statement statement = null;
        
        try{
            Connection con = DatabaseConnection.getConnection();
            statement = con.createStatement();
            
            ResultSet rs1 = statement.executeQuery(queryCustomers);
            while(rs1.next())
            {
                System.out.println(rs1.getString("fullName"));
            }
            
            System.out.println();
            
            ResultSet rs2 = statement.executeQuery(queryProducts);
            while(rs2.next())
            {
                System.out.println(rs2.getString("name"));
            }
            
        }catch(SQLException err)
        {
            err.printStackTrace();
        }
        
    }
}
