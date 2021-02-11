/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form;

import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Katarina
 */
public class FrmProduct extends javax.swing.JDialog {

    /**
     * Creates new form FrmProduct
     */
    public FrmProduct(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelProduct = new javax.swing.JPanel();
        lblArticle = new javax.swing.JLabel();
        txtArticle = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblCategory = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        lblDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        lblPriceWithoutVat = new javax.swing.JLabel();
        lblPriceWithVat = new javax.swing.JLabel();
        txtPriceWithoutVAT = new javax.swing.JTextField();
        txtPriceWithVAT = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        listSelectedSizes = new javax.swing.JList<>();
        lblSelectedSizes = new javax.swing.JLabel();
        cmbSize = new javax.swing.JComboBox<>();
        btnAddSize = new javax.swing.JButton();
        btnRemoveSize = new javax.swing.JButton();
        lblArticleError = new javax.swing.JLabel();
        lblNameError = new javax.swing.JLabel();
        lblSizeError = new javax.swing.JLabel();
        lblVATPercentage = new javax.swing.JLabel();
        lblPriceWithoutWatError = new javax.swing.JLabel();
        txtVATPercentage = new javax.swing.JTextField();
        btnCalculatePriceWithVAT = new javax.swing.JButton();
        lblCategoryError = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnRevert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Product");

        panelProduct.setBorder(javax.swing.BorderFactory.createTitledBorder("Product"));
        panelProduct.setPreferredSize(new java.awt.Dimension(800, 371));

        lblArticle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblArticle.setText("Article:");
        lblArticle.setMaximumSize(null);
        lblArticle.setPreferredSize(new java.awt.Dimension(110, 29));

        txtArticle.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtArticle.setPreferredSize(new java.awt.Dimension(69, 29));

        lblName.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblName.setText("Name:");
        lblName.setPreferredSize(new java.awt.Dimension(110, 29));

        txtName.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtName.setPreferredSize(new java.awt.Dimension(69, 29));

        lblCategory.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblCategory.setText("Category:");
        lblCategory.setPreferredSize(new java.awt.Dimension(95, 29));

        cmbCategory.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCategory.setSelectedIndex(-1);
        cmbCategory.setPreferredSize(new java.awt.Dimension(65, 29));

        lblDescription.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblDescription.setText("Description:");

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblPriceWithoutVat.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblPriceWithoutVat.setText("Price without VAT:");
        lblPriceWithoutVat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblPriceWithVat.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblPriceWithVat.setText("Price with VAT:");
        lblPriceWithVat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtPriceWithoutVAT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPriceWithoutVAT.setPreferredSize(new java.awt.Dimension(77, 29));
        txtPriceWithoutVAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceWithoutVATActionPerformed(evt);
            }
        });

        txtPriceWithVAT.setEditable(false);
        txtPriceWithVAT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPriceWithVAT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPriceWithVAT.setPreferredSize(new java.awt.Dimension(77, 29));

        listSelectedSizes.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        listSelectedSizes.setModel(new DefaultListModel());
        listSelectedSizes.setFocusable(false);
        listSelectedSizes.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        jScrollPane2.setViewportView(listSelectedSizes);

        lblSelectedSizes.setText("Selected sizes:");

        cmbSize.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cmbSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAddSize.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnAddSize.setText("Add Size");

        btnRemoveSize.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnRemoveSize.setText("Remove Size");

        lblArticleError.setForeground(new java.awt.Color(229, 10, 10));

        lblNameError.setForeground(new java.awt.Color(229, 10, 10));

        lblSizeError.setForeground(new java.awt.Color(229, 10, 10));

        lblVATPercentage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblVATPercentage.setText("VAT rate in %:");

        lblPriceWithoutWatError.setForeground(new java.awt.Color(229, 10, 10));

        txtVATPercentage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtVATPercentage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVATPercentage.setText("20");

        btnCalculatePriceWithVAT.setText("Calculate");

        lblCategoryError.setForeground(new java.awt.Color(229, 10, 10));

        javax.swing.GroupLayout panelProductLayout = new javax.swing.GroupLayout(panelProduct);
        panelProduct.setLayout(panelProductLayout);
        panelProductLayout.setHorizontalGroup(
            panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProductLayout.createSequentialGroup()
                        .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblArticleError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtArticle, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(lblNameError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelProductLayout.createSequentialGroup()
                        .addComponent(lblDescription)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProductLayout.createSequentialGroup()
                        .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelProductLayout.createSequentialGroup()
                                .addGap(0, 1, Short.MAX_VALUE)
                                .addComponent(cmbSize, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAddSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnRemoveSize, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblSizeError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSelectedSizes)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProductLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblPriceWithoutWatError, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelProductLayout.createSequentialGroup()
                        .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelProductLayout.createSequentialGroup()
                                .addComponent(lblPriceWithoutVat, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPriceWithoutVAT, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelProductLayout.createSequentialGroup()
                                .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelProductLayout.createSequentialGroup()
                                        .addComponent(lblPriceWithVat, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProductLayout.createSequentialGroup()
                                        .addComponent(lblVATPercentage)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtVATPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(139, 139, 139)))
                                .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCalculatePriceWithVAT)
                                    .addComponent(txtPriceWithVAT, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelProductLayout.createSequentialGroup()
                                .addComponent(lblCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblCategoryError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbCategory, 0, 252, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        panelProductLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCalculatePriceWithVAT, lblPriceWithoutWatError, txtPriceWithVAT, txtPriceWithoutVAT});

        panelProductLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAddSize, btnRemoveSize});

        panelProductLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane1, txtName});

        panelProductLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblArticleError, txtArticle});

        panelProductLayout.setVerticalGroup(
            panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductLayout.createSequentialGroup()
                .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelProductLayout.createSequentialGroup()
                        .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelProductLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lblArticleError, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblCategoryError, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblArticle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtArticle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCategory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCategory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelProductLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(lblNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProductLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSelectedSizes)
                                    .addComponent(lblSizeError, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelProductLayout.createSequentialGroup()
                                .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbSize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAddSize, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelProductLayout.createSequentialGroup()
                                .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelProductLayout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addComponent(btnRemoveSize))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(lblPriceWithoutWatError, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescription)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelProductLayout.createSequentialGroup()
                                .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPriceWithoutVat)
                                    .addComponent(txtPriceWithoutVAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelProductLayout.createSequentialGroup()
                                        .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblVATPercentage)
                                            .addComponent(txtVATPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(20, 20, 20))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProductLayout.createSequentialGroup()
                                        .addComponent(btnCalculatePriceWithVAT)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPriceWithVAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPriceWithVat))))))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        panelProductLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAddSize, btnRemoveSize, cmbCategory, cmbSize, lblArticle, lblCategory, lblDescription, lblName, lblPriceWithVat, lblPriceWithoutVat, txtArticle, txtName, txtPriceWithVAT, txtPriceWithoutVAT});

        panelProductLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblVATPercentage, txtVATPercentage});

        btnSave.setBackground(new java.awt.Color(2, 26, 126));
        btnSave.setFont(new java.awt.Font("Bauhaus 93", 1, 24)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Save");

        btnReset.setBackground(new java.awt.Color(2, 26, 126));
        btnReset.setFont(new java.awt.Font("Bauhaus 93", 1, 24)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");

        btnRevert.setBackground(new java.awt.Color(2, 26, 126));
        btnRevert.setFont(new java.awt.Font("Bauhaus 93", 1, 24)); // NOI18N
        btnRevert.setForeground(new java.awt.Color(255, 255, 255));
        btnRevert.setText("Revert");

        btnUpdate.setBackground(new java.awt.Color(2, 26, 126));
        btnUpdate.setFont(new java.awt.Font("Bauhaus 93", 1, 24)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");

        btnDelete.setBackground(new java.awt.Color(204, 0, 0));
        btnDelete.setFont(new java.awt.Font("Bauhaus 93", 1, 24)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(183, 183, 183)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRevert, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRevert, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPriceWithoutVATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceWithoutVATActionPerformed
//        calculatePriceWithVAT();
    }//GEN-LAST:event_txtPriceWithoutVATActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddSize;
    private javax.swing.JButton btnCalculatePriceWithVAT;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRemoveSize;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnRevert;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<Object> cmbCategory;
    private javax.swing.JComboBox<Object> cmbSize;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblArticle;
    private javax.swing.JLabel lblArticleError;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblCategoryError;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNameError;
    private javax.swing.JLabel lblPriceWithVat;
    private javax.swing.JLabel lblPriceWithoutVat;
    private javax.swing.JLabel lblPriceWithoutWatError;
    private javax.swing.JLabel lblSelectedSizes;
    private javax.swing.JLabel lblSizeError;
    private javax.swing.JLabel lblVATPercentage;
    private javax.swing.JList<Object> listSelectedSizes;
    private javax.swing.JPanel panelProduct;
    private javax.swing.JTextField txtArticle;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPriceWithVAT;
    private javax.swing.JTextField txtPriceWithoutVAT;
    private javax.swing.JTextField txtVATPercentage;
    // End of variables declaration//GEN-END:variables

    public JComboBox<Object> getCmbCategory() {
        return cmbCategory;
    }

    public JComboBox<Object> getCmbSize() {
        return cmbSize;
    }

    public JButton getBtnAddSize() {
        return btnAddSize;
    }

    public JButton getBtnCalculatePriceWithVAT() {
        return btnCalculatePriceWithVAT;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnRemoveSize() {
        return btnRemoveSize;
    }

    public JButton getBtnReset() {
        return btnReset;
    }

    public JButton getBtnRevert() {
        return btnRevert;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public JTextField getTxtArticle() {
        return txtArticle;
    }

    public JTextArea getTxtDescription() {
        return txtDescription;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public JTextField getTxtPriceWithVAT() {
        return txtPriceWithVAT;
    }

    public JTextField getTxtPriceWithoutVAT() {
        return txtPriceWithoutVAT;
    }

    public JTextField getTxtVATPercentage() {
        return txtVATPercentage;
    }

    public JList<Object> getListSelectedSizes() {
        return listSelectedSizes;
    }

    public JLabel getLblArticleError() {
        return lblArticleError;
    }

    public JLabel getLblCategoryError() {
        return lblCategoryError;
    }

    public JLabel getLblNameError() {
        return lblNameError;
    }

    public JLabel getLblPriceWithoutWatError() {
        return lblPriceWithoutWatError;
    }

    public JLabel getLblSizeError() {
        return lblSizeError;
    }
    

    public void btnSaveAddActionListener(ActionListener actionListener) {
        btnSave.addActionListener(actionListener);
    }

    public void btnCalculatePriceWithVATAddActionListener(ActionListener actionListener) {
        btnCalculatePriceWithVAT.addActionListener(actionListener);
    }

    public void btnRevertAddActionListener(ActionListener actionListener) {
        btnRevert.addActionListener(actionListener);
    }

    public void btnUpdateAddActionListener(ActionListener actionListener) {
        btnUpdate.addActionListener(actionListener);
    }

    public void btnDeleteAddActionListener(ActionListener actionListener) {
        btnDelete.addActionListener(actionListener);
    }

    public void btnResetAddActionListener(ActionListener actionListener) {
        btnReset.addActionListener(actionListener);
    }

    public void btnAddSizeAddActionListener(ActionListener actionListener) {
        btnAddSize.addActionListener(actionListener);
    }

    public void btnRemoveSizeAddActionListener(ActionListener actionListener) {
        btnRemoveSize.addActionListener(actionListener);
    }
   
}