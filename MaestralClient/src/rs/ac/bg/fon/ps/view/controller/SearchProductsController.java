/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Category;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.view.component.table.ProductTableModel;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmSearchProducts;
import rs.ac.bg.fon.ps.view.util.FormMode;

/**
 *
 * @author Katarina
 */
public class SearchProductsController {

    private final FrmSearchProducts frmSearchProducts;
    private final FrmMain parent;
    private FormMode mode;

    public SearchProductsController(FrmSearchProducts frmSearchProducts) {
        this.frmSearchProducts = frmSearchProducts;
        this.parent = MainCordinator.getInstance().getMainController().getFrmMain();
        addActionListener();
    }

    private void addActionListener() {
        frmSearchProducts.btnSearchAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchProducts(null);
            }
        });

        frmSearchProducts.btnCancelFilterAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelFilter();
            }

            private void cancelFilter() {
                frmSearchProducts.getTxtArticle().setText("");
                frmSearchProducts.getCmbCategories().setSelectedIndex(0);
                Controller.getInstance().refreshProductsView();
            }
        });

        frmSearchProducts.btnDetailsAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetail();
            }

            private void showDetail() {
                int selectedRow = frmSearchProducts.getTblProducts().getSelectedRow();
                if (selectedRow >= 0) {
                    ProductTableModel model = (ProductTableModel) frmSearchProducts.getTblProducts().getModel();
                    Product selectedProduct = model.getProduct(selectedRow);
                    MainCordinator.getInstance().addParam(Constants.PARAM_PRODUCT, selectedProduct);
                    MainCordinator.getInstance().openProductDetailsForm();
                } else {
                    JOptionPane.showMessageDialog(frmSearchProducts, 
                            java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchProducts.msg.SEARCH_ERROR"), 
                            java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchProducts.msg.SEARCH_TITLE"), 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmSearchProducts.btnAddAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add();
            }

            private void add() {
                MainCordinator.getInstance().openAddNewProductForm();
            }
        });
    }

    public void openForm(FormMode mode) {
        this.mode = mode;
        frmSearchProducts.setLocationRelativeTo(parent);
        frmSearchProducts.setResizable(false);
        prepareView();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SearchProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmSearchProducts.setVisible(true);
    }

    private void prepareView() {
        gatherDataForTblProducts();
        Controller.getInstance().getAllCategories();
        frmSearchProducts.getContentPane().setBackground(Color.WHITE);
        setupComponents();
    }

    public void setupComponents() {
        switch (mode) {
            case FORM_ADMIN:
                frmSearchProducts.getBtnAdd().setVisible(true);
                break;
            case FORM_USER:
                frmSearchProducts.getBtnAdd().setVisible(false);
                break;
        }
    }

    private void gatherDataForTblProducts() {
        Controller.getInstance().getAllProducts();
    }

    public void fillTableProducts(List<Product> products) {
        ProductTableModel model = new ProductTableModel(products);
        frmSearchProducts.getTblProducts().setModel(model);
    }

    public void refreshProductsView(List<Product> products) {
        searchProducts(products);
    }

    public FrmSearchProducts getFrmSearchProducts() {
        return frmSearchProducts;
    }

    public void viewInitialisationFailed() {
        JOptionPane.showMessageDialog(frmSearchProducts,
                java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("INITIALIZATION_FAILED"),
                java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("ERROR"),
                JOptionPane.ERROR_MESSAGE);
        frmSearchProducts.dispose();
    }

    public void fillCategories(List<Category> categories) {
        frmSearchProducts.getCmbCategories().removeAllItems();
        frmSearchProducts.getCmbCategories().addItem(java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FILTER_ALL"));
        for (Category category : categories) {
            frmSearchProducts.getCmbCategories().addItem(category);
        }

        frmSearchProducts.getCmbCategories().setSelectedIndex(0);
    }

    private void searchProducts(List<Product> products) {
        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        if (!frmSearchProducts.getTxtArticle().getText().isEmpty()) {
            try {
                int article = Integer.parseInt(frmSearchProducts.getTxtArticle().getText());
                columns.add("article");
                values.add(article);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frmSearchProducts, 
                        java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchProducts.msg.SEARCH_ARTICLE_ERROR"), 
                        java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchProducts.msg.SEARCH_TITLE"), 
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (frmSearchProducts.getCmbCategories().getSelectedIndex() > 0) {
            Category c = (Category) frmSearchProducts.getCmbCategories().getSelectedItem();
            columns.add("category_id");
            values.add(c.getId());
        }

        if (!columns.isEmpty()) {
            Controller.getInstance().getFilteredProducts(columns, values);
        } else if (products == null) {
            Controller.getInstance().refreshProductsView();
        } else {
            ProductTableModel model = (ProductTableModel) frmSearchProducts.getTblProducts().getModel();
            model.setProducts(products);
        }
    }

    public void setFilteredProducts(List<Product> products) {
        ProductTableModel model = (ProductTableModel) frmSearchProducts.getTblProducts().getModel();
        model.setProducts(products);
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(frmSearchProducts, 
                    java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchProducts.msg.NO_PRODUCTS"), 
                    java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchProducts.msg.SEARCH_TITLE"), 
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void filterProductsFailed(String message) {
        JOptionPane.showMessageDialog(frmSearchProducts, 
                java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchProducts.msg.FILTER_ERROR"), 
                java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchProducts.msg.SEARCH_TITLE"), 
                JOptionPane.ERROR_MESSAGE);
    }

}
