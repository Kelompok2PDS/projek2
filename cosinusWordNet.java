package cosinus;

import java.util.*;
import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.ws4j.impl.WuPalmer;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;

public class Cosinus {

    public static void main(String[] args) {
        ILexicalDatabase db = new NictWordNet();
        WS4JConfiguration.getInstance().setMFS(true);
        Vector<String> tampungPisah = new Vector();
        Vector<Double> hitungPisah1 = new Vector();
        Vector<Double> hitungPisah2 = new Vector();
        Vector<Double> pisah1PlusPisah2 = new Vector();
        Scanner in = new Scanner(System.in);

        System.out.println("Masukkan kalimat 1: ");
        String t1 = in.nextLine();
        System.out.println("Masukkan kalimat 2: ");
        String t2 = in.nextLine();
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
        double jumlahHitung = 0;
        for (int i = 0; i < tampungPisah.size(); i++) {
            for (int j = 0; j < pisaht1.length; j++) {
                if (tampungPisah.get(i).equalsIgnoreCase(pisaht1[j])) {
                    hitung++;
                } else {
                    hitung = hitung;
                }
            }
            hitungPisah1.add(hitung);
            if (hitungPisah1.get(i) == 0) {
                for (int j = 0; j < pisaht1.length; j++) {
                    double hitung1 = new WuPalmer(db).calcRelatednessOfWords(tampungPisah.get(i), pisaht1[j]);
                    jumlahHitung = jumlahHitung + hitung1;
                }
                hitungPisah1.setElementAt(jumlahHitung, i);
                jumlahHitung = 0;
            }
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
            if (hitungPisah2.get(i) == 0) {
                for (int j = 0; j < pisaht2.length; j++) {
                    double hitung1 = new WuPalmer(db).calcRelatednessOfWords(tampungPisah.get(i), pisaht2[j]);
                    jumlahHitung = jumlahHitung + hitung1;
                }
                hitungPisah2.setElementAt(jumlahHitung, i);
                jumlahHitung = 0;
            }
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
// System.out.println(tampungPisah.get(i) +" = " +pisah1PlusPisah2.get(i));
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
