package inter;


import model.Request;
import model.Response;

/**
 * Created by zoujiajian on 2017-3-6.
 * http 远程方法接口
 */
public interface HttpService {

    /**
     * http 远程访问方法
     * @param request
     * @return
     */
    Response remote(Request request);

}
