/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.component;

import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public final static int FILE_SIZE = 6022386;

    public SaveFileChooser() {
        super();
        prepareView();
    }

    @Override
    public void approveSelection() {
        if (getDialogType() == SAVE_DIALOG) {
            File selectedFile = new File(getSelectedFile().getAbsolutePath() + ".pdf");
            if (selectedFile.exists()) {
                int response = JOptionPane.showConfirmDialog(this,
                        "The file " + selectedFile.getName()
                        + " already exists. Do you want to replace the existing file?",
                        "Ovewrite file", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
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

                int answer = JOptionPane.showConfirmDialog(null, "Prikazi racun?", "Prikazi", JOptionPane.YES_NO_OPTION);
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
