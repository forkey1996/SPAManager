/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrappers;

/**
 *
 * @author AIM632
 */
public class CustomerWrapper {
    
       private int customerID;
       private int walletID;
       private String customerName;

    public CustomerWrapper() {}

    public CustomerWrapper(int customerID, int walletID, String customerName) {
        this.customerID = customerID;
        this.walletID = walletID;
        this.customerName = customerName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getWalletID() {
        return walletID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setWalletID(int walletID) {
        this.walletID = walletID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
       
}
