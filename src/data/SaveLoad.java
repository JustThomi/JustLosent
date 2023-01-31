package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class SaveLoad {
    
    public SaveLoad(){

    }

    public static void readData() throws Exception{
        Scanner scanner = new Scanner(new File("src/data/scores.csv"));  
        scanner.useDelimiter(",");
        
        while (scanner.hasNext())
        {  
            System.out.print(scanner.next()); 
        }   
    
        scanner.close();
    }

    public static void writeData(String name, int score) throws Exception{
        File scores = new File("src/data/scores.csv");  
        FileWriter writer = new FileWriter(scores, true);
        BufferedWriter out = new BufferedWriter(writer);
        
        out.write(name + ", " + Integer.toString(score));
        out.newLine();
        out.close();
    }
}  

