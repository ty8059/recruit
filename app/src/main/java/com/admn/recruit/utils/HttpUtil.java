package com.admn.recruit.utils;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wangyi on 2018/3/11.
 */

public class HttpUtil {


    public static HttpURLConnection httpInit(String address) {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            //往网页写入POST数据，和网页POST方法类似，参数间用‘&’连接
            connection .setDoInput(true);
            connection .setDoOutput(true);
            connection .setRequestMethod("POST");
            connection.setRequestProperty("Charset", "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}

