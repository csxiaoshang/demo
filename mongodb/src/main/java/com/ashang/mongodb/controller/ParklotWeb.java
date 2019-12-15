package com.ashang.mongodb.controller;

import com.ashang.mongodb.entity.request.ParklotNearByRequest;
import com.ashang.mongodb.entity.response.ParklotNearByRepData;
import com.ashang.mongodb.entity.response.Resp;
import com.ashang.mongodb.service.ParklotService;
import com.ashang.mongodb.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ashang  970090853@qq.com
 * @Date 19-12-15 上午10:23
 * <p>
 * 类说明：
 */
@Controller
@RequestMapping("parklot")
public class ParklotWeb {

    @Autowired
    private ParklotService parklotService;

    @PostMapping("nearby")
    public Resp parklotNearBy(@RequestBody ParklotNearByRequest parklotNearByRequest) {
        ParklotNearByRepData parklotNearByRepData = parklotService.queryNearByParklot(parklotNearByRequest);
        return RespUtil.successResp(parklotNearByRepData);
    }
}
