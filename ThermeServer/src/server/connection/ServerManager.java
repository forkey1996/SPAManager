/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.connection;

import core.connection.RequestWrapper;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author fernando
 */
public class ServerManager {
    private static ServerSocket serverSocket;
    
    private ServerManager() {}
    
    public static void runServer() throws IOException {
        serverSocket = new ServerSocket(RequestWrapper.PORT);
        startListening();
    }
    
    private static void startListening() {
	while(true)
	{
	    try {
		Socket clientSocket = serverSocket.accept();
		ManageRequestThread m = new ManageRequestThread(clientSocket);
		m.start();
	    } catch (IOException IOEx) {
		System.out.println("Server manager start listening error");
	    }
	}
    }
}
