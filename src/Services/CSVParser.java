/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Florian
 */
public class CSVParser {
    
    public String[][] readCSVFile(String path)
    {
        BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ";";
        ArrayList<String[]> ergebnis = new ArrayList<String[]>();
        
        try {
 
		br = new BufferedReader(new FileReader(path));
		while ((line = br.readLine()) != null) {
                        int index = 0;
		        // use semicolon as separator
			String[] vogel = new String[4];
                        vogel = line.split(cvsSplitBy);                       
                        ergebnis.add(vogel);
		}
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
 
        
        
        return null;   
    }
    
}
