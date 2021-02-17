/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Category;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Size;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.exception.NegativePriceException;
import rs.ac.bg.fon.ps.exception.RequiredFieldsEmptyException;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmProduct;
import rs.ac.bg.fon.ps.view.util.FormMode;

/**
 *
 * @author Katarina
 */
public class ProductController {

    private final FrmProduct frmProduct;

    public ProductController(FrmProduct frmProduct) {
        this.frmProduct = frmProduct;
        addActionListener();
    }

    private void addActionListener() {
        frmProduct.btnSaveAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }

            private void save() {
                try {
                    validateForm();

                    Product product = makeProductFromForm();
                    Controller.getInstance().addProduct(product);

                } catch (RequiredFieldsEmptyException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmProduct, "Please fill out required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frmProduct, "Price must be a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmProduct.btnCalculatePriceWithVATAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatePriceWithVAT();
            }
        });

        frmProduct.btnRevertAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
                fillProductForm();
            }
        });

        frmProduct.btnUpdateAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }

            private void update() {
                try {
                    int answer = JOptionPane.showConfirmDialog(frmProduct,
                            "Are you sure you want to make this changes",
                            "Change product",
                            JOptionPane.YES_NO_OPTION);
                    if (answer == 0) {
                        validateForm();

                        Product product = makeProductFromForm();
                        Controller.getInstance().updateProduct(product);
                    }
                } catch (RequiredFieldsEmptyException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmProduct, "Please fill out the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frmProduct, "Price must be a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmProduct.btnDeleteAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }

            private void delete() {
                int answer = JOptionPane.showConfirmDialog(frmProduct, "Are you sure you want to delete this product?",
                        "Confirm", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    Product product = makeProductFromForm();
                    Controller.getInstance().deleteProduct(product);
                }
            }
        });

        frmProduct.btnResetAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        frmProduct.btnAddSizeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSize();
            }

            private void addSize() {
                DefaultListModel listModel = (DefaultListModel) frmProduct.getListSelectedSizes().getModel();
                List<Size> selectedSizes = getSelectedSizes();
                if (!selectedSizes.contains((Size) frmProduct.getCmbSize().getSelectedItem())) {
                    listModel.addElement(frmProduct.getCmbSize().getSelectedItem());
                }
            }
        });

        frmProduct.btnRemoveSizeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSize();
            }

            private void removeSize() {
                DefaultListModel listModel = (DefaultListModel) frmProduct.getListSelectedSizes().getModel();
                List<Object> selectedSizes = frmProduct.getListSelectedSizes().getSelectedValuesList();
                for (Object selectedSize : selectedSizes) {
                    listModel.removeElement(selectedSize);
                }
            }
        });
    }

    public void openForm(FormMode formMode) {
        frmProduct.setLocationRelativeTo(frmProduct.getParent());
        frmProduct.setResizable(false);
        prepareView(formMode);
        frmProduct.setVisible(true);
    }

    private void prepareView(FormMode formMode) {
        fillCmbCategory();
        fillCmbSize();
        fillDefaultValues();
        setupComponents(formMode);
    }

    private void fillCmbCategory() {
        Controller.getInstance().getAllCategories();
    }

    private void fillCmbSize() {
        Controller.getInstance().getAllSizes();
    }

    private void fillDefaultValues() {
        frmProduct.getTxtVATPercentage().setText(String.valueOf(Constants.VAT_PERCENTAGE));
    }

    private void setupComponents(FormMode formMode) {
        switch (formMode) {
            case FORM_ADD:
                frmProduct.setTitle("Add new Product");
                frmProduct.getBtnSave().setVisible(true);
                frmProduct.getBtnReset().setVisible(true);
                frmProduct.getBtnRevert().setVisible(false);
                frmProduct.getBtnUpdate().setVisible(false);
                frmProduct.getBtnDelete().setVisible(false);
                break;
            case FORM_DETAIL:
                boolean admin = ((User) MainCordinator.getInstance().getParam(Constants.PARAM_CURRENT_USER)).isAdmin();
                frmProduct.setTitle("Product Details");
                frmProduct.getBtnSave().setVisible(false);
                frmProduct.getBtnReset().setVisible(false);
                setEditable(admin);
                frmProduct.getTxtArticle().setEditable(false);
                fillProductForm();
                break;
        }
    }

    private void setEditable(boolean edit) {
        for (Component component : frmProduct.getPanelProduct().getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setEditable(edit);
            } else if (component instanceof JButton) {
                ((JButton) component).setEnabled(edit);
            }
            frmProduct.getTxtDescription().setEditable(edit);
            frmProduct.getCmbCategory().setEnabled(edit);
            frmProduct.getBtnRevert().setVisible(edit);
            frmProduct.getBtnUpdate().setVisible(edit);
            frmProduct.getBtnDelete().setVisible(edit);
        }
    }

    private void fillProductForm() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Product product = (Product) MainCordinator.getInstance().getParam(Constants.PARAM_PRODUCT);
        frmProduct.getTxtArticle().setText(String.valueOf(product.getArticle()));
        frmProduct.getTxtName().setText(String.valueOf(product.getName()));
        frmProduct.getTxtDescription().setText(String.valueOf(product.getDescription()));
        frmProduct.getCmbCategory().setSelectedItem(product.getCategory());
        DefaultListModel listModel = (DefaultListModel) frmProduct.getListSelectedSizes().getModel();

        List<Size> sizes = product.getSizes();
        Collections.sort(sizes, new Comparator<Size>() {
            @Override
            public int compare(Size o1, Size o2) {
                return Integer.compare(o1.getSizeNumber(), o2.getSizeNumber());
            }
        });
        for (Size size : sizes) {
            listModel.addElement(size);
        }

        frmProduct.getTxtPriceWithoutVAT().setText(String.valueOf(product.getPriceWithoutVAT().setScale(2, RoundingMode.HALF_UP).doubleValue()));
        frmProduct.getTxtPriceWithVAT().setText(String.valueOf(product.getPriceWithVAT().setScale(2, RoundingMode.HALF_UP).doubleValue()));
    }

    private Product makeProductFromForm() throws NumberFormatException {
        Product product = new Product();
        product.setArticle(Long.parseLong(frmProduct.getTxtArticle().getText().trim()));
        product.setName(frmProduct.getTxtName().getText().trim());
        product.setDescription(frmProduct.getTxtDescription().getText().trim());
        product.setCategory((Category) frmProduct.getCmbCategory().getSelectedItem());
        product.setSizes(getSelectedSizes());
        product.setPriceWithoutVAT(new BigDecimal(frmProduct.getTxtPriceWithoutVAT().getText().trim()));
        product.setPriceWithVAT(calculatePriceWithVAT());

        return product;
    }

    private void resetErrors() {
        frmProduct.getLblArticleError().setText("");
        frmProduct.getLblNameError().setText("");
        frmProduct.getLblCategoryError().setText("");
        frmProduct.getLblSizeError().setText("");
        frmProduct.getLblPriceWithoutWatError().setText("");
    }

    private void resetForm() {
        resetErrors();

        frmProduct.getTxtArticle().setText("");
        frmProduct.getTxtName().setText("");
        frmProduct.getTxtDescription().setText("");
        frmProduct.getCmbCategory().setSelectedIndex(-1);
        frmProduct.getTxtPriceWithoutVAT().setText("");
        frmProduct.getTxtPriceWithVAT().setText("");

        DefaultListModel listModel = (DefaultListModel) frmProduct.getListSelectedSizes().getModel();
        listModel.removeAllElements();
    }

    private void validateForm() throws RequiredFieldsEmptyException {
        resetErrors();
        boolean errors = false;

        if (frmProduct.getTxtArticle().getText() == null || frmProduct.getTxtArticle().getText().isEmpty()) {
            frmProduct.getLblArticleError().setText("Required!");
            errors = true;
        }
        if (frmProduct.getTxtName().getText() == null || frmProduct.getTxtName().getText().isEmpty()) {
            frmProduct.getLblNameError().setText("Required!");
            errors = true;
        }
        if (frmProduct.getCmbCategory().getSelectedIndex() == -1) {
            frmProduct.getLblCategoryError().setText("Required!");
            errors = true;
        }
        if (getSelectedSizes() == null || getSelectedSizes().isEmpty()) {
            frmProduct.getLblSizeError().setText("Required!");
            errors = true;
        }
        if (frmProduct.getTxtPriceWithoutVAT().getText() == null || frmProduct.getTxtPriceWithoutVAT().getText().isEmpty()) {
            frmProduct.getLblPriceWithoutWatError().setText("Required!");
            errors = true;
        }

        if (errors) {
            throw new RequiredFieldsEmptyException("Required fields can not be empty.");
        }

    }

    private void validatePrice() throws Exception {
        double priceWithout = Double.parseDouble(frmProduct.getTxtPriceWithoutVAT().getText().trim());
        if (priceWithout <= 0) {
            throw new NegativePriceException("Price must be a natural number!");
        }

    }

    private BigDecimal calculatePriceWithVAT() {
        try {
            validatePrice();
            BigDecimal priceWithout = new BigDecimal(frmProduct.getTxtPriceWithoutVAT().getText().trim());
            int VATpercentage = Constants.VAT_PERCENTAGE;
            BigDecimal priceWith = priceWithout.multiply(new BigDecimal(1 + VATpercentage / 100.00));
            frmProduct.getTxtPriceWithVAT().setText(String.valueOf(priceWith.setScale(2, RoundingMode.HALF_UP).doubleValue()));
            return priceWith;
        } catch (NumberFormatException | NegativePriceException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frmProduct, "Price must be a positive number!", "Price Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(FrmProduct.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private List<Size> getSelectedSizes() {
        List<Size> selectedSizes = new ArrayList<>();
        for (int i = 0; i < frmProduct.getListSelectedSizes().getModel().getSize(); i++) {
            selectedSizes.add((Size) frmProduct.getListSelectedSizes().getModel().getElementAt(i));
        }

        return selectedSizes;
    }

    public void fillCategories(List<Category> categories) {
        frmProduct.getCmbCategory().removeAllItems();
        for (Category category : categories) {
            frmProduct.getCmbCategory().addItem(category);
        }
        frmProduct.getCmbCategory().setSelectedIndex(-1);
    }

    public void viewInitialisationFailed() {
        JOptionPane.showMessageDialog(frmProduct, "View initialisation failed!", "Error", JOptionPane.ERROR_MESSAGE);
        frmProduct.dispose();
    }

    public void fillSizes(List<Size> sizes) {
        frmProduct.getCmbSize().removeAllItems();
        for (Size size : sizes) {
            frmProduct.getCmbSize().addItem(size);
        }
    }

    public void saveProductSuccess() {
        resetForm();
        JOptionPane.showMessageDialog(frmProduct, "Product successfully saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
        Controller.getInstance().refreshProductsView();
    }

    public void saveProductFailed(String message) {
        JOptionPane.showMessageDialog(frmProduct, "Error occured. Save product failed.\n" + message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void updateProductSuccess() {
        Controller.getInstance().refreshProductsView();
        JOptionPane.showMessageDialog(frmProduct, "Product successfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void updateProductFailed() {
        JOptionPane.showMessageDialog(frmProduct, "Error occured. Update product failed.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void deleteProductSuccess() {
        Controller.getInstance().refreshProductsView();
        JOptionPane.showMessageDialog(frmProduct, "Product successfully deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
        frmProduct.dispose();
    }

    public void deleteProductFailed(String message) {
        JOptionPane.showMessageDialog(frmProduct, "Error occured. Delete product failed.\n" + message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
