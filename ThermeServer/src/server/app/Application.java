/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.connection.ServerManager;
import server.database.DatabaseConnection;

/**
 *
 * @author fernando
 */
public class Application {
    public static void main(String[] args) {
        try {
            DatabaseConnection.connect();
            ServerManager.runServer();
            DatabaseConnection.closeConnection();
        } catch (IOException ex) {
            System.out.println("Could not start server");
        } catch (SQLException ex) {
            System.out.println("Database connection error");
        }
    }
}
