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
	MainFrame mainPanel = new MainFrame();
	mainPanel.setVisible(true);
    }
}
