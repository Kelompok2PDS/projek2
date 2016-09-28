package pemodelanbaca;

import java.sql.*;
import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PemodelanBaca {

    public Connection konek;

    public void koneksi() throws SQLException {
        try {
            konek = null;
            Class.forName("com.mysql.jdbc.Driver");
            konek = DriverManager.getConnection("jdbc:mysql://localhost/pemodelan", "root", "");
        } catch (ClassNotFoundException ex) {
        } catch (SQLException es) {
        }

    }

    public void mencariLink() throws IOException, SQLException {
        koneksi();
        Statement stat = konek.createStatement();
        Document doc = Jsoup.connect("http://www.uinsby.ac.id").get();
        String title = doc.title();
        System.out.println("title : " + title);
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            String sql = "INSERT INTO uinsby (judul_link, nama_link, konten_link, tanggal, waktu) VALUES ('" + link.text() + "','" + link.attr("href") + "','',NOW(), NOW());";
            stat.executeUpdate(sql);
            System.out.println(sql);
            String sqlDelete = "DELETE from uinsby where nama_link = '#'";
            String sqlDelete2 = "DELETE from uinsby where nama_link = ''";
            stat.executeUpdate(sqlDelete);
            stat.executeUpdate(sqlDelete2);
            Document docN = Jsoup.connect(link.attr("href")).get();
            Elements linksN = docN.select("a[href]");
            for(Element linkN : linksN){
                String sqlUpdate = "UPDATE uinsby set konten_link = '"+linkN.attr("href") +"' where nama_link = '"+link.attr("href")+"';"; 
                stat.executeUpdate(sqlUpdate);
            }
        }
        stat.close();
        konek.close();
    }
    public static void main(String[] args) throws IOException, SQLException {
        PemodelanBaca jalan = new PemodelanBaca();
        jalan.mencariLink();
    }
}
