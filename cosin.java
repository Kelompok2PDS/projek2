package cosinus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class Cosinus {

    public static void main(String[] args) {
        String t1 = "bapak suka naik sepeda motor";
        String t2 = "saya suka naik sepeda ontel";
        
        String [] pisaht1 = t1.split(" ");
        String [] pisaht2 = t2.split(" ");
        
        String gabung = t1.concat(" "+t2);
        String [] pisah = gabung.split(" ");
        
        HashSet terpisah = new HashSet(Arrays.asList(pisah));
       
        for (Iterator h = terpisah.iterator();h.hasNext();) {
            String n = (String) h.next();
            
            int hitung = 0;
            int jumlah = 0;
            //mencari nilai t1
            for (int f = 0; f < n.length(); f++) {
                if (n.equalsIgnoreCase(pisah[f])) {
                    jumlah=jumlah+1;
                }else{
                    jumlah=jumlah;
                }
            }
            System.out.print(n + " " + Integer.toString(jumlah) + " ");
            
            //mencari nilai t2
            for (int a = 0; a < n.length(); a++) {
                if (n.equalsIgnoreCase(pisaht2[a])) {
                    hitung=hitung+1;
                }else{
                    hitung=hitung;
                }
            }
            //System.out.print(n + " " + Integer.toString(hitung) + " ");
        }
        
    }
    
}
