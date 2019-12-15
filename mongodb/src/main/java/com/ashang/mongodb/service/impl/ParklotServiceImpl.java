package com.ashang.mongodb.service.impl;

import com.ashang.mongodb.entity.request.ParklotNearByRequest;
import com.ashang.mongodb.entity.response.ParklotNearByRepData;
import com.ashang.mongodb.service.ParklotService;
import org.springframework.stereotype.Service;

/**
 * @author ashang  970090853@qq.com
 * @Date 19-12-15 上午10:29
 * <p>
 * 类说明：
 */
@Service
public class ParklotServiceImpl implements ParklotService {


    @Override
    public ParklotNearByRepData queryNearByParklot(ParklotNearByRequest parklotNearByRequest) {

        ParklotNearByRepData parklotNearByRepData = new ParklotNearByRepData();
        ParklotNearByRequest.MapBean map = parklotNearByRequest.getMap();
        ParklotNearByRequest.LocateBean locate = parklotNearByRequest.getLocate();

    }
}
