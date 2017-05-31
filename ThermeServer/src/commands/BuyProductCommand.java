/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import com.mysql.jdbc.Statement;
import core.connection.RequestWrapper;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import server.database.DatabaseConnection;

/**
 *
 * @author AIM632
 */
public class BuyProductCommand extends Command {

    private static final int TICKET = 1;
    private static final int PRODUCT = 2;

    public BuyProductCommand(RequestWrapper request, ObjectOutputStream output) {
        super(request, output);
    }

    @Override
    public void Execute() {
        
        String query;
        int rowsAffected;
        int idTransaction = 0;
        int quantityInserted = 0;
        int productType = 0;
        int area = 0;

        Connection con = DatabaseConnection.getConnection();

        try {
            if (Integer.valueOf(request.getRequestParameters().get("Quantity")) == 0) {
                output.writeObject(quantityInserted);
            } else {

                //verify the type of product
                query = "SELECT typeID FROM product WHERE productID = ?";

                PreparedStatement statement = con.prepareStatement(query);

                statement.setInt(1, Integer.valueOf(request.getRequestParameters().get("ProductID")));

                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    productType = result.getInt(1);
                }
         
                quantityInserted = Integer.valueOf(request.getRequestParameters().get("Quantity"));
                if (productType == TICKET) {
                    
                    if(quantityInserted > 1)
                        quantityInserted = 1;
                    
                    //the areaID of the productID
                    query = "SELECT areaID FROM area WHERE productID = ?";

                    statement = con.prepareStatement(query);

                    statement.setInt(1, Integer.valueOf(request.getRequestParameters().get("ProductID")));

                    result = statement.executeQuery();
                    if (result.next()) {
                        area = result.getInt(1);
                    }
                        
                    //check if it is already in access table; if not, then introduce it
                    query = "SELECT areaID FROM access WHERE customerID = ?";

                    statement = con.prepareStatement(query);

                    statement.setInt(1, Integer.valueOf(request.getRequestParameters().get("CustomerID")));

                    result = statement.executeQuery();
                    while (result.next()) {
                        
                        if (result.getInt(1) == area) {
                            quantityInserted = 0;
                            break;
                        }
                    }
                   
                    if (quantityInserted != 0) {
                        //insert in access table
                        query = "INSERT INTO access VALUES(?,?)";

                        statement = con.prepareStatement(query);

                        statement.setInt(1, Integer.valueOf(request.getRequestParameters().get("CustomerID")));
                        statement.setInt(2, area);

                        rowsAffected = statement.executeUpdate();
                        if (rowsAffected == 0) {
                            quantityInserted = 0;
                        }
                      
                    }

                }

                if (quantityInserted > 0) {
                    // Add a new transaction
                    query = "INSERT INTO transaction(productID, quantity) VALUES(?,?)";

                    statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    statement.setInt(1, Integer.valueOf(request.getRequestParameters().get("ProductID")));
                    statement.setInt(2, quantityInserted);

                    rowsAffected = statement.executeUpdate();

                    result = statement.getGeneratedKeys();
                    if (result.next()) {
                        idTransaction = result.getInt(1);
                    }
                    
                    if (rowsAffected == 0) {
                        quantityInserted = 0;
                    }
                    
                    // Add transaction to wallet
                    query = "INSERT INTO wallettransaction VALUES(?,?)";

                    statement = con.prepareStatement(query);
                    statement.setInt(1, Integer.valueOf(request.getRequestParameters().get("CustomerID")));
                    statement.setInt(2, idTransaction);

                    rowsAffected = statement.executeUpdate();

                    if (rowsAffected == 0) {
                        quantityInserted = 0;
                    }
                  
                }
                
                output.writeObject(quantityInserted);
            }
        } catch (IOException | SQLException ex) {
            System.out.println("Error while proccessing request or sending a response: " + ex.getMessage());
        }

    }
}