package zein.apps.mu7.Model;

import java.util.List;

/**
 * Created by VILLA-PC on 8/15/2016.
 */
public class Itemdetil {
    String id, url, title, konten, kategori, penulis, sumber,waktu, time, img, img_tumb, is_pilihan, views;
    List<Itemlist> terkait;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
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

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg_tumb() {
        return img_tumb;
    }

    public void setImg_tumb(String img_thumb) {
        this.img_tumb = img_thumb;
    }

    public String getIs_pilihan() {
        return is_pilihan;
    }

    public void setIs_pilihan(String isi_pilihan) {
        this.is_pilihan = isi_pilihan;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public List<Itemlist> getTerkait() {
        return terkait;
    }

    public void setTerkait(List<Itemlist> terkait) {
        this.terkait = terkait;
    }
}