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
public class UserFilter {
    
    public static String ALL;
    public static String ADMIN;
    public static String NOT_ADMIN;
    
    private static void defineConstants() {
        ALL = 
                java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchUser.cmb.FILTER_ALL");
        ADMIN = 
                java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchUser.cmb.FILTER_ADMIN");
        NOT_ADMIN = 
                java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchUser.cmb.FILTER_NOT_ADMIN");
    }
    
    public static String[] getAllConstants() {
        defineConstants();
        String[] constants = {ALL, ADMIN, NOT_ADMIN};
        return constants;
    }
}
