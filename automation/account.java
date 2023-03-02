package odevjava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class account {
      // command .txt doosyasını okuyarak burdaki verileri dizilere parsladık ve monitoring için uygun işlemler yaptık
    public void commandtxt() throws FileNotFoundException, IOException {

        ArrayList<String> listPersonId = new ArrayList<>();
        ArrayList<String> listSporEatId = new ArrayList<>();
        ArrayList<String> listPortionDuration = new ArrayList<>();

        food food = new food();
        sport sp = new sport();
        people peop = new people();

        String text;
        int calculate;
        ArrayList<String> getData;

        File file = new File("C:\\Users\\Fatih\\Desktop\\JAVA ODEVİ\\odevjava\\command.txt");
        FileReader fileReader = new FileReader(file);
        String line;
        BufferedReader br = new BufferedReader(fileReader);

        while ((line = br.readLine()) != null) {

            String[] arr = line.split("\t");
            if (arr[0].startsWith("1")) {

                listPersonId.add(arr[0]);
                listSporEatId.add(arr[1]);
                listPortionDuration.add(arr[2]);
            //yemek calory sini ve spor calorysini alarak uygun işlemler ile monitoring dosyasına hazırladık
                if (arr[1].startsWith("1")) {
                    getData = food.getFoodCalory(arr[1]);
                    calculate = Integer.parseInt(getData.get(1)) * Integer.parseInt(arr[2]);
                    text = arr[0] + "\thas\ttaken\t" + calculate + "kcal\tfrom\t" + getData.get(0);
                    writeInMonitor(text);
                    getData.clear();
                } else {
                    getData = sp.getSportCalory(arr[1]);
                    calculate = Integer.parseInt(getData.get(1)) * (Integer.parseInt(arr[2]) / 60);
                    text = arr[0] + "\thas\tburned\t" + calculate + "kcal\tthanks\tto\t" + getData.get(0);
                    writeInMonitor(text);
                    getData.clear();
                }
                getData.clear();

                calorieCount(arr[0]);

            } else {
                int id;
                String[] arr2 = arr[0].split("[(]");
                if (arr2[0].equals("print")) {
                    if (arr2.length > 1) {
                        //print id yi bölerek içindeki id degerini aldık ve uygun fonksiyonu çağırdık 
                        id = Integer.parseInt(arr2[1].split("[)]")[0]);
                        ArrayList<String> firstData = peop.read(id);
                        double dailyCal = calorieCount(String.valueOf(id));
                        ArrayList<Integer> secData = getDailyFood(id);
                        double secResult = secData.get(0)-dailyCal+secData.get(1);
                        String lastData = firstData.get(0)+"\t"+firstData.get(1)+"\t"+dailyCal+
                                "kcal\t"+secData.get(0)+"kcal\t"+secData.get(1)+"kcal\t"+secResult+"kcal";
                        writeInMonitor(lastData);
                    }
                }else if(arr2[0].equals("printList")){
                    writeInMonitorAll();
                }

            }

        }

    }
    
    public void writeInMonitorAll() throws FileNotFoundException, IOException{
        //burada gelen id bilgisine göre calory durumu anlık gösterilir
        File file = new File("C:\\Users\\Fatih\\Desktop\\JAVA ODEVİ\\odevjava\\monitoring.txt");
        FileReader fileReader = new FileReader(file);
        String line;
        BufferedReader br = new BufferedReader(fileReader);
        
        ArrayList<String> datas = new ArrayList<>();
        
        while ((line = br.readLine()) != null) {
            String[] arr = line.split("\t");
            
            if(arr[0].startsWith("1")){
                
                if(!datas.contains(arr[0])){
                    datas.add(arr[0]);
                }
               
            }
        }
     
    }
    
    public ArrayList getDailyFood(int id) throws FileNotFoundException, IOException{
        //kişinin id sine göre gün boyunca yediği yiyeceklerin kalorisi ve yaktığı kalorinin bilgileri işlenir fpnk. gönderilir
        File file = new File("C:\\Users\\Fatih\\Desktop\\JAVA ODEVİ\\odevjava\\monitoring.txt");
        FileReader fileReader = new FileReader(file);
        String line;
        BufferedReader br = new BufferedReader(fileReader);
        
        ArrayList<Integer> data = new ArrayList<>();
        
        int resultTaken = 0;
        int resultSports = 0;
        
        while ((line = br.readLine()) != null) {
            
            
            
            String[] arr = line.split("\t");
            
            if(arr[0].equals(String.valueOf(id))){
                if(arr[2].equals("taken")){
                    resultTaken += Integer.parseInt(arr[3].split("k")[0]);
                }else{
                    resultSports += Integer.parseInt(arr[3].split("k")[0]);
                }
            }
        }
        data.add(resultTaken);
        data.add(resultSports);
        return data;
    }
    
    public void writeInMonitor(String data) throws IOException {
       //içine gelen  veriyi dosyaya yazdırır print id komutu için çalışır
        FileWriter myWriter = new FileWriter("C:\\Users\\Fatih\\Desktop\\JAVA ODEVİ\\odevjava\\monitoring.txt", true);
        myWriter.write(data + "\n***************\n");
        myWriter.close();

    }
   

    public double calorieCount(String id) throws FileNotFoundException, IOException {
        // içine gelen bilgilere göre kişi için uygun kalori hesabı yapar
        double newCalory = 0;
        File file = new File("C:\\Users\\Fatih\\Desktop\\JAVA ODEVİ\\odevjava\\people.txt");
        FileReader fileReader = new FileReader(file);
        String line;
        BufferedReader br = new BufferedReader(fileReader);

        while ((line = br.readLine()) != null) {
            String[] arr = line.split("\t");
            if (arr[0].equals(id)) {
                if (arr[2].equals("male")) {
                    double calory = 66 + (13.75 * Integer.parseInt(arr[3])) + (5 * Integer.parseInt(arr[4])) - (6.8 * (2022 - Integer.parseInt(arr[5])));
                    newCalory = Math.round(calory);
                } else {
                    double calory = 665 + (9.6 * Integer.parseInt(arr[3])) + (1.7 * Integer.parseInt(arr[4])) - (4.7 * (2022 - Integer.parseInt(arr[5])));
                    newCalory = Math.round(calory);
                }
            }

        }
        return newCalory;

    }

}
