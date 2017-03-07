package model;

import java.io.Serializable;

/**
 * Created by zoujiajian on 2017-3-6.
 * http response
 */
public class Response implements Serializable{

    /**
     * 返回状态码
     */
    private int statusCode;

    /**
     * 请求耗时 startTime - endTime
     */
    private long time;

    /**
     * 返回内容
     */
    private String body;

    public Response(int statusCode, long time, String body) {
        this.statusCode = statusCode;
        this.time = time;
        this.body = body;
    }

    public Response() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Response{" +
                "statusCode=" + statusCode +
                ", time=" + time +
                ", body='" + body + '\'' +
                '}';
    }
}
