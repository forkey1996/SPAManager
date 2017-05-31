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
import javax.swing.JOptionPane;
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
                     "select wt.transactionID, p.productID, p.name, p.price, t.quantity"
                    +" from customer c join wallettransaction wt on(c.customerID=wt.customerID)"
                    +" join transaction t on(wt.transactionID=t.transactionID)"
                    +" join product p on(t.productID = p.productID)"
                    +" where c.customerID = ?";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,Integer.valueOf(request.getRequestParameters().get("CustomerID")));
            ResultSet result = statement.executeQuery();
            
            for(BoughtProductWrapper p: invoice)
            {
               System.out.println(p);
            }
            
            while(result.next())
            {
                int transaction = result.getInt("transactionID");
                int product     = result.getInt("productID");
                double price    = result.getDouble("price");
                String name     = result.getString("name");
                int amount      = result.getInt("quantity");    
                invoice.add(new BoughtProductWrapper(product, transaction, name, price, amount));
            }

            
            output.writeObject(invoice);
        }
        catch(IOException | SQLException err)
        {
            System.out.println("Error while proccessing request or sending a response: "+err.getMessage());
        }
    }
    
}
