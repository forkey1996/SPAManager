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
import java.util.logging.Level;
import java.util.logging.Logger;
import server.database.DatabaseConnection;

/**
 *
 * @author Ioan-Emanuel Popescu
 */
public class DeleteTransactionsCommand extends Command{
    private static final int TICKET = 1;
    private static final int PRODUCT = 2;

    public DeleteTransactionsCommand(RequestWrapper request, ObjectOutputStream output) {
        super(request, output);
    }

    @Override
    public void Execute() throws SQLException, IOException {
        Connection con = DatabaseConnection.getConnection();
        int affectedRows = 0;
        
        String query = "delete from wallettransaction where customerID=?";
        try(PreparedStatement st = con.prepareStatement(query);){
            st.setInt(1, Integer.valueOf(request.getRequestParameters().get("CustomerID")));
            affectedRows = st.executeUpdate(); 
            output.writeObject(affectedRows);
        } 
        
        query = "delete from current where customerID=?";
        try(PreparedStatement st = con.prepareStatement(query);){
            st.setInt(1, Integer.valueOf(request.getRequestParameters().get("CustomerID")));
            affectedRows = st.executeUpdate(); 
            output.writeObject(affectedRows);
        } 
        
        query = "delete from access where customerID=?";
        try(PreparedStatement st = con.prepareStatement(query);){
            st.setInt(1, Integer.valueOf(request.getRequestParameters().get("CustomerID")));
            affectedRows = st.executeUpdate(); 
            output.writeObject(affectedRows);
        } 
        

    }
}

