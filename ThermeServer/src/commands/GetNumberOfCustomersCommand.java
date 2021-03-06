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
import wrappers.BoughtProductWrapper;

/**
 *
 * @author Ioan-Emanuel Popescu
 */
public class GetNumberOfCustomersCommand extends Command {

    public GetNumberOfCustomersCommand(RequestWrapper request, ObjectOutputStream output) {
        super(request, output);
    }

    @Override
    public void Execute() throws SQLException, IOException {
        Connection con = DatabaseConnection.getConnection();

        Integer index = Integer.parseInt(request.getRequestParameters().get("indexArea"));
        String query = null;
        PreparedStatement statement = null;
        try {
            if (index == 0) {
                query = "select count(customerID) nr from current";
                statement = con.prepareStatement(query);
            } else {
                query = "select count(customerID) nr from current where areaID=?";
                statement = con.prepareStatement(query);
                statement.setInt(1, index);
            }

            try (ResultSet result = statement.executeQuery();) {

                int count = 0;

                while (result.next()) {
                    count = result.getInt("nr");
                }

                output.writeObject(count);
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
