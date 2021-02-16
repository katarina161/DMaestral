/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form;

import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Katarina
 */
public class FrmMain extends javax.swing.JFrame {

    /**
     * Creates new form FrmMain
     */
    public FrmMain() {
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

        lblImage = new javax.swing.JLabel();
        menuMain = new javax.swing.JMenuBar();
        menuProduct = new javax.swing.JMenu();
        miProductNew = new javax.swing.JMenuItem();
        miProductSearch = new javax.swing.JMenuItem();
        menuInvoice = new javax.swing.JMenu();
        miInvoiceNew = new javax.swing.JMenuItem();
        miInvoiceSearch = new javax.swing.JMenuItem();
        menuEmployer = new javax.swing.JMenu();
        miEmployerNew = new javax.swing.JMenuItem();
        miEmployerSearch = new javax.swing.JMenuItem();
        menuUser = new javax.swing.JMenu();
        miLogOut = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Maestral");
        setMinimumSize(new java.awt.Dimension(1048, 800));

        lblImage.setFont(new java.awt.Font("Tahoma", 1, 53)); // NOI18N
        lblImage.setForeground(new java.awt.Color(0, 0, 255));
        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rs/ac/bg/fon/ps/view/image/teddy.png"))); // NOI18N
        lblImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblImage.setMinimumSize(new java.awt.Dimension(0, 0));

        menuMain.setPreferredSize(new java.awt.Dimension(125, 40));

        menuProduct.setForeground(new java.awt.Color(2, 26, 126));
        menuProduct.setText("Product");
        menuProduct.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N

        miProductNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        miProductNew.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miProductNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rs/ac/bg/fon/ps/view/image/plus.png"))); // NOI18N
        miProductNew.setText("New");
        menuProduct.add(miProductNew);

        miProductSearch.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        miProductSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miProductSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rs/ac/bg/fon/ps/view/image/search.png"))); // NOI18N
        miProductSearch.setText("Search");
        menuProduct.add(miProductSearch);

        menuMain.add(menuProduct);

        menuInvoice.setForeground(new java.awt.Color(2, 26, 126));
        menuInvoice.setText("Invoice");
        menuInvoice.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N

        miInvoiceNew.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miInvoiceNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rs/ac/bg/fon/ps/view/image/invoice.png"))); // NOI18N
        miInvoiceNew.setText("Create");
        menuInvoice.add(miInvoiceNew);

        miInvoiceSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miInvoiceSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rs/ac/bg/fon/ps/view/image/invoice-search.png"))); // NOI18N
        miInvoiceSearch.setText("Search");
        menuInvoice.add(miInvoiceSearch);

        menuMain.add(menuInvoice);

        menuEmployer.setForeground(new java.awt.Color(2, 26, 126));
        menuEmployer.setText("Employer");
        menuEmployer.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N

        miEmployerNew.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miEmployerNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rs/ac/bg/fon/ps/view/image/register.png"))); // NOI18N
        miEmployerNew.setText("Register");
        menuEmployer.add(miEmployerNew);

        miEmployerSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miEmployerSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rs/ac/bg/fon/ps/view/image/search.png"))); // NOI18N
        miEmployerSearch.setText("Search");
        menuEmployer.add(miEmployerSearch);

        menuMain.add(menuEmployer);

        menuUser.setForeground(new java.awt.Color(2, 26, 126));
        menuUser.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        miLogOut.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rs/ac/bg/fon/ps/view/image/logout.png"))); // NOI18N
        miLogOut.setText("Log out");
        menuUser.add(miLogOut);

        menuMain.add(menuUser);

        setJMenuBar(menuMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblImage;
    private javax.swing.JMenu menuEmployer;
    private javax.swing.JMenu menuInvoice;
    private javax.swing.JMenuBar menuMain;
    private javax.swing.JMenu menuProduct;
    private javax.swing.JMenu menuUser;
    private javax.swing.JMenuItem miEmployerNew;
    private javax.swing.JMenuItem miEmployerSearch;
    private javax.swing.JMenuItem miInvoiceNew;
    private javax.swing.JMenuItem miInvoiceSearch;
    private javax.swing.JMenuItem miLogOut;
    private javax.swing.JMenuItem miProductNew;
    private javax.swing.JMenuItem miProductSearch;
    // End of variables declaration//GEN-END:variables

    public JMenu getMenuUser() {
        return menuUser;
    }

    public void jmiProductNewAddActionListener(ActionListener actionListener) {
        miProductNew.addActionListener(actionListener);
    }

    public void jmiProductSearchAddActionListener(ActionListener actionListener) {
        miProductSearch.addActionListener(actionListener);
    }

    public void jmiLogOutAddActionListener(ActionListener actionListener) {
        miLogOut.addActionListener(actionListener);
    }

    public void jmiInvoiceNewAddActionListener(ActionListener actionListener) {
        miInvoiceNew.addActionListener(actionListener);
    }

    public void jmiInvoiceSearchAddActionListener(ActionListener actionListener) {
        miInvoiceSearch.addActionListener(actionListener);
    }

    public JLabel getLblImage() {
        return lblImage;
    }

    public JMenu getMenuEmployer() {
        return menuEmployer;
    }

    public JMenuItem getMiInvoiceNew() {
        return miInvoiceNew;
    }

    public JMenuItem getMiProductNew() {
        return miProductNew;
    }

    public void jmiEmployerNewAddActionListener(ActionListener actionListener) {
        miEmployerNew.addActionListener(actionListener);
    }

    public void jmiEmployerSearchAddActionListener(ActionListener actionListener) {
        miEmployerSearch.addActionListener(actionListener);
    }
    
}
