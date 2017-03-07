package model;

import enums.RequestMethod;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zoujiajian on 2017-3-6.
 * http request
 */
public class Request implements Serializable{

    /**
     * 请求url
     */
    private String url;

    /**
     * 请求参数
     */
    private Map<String,String> params;

    /**
     * 请求方法 get del put post
     */
    private RequestMethod requestMethod;

    public Request(String url, Map<String, String> params, RequestMethod requestMethod) {
        this.url = url;
        this.params = params;
        this.requestMethod = requestMethod;
    }

    public Request() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    @Override
    public String toString() {
        return "Request{" +
                "url='" + url + '\'' +
                ", params=" + params +
                ", requestMethod=" + requestMethod +
                '}';
    }
}
