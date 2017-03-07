package service;

import com.google.common.collect.Lists;
import exception.HttpRemoteException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.util.List;


/**
 * Created by zoujiajian on 2017-3-6.
 */
public class HttpRemoteService extends HttpRemote{

    /**
     * 设置请求超时3秒钟
     */
    private static final int DEFAULT_REQUEST_TIMEOUT =  3 * 1000;

    /**
     * 设置等待数据超时时间
     */
    private static final int DEFAULT_WAIT_TIMEOUT =  3 * 1000;

    /**
     * socket超时时间
     * @return
     */
    private static final int DEFAULT_SOCKET_TIME =  3 * 1000;

    private CloseableHttpClient httpClient;

    private RequestConfig requestConfig;

    private void httpClientInit(int requestTimeOut , int waitTimeOut, int socketTimeOut){

        requestConfig = RequestConfig.custom().
                setConnectTimeout(requestTimeOut ).
                setConnectionRequestTimeout(waitTimeOut ).
                setSocketTimeout(socketTimeOut)
                .build();

        List<BasicHeader> headers = Lists.newArrayList();
        HttpClientBuilder httpClientBuilder = HttpClients.custom();

        headers.add(new BasicHeader(HTTP.CONTENT_TYPE,CONTENT_TYPE));
        headers.add(new BasicHeader(HTTP.CONTENT_ENCODING,CONTENT_ENCODING));
        httpClientBuilder.setDefaultHeaders(headers);

        httpClient = httpClientBuilder.build();
    }

    public HttpRemoteService(int requestTimeOut , int waitTimeOut, int socketTimeOut){
        checkTime(requestTimeOut,waitTimeOut,socketTimeOut);
        httpClientInit(requestTimeOut,waitTimeOut,socketTimeOut);
    }

    public HttpRemoteService(){
        httpClientInit(DEFAULT_REQUEST_TIMEOUT,DEFAULT_WAIT_TIMEOUT,DEFAULT_SOCKET_TIME);
    }

    private static void checkTime(int requestTimeOut , int waitTimeOut, int socketTimeOut){
        if(requestTimeOut < 0 || waitTimeOut < 0 || socketTimeOut < 0){
            throw new HttpRemoteException("timeOut less than zero");
        }
    }

    @Override
    protected CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    @Override
    protected RequestConfig getRequestConfig() {
        return requestConfig;
    }
}
