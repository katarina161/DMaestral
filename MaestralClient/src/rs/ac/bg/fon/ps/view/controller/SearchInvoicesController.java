/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Invoice;
import rs.ac.bg.fon.ps.view.component.table.InvoiceTableModel;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.constant.InvoiceFilter;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmSearchInvoices;

/**
 *
 * @author Katarina
 */
public class SearchInvoicesController {

    private final FrmSearchInvoices frmSearchInvoices;
    private final FrmMain parent;
    SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy.");

    public SearchInvoicesController(FrmSearchInvoices frmSearchInvoices) {
        this.frmSearchInvoices = frmSearchInvoices;
        this.parent = MainCordinator.getInstance().getMainController().getFrmMain();
        addActionListener();
    }

    private void addActionListener() {
        frmSearchInvoices.btnDetailsAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seeDetail();
            }

            private void seeDetail() {
                int row = frmSearchInvoices.getTblInvoices().getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(frmSearchInvoices,
                            java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchInvoices.msg.SEARCH_ERROR"),
                            java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchInvoices.msg.SEARCH_TITLE"),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    InvoiceTableModel model = (InvoiceTableModel) frmSearchInvoices.getTblInvoices().getModel();
                    Invoice invoice = model.getInvoice(row);
                    MainCordinator.getInstance().addParam(Constants.PARAM_INVOICE, invoice);
                    MainCordinator.getInstance().openInvoiceDetailsForm();
                }
            }
        });

        frmSearchInvoices.btnSearchAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterInvoices();
            }

            private void filterInvoices() {
                List<String> columns = new ArrayList<>();
                List<Object> values = new ArrayList<>();

                String number = frmSearchInvoices.getTxtSearchNumber().getText().trim();
                String partner = frmSearchInvoices.getTxtSearchPartner().getText().trim();
                Date date = frmSearchInvoices.getjDateChooser().getDate();
                String status = (String) frmSearchInvoices.getCmbFilter().getSelectedItem();

                if (!number.isEmpty()) {
                    columns.add("number");
                    values.add(number);
                }
                if (!partner.isEmpty()) {
                    columns.add("partner");
                    values.add(partner);
                }
                if (date != null) {
                    columns.add("date");
                    values.add(new java.sql.Date(date.getTime()));
                }
                if (status != null) {
                    if (status.equals(InvoiceFilter.CANCELED)) {
                        columns.add("canceled");
                        values.add(true);
                    } else if (status.equals(InvoiceFilter.PROCESSED)) {
                        columns.add("processed");
                        values.add(true);
                    } else if (status.equals(InvoiceFilter.UNPROCESSED)) {
                        columns.add("processed");
                        values.add(false);
                    }
                }

                if (!columns.isEmpty()) {
                    Controller.getInstance().getFilteredInvoices(columns, values);
                } else {
                    Controller.getInstance().refreshInvoicesView();
                }
            }
        });

        frmSearchInvoices.btnCancelFilterAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelFilter();
            }

            private void cancelFilter() {
                frmSearchInvoices.getTxtSearchNumber().setText("");
                frmSearchInvoices.getTxtSearchPartner().setText("");
                frmSearchInvoices.getjDateChooser().setCalendar(null);
                frmSearchInvoices.getCmbFilter().setSelectedIndex(0);
                Controller.getInstance().refreshInvoicesView();
            }
        });
    }

    public void openForm() {
        frmSearchInvoices.setLocationRelativeTo(parent);
        frmSearchInvoices.setResizable(false);
        frmSearchInvoices.getContentPane().setBackground(Color.WHITE);
        prepareView();
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
            Logger.getLogger(SearchInvoicesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmSearchInvoices.setVisible(true);
    }

    private void prepareView() {
        fillCmbFiter();
        gatherDataForTblInvoices();
    }

    private void fillCmbFiter() {
        frmSearchInvoices.getCmbFilter().removeAllItems();
        for (String filter : InvoiceFilter.getAllConstants()) {
            frmSearchInvoices.getCmbFilter().addItem(filter);
        }
    }

    private void gatherDataForTblInvoices() {
        Controller.getInstance().getAllInvoices();
    }

    public void fillTblInvoices(List<Invoice> invoices) throws Exception {
        InvoiceTableModel model = new InvoiceTableModel(invoices);
        frmSearchInvoices.getTblInvoices().setModel(model);
    }

    public void viewInitialisationFailed() {
        JOptionPane.showMessageDialog(frmSearchInvoices,
                java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("INITIALIZATION_FAILED"),
                java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("ERROR"),
                JOptionPane.ERROR_MESSAGE);
        frmSearchInvoices.dispose();
    }

    public FrmSearchInvoices getFrmSearchInvoices() {
        return frmSearchInvoices;
    }

    public void refreshInvoicesView(List<Invoice> invoices) {
        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        String number = frmSearchInvoices.getTxtSearchNumber().getText().trim();
        String partner = frmSearchInvoices.getTxtSearchPartner().getText().trim();
        Date date = frmSearchInvoices.getjDateChooser().getDate();
        String status = (String) frmSearchInvoices.getCmbFilter().getSelectedItem();

        if (!number.isEmpty()) {
            columns.add("number");
            values.add(number);
        }
        if (!partner.isEmpty()) {
            columns.add("partner");
            values.add(partner);
        }
        if (date != null) {
            columns.add("date");
            values.add(new java.sql.Date(date.getTime()));
        }
        if (status != null) {
            if (status.equals(InvoiceFilter.CANCELED)) {
                columns.add("canceled");
                values.add(true);
            } else if (status.equals(InvoiceFilter.PROCESSED)) {
                columns.add("processed");
                values.add(true);
            } else if (status.equals(InvoiceFilter.UNPROCESSED)) {
                columns.add("processed");
                values.add(false);
            }
        }

        if (!columns.isEmpty()) {
            Controller.getInstance().getFilteredInvoices(columns, values);
        } else {
            InvoiceTableModel model = (InvoiceTableModel) frmSearchInvoices.getTblInvoices().getModel();
            model.setInvoices(invoices);
        }
    }

    public void setFilteredInvoices(List<Invoice> invoices) {
        InvoiceTableModel model = (InvoiceTableModel) frmSearchInvoices.getTblInvoices().getModel();
        model.setInvoices(invoices);
        if (invoices.isEmpty()) {
            JOptionPane.showMessageDialog(frmSearchInvoices,
                    java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchInvoices.msg.NO_INVOICES"),
                    java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchInvoices.msg.SEARCH_TITLE"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void filterInvoicesFailed(String message) {
        JOptionPane.showMessageDialog(frmSearchInvoices,
                java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchInvoices.msg.FILTER_ERROR"),
                java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchInvoices.msg.SEARCH_TITLE"),
                JOptionPane.ERROR_MESSAGE);
    }

}
