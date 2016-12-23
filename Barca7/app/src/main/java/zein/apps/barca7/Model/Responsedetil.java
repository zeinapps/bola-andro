package zein.apps.barca7.Model;

import java.util.List;

/**
 * Created by VILLA-PC on 8/15/2016.
 */
public class Responsedetil {
    int status, code;
    List<String> message;
    Resultdetil data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Resultdetil getDataResultDetail() {
        return data;
    }

    public void setDataResultDetail(Resultdetil dataResult) {
        this.data = dataResult;
    }


    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
