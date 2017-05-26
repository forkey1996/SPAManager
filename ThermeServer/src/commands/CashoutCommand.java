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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.database.DatabaseConnection;
import wrappers.BoughtProductWrapper;

import wrappers.TransactionWrapper;

/**
 *
 * @author Ioan-Emanuel Popescu
 */
public class CashoutCommand extends Command{

    public CashoutCommand(RequestWrapper request, ObjectOutputStream output) {
        super(request, output);
    }
    
    @Override
    public void Execute() {
        Connection con = DatabaseConnection.getConnection();
        boolean verify = false;
        try{
            ArrayList<BoughtProductWrapper> invoice = new ArrayList<>();
            String query = 
                    "select wt.transactionID, p.productID, p.name, p.price, t.amount"
                    +"from customer c join wallettransaction wt on(c.customerID=wt.customerID)"
                    +"                join transaction t on(wt.transactionID=t.transactionID)"
                    +"                join product p on(t.productID = p.productID)"
                    +"where c.customerID=?";
            
            PreparedStatement statement = con.prepareCall(query);
            statement.setInt(1,Integer.valueOf(request.getRequestParameters().get("customerID")));
            ResultSet result = statement.executeQuery();
            
            while(result.next())
            {
                int transaction = result.getInt("transactionID");
                int product     = result.getInt("productID");
                double price    = result.getDouble("price");
                String name     = result.getString("name");
                int amount      = result.getInt("amount");    
                invoice.add(new BoughtProductWrapper(product, transaction, amount, price, amount));
            }
            
            double sum = 0.0;
            
            // Need to be returned
            for(BoughtProductWrapper product : invoice)
            {
                sum += product.getPrice() * product.getAmount();
            }
            
            output.writeObject(invoice);
        }
        catch(IOException | SQLException err)
        {
            System.out.println("Error while proccessing request or sending a response: "+err.getMessage());
        }
    }
    
}
