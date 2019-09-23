package com.ashang.tomcat.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 配置类
 */
public class BaseConfig {
    //监听端口
    public final static int port = 8081;

    //url servlet映射容器
    public static ConcurrentMap<String,String> ulrServletmap = new ConcurrentHashMap<>();
    static {
        ulrServletmap.put("/","com.ashang.tomcat.servlet.IndexServlet");
    }
}
