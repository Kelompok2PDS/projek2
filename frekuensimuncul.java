package frekuensimuncul;

import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class Frekuensimuncul {

    static Connection konek;

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
        for (int i = 1; i < simpanKonten.length; i++) {
            int tambah = i - 1;
            String pilih = "SELECT konten_link from uinsby" + i;
            ResultSet ambilKata = stat.executeQuery(pilih);

            while (ambilKata.next()) {
                String konten = ambilKata.getString("konten_link");
                simpanKonten[tambah] = konten;

            }
        }

        for (int i = 0; i < simpanKonten.length; i++) {
            String[] pisah = simpanKonten[i].split(" ");

            HashSet terpisah = new HashSet(Arrays.asList(pisah));

            for (Iterator k = terpisah.iterator(); k.hasNext();) {
                String z = (String) k.next();
                int hitung = 0;

                for (int j = 0; j < pisah.length; j++) {
                    if (z.equalsIgnoreCase(pisah[j])) {
                        hitung++;
                    }
                }

                if (!z.equalsIgnoreCase("dan") || !z.equalsIgnoreCase("atau") || !z.equalsIgnoreCase("lalu") || !z.equalsIgnoreCase("tapi")) {
                    String kontenGanti = z.replaceAll("'", "''");
                    String masukDB = "INSERT INTO menghitungkata (karakter, jumlah) VALUES ('" + kontenGanti + "'," + hitung + ");";
                    stat.executeUpdate(masukDB);
                    System.out.println(masukDB);
                }
            }
        }

    }
}
