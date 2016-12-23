package zein.apps.madrid7.Model;

/**
 * Created by VILLA-PC on 8/15/2016.
 */

public class Itemlist {
    String id;
    String title;
    String time;
    String img_tumb;
    String penulis, sumber, url, kategori, waktu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg_tumb() {
        return img_tumb;
    }

    public void setImg_tumb(String img_tumb) {
        this.img_tumb = img_tumb;
    }
    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

    public String getUrl() {
        url = url.replace("\\/","/");
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }
}
