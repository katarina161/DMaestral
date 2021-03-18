/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.component;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Katarina
 */
public class SaveFileChooser extends JFileChooser {

    Object[] options = {java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("JOP.YES"),
        java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("JOP.NO")};

    public SaveFileChooser() {
        super();
        prepareView();
    }

    @Override
    public void approveSelection() {
        if (getDialogType() == SAVE_DIALOG) {
            File selectedFile = new File(getSelectedFile().getAbsolutePath() + ".pdf");
            if (selectedFile.exists()) {
                StringBuilder sb = new StringBuilder();
                sb.append(java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("pdf.EXIST_1"))
                        .append(selectedFile.getName())
                        .append(java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("pdf.EXIST_2"));
                int response = JOptionPane.showOptionDialog(this,
                        sb.toString(),
                        "PDF",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (response != JOptionPane.YES_OPTION) {
                    return;
                }
            }
        }

        super.approveSelection();
    }

    public void savePdf(byte[] pdf) {
        int userSelection = this.showSaveDialog(null);
        OutputStream out = null;

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                File fileToSave = this.getSelectedFile();
                String file = fileToSave.getAbsolutePath() + ".pdf";

                out = new FileOutputStream(file);
                out.write(pdf);
                out.close();

                int answer = JOptionPane.showOptionDialog(null,
                        java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("pdf.SHOW"),
                        "PDF",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (answer == 0) {
                    Desktop.getDesktop().open(new File(file));
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SaveFileChooser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SaveFileChooser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void prepareView() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Document (*.pdf)", "pdf");
        this.setFileFilter(filter);
        this.setDialogTitle("Choose a folder");
    }

}
