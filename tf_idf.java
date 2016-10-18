package tf_idf;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.sql.*;

public class Tf_idf {

    static Connection konek;

    public double tf(Vector<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word)) {
                result++;
            }
        }
        return result / doc.size();
    }

    public double idf(List<Vector<String>> docs, String term) {
        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log(docs.size() / n);
    }

    public double tfIdf(Vector<String> doc, List<Vector<String>> docs, String term) {
        return tf(doc, term) * idf(docs, term);

    }

    public static void main(String[] args) throws SQLException {

        try {
            konek = null;
            Class.forName("com.mysql.jdbc.Driver");
            konek = DriverManager.getConnection("jdbc:mysql://localhost/pemodelan", "root", "");
        } catch (ClassNotFoundException ex) {
        } catch (SQLException es) {
        }

        Statement stat = konek.createStatement();
        String[] simpanKonten = new String[24];
        Vector<String> hitungKata = new Vector();
        Vector<String> simpanKarakter = new Vector();
        Vector<Vector<String>> simpanDokumen = new Vector();

        for (int i = 0; i < 24; i++) {
            int tambah = i + 1;
            String pilih = "SELECT konten_link from uinsby" + tambah;
            ResultSet ambilKata = stat.executeQuery(pilih);
            int urutan = 0;

            while (ambilKata.next()) {
                String konten = ambilKata.getString("konten_link");
                simpanKonten[i] = konten;
                String[] pisah = simpanKonten[i].split(" ");
                hitungKata.add(pisah[urutan]);

                urutan++;
            }
            simpanDokumen.add(hitungKata);

            String ambilKarakter = "SELECT karakter from menghitungkata";
            ResultSet ambilKarakter2 = stat.executeQuery(ambilKarakter);

            while (ambilKarakter2.next()) {
                String konten = ambilKarakter2.getString("karakter");
                simpanKarakter.add(konten);
            }

            List<Vector<String>> documents = Arrays.asList(hitungKata);

            for (int j = 0; j < simpanKarakter.size(); j++) {
                Tf_idf calculator = new Tf_idf();
                double tf = calculator.tf(hitungKata, simpanKarakter.get(j));
                double idf = calculator.idf(documents, simpanKarakter.get(j));
                double tfidf = calculator.tfIdf(hitungKata, documents, simpanKarakter.get(j));
                String ganti = simpanKarakter.get(j).replaceAll("'", "''");
                String masukan = "INSERT INTO tfidf (karakter, tf, idf, tfidf) VALUES ('" + ganti + "','" + tf + "','" + idf + "','" + tfidf + "');";
                stat.executeUpdate(masukan);
                System.out.println(masukan);
            }

            hitungKata.removeAllElements();
        }


    }
}
