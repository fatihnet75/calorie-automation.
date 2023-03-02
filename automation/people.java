package odevjava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;
//people clasın içine arary list ile dizi actım
public class people {
        ArrayList<String> data = new ArrayList<>();

    /**
     dosya açtım okuttum ve dizilerin içine ayırarark attım
     */
    public int sizene=0;
    public ArrayList read(int id) throws FileNotFoundException, IOException {
        File file = new File("C:\\Users\\Fatih\\Desktop\\JAVA ODEVİ\\odevjava\\people.txt");
        FileReader fileReader = new FileReader(file);
        String line;
        BufferedReader br = new BufferedReader(fileReader);

       
            
        while ((line = br.readLine()) != null) {
            String[] arr = line.split("\t");
            
            data.add(arr[1]);
            data.add(String.valueOf(2022-Integer.parseInt(arr[5])));
  
        }
            return data;
    }
        
        
        
      
    

   
}
