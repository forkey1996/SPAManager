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
import commands.GetAreasCommand;
import commands.GetProductsCommand;
import core.connection.RequestWrapper;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


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
            System.out.println("Error");
        }
    }

    private void processRequest(ObjectInputStream input, ObjectOutputStream output) {
        try {
            RequestWrapper request = (RequestWrapper) input.readObject();
            if (request != null) {
                switch (request.getRequestName()) {
                    default:
                        System.out.println(request.getRequestName());
                        output.writeObject("Bad request!");
                        break;
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
                }
            }

            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (ClassNotFoundException | IOException ex) {

        }

    }
}
