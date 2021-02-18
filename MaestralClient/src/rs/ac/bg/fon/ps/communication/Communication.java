/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.communication;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;

/**
 *
 * @author Katarina
 */
public class Communication extends Thread {

    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private boolean end = false;

    public Communication() {
    }

    @Override
    public void run() {
        while (!end) {
            try {
                ResponseObject response = (ResponseObject) receiver.receive();
                switch (response.getOperation()) {
                    case LOG_IN:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getLogInController().logInSuccess((User) response.getResult());
                        } else {
                            MainCordinator.getInstance().getLogInController().logInFailed(response.getMessage());
                            System.err.println(response.getMessage());
                        }
                        break;
                    case LOG_OUT:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getMainController().logOutSuccess();
                        } else {
                            MainCordinator.getInstance().getMainController().logOutFailed();
                            System.err.println(response.getMessage());
                        }
                        break;
                    case SAVE_PRODUCT:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getProductController().saveProductSuccess();
                        } else {
                            MainCordinator.getInstance().getProductController().saveProductFailed(response.getMessage());
                            System.err.println(response.getMessage());
                        }
                        break;
                    case UPDATE_PRODUCT:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getProductController().updateProductSuccess();
                        } else {
                            MainCordinator.getInstance().getProductController().updateProductFailed();
                            System.err.println(response.getMessage());
                        }
                        break;
                    case DELETE_PRODUCT:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getProductController().deleteProductSuccess();
                        } else {
                            MainCordinator.getInstance().getProductController().deleteProductFailed(response.getMessage());
                            System.err.println(response.getMessage());
                        }
                        break;
                    case REFRESH_PRODUCTS:
                        if (MainCordinator.getInstance().isSearchProductsVisible()) {
                            if (response.getException() == null) {
                                MainCordinator.getInstance().refreshProductsView((List<Product>) response.getResult());
                            } else {
                                System.err.println(response.getMessage());
                            }
                        }
                        break;
                    case GET_ALL_CATEGORIES:
                        if (response.getException() == null) {
                            if (MainCordinator.getInstance().getProductController() != null) {
                                MainCordinator.getInstance().getProductController().fillCategories((List<Category>) response.getResult());
                            }
                            if (MainCordinator.getInstance().getSearchProductsController() != null) {
                                MainCordinator.getInstance().getSearchProductsController().fillCategories((List<Category>) response.getResult());
                            }
                        } else {
                            if (MainCordinator.getInstance().getProductController() != null) {
                                MainCordinator.getInstance().getProductController().viewInitialisationFailed();
                            }
                            if (MainCordinator.getInstance().getSearchProductsController() != null) {
                                MainCordinator.getInstance().getSearchProductsController().viewInitialisationFailed();
                            }
                        }
                        break;
                    case GET_ALL_SIZES:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getProductController().fillSizes((List<Size>) response.getResult());
                        } else {
                            MainCordinator.getInstance().getProductController().viewInitialisationFailed();
                        }
                        break;
                    case GET_ALL_PRODUCTS:
                        if (response.getException() == null) {
                            if (MainCordinator.getInstance().getSearchProductsController() != null) {
                                MainCordinator.getInstance().getSearchProductsController().fillTableProducts((List<Product>) response.getResult());
                            }
                            if (MainCordinator.getInstance().getInvoiceController() != null) {
                                MainCordinator.getInstance().getInvoiceController().setProducts((List<Product>) response.getResult());
                            }
                        } else {
                            if (MainCordinator.getInstance().getSearchProductsController() != null) {
                                MainCordinator.getInstance().getSearchProductsController().viewInitialisationFailed();
                            }
                            if (MainCordinator.getInstance().getInvoiceController() != null) {
                                MainCordinator.getInstance().getInvoiceController().viewInitialisationFailed();
                            }
                        }
                        break;
                    case GET_ALL_INVOICES:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getSearchInvoicesController().fillTblInvoices((List<Invoice>) response.getResult());
                        } else {
                            MainCordinator.getInstance().getSearchInvoicesController().viewInitialisationFailed();
                        }
                        break;
                    case SAVE_INVOICE:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getInvoiceController().saveInvoiceSuccess((Invoice) response.getResult());
                        } else {
                            MainCordinator.getInstance().getInvoiceController().saveInvoiceFailed(response.getMessage());
                            System.err.println(response.getMessage());
                        }
                        break;
                    case GENERATE_INVOICE_NUMBER:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getInvoiceController().setGeneratedNumber(String.valueOf(response.getResult()));
                        } else {
                            System.err.println(response.getMessage());
                        }
                        break;
                    case UPDATE_INVOICE:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getInvoiceController().updateSuccess((Invoice) response.getResult());
                        } else {
                            MainCordinator.getInstance().getInvoiceController().updateFailed(response.getMessage());
                            System.err.println(response.getMessage());
                        }
                        break;
                    case REFRESH_INVOICES:
                        if (MainCordinator.getInstance().isSearchInvoicesVisible()) {
                            if (response.getException() == null) {
                                MainCordinator.getInstance().refreshInvoicesView((List<Invoice>) response.getResult());
                            } else {
                                System.err.println(response.getMessage());
                            }
                        }
                        break;
                    case PROCESS_INVOICE:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getInvoiceController().processSuccess();
                        } else {
                            MainCordinator.getInstance().getInvoiceController().processFailed(response.getMessage());
                            System.err.println(response.getMessage());
                        }
                        break;
                    case DELETE_INVOICE:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getInvoiceController().deleteSuccess();
                        } else {
                            MainCordinator.getInstance().getInvoiceController().deleteFailed(response.getMessage());
                            System.err.println(response.getMessage());
                        }
                        break;
                    case CANCEL_INVOICE:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getInvoiceController().cancelSuccess();
                        } else {
                            MainCordinator.getInstance().getInvoiceController().cancelFailed(response.getMessage());
                            System.err.println(response.getMessage());
                        }
                        break;
                    case GET_FILTERED_INVOICES:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getSearchInvoicesController().setFilteredInvoices((List<Invoice>) response.getResult());
                        } else {
                            MainCordinator.getInstance().getSearchInvoicesController().filterInvoicesFailed(response.getMessage());
                            System.err.println(response.getMessage());
                        }
                        break;
                    case GET_ALL_IMAGES:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getUserController().fillCmbImages((List<UserImage>) response.getResult());
                        } else {
                            MainCordinator.getInstance().getUserController().viewInitialisationFailed();
                            System.err.println(response.getMessage());
                        }
                        break;
                    case GET_ALL_USERS:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getSearchUsersController().fillTblUsers((List<User>) response.getResult());
                        } else {
                            MainCordinator.getInstance().getSearchUsersController().viewInitialisationFailed();
                            System.err.println(response.getMessage());
                        }
                        break;
                    case GET_FILTERED_USERS:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getSearchUsersController().setFilteredUsers((List<User>) response.getResult());
                        } else {
                            MainCordinator.getInstance().getSearchUsersController().filterUsersFailed(response.getMessage());
                            System.err.println(response.getMessage());
                        }
                        break;
                    case REFRESH_USERS:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().refreshUsersView((List<User>) response.getResult());
                        } else {
                            System.err.println(response.getMessage());
                        }
                        break;
                    case SAVE_USER:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getUserController().saveUserSuccess((User) response.getResult());
                        } else {
                            MainCordinator.getInstance().getUserController().saveUserFailed(response.getMessage());
                            System.err.println(response.getMessage());
                        }
                        break;
                    case UPDATE_USER:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getUserController().updateSuccess((User) response.getResult());
                        } else {
                            MainCordinator.getInstance().getUserController().updateFailed(response.getMessage());
                            System.err.println(response.getMessage());
                        }
                        break;
                    case GET_FILTERED_PRODUCTS:
                        if (response.getException() == null) {
                            MainCordinator.getInstance().getSearchProductsController().setFilteredProducts((List<Product>) response.getResult());
                        } else {
                            MainCordinator.getInstance().getSearchProductsController().filterProductsFailed(response.getMessage());
                            System.err.println(response.getMessage());
                        }
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Server stopped working.", "Error", JOptionPane.ERROR_MESSAGE);
                stopThread();
                System.exit(0);
            }
        }
    }

    public void stopThread() {
        end = true;
    }

    public void connect(int port) throws IOException {
        socket = new Socket("localhost", port);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public void sendRequest(RequestObject request) {
        try {
            sender.send(request);
        } catch (Exception ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
