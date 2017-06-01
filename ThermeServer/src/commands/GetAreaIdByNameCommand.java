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
import java.util.ArrayList;
import server.database.DatabaseConnection;
import wrappers.AreaWrapper;

/**
 *
 * @author Ioan-Emanuel Popescu
 */
public class GetAreaIdByNameCommand extends Command {
    public GetAreaIdByNameCommand(RequestWrapper request, ObjectOutputStream output){
        super(request, output);
    }
    
    @Override
    public void Execute() throws SQLException, IOException{
        
        Connection con = DatabaseConnection.getConnection();
        
        String query = "SELECT areaID FROM area WHERE name=?";

        try(PreparedStatement statement = con.prepareStatement(query);){
            statement.setString(1,request.getRequestParameters().get("areaName"));

            try(ResultSet result = statement.executeQuery();){
                int id = 1;
                while(result.next())
                    id = result.getInt("areaID"); 
                output.writeObject(id);
            }
        }
    }
}
