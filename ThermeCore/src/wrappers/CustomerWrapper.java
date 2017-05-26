/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrappers;

import java.io.Serializable;

/**
 *
 * @author AIM632
 */
public class CustomerWrapper implements Serializable{
    
       private int customerID;
       private String customerName;

    public CustomerWrapper() {}

    public CustomerWrapper(int customerID, String customerName) {
        this.customerID = customerID;
        
        this.customerName = customerName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "CustomerWrapper{" + "customerID=" + customerID + ", customerName=" + customerName + '}';
    }
       
}
