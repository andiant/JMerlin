package Materialien;

/**
 *Dieser enum Typ beschreibt die verschiedenen Berechtigungsstufen, die ein 
 *Benutzer der Merlin  Datenbank haben kann.
 * 
 * R01 DB-Admin: besitzt System-Admin-Rechte für die Datenbank
 * R02 Content-Admin: darf die Stammdaten in Merlin ändern
 * R03 Birdwatcher:darf	eigene Beobachtungsdaten pflegen und Auswertungen anstoßen
 */
enum Berechtigungsstufe {
    R01,
    R02,
    R03;   
}
