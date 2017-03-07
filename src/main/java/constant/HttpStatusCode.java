package constant;

import java.io.Serializable;

/**
 * Created by zoujiajian on 2017-3-6.
 * http 常见状态码
 */
public class HttpStatusCode implements Serializable{

    private static final int SUUCESS = 200;

    private static final int MOVED_PERMANENTY = 301;

    private static final int FOUND = 302;

    private static final int NOT_MODIFIED = 307;

    private static final int BAD_REQUEST = 400;

    private static final int UNAUTHORIZED = 401;

    private static final int FORBIDDEN = 403;

    private static final int NOT_FOUND = 404;

    private static final int ERROR = 500;

    private static final int NOT_IMPLEMENTED = 501;

    private HttpStatusCode(){}{

    }
}
