package wordnet;

import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.ws4j.impl.WuPalmer;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;
import java.util.*;

public class Wordnet {

    public static void main(String[] args) {

        ILexicalDatabase db = new NictWordNet();

        WS4JConfiguration.getInstance().setMFS(true);
        Scanner input = new Scanner(System.in);

        System.out.println("Masukkan Kata 1 : ");
        String kata1 = input.next();
        System.out.println("Masukkan Kata 2 : ");
        String kata2 = input.next();

        double hitung = new WuPalmer(db).calcRelatednessOfWords(kata1, kata2);
        System.out.println("Kata 1 = " +kata1);
        System.out.println("Kata 2 = " +kata2);
        System.out.println("Hasil = " +hitung);
    }
}
