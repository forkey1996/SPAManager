/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.connection;

import core.connection.RequestWrapper;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class ManageRequestThread extends Thread {
    Socket clientSocket;

    public ManageRequestThread(Socket clientSocket) {
	this.clientSocket = clientSocket;
    }
    
    @Override
    public void run() {
	try (ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
	    ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream())) {
	    processRequest(input, output);
	} catch (IOException IOEx) {
	    System.out.println("Error");
	}
    }

    private void processRequest(ObjectInputStream input, ObjectOutputStream output) {
	try {
	    RequestWrapper request = (RequestWrapper)input.readObject();
	    if (request != null) {
		switch (request.getRequestName()) {
		    default:
			System.out.println(request.getRequestName());
			output.writeObject("Bad request!");
			break;
		}
	    }
	} catch (IOException ex) {
	    
	} catch (ClassNotFoundException ex) {
	    
	}
	

    }
}
