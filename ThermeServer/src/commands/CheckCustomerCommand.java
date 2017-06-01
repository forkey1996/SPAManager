/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

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
public class CheckCustomerCommand extends Command {

    public CheckCustomerCommand(RequestWrapper request, ObjectOutputStream output) {
        super(request, output);
    }

    @Override
    public void Execute() throws SQLException, IOException {

        boolean verify = false;
        Connection con = DatabaseConnection.getConnection();
        
        String query = "SELECT customerID FROM customer WHERE customerID = ?";

        try(PreparedStatement statement = con.prepareStatement(query);){
            statement.setInt(1, Integer.valueOf(request.getRequestParameters().get("CustomerID")));
            try(ResultSet result = statement.executeQuery();){
                if(result.next()) 
                    verify = true;
                output.writeObject(verify);
            }
        }

    }

}
