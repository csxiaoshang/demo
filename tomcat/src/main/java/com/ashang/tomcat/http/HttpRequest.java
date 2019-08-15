package com.ashang.tomcat.http;

import lombok.Data;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * 封装request请求
 */
@Data
public class HttpRequest {

    //http 方法
    private String method;

    //http url
    private String url;

    public HttpRequest(InputStream inputStream) {

        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        try {
            length = inputStream.read(httpRequestBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (length > 0) {
            httpRequest = new String(httpRequestBytes, 0, length);
        }
        Optional.ofNullable(httpRequest).orElseThrow(() -> new RuntimeException("httpResquest is null"));
        //解析http报文 存储第一行
        String httpRequestHead = httpRequest.split("\n")[0];
        method = httpRequestHead.split("\\s")[0];
        url = httpRequestHead.split("\\s")[1];
    }
}
