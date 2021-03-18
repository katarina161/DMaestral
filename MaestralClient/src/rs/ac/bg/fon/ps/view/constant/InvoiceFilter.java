/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.constant;

/**
 *
 * @author Katarina
 */
public class InvoiceFilter {
    public static String ALL;
    public static String UNPROCESSED;
    public static String PROCESSED;
    public static String CANCELED;
    
    private static void defineConstants() {
        ALL = 
            java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchInvoices.msg.FILTER_ALL");
    UNPROCESSED = 
            java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchInvoices.msg.FILTER_UNPROCESSED");
    PROCESSED = 
            java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchInvoices.msg.FILTER_PROCESSED");
    CANCELED = 
            java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchInvoices.msg.FILTER_CANCELED");
    }
    
    public static String[] getAllConstants() {
        defineConstants();
        String[] constants = {ALL, UNPROCESSED, PROCESSED, CANCELED};
        return constants;
    }
}
