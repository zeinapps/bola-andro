package zein.apps.mu7.Model;

import java.util.List;

/**
 * Created by VILLA-PC on 8/16/2016.
 */
public class Resultindex {
    List<Itemdetil> berita_pilihan, berita_terbaru, berita, internasional, politik, olahraga;

    public List<Itemdetil> getBerita_pilihan() {
        return berita_pilihan;
    }

    public void setBerita_pilihan(List<Itemdetil> berita_pilihan) {
        this.berita_pilihan = berita_pilihan;
    }

    public List<Itemdetil> getBerita_terbaru() {
        return berita_terbaru;
    }

    public void setBerita_terbaru(List<Itemdetil> berita_terbaru) {
        this.berita_terbaru = berita_terbaru;
    }

    public List<Itemdetil> getBerita() {
        return berita;
    }

    public void setBerita(List<Itemdetil> berita) {
        this.berita = berita;
    }

    public List<Itemdetil> getInternasional() {
        return internasional;
    }

    public void setInternasional(List<Itemdetil> internasional) {
        this.internasional = internasional;
    }

    public List<Itemdetil> getPolitik() {
        return politik;
    }

    public void setPolitik(List<Itemdetil> politik) {
        this.politik = politik;
    }

    public List<Itemdetil> getOlahraga() {
        return olahraga;
    }

    public void setOlahraga(List<Itemdetil> olahraga) {
        this.olahraga = olahraga;
    }
}
