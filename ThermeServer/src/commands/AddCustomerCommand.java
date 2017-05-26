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
public class AddCustomerCommand extends Command {
    public AddCustomerCommand(RequestWrapper request, ObjectOutputStream output) {
        super(request, output);
    }

    @Override
    public void Execute() {
        Connection con = DatabaseConnection.getConnection();
        boolean verify = false;
        try
        {
            String query = "insert into customer(fullName,subtotal)"+
                           "VALUES(?,?)";
            
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,request.getRequestParameters().get("fullName"));
            statement.setDouble(2,0.0);
            
            int result = statement.executeUpdate();
            if(result==1)
                verify = true;
            
            ResultSet rs = statement.getGeneratedKeys();
            
            int customerID;
            if(rs.next())
            {
                customerID = rs.getInt(1);   // last ID
            }
           
            output.writeObject(verify);
        }
        catch(SQLException | IOException SQLErr)
        {
            System.out.println("Error while proccessing request or sending a response: "+SQLErr.getMessage());
        }
    }
}
