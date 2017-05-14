/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.connection;

import core.connection.RequestWrapper;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.omg.CORBA.portable.UnknownException;

/**
 *
 * @author fernando
 */

public class ClientManager {
    private Object response;

    public Object getResponse() {
	return response;
    }
    
    public ConnectionError startClient(RequestWrapper request) throws UnknownHostException {
	InetAddress address = InetAddress.getLocalHost();
	try (Socket clientSocket = new Socket(address, RequestWrapper.PORT); 	    
	    ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
	    ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream())) {
	    output.writeObject(request);
	    response = input.readObject();
	} catch (UnknownHostException hostEx) { 
	    response = "Unreachable Host:" + hostEx.getMessage(); 
	    return ConnectionError.UNKNOWNHOSTEXCEPTION;
	} catch (IOException IOEx) {
	    response = "I/O error: " + IOEx.getMessage();
	    return ConnectionError.IOEXCEPTION;
	} catch (ClassNotFoundException classEx) {
	    response = "Bad deserialization: " + classEx.getMessage();
	    return ConnectionError.CLASSNOTFOUNDEXCEPTION;
	}
	return ConnectionError.NOERROR;
    }
}
