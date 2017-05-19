/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.app;

import java.net.UnknownHostException;
import uiPannels.MainFrame;

/**
 *
 * @author fernando
 */
public class Application {
    public static void main(String[] args) throws UnknownHostException {
//	RequestWrapper request = new RequestWrapper("Test");
//	ClientManager client = new ClientManager();
//	client.startClient(request);
//	System.out.println(client.getResponse());

	MainFrame mainPanel = new MainFrame();
	mainPanel.setVisible(true);
    }
}
