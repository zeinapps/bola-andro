package zein.apps.madrid7.Model;

import java.util.List;

/**
 * Created by VILLA-PC on 8/16/2016.
 */
public class Responseindex {
    int status, code;
    List<String> message;
    Resultindex data;

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

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public Resultindex getData() {
        return data;
    }

    public void setData(Resultindex data) {
        this.data = data;
    }
}
