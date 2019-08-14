package com.ashang.spring.controller;

import com.ashang.spring.annotation.MyAutowired;
import com.ashang.spring.annotation.MyController;
import com.ashang.spring.annotation.MyRequestMapping;
import com.ashang.spring.service.IndexService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ashang  970090853@qq.com
 * @Date 19-9-23 下午2:40
 * <p>
 * 类说明：
 */
@MyController
@MyRequestMapping("/")
public class IndexWeb {

    @MyAutowired
    private IndexService indexService;

    @MyRequestMapping("index")
    public void index(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        try {
            httpServletResponse.getWriter().write(indexService.index());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
