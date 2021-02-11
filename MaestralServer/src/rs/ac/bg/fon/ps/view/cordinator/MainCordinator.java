/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.cordinator;

import rs.ac.bg.fon.ps.view.controller.DatabaseConfigController;
import rs.ac.bg.fon.ps.view.controller.MainController;
import rs.ac.bg.fon.ps.view.controller.ServerConfigController;
import rs.ac.bg.fon.ps.view.form.FrmDatabaseConfig;
import rs.ac.bg.fon.ps.view.form.FrmServer;
import rs.ac.bg.fon.ps.view.form.FrmServerConfig;

/**
 *
 * @author Katarina
 */
public class MainCordinator {
    private static MainCordinator instance;
    private MainController mainController;

    private MainCordinator() {
    }

    public static MainCordinator getInstance() {
        if (instance == null) {
            instance = new MainCordinator();
        }
        return instance;
    }

    public void openMainForm() {
        mainController = new MainController(new FrmServer());
        mainController.openForm();
    }

    public void openServerConfigForm() {
        ServerConfigController serverConfigController = 
                new ServerConfigController(new FrmServerConfig(mainController.getFrmServer(), true));
        serverConfigController.openForm();
    }

    public void openDatabaseConfigForm() {
        DatabaseConfigController databaseConfigController = 
                new DatabaseConfigController(new FrmDatabaseConfig(mainController.getFrmServer(), true));
        databaseConfigController.openForm();
    }
    
}
