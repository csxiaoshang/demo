package com.ashang.tomcat.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BaseConfig {
    public final static int port = 8081;

    public static ConcurrentMap<String,String> ulrServletmap = new ConcurrentHashMap<>();
    static {
        ulrServletmap.put("/","com.ashang.tomcat.servlet.IndexServlet");
    }
}
