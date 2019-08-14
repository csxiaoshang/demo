package com.ashang.tomcat.servlet;

import com.ashang.tomcat.http.HttpRequest;
import com.ashang.tomcat.http.HttpResponse;

public abstract class Servlet {

public abstract void doGet(HttpRequest httpRequest, HttpResponse httpResponse);

public abstract void doPost(HttpRequest httpRequest,HttpResponse httpResponse);

public void service(HttpRequest httpRequest, HttpResponse httpResponse){
    if ("POST".equalsIgnoreCase(httpRequest.getMethod())){
        doPost(httpRequest,httpResponse);
    }else if ("GET".equalsIgnoreCase(httpRequest.getMethod())){
        doGet(httpRequest,httpResponse);
    }
}
}
