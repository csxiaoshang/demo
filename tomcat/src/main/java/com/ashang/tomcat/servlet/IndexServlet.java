package com.ashang.tomcat.servlet;

import com.ashang.tomcat.http.HttpRequest;
import com.ashang.tomcat.http.HttpResponse;

public class IndexServlet extends Servlet {

    public void doGet(HttpRequest httpRequest, HttpResponse httpResponse){
        httpResponse.write("welcome to tomcat");
    }

public void doPost(HttpRequest httpRequest,HttpResponse httpResponse){
        httpResponse.write("welcome to tomcat post");
}
}
