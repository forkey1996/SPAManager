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
import server.database.DatabaseConnection;

/**
 *
 * @author AIM632
 */
public class BuyProductCommand extends Command {

    public BuyProductCommand(RequestWrapper request, ObjectOutputStream output) {
        super(request, output);
    }

    @Override
    public void Execute() {

        boolean verify = false;
        String query;
        int rowsAffected;
        int idTransaction = 0;
        int idWallet = 0;
        Connection con = DatabaseConnection.getConnection();
        try {
            query = "SELECT walletID FROM customer WHERE customerID = ?";

            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, Integer.valueOf(request.getRequestParameters().get("CustomerID")));

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                idWallet = result.getInt("walletID");
            }

            query = "INSERT INTO transaction(idProduct, amount) VALUES(?,?)";

            statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, Integer.valueOf(request.getRequestParameters().get("ProductID")));
            statement.setInt(2, Integer.valueOf(request.getRequestParameters().get("Quantity")));

            rowsAffected = statement.executeUpdate();

            result = statement.getGeneratedKeys();
            if (result.next()) {
                idTransaction = result.getInt(1);
            }

            if (rowsAffected == 1) {
                verify = true;
            }

            query = "INSERT INTO wallettransaction VALUES(?,?)";

            statement = con.prepareStatement(query);
            statement.setInt(1, idWallet);
            statement.setInt(2, idTransaction);

            rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                verify = false;
            }

            output.writeObject(verify);
        } catch (IOException | SQLException ex) {
            System.out.println("Error while proccessing request or sending a response: " + ex.getMessage());
        }

    }
}
