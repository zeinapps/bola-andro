package zein.apps.chelsea7.Model;

import java.util.List;

/**
 * Created by VILLA-PC on 8/15/2016.
 */
public class Resultdetil {
    Itemdetil detil;
    List<Itemlist> terkait;

    public Itemdetil getDetil() {
        return detil;
    }

    public void setDetil(Itemdetil detil) {
        this.detil = detil;
    }

    public List<Itemlist> getTerkait() {
        return terkait;
    }

    public void setTerkait(List<Itemlist> terkait) {
        this.terkait = terkait;
    }
}
