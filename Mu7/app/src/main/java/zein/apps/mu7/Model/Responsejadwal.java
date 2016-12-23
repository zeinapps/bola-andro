package zein.apps.mu7.Model;

import java.util.List;

/**
 * Created by VILLA-PC on 8/15/2016.
 */
public class Responsejadwal {
    int status, code;
    List<String> message;
    List<Itemjadwal> data;

    public int getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public List<Itemjadwal> getData() {
        return data;
    }

}
