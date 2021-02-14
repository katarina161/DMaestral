/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.view.component.table.ProductTableModel;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmSearchProducts;

/**
 *
 * @author Katarina
 */
public class SearchProductsController {

    private final FrmSearchProducts frmSearchProducts;
    private final FrmMain parent;

    public SearchProductsController(FrmSearchProducts frmSearchProducts) {
        this.frmSearchProducts = frmSearchProducts;
        this.parent = MainCordinator.getInstance().getMainController().getFrmMain();
        addActionListener();
    }

    private void addActionListener() {
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
                    JOptionPane.showMessageDialog(frmSearchProducts, "You must select a product.", "Error", JOptionPane.ERROR_MESSAGE);
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

    public void openForm() {
        frmSearchProducts.setLocationRelativeTo(parent);
        frmSearchProducts.setResizable(false);
        prepareView();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(SearchProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmSearchProducts.setVisible(true);
    }

    private void prepareView() {
        gatherDataForTblProducts();
    }

    private void gatherDataForTblProducts() {
        Controller.getInstance().getAllProducts();
    }

    public void fillTableProducts(List<Product> products) {
        ProductTableModel model = new ProductTableModel(products);
        frmSearchProducts.getTblProducts().setModel(model);
    }

    public void refreshProductsView(List<Product> products) {
        ProductTableModel model = (ProductTableModel) frmSearchProducts.getTblProducts().getModel();
        model.setProducts(products);
    }

    public FrmSearchProducts getFrmSearchProducts() {
        return frmSearchProducts;
    }

    public void viewInitialisationFailed() {
        JOptionPane.showMessageDialog(frmSearchProducts, "View initialisation failed!", "Error", JOptionPane.ERROR_MESSAGE);
        frmSearchProducts.dispose();
    }

}
