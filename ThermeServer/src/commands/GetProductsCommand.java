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
import wrappers.ProductWrapper;

/**
 *
 * @author AIM632
 */
public class GetProductsCommand extends Command {

    public GetProductsCommand(RequestWrapper request, ObjectOutputStream output) {
        super(request, output);
    }

    @Override
    public void Execute() {

        ArrayList<ProductWrapper> products = new ArrayList<>();
        Connection con = DatabaseConnection.getConnection();
        try {
            String query = "SELECT * FROM product";

            PreparedStatement statement = con.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                int id = result.getInt("idProduct");
                String name = result.getString("name");
                double price = result.getInt("price");
                int typeid = result.getInt("idType");
                ProductWrapper product = new ProductWrapper(id, name, price, typeid);
                products.add(product);
                
            }

            output.writeObject(products);
            
        } catch (IOException | SQLException ex) {
            System.out.println("Error while proccessing request or sending a response: " + ex.getMessage());
        }

    }
}
