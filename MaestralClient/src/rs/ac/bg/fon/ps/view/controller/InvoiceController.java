/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Invoice;
import rs.ac.bg.fon.ps.domain.InvoiceItem;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Size;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.exception.InvalidFormException;
import rs.ac.bg.fon.ps.view.component.table.InvoiceItemTableModel;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmInvoice;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.util.FormMode;

/**
 *
 * @author Katarina
 */
public class InvoiceController {

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy.");

    private final FrmInvoice frmInvoice;
    private final FrmMain parent;

    public InvoiceController(FrmInvoice frmInvoice) {
        this.frmInvoice = frmInvoice;
        this.parent = MainCordinator.getInstance().getMainController().getFrmMain();
        addActionListener();
    }

    private void addActionListener() {
        frmInvoice.btnAddItemAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }

            private void addItem() {
                try {
                    validateItemForm();

                    InvoiceItem item = makeItemFromForm();
                    InvoiceItemTableModel model = (InvoiceItemTableModel) frmInvoice.getTblItems().getModel();
                    model.addInvoiceItem(item);
                } catch (InvalidFormException ex) {
                    frmInvoice.getLblAddItemError().setText(ex.getMessage());
                } catch (Exception ex) {
                    Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        frmInvoice.btnRemoveItemAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItem();
            }

            private void removeItem() {
                int row = frmInvoice.getTblItems().getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(frmInvoice,
                            "Choose an item that you want to be removed from list.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    int answer
                            = JOptionPane.showConfirmDialog(frmInvoice,
                                    "Are you sure you want to remove selected invoice item?",
                                    "Remove",
                                    JOptionPane.YES_NO_OPTION);
                    if (answer == 0) {
                        InvoiceItemTableModel model = (InvoiceItemTableModel) frmInvoice.getTblItems().getModel();
                        model.removeveInvoiceItem(row);
                    }
                }
            }
        });

        frmInvoice.btnCloseAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeForm();
            }

            private void closeForm() {
                int answer = JOptionPane.showConfirmDialog(frmInvoice,
                        "Are you sure you want to close this window, your inputs won't be saved?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    frmInvoice.dispose();
                }
            }
        });

        frmInvoice.btnSaveAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveInvoice();
            }

            private void saveInvoice() {
                try {
                    validateForm();

                    Invoice invoice = makeInvoiceFromForm();
                    Controller.getInstance().saveInvoice(invoice);
                } catch (InvalidFormException ex) {
                    Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmInvoice, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException ex) {
                    Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmInvoice, "Date must be in 'dd.MM.yyyy' format", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void openForm(FormMode formMode) {
        frmInvoice.setLocationRelativeTo(parent);
        frmInvoice.setResizable(false);
        prepareView(formMode);
        frmInvoice.setVisible(true);
    }

    private void prepareView(FormMode formMode) {
        fillCmbProducts();
        fillTblItems();
        frmInvoice.getCmbSizes().removeAllItems();
        try {
            Thread.sleep(1300);
        } catch (InterruptedException ex) {
            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setupComponents(formMode);
    }

    private void fillCmbProducts() {
        Controller.getInstance().getAllProducts();
    }

    private void fillCmbSizes(Product product) {
        frmInvoice.getCmbSizes().removeAllItems();
        List<Size> sizes = product.getSizes();
        Collections.sort(sizes, new Comparator<Size>() {
            @Override
            public int compare(Size o1, Size o2) {
                return Integer.compare(o1.getSizeNumber(), o2.getSizeNumber());
            }
        });
        for (Size size : sizes) {
            frmInvoice.getCmbSizes().addItem(size);
        }

        frmInvoice.getCmbSizes().setSelectedIndex(-1);
    }

    private void fillTblItems() {
        InvoiceItemTableModel model = new InvoiceItemTableModel();
        frmInvoice.getTblItems().setModel(model);

        frmInvoice.getTblItems().getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                InvoiceItemTableModel model = (InvoiceItemTableModel) frmInvoice.getTblItems().getModel();
                BigDecimal total = model.getInvoice().getTotal();
                frmInvoice.getLblTotal().setText(String.valueOf(total));
            }
        });

        frmInvoice.tblItemsAddSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = frmInvoice.getTblItems().getSelectedRow();
                if (row >= 0) {
                    InvoiceItemTableModel model = (InvoiceItemTableModel) frmInvoice.getTblItems().getModel();
                    Product product = (Product) model.getValueAt(row, 1);
                    JComboBox sizes = new JComboBox(product.getSizes().toArray());
                    TableColumn colSize = frmInvoice.getTblItems().getColumnModel().getColumn(2);
                    colSize.setCellEditor(new DefaultCellEditor(sizes));
                }
            }
        });
    }

    private void setupComponents(FormMode formMode) {
        switch (formMode) {
            case FORM_ADD:
                frmInvoice.getTxtNumber().setEnabled(true);
                frmInvoice.getTxtDate().setEnabled(true);
                frmInvoice.getTxtPartner().setEnabled(true);
                frmInvoice.getBtnRemoveItem().setVisible(true);
                frmInvoice.getPanelItem().setVisible(true);
                frmInvoice.getBtnClose().setVisible(true);
                frmInvoice.getBtnSave().setVisible(true);
                frmInvoice.getBtnCancel().setVisible(false);
                frmInvoice.getBtnDelete().setVisible(false);
                frmInvoice.getBtnEdit().setVisible(false);
                frmInvoice.getBtnProcess().setVisible(false);
                frmInvoice.getTblItems().setEnabled(true);
                fillDefaultValues();
                break;
            case FORM_DETAIL:
                Invoice invoice = (Invoice) MainCordinator.getInstance().getParam(Constants.PARAM_INVOICE);
                if (invoice.isProcessed() && !invoice.isCanceld()) {
                    openProcessedInvoice();
                } else if (invoice.isCanceld()) {
                    openCanceledInvoice();
                } else {
                    openSavedInvoice();
                }
                fillForm(invoice);
                break;
        }
    }

    private void fillDefaultValues() {
        frmInvoice.getTxtDate().setText(DATE_FORMAT.format(new Date()));
        frmInvoice.getLblUser().setText(String.valueOf(MainCordinator.getInstance().getParam(Constants.PARAM_CURRENT_USER)));
    }

    private void validateItemForm() throws InvalidFormException {
        frmInvoice.getLblAddItemError().setText("");
        String message = "*";
        boolean error = false;

        if (frmInvoice.getCmbProducts().getSelectedIndex() == -1) {
            message += "Please select a product. ";
            error = true;
        }
        if (frmInvoice.getCmbSizes().getComponentCount() > 0 && frmInvoice.getCmbSizes().getSelectedIndex() == -1) {
            message += "Please select a size. ";
            error = true;
        }
        if (frmInvoice.getTxtPrice().getText().matches(".*[a-zA-Z-]+.*|0")) {
            message += "Price must be a positive number. ";
            error = true;
        }
        if (frmInvoice.getTxtQuantity().getText().matches(".*[a-zA-Z-]+.*|0")) {
            message += "Quantity must be a positive number. ";
            error = true;
        }

        if (error) {
            throw new InvalidFormException(message);
        }
    }

    private InvoiceItem makeItemFromForm() {
        Product product = (Product) frmInvoice.getCmbProducts().getSelectedItem();
        Size size = (Size) frmInvoice.getCmbSizes().getSelectedItem();
        BigDecimal price = new BigDecimal(frmInvoice.getTxtPrice().getText().trim());
        int quantity = Integer.parseInt(frmInvoice.getTxtQuantity().getText().trim());

        InvoiceItem item = new InvoiceItem();
        item.setProduct(product);
        item.setSize(size);
        item.setPrice(price);
        item.setQuantity(quantity);

        return item;
    }

    private void validateForm() throws InvalidFormException {
        String message = "";
        if (frmInvoice.getTxtNumber().getText() == null || frmInvoice.getTxtNumber().getText().isEmpty()) {
            message += "Enter an invoice number.\n";
        }
        if (frmInvoice.getTxtDate().getText() == null || frmInvoice.getTxtDate().getText().isEmpty()) {
            message += "Insert date.\n";
        }
        if (frmInvoice.getTxtPartner().getText() == null || frmInvoice.getTxtPartner().getText().isEmpty()) {
            message += "Insert partner.\n";
        }
        InvoiceItemTableModel model = (InvoiceItemTableModel) frmInvoice.getTblItems().getModel();
        if (model.getItems().isEmpty()) {
            message += "Add at least one invoice item.\n";
        }
        if (!message.isEmpty()) {
            throw new InvalidFormException(message);
        }
    }

    private Invoice makeInvoiceFromForm() throws ParseException {
        String number = frmInvoice.getTxtNumber().getText().trim();
        Date date = DATE_FORMAT.parse(frmInvoice.getTxtDate().getText());
        String partner = frmInvoice.getTxtPartner().getText();
        User user = (User) MainCordinator.getInstance().getParam(Constants.PARAM_CURRENT_USER);
        InvoiceItemTableModel model = (InvoiceItemTableModel) frmInvoice.getTblItems().getModel();
        Invoice invoice = model.getInvoice();
        invoice.setNumber(number);
        invoice.setDate(date);
        invoice.setPartner(partner);
        invoice.setUser(user);

        return invoice;
    }

    private void openProcessedInvoice() {
        frmInvoice.getTxtNumber().setEnabled(false);
        frmInvoice.getTxtDate().setEnabled(false);
        frmInvoice.getTxtPartner().setEnabled(false);
        frmInvoice.getBtnClose().setVisible(true);
        frmInvoice.getBtnSave().setVisible(false);
        frmInvoice.getBtnCancel().setVisible(true);
        frmInvoice.getBtnDelete().setVisible(false);
        frmInvoice.getBtnEdit().setVisible(false);
        frmInvoice.getBtnProcess().setVisible(false);
        frmInvoice.getBtnRemoveItem().setVisible(false);
        frmInvoice.getPanelItem().setVisible(false);
        frmInvoice.getTblItems().setEnabled(false);
    }

    private void openCanceledInvoice() {
        frmInvoice.getTxtNumber().setEnabled(false);
        frmInvoice.getTxtDate().setEnabled(false);
        frmInvoice.getTxtPartner().setEnabled(false);
        frmInvoice.getBtnClose().setVisible(true);
        frmInvoice.getBtnSave().setVisible(false);
        frmInvoice.getBtnCancel().setVisible(false);
        frmInvoice.getBtnDelete().setVisible(false);
        frmInvoice.getBtnEdit().setVisible(false);
        frmInvoice.getBtnProcess().setVisible(false);
        frmInvoice.getBtnRemoveItem().setVisible(false);
        frmInvoice.getPanelItem().setVisible(false);
        frmInvoice.getTblItems().setEnabled(false);
    }

    private void openSavedInvoice() {
        frmInvoice.getTxtNumber().setEnabled(false);
        frmInvoice.getBtnClose().setVisible(true);
        frmInvoice.getBtnSave().setVisible(false);
        frmInvoice.getBtnCancel().setVisible(false);
        frmInvoice.getBtnDelete().setVisible(true);
        frmInvoice.getBtnEdit().setVisible(true);
        frmInvoice.getBtnProcess().setVisible(true);
        frmInvoice.getTblItems().setEnabled(true);
    }

    private void fillForm(Invoice invoice) {
        frmInvoice.getTxtNumber().setText(invoice.getNumber());
        frmInvoice.getTxtDate().setText(DATE_FORMAT.format(invoice.getDate()));
        frmInvoice.getTxtPartner().setText(invoice.getPartner());
        frmInvoice.getLblUser().setText(String.valueOf(invoice.getUser()));
        if (invoice.isProcessed()) {
            frmInvoice.getCbProcessed().setSelected(true);
        }
        if (invoice.isCanceld()) {
            frmInvoice.getCbCanceled().setSelected(true);
        }
        InvoiceItemTableModel model = (InvoiceItemTableModel) frmInvoice.getTblItems().getModel();
        model.setInvoice(invoice);

        frmInvoice.getLblTotal().setText(String.valueOf(invoice.getTotal()));
    }

    public void setProducts(List<Product> products) {
        frmInvoice.getCmbProducts().removeAllItems();

        for (Product product : products) {
            frmInvoice.getCmbProducts().addItem(product);
        }

        frmInvoice.getCmbProducts().setSelectedIndex(-1);
        frmInvoice.getCmbProducts().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Product product = (Product) e.getItem();
                    fillCmbSizes(product);
                    frmInvoice.getTxtPrice().setText(product.getPriceWithVAT().setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
                    frmInvoice.getTxtQuantity().setText("1");
                    frmInvoice.getTxtQuantity().grabFocus();
                    frmInvoice.getTxtQuantity().setSelectionStart(0);
                }
            }
        });
    }

    public void viewInitialisationFailed() {
        JOptionPane.showMessageDialog(frmInvoice, "View initialisation failed!", "Error", JOptionPane.ERROR_MESSAGE);
        frmInvoice.dispose();
    }

    public void saveInvoiceSuccess(Invoice invoice) {
        JOptionPane.showMessageDialog(frmInvoice,
                "Invoice (id=" + invoice.getId() + ") successfully saved!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        MainCordinator.getInstance().addParam(Constants.PARAM_INVOICE, invoice);
        setupComponents(FormMode.FORM_DETAIL);
    }

    public void saveInvoiceFailed() {
        JOptionPane.showMessageDialog(frmInvoice, "Save invoice failed!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
