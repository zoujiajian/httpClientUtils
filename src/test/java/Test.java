import com.alibaba.fastjson.JSON;
import enums.RequestMethod;
import inter.HttpService;
import model.Request;
import service.HttpRemoteService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zoujiajian on 2017-3-6.
 */
public class Test {

    @org.junit.Test
    public void test(){
        String url = "http://blog.csdn.net/johnnywww/article/details/8161396";
        HttpService httpService = new HttpRemoteService();
        Request request = new Request();
        Map<String,String> params = new HashMap<>();
        params.put("name","xxxx");
        params.put("age","20");

        request.setUrl(url);
        request.setRequestMethod(RequestMethod.GET);
        request.setParams(params);
        System.out.println(JSON.toJSONString(httpService.remote(request)));
    }
}
