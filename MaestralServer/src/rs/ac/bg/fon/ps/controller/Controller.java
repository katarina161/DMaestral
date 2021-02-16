/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.configuration.Configuration;
import rs.ac.bg.fon.ps.domain.Category;
import rs.ac.bg.fon.ps.domain.Invoice;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Size;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.domain.UserImage;
import rs.ac.bg.fon.ps.so.AbstractSystemOperation;
import rs.ac.bg.fon.ps.so.AddInvoice;
import rs.ac.bg.fon.ps.so.AddProduct;
import rs.ac.bg.fon.ps.so.CancelInvoice;
import rs.ac.bg.fon.ps.so.DeleteInvoice;
import rs.ac.bg.fon.ps.so.DeleteProduct;
import rs.ac.bg.fon.ps.so.GenerateInvoiceNumber;
import rs.ac.bg.fon.ps.so.GetAllCategories;
import rs.ac.bg.fon.ps.so.GetAllImages;
import rs.ac.bg.fon.ps.so.GetAllInvoices;
import rs.ac.bg.fon.ps.so.GetAllProducts;
import rs.ac.bg.fon.ps.so.GetAllSizes;
import rs.ac.bg.fon.ps.so.GetAllUsers;
import rs.ac.bg.fon.ps.so.GetFilteredInvoices;
import rs.ac.bg.fon.ps.so.GetFilteredUsers;
import rs.ac.bg.fon.ps.so.LogIn;
import rs.ac.bg.fon.ps.so.ProcessInvoice;
import rs.ac.bg.fon.ps.so.UpdateInvoice;
import rs.ac.bg.fon.ps.so.UpdateProduct;
import rs.ac.bg.fon.ps.thread.ClientThread;
import rs.ac.bg.fon.ps.transfer.ResponseObject;

/**
 *
 * @author Katarina
 */
public class Controller {
    
    private static Controller instance;
    private List<ClientThread> clientThreads;
    private List<User> onlineUsers;

    private Controller() {
        clientThreads = new LinkedList<>();
        onlineUsers = new LinkedList<>();
    }
    
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        
        return instance;
    }
    
    public void changePortNumber(int port) throws Exception {
        Configuration.getInstance().setPort(port);
    }

    public void changeDatabaseConfig(String url, String username, String password) throws Exception {
        Configuration.getInstance().setDbConfig(username, password, url);
    }
    
    public void addClient(ClientThread client) {
        clientThreads.add(client);
    }

    public void removeClient(ClientThread client) {
        try {
            client.getSocket().close();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, 
                    "An error occurred while trying to close client socket!"
                    , "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
        onlineUsers.remove(client.getLoggedUser());
        clientThreads.remove(client);
    }
    
    public void removeAllClients() {
        for (ClientThread client : clientThreads) {
            try {
                client.getSocket().close();
                client.stopThread();
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        clientThreads = new LinkedList<>();
    }
    
    public List<User> getOnlineUsers() {
        List<User> onlineUsers = new ArrayList<>();
        for (ClientThread client: clientThreads) {
            onlineUsers.add(client.getLoggedUser());
        }
        return onlineUsers;
    }
    
    public User logIn(String username, String password) throws Exception {
        if (onlineUsers.contains(new User(username, password))) {
            throw new Exception("That user is already connected");
        }
        AbstractSystemOperation so = new LogIn(username, password);
        so.executeOperation();
        User user = ((LogIn) so).getUser();
        onlineUsers.add(user);
        return user;
    }
    
    public List<Category> getAllCategories() throws Exception {
        AbstractSystemOperation so = new GetAllCategories();
        so.executeOperation();
        return ((GetAllCategories) so).getCategories();
    }
    
    public List<Size> getAllSizes() throws Exception {
        AbstractSystemOperation so = new GetAllSizes();
        so.executeOperation();
        return ((GetAllSizes) so).getSizes();
    }
    
    public void addProduct(Product product) throws Exception {
        AbstractSystemOperation so = new AddProduct(product);
        so.executeOperation();
    }
    
    public List<Product> getAllProducts() throws Exception {
        AbstractSystemOperation so = new GetAllProducts();
        so.executeOperation();
        return ((GetAllProducts) so).getProducts();
    }

    public void updateProduct(Product product) throws Exception {
        AbstractSystemOperation so = new UpdateProduct(product);
        so.executeOperation();
    }

    public void deleteProduct(Product product) throws Exception {
        AbstractSystemOperation so = new DeleteProduct(product);
        so.executeOperation();
    }

    public void saveInvoice(Invoice invoice) throws Exception {
        AbstractSystemOperation so = new AddInvoice(invoice);
        so.executeOperation();
    }
    
    public List<Invoice> getAllInvoices() throws Exception {
        AbstractSystemOperation so = new GetAllInvoices();
        so.executeOperation();
        return ((GetAllInvoices) so).getInvoices();
    }

    public void informAllUsers(ResponseObject response) {
        for (ClientThread client: clientThreads) {
            client.sendResponse(response);
        }
    }

    public void logOut(ClientThread client) {
        onlineUsers.remove(client.getLoggedUser());
    }

    public String generateInvoiceNumber() throws Exception{
        AbstractSystemOperation so = new GenerateInvoiceNumber();
        so.executeOperation();
        return ((GenerateInvoiceNumber) so).getNumber();
    }

    public void updateInvoice(Invoice invoice) throws Exception{
        AbstractSystemOperation so = new UpdateInvoice(invoice);
        so.executeOperation();
    }

    public void processInvoice(Invoice invoice) throws Exception{
        AbstractSystemOperation so = new ProcessInvoice(invoice);
        so.executeOperation();
    }

    public void deleteInvoice(Invoice invoice) throws Exception {
        AbstractSystemOperation so = new DeleteInvoice(invoice);
        so.executeOperation();
    }

    public void cancelInvoice(Invoice invoice) throws Exception {
        AbstractSystemOperation so = new CancelInvoice(invoice);
        so.executeOperation();
    }

    public List<Invoice> getFilteredInvoices(List<String> columns, List<Object> values) throws Exception {
        AbstractSystemOperation so = new GetFilteredInvoices(columns, values);
        so.executeOperation();
        return ((GetFilteredInvoices) so).getInvoices();
    }

    public List<UserImage> getAllImages() throws Exception {
        AbstractSystemOperation so = new GetAllImages();
        so.executeOperation();
        return ((GetAllImages) so).getImages();
    }

    public List<User> getAllUsers() throws Exception {
        AbstractSystemOperation so = new GetAllUsers();
        so.executeOperation();
        return ((GetAllUsers) so).getUsers();
    }

    public List<User> getFilteredUsers(List<String> columns, List<Object> values) throws Exception {
        AbstractSystemOperation so = new GetFilteredUsers(columns, values);
        so.executeOperation();
        return ((GetFilteredUsers) so).getUsers();
    }
}
