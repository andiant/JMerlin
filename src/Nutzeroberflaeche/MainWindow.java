package Nutzeroberflaeche;

import Services.CSVParserService;
import Services.DBService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

/**
 * Ein Exemplar dieser Klasse erstellt ein neues User Interface für das
 * Arbeiten, auf der Merlin Datenbank
 */
public class MainWindow {

    private DBService _dbService;
    private MainWindowUI _ui;
    private LoginDialog _loginDialog;

    public MainWindow() {
        _dbService = new DBService();
        _ui = new MainWindowUI();
        
        _ui.getEintragenButton().setEnabled(false);
        _ui.getTabbedPane().setEnabledAt(0, true);
        _ui.getTabbedPane().setEnabledAt(1, false);
        _ui.getTabbedPane().setEnabledAt(2, false);
        
        _loginDialog = new LoginDialog();

        registriereUIAktionen();
    }

    /**
     * In dieser Methode werden alle UI Komponenten des Main Windows mit
     * Funktionen versehen.
     */
    private void registriereUIAktionen() {
        _ui.getLoginButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                _loginDialog.erscheine(_dbService);
                if (!(_loginDialog.getId().equals(""))) {
                    _ui.getAktuellerBenutzerLabel().setText("" + _loginDialog.getId());
                    _ui.getBerechtigungsstufeLabel().setText("" + _dbService.getBerechtigungsstufeVon(_loginDialog.getId()));
                    if(_dbService.getBerechtigungsstufeVon(_loginDialog.getId()).equals("R03"))
                    {
                        _ui.getEintragenButton().setEnabled(true);
                        _ui.getTabbedPane().setEnabledAt(0, true);
                        _ui.getTabbedPane().setEnabledAt(1, true);
                        _ui.getTabbedPane().setEnabledAt(2, false);
                    }
                    else if(_dbService.getBerechtigungsstufeVon(_loginDialog.getId()).equals("R02") || _dbService.getBerechtigungsstufeVon(_loginDialog.getId()).equals("R01"))
                    {
                        _ui.getEintragenButton().setEnabled(true);
                        _ui.getTabbedPane().setEnabledAt(0, true);
                        _ui.getTabbedPane().setEnabledAt(1, true);
                        _ui.getTabbedPane().setEnabledAt(2, true);
                    }
                }
            }
        });
        
        _ui.getImportPathButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int rueckgabeWert = chooser.showOpenDialog(null);
                
                if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
                {
                    _ui.getImportTextFeld().setText(chooser.getSelectedFile().getPath());
                }
            }
        });
        
        _ui.getImportImportButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DBService dbservice = new DBService();
                dbservice.CSVTabelleeinfuegen(_ui.getImportTextFeld().getText());
                _ui.getImportLabelInsert().setText("Es wurde(n) " + dbservice.getAenderungInsert() + " Zeile(n) hinzugefügt");
                _ui.getImportLabelUpdate().setText("Es wurde(n) " + dbservice.getAenderungUpdate() + " Zeile(n) aktualisiert");
            }
        });

    }

}
