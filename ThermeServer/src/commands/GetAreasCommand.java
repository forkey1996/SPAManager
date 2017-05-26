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
 * @author AIM632
 */
public class GetAreasCommand extends Command{
    
    public GetAreasCommand(RequestWrapper request, ObjectOutputStream output){
        super(request, output);
    }
    
    @Override
    public void Execute(){
        
        ArrayList<AreaWrapper> areas = new ArrayList<>();
        Connection con = DatabaseConnection.getConnection();
       try{ 
        String query = "SELECT * FROM area";
        
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        
        while(result.next()){
       
            int id = result.getInt("areaID");
            String name = result.getString("name");
            int product = result.getInt("productID");
            AreaWrapper area = new AreaWrapper(id, name, product);
            areas.add(area);
        }
        
            output.writeObject(areas);
        } catch (IOException | SQLException ex) {
           System.out.println("Error while proccessing request or sending a response: " + ex.getMessage());
        }
        
    }
}
