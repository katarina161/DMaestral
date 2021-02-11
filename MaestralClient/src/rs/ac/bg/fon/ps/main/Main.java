/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.main;

import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;

/**
 *
 * @author Katarina
 */
public class Main {
    
    public static void main(String[] args) {
        Controller.getInstance().connect(9000);
        MainCordinator.getInstance().openLogInForm();
    }
    
}
