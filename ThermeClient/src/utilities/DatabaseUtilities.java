/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import client.connection.ClientManager;
import core.connection.RequestWrapper;
import java.awt.List;

/**
 *
 * @author fernando
 */
public class DatabaseUtilities {
    
    private DatabaseUtilities() {}
    
    public static List<AreaWrapper> getAllAreas() {
	RequestWrapper request = new RequestWrapper("GetAllAreas");
	ClientManager client = new ClientManager();
	client.startClient(request);
	if (client.getResponse() != null) {
	    return (List<AreaWrapper>)client.getResponse();
	}
	return null;
    }
    
    public static List<ProductWrapper> getAllProducts() {
	RequestWrapper request = new RequestWrapper("GetAllProducts");
	ClientManager client = new ClientManager();
	client.startClient(request);
	if (client.getResponse() != null) {
	    return (List<ProductWrapper>)client.getResponse();
	}
	return null;
    }
    
        
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
    
    public static CustomerWrapper addCustomer(String CustomerName) {
	RequestWrapper request = new RequestWrapper("Cashout");
	request.getRequestParameters().put("CustomerName", CustomerName);
	ClientManager client = new ClientManager();
	client.startClient(request);
	if (client.getResponse() != null) {
	    return (CustomerWrapper)client.getResponse();
	}
	return null;
    }
}
