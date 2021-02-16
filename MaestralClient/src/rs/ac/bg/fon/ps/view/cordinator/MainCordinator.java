/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.cordinator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rs.ac.bg.fon.ps.domain.Invoice;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.controller.InvoiceController;
import rs.ac.bg.fon.ps.view.controller.LogInController;
import rs.ac.bg.fon.ps.view.controller.MainController;
import rs.ac.bg.fon.ps.view.controller.ProductController;
import rs.ac.bg.fon.ps.view.controller.SearchInvoicesController;
import rs.ac.bg.fon.ps.view.controller.SearchProductsController;
import rs.ac.bg.fon.ps.view.controller.SearchUsersController;
import rs.ac.bg.fon.ps.view.controller.UserController;
import rs.ac.bg.fon.ps.view.form.FrmInvoice;
import rs.ac.bg.fon.ps.view.form.FrmLogIn;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmProduct;
import rs.ac.bg.fon.ps.view.form.FrmSearchInvoices;
import rs.ac.bg.fon.ps.view.form.FrmSearchProducts;
import rs.ac.bg.fon.ps.view.form.FrmSearchUsers;
import rs.ac.bg.fon.ps.view.form.FrmUser;
import rs.ac.bg.fon.ps.view.util.FormMode;

/**
 *
 * @author Katarina
 */
public class MainCordinator {

    private static MainCordinator instance;
    private Map<String, Object> params;

    private MainController mainController;
    private LogInController logInController;
    private ProductController productController;
    private InvoiceController invoiceController;
    private UserController userController;
    private SearchProductsController searchProductsController;
    private SearchInvoicesController searchInvoicesController;
    private SearchUsersController searchUsersController;

    private MainCordinator() {
        params = new HashMap<>();
    }

    public static MainCordinator getInstance() {
        if (instance == null) {
            instance = new MainCordinator();
        }
        return instance;
    }

    public void openLogInForm() {
        logInController = new LogInController(new FrmLogIn());
        logInController.openForm();
    }

    public MainController getMainController() {
        return mainController;
    }

    public Object getParam(String name) {
        return params.get(name);
    }

    public void addParam(String name, Object object) {
        params.put(name, object);
    }

    public void openMainForm(FormMode mode) {
        mainController = new MainController(new FrmMain());
        mainController.openForm(mode);
    }

    public void openAddNewProductForm() {
        productController = new ProductController(new FrmProduct(mainController.getFrmMain(), true));
        productController.openForm(FormMode.FORM_ADD);
    }

    public void openViewAllProductsForm(FormMode mode) {
        searchProductsController
                = new SearchProductsController(new FrmSearchProducts(mainController.getFrmMain(), true));
        searchProductsController.openForm(mode);
    }
    
    public void openProductDetailsForm() {
        productController = new ProductController(new FrmProduct(mainController.getFrmMain(), true));
        productController.openForm(FormMode.FORM_DETAIL);
    }

    public void refreshProductsView(List<Product> products) {
        if (searchProductsController != null && searchProductsController.getFrmSearchProducts().isVisible()) {
            searchProductsController.refreshProductsView(products);
        }
    }

    public void openAddNewInvoiceForm() {
        invoiceController = new InvoiceController(new FrmInvoice(mainController.getFrmMain(), true));
        invoiceController.openForm(FormMode.FORM_ADD);
    }

    public void openViewAllInvoicesForm() {
        searchInvoicesController = new SearchInvoicesController(new FrmSearchInvoices(mainController.getFrmMain(), true));
        searchInvoicesController.openForm();
    }

    public void openInvoiceDetailsForm() {
        invoiceController = new InvoiceController(new FrmInvoice(mainController.getFrmMain(), true));
        invoiceController.openForm(FormMode.FORM_DETAIL);
    }

    public LogInController getLogInController() {
        return logInController;
    }

    public ProductController getProductController() {
        return productController;
    }

    public InvoiceController getInvoiceController() {
        return invoiceController;
    }

    public SearchProductsController getSearchProductsController() {
        return searchProductsController;
    }

    public SearchInvoicesController getSearchInvoicesController() {
        return searchInvoicesController;
    }

    public SearchUsersController getSearchUsersController() {
        return searchUsersController;
    }

    public UserController getUserController() {
        return userController;
    }

    public void removeParams() {
        params = new HashMap<>();
    }
    
    public boolean isSearchInvoicesVisible() {
        return searchInvoicesController != null && searchInvoicesController.getFrmSearchInvoices().isVisible();
    }
    
    public boolean isSearchProductsVisible() {
        return searchProductsController != null && searchProductsController.getFrmSearchProducts().isVisible();
    }
    
    public boolean isSearchUsersVisible() {
        return searchUsersController != null && searchUsersController.getFrmSearchUsers().isVisible();
    }

    public void refreshInvoicesView(List<Invoice> invoices) {
        if (searchInvoicesController != null && searchInvoicesController.getFrmSearchInvoices().isVisible()) {
            searchInvoicesController.refreshInvoicesView(invoices);
        }
    }

    public void openAddNewUserForm() {
        userController = new UserController(new FrmUser(mainController.getFrmMain(), true));
        userController.openForm(FormMode.FORM_ADD);
    }

    public void openViewAllUsersForm() {
        searchUsersController = new SearchUsersController(new FrmSearchUsers(mainController.getFrmMain(), true));
        searchUsersController.openForm();
    }
    
    public void refreshUsersView(List<User> users) {
        if (searchUsersController != null && searchUsersController.getFrmSearchUsers().isVisible()) {
            searchUsersController.refreshUsersView(users);
        }
    }

    public void openUserDetailsForm() {
        userController = new UserController(new FrmUser(mainController.getFrmMain(), true));
        userController.openForm(FormMode.FORM_DETAIL);
    }
}
