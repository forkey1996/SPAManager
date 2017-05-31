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
    public void Execute() throws SQLException, IOException {
        Connection con = DatabaseConnection.getConnection();
        boolean verify = false;

        String query = "insert into customer(fullName)"+
                       "VALUES(?)";

        try(PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);){
            statement.setString(1,request.getRequestParameters().get("CustomerName"));

            int result = statement.executeUpdate();
            if(result==1)
                verify = true;

            try(ResultSet rs = statement.getGeneratedKeys();){
                int customerID = -1;
                if(rs.next())
                    customerID = rs.getInt(1);   // last ID
                output.writeObject(customerID);
            }
        }
        
    }
}
