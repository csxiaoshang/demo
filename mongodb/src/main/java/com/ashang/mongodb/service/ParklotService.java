package com.ashang.mongodb.service;

import com.ashang.mongodb.entity.request.ParklotNearByRequest;
import com.ashang.mongodb.entity.response.ParklotNearByRepData;

/**
 * @author ashang  970090853@qq.com
 * @Date 19-12-15 上午10:28
 * <p>
 * 类说明：
 */
public interface ParklotService {


    ParklotNearByRepData queryNearByParklot(ParklotNearByRequest parklotNearByRequest);

}
