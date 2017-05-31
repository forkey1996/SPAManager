/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.connection;

import commands.AddCustomerCommand;
import commands.BuyProductCommand;
import commands.CashoutCommand;
import commands.ChangeAreaCommand;
import commands.CheckCustomerCommand;
import commands.DeleteTransactionsCommand;
import commands.GetAreaIdByNameCommand;
import commands.GetAreasCommand;
import commands.GetNumberOfCustomersCommand;
import commands.GetProductsCommand;
import core.connection.RequestWrapper;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author fernando
 */
public class ManageRequestThread extends Thread {

    Socket clientSocket;

    public ManageRequestThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream())) {
            processRequest(input, output);
        } catch (IOException IOEx) {
            System.out.println("Client socket error");
        }
    }

    private void processRequest(ObjectInputStream input, ObjectOutputStream output) throws IOException {
        try {
            RequestWrapper request = (RequestWrapper) input.readObject();
            if (request != null) {
                switch (request.getRequestName()) {

                    case "GetAllAreas":
                        GetAreasCommand getAreasCommand = new GetAreasCommand(request, output);
                        getAreasCommand.Execute();
                        break;
                    case "GetAllProducts":
                        GetProductsCommand getProductsCommand = new GetProductsCommand(request, output);
                        getProductsCommand.Execute();
                        break;
                    case "CheckCustomer":
                        CheckCustomerCommand checkCustomerCommand = new CheckCustomerCommand(request, output);
                        checkCustomerCommand.Execute();
                        break;
                    case "ChangeArea":
                        ChangeAreaCommand changeAreaCommand = new ChangeAreaCommand(request, output);
                        changeAreaCommand.Execute();
                        break;
                    case "BuyProduct":
                        BuyProductCommand buyProductCommand = new BuyProductCommand(request, output);
                        buyProductCommand.Execute();
                        break;
                    case "AddCustomer":
                        AddCustomerCommand addCustomerCommand = new AddCustomerCommand(request,output);
                        addCustomerCommand.Execute();
                        break;
                    case "Cashout":
                        CashoutCommand cashoutCommand = new CashoutCommand(request,output);
                        cashoutCommand.Execute();
                        break;
                    case "GetAreaIdByName":
                        GetAreaIdByNameCommand getArea = new GetAreaIdByNameCommand(request,output);
                        getArea.Execute();
                        break;
                    case "GetNumberOfCustomers":
                        GetNumberOfCustomersCommand getCustomers = new GetNumberOfCustomersCommand(request,output);
                        getCustomers.Execute();
                        break;
                    case "DeleteTransactions":
                        DeleteTransactionsCommand deleteTransactions = new DeleteTransactionsCommand(request,output);
                        deleteTransactions.Execute();
                        break;
                    default:
                        System.out.println(request.getRequestName());
                        output.writeObject("Bad request!");
                        break;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Request processing error: "+ex.getMessage());
        } finally {
            if (clientSocket != null) {
                clientSocket.close();
            }
        }

    }
}
