
package tn.gamingroom.outils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Dah
 */
public class Outils {
    
    
    public static boolean containsBadWords(String body){
        File file = new File("src/tn/gamingroom/outils/badWords.txt");
        List<String> bodyList=Arrays.asList(body.toLowerCase().split(" "));
        
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(bodyList.contains(line)) { 
                    return true;
                }
            }
        } catch(FileNotFoundException e) { 
            e.printStackTrace();
        }
        
        return false;
    }
    
    
}
