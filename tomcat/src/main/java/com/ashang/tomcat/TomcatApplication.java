package com.ashang.tomcat;

import com.ashang.tomcat.config.BaseConfig;
import com.ashang.tomcat.http.HttpRequest;
import com.ashang.tomcat.http.HttpResponse;
import com.ashang.tomcat.servlet.Servlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

@Slf4j
public class TomcatApplication {

    private int port = 8080;

    public TomcatApplication(int port) {

        this.port = port;
    }

    public static void main(String[] args) {
        TomcatApplication tomcat = new TomcatApplication(BaseConfig.port);
        tomcat.start();
    }

    public void start() {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            log.info("tomcat start");
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                HttpRequest httpRequest = new HttpRequest(inputStream);
                HttpResponse httpResponse = new HttpResponse(outputStream);
                dispatch(httpRequest,httpResponse);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void dispatch(HttpRequest httpRequest,HttpResponse httpResponse){
            if (!"/favicon.ico".equalsIgnoreCase(httpRequest.getUrl())){
                String clazz = BaseConfig.ulrServletmap.get(httpRequest.getUrl());
                Optional.ofNullable(clazz).orElseThrow(()-> new RuntimeException("url 无对应servlet"));
                try {
                    Class<Servlet> servletClass = (Class<Servlet>) Class.forName(clazz);
                    Servlet servlet = servletClass.newInstance();
                    servlet.service(httpRequest,httpResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
}
