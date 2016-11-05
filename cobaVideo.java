package cobavideo;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.sql.*;
import java.util.Vector;

public class CobaVideo {

    static Connection konek;

    public static void main(String[] args) throws IOException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            konek = DriverManager.getConnection("jdbc:mysql://localhost/pemodelan", "root", "");
        } catch (ClassNotFoundException ex) {
        } catch (SQLException es) {
        }

        Statement stat = konek.createStatement();

        Document doc = Jsoup.connect("http://localhost/mencariLink/agenda.html").get();

        Elements elemn = doc.select("div#isi-content");
        for (Element element : elemn.select("div.event-ancor1")) {
            String linkGambar = element.select("div.event-ancor1 a").attr("href");

            Document doc2 = Jsoup.connect(linkGambar).get();
            Elements elemn2 = doc2.select("div#level-right");
            String judul = element.select("div.event-ancor1 h3").text();
            for (Element elemn3 : doc2.select("div#bawah-baca")) {
                String konten = elemn3.select("div#bawah-baca p").text();
                String judulGanti = judul.replaceAll("'", "''");
                String kontenGanti = konten.replaceAll("'", "''");
                String linkGambarGanti = linkGambar.replaceAll("'", "''");

                String masukan = "INSERT into simpanlink (judul_link, link, konten_link, tanggal, waktu) VALUES('" + judulGanti + "','" + linkGambarGanti + "','" + kontenGanti + "',NOW(),NOW());";

                stat.executeUpdate(masukan);


            }

        }

        Document docu = Jsoup.connect("http://localhost/mencariLink/berita.html").timeout(10000).get();
        Elements elem = docu.select("div#isi-content");
        for (Element element : elem.select("div.event-ancor1")) {
            String linkGambar = element.select("div.event-ancor1 a").attr("href");

            Document docu2 = Jsoup.connect(linkGambar).get();
            Elements elem2 = docu2.select("div#level-right");
            String judul = element.select("div.event-ancor1 h3").text();
            for (Element elem3 : docu2.select("div#bawah-baca")) {
                String konten = elem3.select("div#bawah-baca p").text();
                String judulGanti = judul.replaceAll("'", "''");
                String kontenGanti = konten.replaceAll("'", "''");
                String linkGambarGanti = linkGambar.replaceAll("'", "''");
                String masukan = "INSERT into simpanlink (judul_link, link, konten_link, tanggal, waktu) VALUES('" + judulGanti + "','" + linkGambarGanti + "','" + kontenGanti + "',NOW(),NOW());";

                stat.executeUpdate(masukan);
            }

        }

        Document doc2 = Jsoup.connect("http://localhost/mencariLink/pengumuman.htm").get();

        Elements elemn2 = doc2.select("div#isi-content");
        for (Element element : elemn2.select("div.event-ancor1")) {
            String linkGambar = element.select("div.event-ancor1 a").attr("href");

            Document docs2 = Jsoup.connect(linkGambar).get();
            Elements elemen2 = docs2.select("div#level-right");
            String judul = element.select("div.event-ancor1 h3").text();
                String author = element.select("div.author").text();
                String judulGanti = judul.replaceAll("'", "''");
                String linkGambarGanti = linkGambar.replaceAll("'", "''");
                String authorGanti = author.replaceAll("'", "''");

                String masukan = "INSERT into simpanlink (judul_link, link, konten_link, tanggal, waktu) VALUES('" + judulGanti + "','" + linkGambarGanti + "','" + authorGanti + "',NOW(),NOW());";

                stat.executeUpdate(masukan);
        }
        
        }
        
        }
