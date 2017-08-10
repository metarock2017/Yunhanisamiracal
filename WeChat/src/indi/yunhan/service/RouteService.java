package indi.yunhan.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by asus on 2017/8/8.
 */
public class RouteService {

    private HttpServletRequest request = null;

    private String msgType;
    private String msgContent;
    private boolean isEncrypt;

    public RouteService(HttpServletRequest request) {
        this.request = request;
    }

    public boolean initRouteSerive(){
        return false;
    }
}
