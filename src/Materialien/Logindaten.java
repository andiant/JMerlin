package Materialien;

import java.util.Scanner;

/**
 * Die Klasse dient als zentraler Zugriff auf die Logindaten w�hrend der Laufzeit des Programms.
 */
public class Logindaten {
	
	private static String ID;
	private static String PW;

	
	public static void setzeLogindaten()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Username");
		ID = scanner.nextLine();
		System.out.println("Enter Password");
		PW = scanner.nextLine();
		scanner.close();
	}
	
	public static String getID()
	{
		return ID;
	}
	
	public static String getPW()
	{
		return PW;
	}

}