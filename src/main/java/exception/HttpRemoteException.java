package exception;

import java.io.Serializable;

/**
 * Created by zoujiajian on 2017-3-6.
 */
public class HttpRemoteException extends RuntimeException implements Serializable{

    private String message;

    private int code;

    public HttpRemoteException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public HttpRemoteException(String message){
        super(message);
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "HttpRemoteException{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
