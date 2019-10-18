package com.ashang.tomcat;

import com.ashang.tomcat.config.BaseConfig;
import com.ashang.tomcat.http.HttpRequest;
import com.ashang.tomcat.http.HttpResponse;
import com.ashang.tomcat.servlet.Servlet;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Optional;


@Slf4j
public class TomcatApplication {

    private int port = 8080;

    public TomcatApplication(int port) {

        this.port = port;
    }

    public static void main(String[] args) throws IOException {
        TomcatApplication tomcat = new TomcatApplication(BaseConfig.port);
        tomcat.start();
    }

    public void start() throws IOException {

        ServerSocket serverSocket = null;
        //设置监听端口
        serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            long time = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
            System.out.println("currentTime is "+simpleDateFormat.format(time));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    InputStream inputStream = null;
                    try {
                        inputStream = socket.getInputStream();
                        OutputStream outputStream = socket.getOutputStream();
                        //封装到HttpRequest对象中
                        HttpRequest httpRequest = new HttpRequest(inputStream);
                        //封装到HttpResponse对象中
                        HttpResponse httpResponse = new HttpResponse(outputStream);
                        //根据url 分发Servlet
                        dispatch(httpRequest, httpResponse);
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();

        }
    }

    public void dispatch(HttpRequest httpRequest, HttpResponse httpResponse) {

        //不接收分发/favicon.ico 请求
        if (!"/favicon.ico".equalsIgnoreCase(httpRequest.getUrl())) {
            String clazz = BaseConfig.ulrServletmap.get(httpRequest.getUrl());
            Optional.ofNullable(clazz).orElseThrow(() -> new RuntimeException("url 无对应servlet"));
            try {
                //反射调用servlet service方法
                Class<Servlet> servletClass = (Class<Servlet>) Class.forName(clazz);
                Servlet servlet = servletClass.newInstance();
                servlet.service(httpRequest, httpResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
