/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.configuration.Configuration;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.view.controller.MainController;

/**
 *
 * @author Katarina
 */
public class ServerThread extends Thread{
    
    private ServerSocket serverSocket;
    private boolean end = false;
    private final MainController mainController;

    public ServerThread(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void run() {
        try {
            int port = Integer.parseInt(Configuration.getInstance().getPort());
            serverSocket = new ServerSocket(port);
            mainController.setServerStatus(true);
            while (!end) {                
                System.out.println("Waiting clients...");
                try {
                    Socket socket = serverSocket.accept();
                    handleClient(socket);
                    System.out.println("Client connected!");
                } catch (Exception e) {
                    System.out.println("***Server closed!***");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            mainController.serverError();
        }
    }

    private void handleClient(Socket socket) {
        ClientThread clientThread = new ClientThread(socket);
        clientThread.start();
        Controller.getInstance().addClient(clientThread);
    }
    
    public void stopServer() {
        try {
            end = true;
            serverSocket.close();
            mainController.setServerStatus(false);
            Controller.getInstance().removeAllClients();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
