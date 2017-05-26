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
public class ChangeAreaCommand extends Command {

    public ChangeAreaCommand(RequestWrapper request, ObjectOutputStream output) {
        super(request, output);
    }

    @Override
    public void Execute() {

        boolean verify = false;
        String query;
        Connection con = DatabaseConnection.getConnection();
        try {
            query = "SELECT customerID, areaID FROM access WHERE customerID = ? AND areaID= ?";

            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, Integer.valueOf(request.getRequestParameters().get("CustomerID")));
            statement.setInt(2, Integer.valueOf(request.getRequestParameters().get("AreaID")));

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                verify = true;
            }

            if (verify == true) {
                query = "UPDATE current SET areaID = ? WHERE customerID = ?";

                statement = con.prepareStatement(query);
                statement.setInt(1, Integer.valueOf(request.getRequestParameters().get("AreaID")));
                statement.setInt(2, Integer.valueOf(request.getRequestParameters().get("CustomerID")));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected == 0) {
                    query = "INSERT INTO current VALUES(?,?)";

                    statement = con.prepareStatement(query);
                    statement.setInt(1, Integer.valueOf(request.getRequestParameters().get("CustomerID")));
                    statement.setInt(2, Integer.valueOf(request.getRequestParameters().get("AreaID")));

                    rowsAffected = statement.executeUpdate();
                }

            }

            output.writeObject(verify);
        } catch (IOException | SQLException ex) {
            System.out.println("Error while proccessing request or sending a response: " + ex.getMessage());
        }

    }
}
