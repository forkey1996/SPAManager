/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.app;

import java.io.IOException;
import server.connection.ServerManager;
import server.database.DatabaseConnection;

/**
 *
 * @author fernando
 */
public class Application {
    public static void main(String[] args) throws IOException {
	
        DatabaseConnection.connect();
        ServerManager.runServer();
        
    }
}
