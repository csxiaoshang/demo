package com.ashang.mongodb.util;

import com.ashang.mongodb.entity.response.Resp;

/**
 * @author 蒙延章 970915683@qq.com
 * @version 2.0.1 创建时间: 2018/2/23 17:46
 * <p>
 * 类说明：
 *     返回工具类
 */
public class RespUtil {

    public static <T> Resp<T> successResp(T data) {
        Resp<T> resp = new Resp<>();
        resp.setError_code(2000);
        resp.setError_message("success");
        resp.setData(data);
        return resp;
    }

    public static Resp successResp() {
        return successResp(null);
    }

    public static Resp errorResp(Integer errCode, String errMsg) {
        Resp resp = new Resp();
        resp.setError_code(errCode);
        resp.setError_message(errMsg);
        resp.setData(null);
        return resp;
    }

}
