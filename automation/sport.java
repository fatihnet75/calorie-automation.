package odevjava;
//kütüphaneler

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//sport clasın içine arary list ile dizi

public class sport {

    ArrayList<String> data = new ArrayList<>();

    public ArrayList getSportCalory(String sportId) throws FileNotFoundException, IOException {
        //dosya açtım okuttum ve dizilerin içine ayırarark attım
        File file = new File("C:\\Users\\Fatih\\Desktop\\JAVA ODEVİ\\odevjava\\sport.txt");
        FileReader fileReader = new FileReader(file);
        String line;
        BufferedReader br = new BufferedReader(fileReader);

        while ((line = br.readLine()) != null) {
            String[] arr = line.split("\t");
            data.clear();
            if(sportId.equals(arr[0])){
                data.add(arr[1]);
                data.add(arr[2]);
                break;
            }

        }
        return data;
        

    }

}
