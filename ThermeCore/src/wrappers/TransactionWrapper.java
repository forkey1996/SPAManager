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
public class TransactionWrapper implements Serializable {
    private final int TransactionID;
    private int ProductID, Amount;

    public TransactionWrapper(int transactionID, int productID, int amount)
    {
        TransactionID = transactionID;
        ProductID = productID;
        Amount = amount;
    }
    
    
    public int getAmount() {
        return Amount;
    }

    public int getProductID() {
        return ProductID;
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    @Override
    public String toString() {
        return "TransactionID: " + TransactionID + " ProductID: " + ProductID + " Amount: " + Amount;
    }
    
}
