package Nutzeroberflaeche;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * Bauplan für das User Interface eines Login Dialogs
 */
public class LoginDialogUI {
    
    private JDialog _logindialog;
    JLabel _dialogIDLabel;
    JLabel _dialogPWLabel;
    private JButton _dialogOK;
    private JButton _dialogCancel;
    private JTextField _dialogID;
    private JPasswordField _dialogPW;
    
    public LoginDialogUI()
    {
        _logindialog = new JDialog();
		_logindialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		_logindialog.setLocationRelativeTo(_mainframe);
		_logindialog.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
		_logindialog.setLayout(new GridLayout(2, 3));
		
		_dialogIDLabel = new JLabel("User: ");
		_dialogPWLabel = new JLabel("Password: ");
		_dialogOK = new JButton("OK");
		_dialogCancel = new JButton("Cancel");
		_dialogID = new JTextField();
		_dialogPW = new JPasswordField();
		
		_logindialog.add(_dialogIDLabel);
		_logindialog.add(_dialogID);
		_logindialog.add(_dialogOK);
		_logindialog.add(_dialogPWLabel);
		_logindialog.add(_dialogPW);		
		_logindialog.add(_dialogCancel);
		
		_logindialog.pack();	
    }

    /**
     * @return the _dialogOK
     */
    public JButton getDialogOK() {
        return _dialogOK;
    }

    /**
     * @return the _dialogCancel
     */
    public JButton getDialogCancel() {
        return _dialogCancel;
    }

    /**
     * @return the _dialogID
     */
    public JTextField getDialogID() {
        return _dialogID;
    }

    /**
     * @return the _dialogPW
     */
    public JPasswordField getDialogPW() {
        return _dialogPW;
    }

    /**
     * @return the _logindialog
     */
    public JDialog getLogindialog() {
        return _logindialog;
    }
    
}
