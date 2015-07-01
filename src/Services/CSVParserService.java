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
public class CSVParserService {
    
    public ArrayList<String[]> leseCSVFile(String path)
    {
        BufferedReader br = null;
	String line = "";
	String csvSplitBy = ";";
        ArrayList<String[]> ergebnis = new ArrayList<String[]>();
        
        try {
 
		br = new BufferedReader(new FileReader(path));
		while ((line = br.readLine()) != null) {
		        // use semicolon as separator
			String[] vogel = new String[4];
                        
                        String[] tmp = line.split(csvSplitBy);
                        System.arraycopy(tmp, 0, vogel, 0, tmp.length);
                        
//                        vogel = line.split(csvSplitBy);
//                        //Fuelle nicht vorhandene Einträge mit leeren Strings
//                        for(int i = 0; i <= 3; ++i)
//                        {
//                            if(vogel[i].isEmpty())
//                            {
//                                vogel[i] = new String("");
//                            }
//                        }
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
 
        
        System.out.println(ergebnis);
        return ergebnis;   
    }
    
}
