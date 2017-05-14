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
public class AreaWrapper {
    private int areaID;
    private String areaName;
    private int productID;

    public AreaWrapper() {}

    public AreaWrapper(int areaID, String areaName, int productID) {
        this.areaID = areaID;
        this.areaName = areaName;
        this.productID = productID;
    }

    public int getAreaID() {
        return areaID;
    }

    public String getAreaName() {
        return areaName;
    }

    public int getProductID() {
        return productID;
    }

    public void setAreaID(int areaID) {
        this.areaID = areaID;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
    
}
