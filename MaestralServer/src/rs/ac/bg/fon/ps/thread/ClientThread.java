/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.thread;

import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Category;
import rs.ac.bg.fon.ps.domain.Invoice;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Size;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.domain.UserImage;
import rs.ac.bg.fon.ps.transfer.RequestObject;
import rs.ac.bg.fon.ps.transfer.ResponseObject;
import rs.ac.bg.fon.ps.util.Receiver;
import rs.ac.bg.fon.ps.util.Sender;

/**
 *
 * @author Katarina
 */
public class ClientThread extends Thread {

    private final Socket socket;
    private final Receiver receiver;
    private final Sender sender;
    private boolean end = false;

    private User loggedUser;

    public ClientThread(Socket socket) {
        this.socket = socket;
        this.receiver = new Receiver(socket);
        this.sender = new Sender(socket);
    }

    @Override
    public void run() {
        while (!end) {
            try {
                RequestObject request = (RequestObject) receiver.receive();
                ResponseObject response = new ResponseObject();
                response.setOperation(request.getOperation());
                try {
                    switch (request.getOperation()) {
                        case LOG_IN:
                            User checkUser = (User) request.getArgument();
                            User user = Controller.getInstance().logIn(checkUser.getUsername(), checkUser.getPassword());
                            loggedUser = user;
                            response.setResult(user);
                            break;
                        case LOG_OUT:
                            Controller.getInstance().logOut(this);
                            break;
                        case SAVE_PRODUCT:
                            Product saveProduct = (Product) request.getArgument();
                            Controller.getInstance().addProduct(saveProduct);
                            break;
                        case UPDATE_PRODUCT:
                            Product updateProduct = (Product) request.getArgument();
                            Controller.getInstance().updateProduct(updateProduct);
                            response.setResult(updateProduct);
                            break;
                        case DELETE_PRODUCT:
                            Product deleteProduct = (Product) request.getArgument();
                            Controller.getInstance().deleteProduct(deleteProduct);
                            break;
                        case GET_ALL_CATEGORIES:
                            List<Category> categories = Controller.getInstance().getAllCategories();
                            response.setResult(categories);
                            break;
                        case REFRESH_PRODUCTS:
                            List<Product> refreshedProducts = Controller.getInstance().getAllProducts();
                            response.setResult(refreshedProducts);
                            Controller.getInstance().informAllUsers(response);
                            break;
                        case GET_ALL_SIZES:
                            List<Size> sizes = Controller.getInstance().getAllSizes();
                            response.setResult(sizes);
                            break;
                        case GET_ALL_PRODUCTS:
                            List<Product> products = Controller.getInstance().getAllProducts();
                            response.setResult(products);
                            break;
                        case GET_ALL_INVOICES:
                            List<Invoice> invoices = Controller.getInstance().getAllInvoices();
                            response.setResult(invoices);
                            break;
                        case SAVE_INVOICE:
                            Invoice saveInvoice = (Invoice) request.getArgument();
                            Controller.getInstance().saveInvoice(saveInvoice);
                            response.setResult(saveInvoice);
                            break;
                        case GENERATE_INVOICE_NUMBER:
                            String generatedNum = Controller.getInstance().generateInvoiceNumber();
                            response.setResult(generatedNum);
                            break;
                        case UPDATE_INVOICE:
                            Invoice updateInvoice = (Invoice) request.getArgument();
                            Controller.getInstance().updateInvoice(updateInvoice);
                            response.setResult(updateInvoice);
                            break;
                        case REFRESH_INVOICES:
                            List<Invoice> refreshInvoices = Controller.getInstance().getAllInvoices();
                            response.setResult(refreshInvoices);
                            Controller.getInstance().informAllUsers(response);
                            break;
                        case PROCESS_INVOICE:
                            Invoice processInvoice = (Invoice) request.getArgument();
                            Controller.getInstance().processInvoice(processInvoice);
                            response.setResult(processInvoice);
                            break;
                        case DELETE_INVOICE:
                            Invoice deleteInvoice = (Invoice) request.getArgument();
                            Controller.getInstance().deleteInvoice(deleteInvoice);
                            break;
                        case CANCEL_INVOICE:
                            Invoice cancelInvoice = (Invoice) request.getArgument();
                            Controller.getInstance().cancelInvoice(cancelInvoice);
                            break;
                        case GET_FILTERED_INVOICES:
                            List<String> columnsInvoice = request.getColumns();
                            List<Object> valuesInvoice = request.getValues();
                            List<Invoice> filteredInvoices = Controller.getInstance().getFilteredInvoices(columnsInvoice, valuesInvoice);
                            response.setResult(filteredInvoices);
                            break;
                        case GET_ALL_IMAGES:
                            List<UserImage> images = Controller.getInstance().getAllImages();
                            response.setResult(images);
                            break;
                        case GET_ALL_USERS:
                            List<User> users = Controller.getInstance().getAllUsers();
                            response.setResult(users);
                            break;
                        case GET_FILTERED_USERS:
                            List<String> columnsUser = request.getColumns();
                            List<Object> valuesUser = request.getValues();
                            List<User> filteredUsers = Controller.getInstance().getFilteredUsers(columnsUser, valuesUser);
                            response.setResult(filteredUsers);
                            break;
                        case REFRESH_USERS:
                            List<User> refreshUsers = Controller.getInstance().getAllUsers();
                            response.setResult(refreshUsers);
                            Controller.getInstance().informAllUsers(response);
                            break;
                        case SAVE_USER:
                            User addUser = (User) request.getArgument();
                            Controller.getInstance().addUser(addUser);
                            response.setResult(addUser);
                            break;
                        case UPDATE_USER:
                            User updateUser = (User) request.getArgument();
                            Controller.getInstance().updateUser(updateUser);
                            response.setResult(updateUser);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                    response.setMessage(e.getMessage());
                }

                sender.send(response);

            } catch (Exception e) {
                Controller.getInstance().removeClient(this);
                stopThread();
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void stopThread() {
        end = true;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void sendResponse(ResponseObject response) {
        try {
            sender.send(response);
        } catch (Exception ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
