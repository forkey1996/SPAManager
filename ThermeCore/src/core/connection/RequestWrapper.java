/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.connection;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 *
 * @author fernando
 */
public class RequestWrapper implements Serializable {
    
    public final static int PORT = 5001;
    
    private String requestName;
    private Dictionary<String, String> requestParameters = new Hashtable<String, String>();

    public RequestWrapper(String requestName) {
	this.requestName = requestName;
    }

    public RequestWrapper(String requestName, Dictionary<String, String> requestParameters) {
	this.requestName = requestName;
	this.requestParameters = requestParameters;
    }

    public void setRequestName(String requestName) {
	this.requestName = requestName;
    }

    public void setRequestParameters(Dictionary<String, String> requestParameters) {
	this.requestParameters = requestParameters;
    }
    
    public String getRequestName() {
	return requestName;
    }

    public Dictionary<String, String> getRequestParameters() {
	return requestParameters;
    }
}
