/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrappers;

import java.io.Serializable;

/**
 *
 * @author Ioan-Emanuel Popescu
 */
public class BoughtProductWrapper implements Serializable{
    
    private int ProductID, TransactionID;
    private int Name;
    private double price;
    private int amount;

    public BoughtProductWrapper(int ProductID, int TransactionID, int Name, double price, int amount) {
        this.ProductID = ProductID;
        this.TransactionID = TransactionID;
        this.Name = Name;
        this.price = price;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getName() {
        return Name;
    }

    public double getPrice() {
        return price;
    }

    public int getProductID() {
        return ProductID;
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setName(int Name) {
        this.Name = Name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public void setTransactionID(int TransactionID) {
        this.TransactionID = TransactionID;
    }
    
        
    
}
