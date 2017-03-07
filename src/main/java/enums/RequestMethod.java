package enums;

import java.io.Serializable;

/**
 * Created by zoujiajian on 2017-3-6.
 */
public enum RequestMethod implements Serializable{

    GET("GET"),
    POST("POST"),
    DELETE("DELETE"),
    PUT("PUT");

    private RequestMethod(String method){
        this.method = method;
    }

    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "RequestMethod{" +
                "method='" + method + '\'' +
                '}';
    }
}
