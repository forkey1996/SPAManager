/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ioan-Emanuel Popescu
 */
public class DatabaseConnection {
    public static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/spamanager?autoReconnect=true&useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    
    private DatabaseConnection()
    {
        
    }
    
    public static void connect()
    {
        try{
            if(connection == null){
                Class.forName(DRIVER);
                DatabaseConnection.connection = DriverManager.getConnection(URL,"root","");
                System.out.println("Connection established successfully with the database server");
            }
            else
            {
                System.out.println("Already connected to the database server!");
            }
           
        }
        catch(ClassNotFoundException | SQLException err)
        {
            System.out.println("Error: "+err.getMessage());
        }
    }
    
    public static Connection getConnection()
    {
        return connection;
    }
    
    
}
