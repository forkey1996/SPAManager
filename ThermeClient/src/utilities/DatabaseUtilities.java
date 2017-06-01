/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import client.connection.ClientManager;
import client.connection.ConnectionError;
import core.connection.RequestWrapper;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import wrappers.AreaWrapper;
import wrappers.BoughtProductWrapper;
import wrappers.ProductWrapper;
import wrappers.TransactionWrapper;

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
	if(client.startClient(request) != ConnectionError.NOERROR)
            System.exit(-1);
	if (client.getResponse() != null) {
            try {
                return (ArrayList<AreaWrapper>)client.getResponse();
            } catch (ClassCastException ex) {
                JOptionPane.showMessageDialog(null, client.getResponse(), "Error", JOptionPane.ERROR_MESSAGE);
                
            }
	}
	return null;
    }

    public static ArrayList<ProductWrapper> getAllProducts() {
	RequestWrapper request = new RequestWrapper("GetAllProducts");
	ClientManager client = new ClientManager();
	if(client.startClient(request) != ConnectionError.NOERROR)
            System.exit(-1);
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
	if(client.startClient(request) != ConnectionError.NOERROR)
            System.exit(-1);
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
	if(client.startClient(request) != ConnectionError.NOERROR)
            System.exit(-1);
	if (client.getResponse() != null) {
	    return (boolean)client.getResponse();
	}
	return false;
    }
    
    public static Integer buyProduct(Integer customerID, Integer productID, Integer quantity) {
	RequestWrapper request = new RequestWrapper("BuyProduct");
	request.getRequestParameters().put("CustomerID", customerID.toString());
	request.getRequestParameters().put("ProductID", productID.toString());
	request.getRequestParameters().put("Quantity", quantity.toString());
	ClientManager client = new ClientManager();
	if(client.startClient(request) != ConnectionError.NOERROR)
            System.exit(-1);
	if (client.getResponse() != null) {
	    return (Integer)client.getResponse();
	}
	return -1;
    }



    
    /**
     *
     * @param customerID
     * @return
     */
    public static ArrayList<BoughtProductWrapper> cashout(Integer customerID) {
	RequestWrapper request = new RequestWrapper("Cashout");
	request.getRequestParameters().put("CustomerID", customerID.toString());
        ClientManager client = new ClientManager();
	if(client.startClient(request) != ConnectionError.NOERROR)
            System.exit(-1);
	if (client.getResponse() != null) {
	    return (ArrayList<BoughtProductWrapper>)client.getResponse();
	}
	return null;
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
	if(client.startClient(request) != ConnectionError.NOERROR)
            System.exit(-1);
	if (client.getResponse() != null) {
	    return (Integer)client.getResponse();
	}
	return null;
    }
    
    public static Integer getAreaID(String areaName)
    {
        RequestWrapper request = new RequestWrapper("GetAreaIdByName");
	request.getRequestParameters().put("areaName", areaName);
	ClientManager client = new ClientManager();
	if(client.startClient(request) != ConnectionError.NOERROR)
            System.exit(-1);
	if (client.getResponse() != null) {
	    return (Integer)client.getResponse();
	}
	return null;
    }
    
    public static Integer getNumberOfCustomers(Integer indexArea)
    {
        RequestWrapper request = new RequestWrapper("GetNumberOfCustomers");
	request.getRequestParameters().put("indexArea", indexArea.toString());
	ClientManager client = new ClientManager();
	if(client.startClient(request) != ConnectionError.NOERROR)
            System.exit(-1);
	if (client.getResponse() != null) {
	    return (Integer)client.getResponse();
	}
	return null;
    }
    
    public static Integer deleteTransactions(Integer customerID)
    {
        RequestWrapper request = new RequestWrapper("DeleteTransactions");
	request.getRequestParameters().put("CustomerID", customerID.toString());
        ClientManager client = new ClientManager();
	if(client.startClient(request) != ConnectionError.NOERROR)
            System.exit(-1);
	if (client.getResponse() != null) {
            if (client.getResponse() instanceof String){
                System.out.println((String)client.getResponse());
                return -1;
            }
	    return (Integer)client.getResponse();
	}
	return null;
    }

}
