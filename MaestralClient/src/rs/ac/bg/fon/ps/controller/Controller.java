/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Invoice;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.transfer.RequestObject;
import rs.ac.bg.fon.ps.util.Operation;

/**
 *
 * @author Katarina
 */
public class Controller {
    
    private static Controller instance;
    private Communication communication;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    public void connect(int port) {
        try {
            communication = new Communication();
            communication.connect(port);
            communication.start();
            System.out.println("Client connected :D");
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Server is down", "Connect Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    public void logIn(String username, String password) {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.LOG_IN);
        request.setArgument(new User(username, password));
        communication.sendRequest(request);
    }
    
    public void addProduct(Product product) {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.SAVE_PRODUCT);
        request.setArgument(product);
        communication.sendRequest(request);
    }
    
    public void updateProduct(Product product) {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.UPDATE_PRODUCT);
        request.setArgument(product);
        communication.sendRequest(request);
    }
    
    public void deleteProduct(Product product) {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.DELETE_PRODUCT);
        request.setArgument(product);
        communication.sendRequest(request);
    }
    
    public void getAllCategories() {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.GET_ALL_CATEGORIES);
        communication.sendRequest(request);
    }
    
    public void getAllSizes() {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.GET_ALL_SIZES);
        communication.sendRequest(request);
    }

    public void getAllProducts() {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.GET_ALL_PRODUCTS);
        communication.sendRequest(request);
    }

    public void refreshProductsView() {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.REFRESH_PRODUCTS);
        communication.sendRequest(request);
    }

    public void saveInvoice(Invoice invoice) {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.SAVE_INVOICE);
        request.setArgument(invoice);
        communication.sendRequest(request);
    }

    public void getAllInvoices() {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.GET_ALL_INVOICES);
        communication.sendRequest(request);
    }

    public void logOut() {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.LOG_OUT);
        communication.sendRequest(request);
    }

}
