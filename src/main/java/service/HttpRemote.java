package service;

import com.google.common.collect.Lists;
import enums.RequestMethod;
import exception.HttpRemoteException;
import inter.HttpService;
import model.Request;
import model.Response;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by zoujiajian on 2017-3-6.
 * http 接口抽象类 提供默认实现和辅助方法
 * 继承该类可以定制自己的httpClient配置和具体的实现方法
 */
public abstract class HttpRemote implements HttpService{

    /**
     * 默认编码方式
     */
     static final String CONTENT_ENCODING = "UTF-8";

    /**
     * 默认数据格式
     */
     static final String CONTENT_TYPE = "application/json";

     @Override
     public Response remote(Request request){
         if(request == null){
             throw new HttpRemoteException("request is null");
         }
         RequestMethod requestMethod = request.getRequestMethod();
         if(requestMethod.equals(RequestMethod.DELETE)){
             return remoteDel(request);
         }
         if(requestMethod.equals(RequestMethod.GET)){
             return remoteGet(request);
         }
         if(requestMethod.equals(RequestMethod.POST)){
             return remotePost(request);
         }
         if(requestMethod.equals(RequestMethod.PUT)){
             return remotePut(request);
         }
         return null;
     }


    /**
     * 将参数添加至url末尾
     * @param url
     * @param params
     * @return
     */
    protected String urlWithArgument(String url, Map<String,String> params){
        if(StringUtils.isEmpty(url) || params == null){
            throw new HttpRemoteException("url or params is empty");
        }
        try{
            URIBuilder urlBuilder = new URIBuilder(url);
            for (String key : params.keySet()) {
                urlBuilder.addParameter(key, params.get(key));
            }
            return urlBuilder.build().toString();
        }catch (Exception e){
           e.printStackTrace();
        }
        return null;
    }

    /**
     * 构建post参数
     * @param params key value 键值对
     * @return
     */
    protected HttpEntity buildEntity(Map<String,String> params){
        if(params == null){
            throw new HttpRemoteException("params is null");
        }
        List<NameValuePair> parameters = Lists.newArrayList();
        for (String key : params.keySet()) {
            parameters.add(new BasicNameValuePair(key, params.get(key)));
        }
        try {
            return new UrlEncodedFormEntity(parameters, CONTENT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get 方法
     * @param request
     * @return
     */
    private Response remoteGet(Request request){
        checkRequest(request);
        String url;
        if(request.getParams() == null){
            url = request.getUrl();
        }else{
            url = urlWithArgument(request.getUrl(), request.getParams());
        }
        return this.get(url);
    }

    /**
     * post方法
     * @param request
     * @return
     */
    protected Response remotePost(Request request){
        checkRequest(request);
        return post(request);

    }

    /**
     * post的默认实现
     * @param request
     * @return
     */
    private Response post(Request request){
        HttpPost httpPost = new HttpPost(request.getUrl());
        httpPost.setConfig(getRequestConfig());
        CloseableHttpResponse httpResponse;
        Response response = new Response();
        try{
            long startTime = System.currentTimeMillis();
            httpPost.setEntity(buildEntity(request.getParams()));
            httpResponse = getHttpClient().execute(httpPost);

            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
            response.setBody(httpResponse.getEntity().toString());
            response.setTime(System.currentTimeMillis() - startTime);
            return response;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get的默认实现
     * @param url
     * @return
     */
    private Response get(String url)  {
        HttpGet httpGet = new HttpGet(url);
        Response response = new Response();
        CloseableHttpResponse httpResponse = null;
        httpGet.setConfig(getRequestConfig());
        long startTime = System.currentTimeMillis();
        try {
            httpResponse = getHttpClient().execute(httpGet);

            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
            response.setTime(System.currentTimeMillis() - startTime);
            response.setBody(httpResponse.getEntity().toString());
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(httpResponse != null){
                try{
                    httpResponse.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    private void checkRequest(Request request) {
        if (request == null) {
            throw new HttpRemoteException("request is null");
        }
        if(StringUtils.isEmpty(request.getUrl())){
            throw new HttpRemoteException("url is empty");
        }
    }

    /**
     * del方法
     * @param request
     * @return
     */
    protected  Response remoteDel(Request request){ return null;}

    /**
     * put方法
     * @param request
     * @return
     */
    protected  Response remotePut(Request request){return null;}

    /**
     * 将实例化过程交由子类
     * @return CloseableHttpClient
     */
    protected abstract CloseableHttpClient getHttpClient();

    /**
     * 将request配置交由子类
     * @return RequestConfig
     */
    protected abstract RequestConfig getRequestConfig();

}
