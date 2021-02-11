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
import rs.ac.bg.fon.ps.transfer.RequestObject;
import rs.ac.bg.fon.ps.transfer.ResponseObject;
import rs.ac.bg.fon.ps.util.Receiver;
import rs.ac.bg.fon.ps.util.Sender;

/**
 *
 * @author Katarina
 */
public class ClientThread extends Thread {

    private Socket socket;
    private Receiver receiver;
    private Sender sender;
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
