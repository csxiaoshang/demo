package com.ashang.spring.service.impl;

import com.ashang.spring.annotation.MyService;
import com.ashang.spring.service.IndexService;

/**
 * @author ashang  970090853@qq.com
 * @Date 19-9-23 下午2:37
 * <p>
 * 类说明：
 */
@MyService
public class IndexServiceImpl implements IndexService {

    @Override
    public String  index() {

        return "welcome to mySpring!!";
    }
}
