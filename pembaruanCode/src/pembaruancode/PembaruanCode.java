package pembaruancode;

import java.sql.*;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PembaruanCode {

    static Connection konek;

    public static void main(String[] args) throws IOException, SQLException {

        try {
            konek = null;
            Class.forName("com.mysql.jdbc.Driver");
            konek = DriverManager.getConnection("jdbc:mysql://localhost/pemodelan", "root", "");
        } catch (ClassNotFoundException ex) {
        } catch (SQLException es) {
        }

        Statement stat = konek.createStatement();
        Document doc = Jsoup.connect("http://www.uinsby.ac.id").get();

        Elements links = doc.select("a[href]");

        for (Element link : links) {
            String judul = link.text();
            String judulGanti = judul.replaceAll("'", "''");
            String alamat = link.attr("href");
            String alamatGanti = alamat.replaceAll("'", "''");
            String konten = doc.body().text();
            String kontenGanti = konten.replaceAll("'", "''");

            String masukan = "INSERT INTO uinsby1 (judul_link, link, konten_link, tanggal, waktu) VALUES ('" + judulGanti + "','" + alamatGanti + "','" + kontenGanti + "',NOW(), NOW());";
            stat.executeUpdate(masukan);
            System.out.println(masukan);
        }

        String hapus = "DELETE from uinsby1 where link = '#' || link = ''";
        String hapusLinkUtama = "DELETE from uinsby1 where id = 1";
        stat.executeUpdate(hapus);
        stat.executeUpdate(hapusLinkUtama);

        String pilih = "SELECT link from uinsby1";
        ResultSet ambil = stat.executeQuery(pilih);

        int urutanLink = 0;
        String[] ambilLink = new String[180];

        while (ambil.next()) {
            String linknya = ambil.getString("link");
            ambilLink[urutanLink] = linknya;
            urutanLink++;
        }

        for (int i = 2; i < ambilLink.length; i++) {
            String tabel = "CREATE TABLE uinsby" + i + " (id int NOT NULL PRIMARY KEY AUTO_INCREMENT,judul_link text,link text,konten_link text,tanggal date NOT NULL,waktu time NOT NULL);";
            stat.executeUpdate(tabel);

            Document doc2 = Jsoup.connect(ambilLink[i-2]).get();
            Elements links2 = doc2.select("a[href]");

            for (Element link2 : links2) {
                String judul2 = link2.text();
                String judulGanti2 = judul2.replaceAll("'", "''");
                String alamat2 = link2.attr("href");
                String alamatGanti2 = alamat2.replaceAll("'", "''");
                String konten2 = doc2.body().text();
                String kontenGanti2 = konten2.replaceAll("'", "''");

                String masukan2 = "INSERT INTO uinsby" + i + " (judul_link, link, konten_link, tanggal, waktu) VALUES ('" + judulGanti2 + "','" + alamatGanti2 + "','" + kontenGanti2 + "',NOW(), NOW());";
                stat.executeUpdate(masukan2);
                System.out.println(masukan2);
            }
        }
        stat.close();
        konek.close();

    }
}