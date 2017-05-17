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
public class ProductWrapper implements Serializable{
    
    private int productID;
    private String productName;
    private double price;
    private int typeID;

    public ProductWrapper() {}

    public ProductWrapper(int productID, String productName, double price, int typeID) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.typeID = typeID;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    @Override
    public String toString() {
        return "ProductWrapper{" + "productID=" + productID + ", productName=" + productName + ", price=" + price + ", typeID=" + typeID + '}';
    }
    
}
