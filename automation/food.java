package odevjava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * food clasın içine arary list ile dizi
 */
public class food {
    
    ArrayList<String> data = new ArrayList<>();

    public ArrayList getFoodCalory(String foodId) throws FileNotFoundException, IOException {
        //dosya açtım okuttum ve dizilerin içine ayırarark attım
        File file = new File("C:\\Users\\Fatih\\Desktop\\JAVA ODEVİ\\odevjava\\food.txt");
        FileReader fileReader = new FileReader(file);
        String line;
        BufferedReader br = new BufferedReader(fileReader);

        while ((line = br.readLine()) != null) {
            String[] arr = line.split("\t");
            data.clear();
            if(foodId.equals(arr[0])){
                data.add(arr[1]);
                data.add(arr[2]);
                break;
            }

        }
        return data;
        

    }

}
