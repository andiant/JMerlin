/**
 * Ein Exemplar dieser Klasse erstellt einen Login Dialog, mithilfe 
 * der LoginDialogUI Klasse, welche dem User erlaubt sich auf der 
 * Datenbanksoftware einzuloggen
 */
package Nutzeroberflaeche;

import Services.DBService;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Florian
 */
public class LoginDialog {
    
    private LoginDialogUI _ui;
    private DBService _dbService;
    private String _id;
    private String _pw;
    
    
    public LoginDialog()
    {
        _id = "";
        _pw = "";
        
        _ui = new LoginDialogUI();
        registriereUIAktionen();
    }
    
    /**
     * Hier wird das Verhalten der verschiedenen Komponenten auf der Benutzer-
     * oberfläche des Login Dialogs bestimmt.
     */
    private void registriereUIAktionen()
    {
        _ui.getDialogOK().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               if(_dbService.login(leseID(), lesePW()))
               {
                _id = "" + leseID();
                _ui.getDialogID().setForeground(Color.BLACK);
		_ui.getDialogPW().setForeground(Color.BLACK);
		_ui.getDialogID().requestFocus();
                _ui.getLogindialog().dispose();
                _ui.getDialogPW().setText("");
               }
               else
               {
                   _id = "none";
                   _ui.getDialogID().setForeground(Color.RED);
                   _ui.getDialogPW().setForeground(Color.RED);
                   _ui.getDialogID().requestFocus();
               }
            }
        });
        
        _ui.getDialogCancel().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                _ui.getLogindialog().dispose();
            }
        });
    }
    
    /**
     * Beim Aufruf dieser Methode wird der Login Dialog in den Vordergrund gerufen
     * @param dbservice Der Login Dialog bekommt einen DBService sobald er 
     * erscheint, damit er Operationen auf der Datenbank durchführen kann
     */
    public void erscheine(DBService dbservice)
    {
        _dbService = dbservice;
        _ui.getLogindialog().setVisible(true);
    }
    
    /**
     * ließt die aktuell eingegebene ID im Login Dialog
     * @return ID las String
     */
    private String leseID()
    {
        return _ui.getDialogID().getText();
    }
    
    /**
     * ließt das aktuell eingegebene Passwort im Login Dialog
     * @return PW als String
     */
    private String lesePW()
    {
        return _ui.getDialogPW().getText();
    }

    /**
     * @return the _id
     */
    public String getId() {
        return _id;
    }

    /**
     * @return the _pw
     */
    public String getPw() {
        return _pw;
    }

    /**
     * @param _id the _id to set
     */
    public void setId(String id) {
        this._id = id;
    }
    
}
