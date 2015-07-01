package Services;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import Materialien.*;
import Nutzeroberflaeche.MainWindowUI;
import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Exemplare dieser Klasse bieten verschiedene Operationen um auf der Merlin
 * Datenbank zu arbeiten.
 *
 * Der DBService sorgt auch für das Herstellen einer Verbindung zu der Merlin
 * Datenbank
 */
public class DBService {

    private Connection _conn;
    private int _aenderungUpdate;
    private int _aenderungInsert;
    
    public DBService() {
        authentifiziere();
        _aenderungUpdate = 0;
        _aenderungInsert = 0;
    }

    /**
     * Methode stellt eine Verbindung zu der Merlin Datenbank her
     */
    public void authentifiziere() {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Klasse nicht gefunden");
        }

//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Enter Username!");
//		String id = scanner.nextLine();
//		System.out.println("Enter Password");
//		String pw = scanner.nextLine();
//		scanner.close();
//		
//		Console con = System.console();
//		
//		if(con == null)
//		{
//			System.out.println("Unable to open console");
//		}
//		
//		System.out.println("Enter Username!");
//		String id = con.readLine();
//		
//		System.out.println("Enter Password");
//		char[] char_pw = con.readPassword();
//		
//		String pw = char_pw.toString();
        String id = "";
        String pw = "";

        //LOGIN DATEN EINTRAGEN
        try {
            _conn = DriverManager.getConnection("jdbc:oracle:thin:@ora14.informatik.haw-hamburg.de:1521:inf14", Logindaten.getID(), Logindaten.getPW());
        } catch (SQLException e) {
            System.err.println("Could not log In!");
        }
    }

    /**
     * Diese Methode vergleicht übergebene Login Informationen mit den User-
     * informationen in der Datenbank.
     *
     * @param id Zu überprüfende ID
     * @param pw Zu überprüfendes Passwort
     * @return true wenn Logininformationen in der Datenabnk hinterlegt sind,
     * false wenn Logininformationen nicht in der Datenbank vorhanden sind.
     */
    public boolean login(String id, String pw) {
        try {
            Statement sql_stmt = _conn.createStatement();
            ResultSet rset = sql_stmt.executeQuery("SELECT USERNAME FROM BEOBACHTER WHERE USERNAME = '" + id + "' AND PASSWORT = '" + pw + "'");

            if (rset.next()) {
                System.out.println("Login erfolgreich");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in DBService.login()");
        }

        return false;
    }

    /**
     * Gibt die Berechtigungsstufe des übergebenen User wieder.
     *
     * @param id Die zu prüfende User ID
     * @return Die Berechtigungsstufe als String
     */
    public String getBerechtigungsstufeVon(String id) {
        Statement sql_stmt;
        try {
            sql_stmt = _conn.createStatement();
            ResultSet rset = sql_stmt.executeQuery("SELECT BERECHTIGUNGSSTUFE FROM BEOBACHTER WHERE USERNAME = '" + id + "'");
            rset.next();
            return rset.getString("BERECHTIGUNGSSTUFE");
        } catch (SQLException ex) {
            System.err.println("User nicht in der Datenbank, daher kann die Berechtigungsstufe nicht abgerufen werden.");
            System.err.println("Es muss sich erfolgreich eingeloggt werden!");
        }
        return "Invalid Username or Password!";
    }

    public ResultSet getResultSet(String sqlStatement) {
        try {
            Statement sql_stmt = _conn.createStatement();
            ResultSet rset = sql_stmt.executeQuery(sqlStatement);

            return rset;
        } catch (SQLException ex) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public int insert(String insertStatement) {

        try {
            Statement sql_stmt = _conn.createStatement();
            return sql_stmt.executeUpdate(insertStatement);

        } catch (SQLException ex) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
    
    public void CSVTabelleeinfuegen(String path)
    {
        CSVParserService csv = new CSVParserService();
        ArrayList<String[]> csvList = new ArrayList<String[]>();
        csvList = csv.leseCSVFile(path);
        
        try {
        Statement sql_stmt = _conn.createStatement();
        
        System.out.println("" + csvList.get(0)[0] + csvList.get(0)[1] + csvList.get(0)[2] + csvList.get(0)[3]);
        System.out.println("" + csvList.size());
        
        for(int i = 0; i < csvList.size(); ++i)
        {
            String sqlStatement = "";
            
            sqlStatement = "SELECT VogelID FROM Vogelart WHERE GATTUNGART = '" + csvList.get(i)[0] + "' AND UNTERART = '" + csvList.get(i)[1] + "'";
            ResultSet rset = sql_stmt.executeQuery(sqlStatement);
            if(rset.next())
            {
                int vogelID = rset.getInt(1);
                sqlStatement = "UPDATE Vogelart SET NAME_DE = '" + csvList.get(i)[2] + "', NAME_EN = '" + csvList.get(i)[3] + "' WHERE VOGELID = " + vogelID;
                int anzahl = sql_stmt.executeUpdate(sqlStatement);
                _aenderungUpdate = anzahl;
            }
            else
            {
                sqlStatement = "INSERT INTO VOGELART(VOGELID, GATTUNGART, UNTERART, NAME_DE, NAME_EN) VALUES ((SELECT MAX(VOGELID) + 1 FROM Vogelart), '" + csvList.get(i)[0] + "', '" + csvList.get(i)[1] + "', '" + csvList.get(i)[2] + "', '" + csvList.get(i)[3] + "')";
                int anzahl2 = sql_stmt.executeUpdate(sqlStatement);
                _aenderungInsert = anzahl2;
            }
        }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }}
              

//    public ResultSet getVogelarten(String whereClause) {
//
//        if (!whereClause.isEmpty()) {
//            whereClause = "WHERE " + whereClause;
//        }
//
//        try {
//            Statement sql_stmt = _conn.createStatement();
//            ResultSet rset = sql_stmt.executeQuery("SELECT VogelID, GattungArt || ' ' || Unterart AS Name_LT, Name_DE, Name_EN"
//                    + " FROM Vogelart "
//                    + whereClause
//                    + " ORDER BY Name_LT");
//
//            return rset;
//        } catch (SQLException ex) {
//            System.err.println("Fehler in DBService.getVogelarten()" + ex);
//            System.err.println(ex);
//        }
//
//        return null;
//
//    }
    public ResultSet getChecklists() {
        try {
            Statement sql_stmt = _conn.createStatement();
            ResultSet rset = sql_stmt.executeQuery("SELECT OrtID, Region || ' ' || Land || ' ' || Lokalitaet AS Checklist_Name, "
                    + " Region, Land, Lokalitaet "
                    + " FROM Ort");

            return rset;

        } catch (SQLException ex) {
            System.err.println("Fehler in DBService.getChecklists()");
            System.err.println(ex);
        }

        return null;
    }

    /**
     * @return the aenderungUpdate
     */
    public int getAenderungUpdate() {
        return _aenderungUpdate;
    }

    /**
     * @return the aenderungInsert
     */
    public int getAenderungInsert() {
        return _aenderungInsert;
    }
    
    public void commit() {
        try {
            _conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setAutoCommit(Boolean autoCommit) {
        try {
            _conn.setAutoCommit(autoCommit);
        } catch (SQLException ex) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void rollback() {
        try {
            _conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}