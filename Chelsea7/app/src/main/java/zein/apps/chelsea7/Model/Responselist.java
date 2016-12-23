package zein.apps.chelsea7.Model;

import java.util.List;

/**
 * Created by VILLA-PC on 8/15/2016.
 */
public class Responselist {
    int status, code;
    List<String> message;
    Resultlist data;

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

    public Resultlist getDataResult() {
        return data;
    }

    public void setDataResult(Resultlist dataResult) {
        this.data = dataResult;
    }


    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
