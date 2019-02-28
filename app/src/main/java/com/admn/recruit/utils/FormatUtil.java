package com.admn.recruit.utils;

import com.admn.recruit.model.common.ResultEntity;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {

    public static ResultEntity resultToJson(String result) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            boolean success = jsonObject.getBoolean("success");
            String data = jsonObject.getString("data");
            String msg = jsonObject.getString("msg");
            return new ResultEntity(success, data, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultEntity(false, "json转换失败");
    }

    /**
     * 获取当前时间 yyMMddHHmmss,12位数字
     * @return string
     */
    public static String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");// HH:mm:ss
        // 获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
}
