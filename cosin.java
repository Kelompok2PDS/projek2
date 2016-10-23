package cosinus;

import java.util.*;

public class Cosinus {

    public static void main(String[] args) {

        Vector<String> tampungPisah = new Vector();
        Vector<Double> hitungPisah1 = new Vector();
        Vector<Double> hitungPisah2 = new Vector();
        Vector<Double> penyebutPisah1 = new Vector();
        Vector<Double> penyebutPisah2 = new Vector();
        Vector<Double> pisah1PlusPisah2 = new Vector();

        String t1 = "bapak suka naik sepeda motor";
        String t2 = "saya suka naik sepeda ontel";

        String[] pisaht1 = t1.split(" ");
        String[] pisaht2 = t2.split(" ");

        String gabung = t1.concat(" " + t2);
        String[] pisah = gabung.split(" ");

        for (int i = 0; i < pisah.length; i++) {
            tampungPisah.add(pisah[i]);
            for (int j = 0; j < i; j++) {
                if (i != j && pisah[i].equals(pisah[j])) {
                    tampungPisah.remove(pisah[i]);
                    break;
                }
            }
        }
        
        System.out.println("Kata Gabungan : ");
        for (int i = 0; i < tampungPisah.size(); i++) {
            System.out.println(tampungPisah.get(i));
        }
        System.out.println("================================");

        //hitung t1
        System.out.println("Term 1 :");
        double hitung = 0;
        for (int i = 0; i < tampungPisah.size(); i++) {
            for (int j = 0; j < pisaht1.length; j++) {
                if (tampungPisah.get(i).equalsIgnoreCase(pisaht1[j])) {
                    hitung++;
                } else {
                    hitung = hitung;
                }
            }
            hitungPisah1.add(hitung);
            hitung = 0;
             System.out.println(tampungPisah.get(i) + " = " + hitungPisah1.get(i));
        }
        System.out.println("================================");

        // System.out.println("");

        //hitung t2
        System.out.println("Term 2 :");
        double hitung2 = 0;
        for (int i = 0; i < tampungPisah.size(); i++) {
            for (int j = 0; j < pisaht2.length; j++) {
                if (tampungPisah.get(i).equalsIgnoreCase(pisaht2[j])) {
                    hitung2++;
                } else {
                    hitung2 = hitung2;
                }
            }
            hitungPisah2.add(hitung2);
            hitung2 = 0;
               System.out.println(tampungPisah.get(i) + " = " + hitungPisah2.get(i));
        }
        System.out.println("================================");

        //menghitung pembilang
        System.out.println("Pembilang :");
        double jumlah = 0;
        for (int i = 0; i < tampungPisah.size(); i++) {
            jumlah = hitungPisah1.get(i) * hitungPisah2.get(i);
            pisah1PlusPisah2.add(jumlah);
            //    System.out.println(tampungPisah.get(i) +" = " +pisah1PlusPisah2.get(i));
        }

        double pembilang = 0;
        for (int i = 0; i < tampungPisah.size(); i++) {
            pembilang = pembilang + pisah1PlusPisah2.get(i);
        }
        System.out.println(pembilang);

        //menghitung penyebut
        System.out.println("Penyebut :");
        double kuadrat = 0;
        double jumlahKuadrat1 = 0;
        double jumlahKuadrat2 = 0;
        double akarPenyebut1 = 0;
        double akarPenyebut2 = 0;
        double penyebut = 0;
        double cosinus = 0;
        for (int i = 0; i < hitungPisah1.size(); i++) {
            kuadrat = hitungPisah1.get(i) * hitungPisah1.get(i);
            jumlahKuadrat1 = jumlahKuadrat1 + kuadrat;
        }
        akarPenyebut1 = Math.sqrt(jumlahKuadrat1);
        //System.out.println(akarPenyebut1);

        for (int i = 0; i < hitungPisah2.size(); i++) {
            kuadrat = hitungPisah2.get(i) * hitungPisah2.get(i);
            jumlahKuadrat2 = jumlahKuadrat2 + kuadrat;
        }
        akarPenyebut2 = Math.sqrt(jumlahKuadrat2);
        //System.out.println(akarPenyebut2);
        
        penyebut = akarPenyebut1 * akarPenyebut2;
        System.out.println(penyebut);
        
        //Cosinus Akhir
        cosinus = pembilang / penyebut;
        System.out.println("Hasil :");
        System.out.println(cosinus);

    }
}
