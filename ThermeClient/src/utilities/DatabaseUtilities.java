/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import client.connection.ClientManager;
import core.connection.RequestWrapper;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import wrappers.AreaWrapper;
import wrappers.CustomerWrapper;
import wrappers.ProductWrapper;

/**
 *
 * @author fernando
 */
public class DatabaseUtilities {
    
    private DatabaseUtilities() {}
    
    /**
     *
     * @return
     */
    public static ArrayList<AreaWrapper> getAllAreas() {
	RequestWrapper request = new RequestWrapper("GetAllAreas");
	ClientManager client = new ClientManager();
	client.startClient(request);
	if (client.getResponse() != null) {
            try {
                return (ArrayList<AreaWrapper>)client.getResponse();
            } catch (ClassCastException ex) {
                JOptionPane.showMessageDialog(null, client.getResponse(), "Error", JOptionPane.ERROR_MESSAGE);
                
            }
	}
	return null;
    }
    
    /**
     *
     * @return
     */
    public static ArrayList<ProductWrapper> getAllProducts() {
	RequestWrapper request = new RequestWrapper("GetAllProducts");
	ClientManager client = new ClientManager();
	client.startClient(request);
	if (client.getResponse() != null) {
	    return (ArrayList<ProductWrapper>)client.getResponse();
	}
	return null;
    }
    
    /**
     *
     * @param customerID
     * @return
     */
    public static boolean checkCustomer(Integer customerID) {
	RequestWrapper request = new RequestWrapper("CheckCustomer");
	request.getRequestParameters().put("CustomerID", customerID.toString());
	ClientManager client = new ClientManager();
	client.startClient(request);
	if (client.getResponse() != null) {
	    return (boolean)client.getResponse();
	}
	return false;
    }
    
    /**
     *
     * @param customerID
     * @param areaID
     * @return
     */
    public static boolean changeArea(Integer customerID, Integer areaID) {
	RequestWrapper request = new RequestWrapper("ChangeArea");
	request.getRequestParameters().put("CustomerID", customerID.toString());
	request.getRequestParameters().put("AreaID", areaID.toString());
	ClientManager client = new ClientManager();
	client.startClient(request);
	if (client.getResponse() != null) {
	    return (boolean)client.getResponse();
	}
	return false;
    }
    
    public static boolean buyProduct(Integer customerID, Integer productID, Integer quantity) {
	RequestWrapper request = new RequestWrapper("BuyProduct");
	request.getRequestParameters().put("CustomerID", customerID.toString());
	request.getRequestParameters().put("ProductID", productID.toString());
	request.getRequestParameters().put("Quantity", quantity.toString());
	ClientManager client = new ClientManager();
	client.startClient(request);
	if (client.getResponse() != null) {
	    return (boolean)client.getResponse();
	}
	return false;
    }
    
    /**
     *
     * @param customerID
     * @return
     */
    public static boolean cashout(Integer customerID) {
	RequestWrapper request = new RequestWrapper("Cashout");
	request.getRequestParameters().put("CustomerID", customerID.toString());
	ClientManager client = new ClientManager();
	client.startClient(request);
	if (client.getResponse() != null) {
	    return (boolean)client.getResponse();
	}
	return false;
    }
    
    /**
     *
     * @param CustomerName
     * @return
     */
    public static Integer addCustomer(String CustomerName) {
	RequestWrapper request = new RequestWrapper("AddCustomer");
	request.getRequestParameters().put("CustomerName", CustomerName);
	ClientManager client = new ClientManager();
	client.startClient(request);
	if (client.getResponse() != null) {
	    return (Integer)client.getResponse();
	}
	return null;
    }
}
