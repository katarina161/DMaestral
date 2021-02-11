/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.thread.ServerThread;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmServer;

/**
 *
 * @author Katarina
 */
public class MainController {

    private final FrmServer frmServer;
    private ServerThread serverThread;

    public MainController(FrmServer frmServer) {
        this.frmServer = frmServer;
        addActionListener();
    }

    private void addActionListener() {
        frmServer.jmiServerStartAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });

        frmServer.jmiServerStopAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopServer();
            }
        });

        frmServer.jmiConfigServerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openServerConfigForm();
            }
        });

        frmServer.jmiConfigDatabaseAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openDatabaseConfigForm();
            }
        });
    }

    public void openForm() {
        frmServer.setLocationRelativeTo(null);
        frmServer.setResizable(false);
        prepareView(false);
        frmServer.setVisible(true);
    }

    private void prepareView(boolean status) {
        setServerStatus(status);
    }

    public void setServerStatus(boolean status) {
        if (status) {
            frmServer.getMiServerStop().setEnabled(true);
            frmServer.getMiServerStart().setEnabled(false);
            frmServer.getLblStatusIcon().setIcon(new ImageIcon(getClass().getResource("/rs/ac/bg/fon/ps/view/image/up-arrow.png")));
            frmServer.getLblStatusIcon().setText("Up");
            frmServer.getLblStatusIcon().setForeground(new Color(79, 186, 111));
        } else {
            frmServer.getMiServerStop().setEnabled(false);
            frmServer.getMiServerStart().setEnabled(true);
            frmServer.getLblStatusIcon().setIcon(new ImageIcon(getClass().getResource("/rs/ac/bg/fon/ps/view/image/server-down.png")));
            frmServer.getLblStatusIcon().setText("Down");
            frmServer.getLblStatusIcon().setForeground(new Color(214, 26, 26));
        }
    }

    public void serverError() {
        JOptionPane.showMessageDialog(frmServer, "An error occurred while trying to start server!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void startServer() {
        serverThread = new ServerThread(this);
        serverThread.start();
    }

    private void stopServer() {
        serverThread.stopServer();
    }

    public FrmServer getFrmServer() {
        return frmServer;
    }

}
