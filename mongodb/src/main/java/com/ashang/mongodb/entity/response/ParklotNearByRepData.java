package com.ashang.mongodb.entity.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 赵翔 xiangflight@foxmail.com
 * @version 2.0.1 创建时间: 2018/2/27 下午6:39
 * <p>
 * 类说明：
 *     附近空车位返回参数
 */

@Data
public class ParklotNearByRepData {


    private List<ParklotsBean> parklots;

    public List<ParklotsBean> getParklots() {
        return parklots;
    }

    public void setParklots(List<ParklotsBean> parklots) {
        this.parklots = parklots;
    }

    @Data
    public static class ParklotsBean {
        /**
         * id : 1
         * name : 同方信息港停车场
         * type : 1
         * address : 朗山路11号
         * kind: 1
         * lat : 11.2323
         * lng : 11.2322
         * navi_lat : 11.2323
         * navi_lng : 119.2321
         * distance : 100m
         * total_amount : 1000
         * idle_amount : 10
         * reserve_fee : 5.0
         * parking_fee : 10.0
         * charge_type : 0: 道闸计费,1:地锁计费
         */

        private int id;
        private String name;
        private int type;
        private String address;
        private int kind;
        private String lat;
        private String lng;
        private String navi_lat;
        private String navi_lng;
        private String distance;
        private Integer total_amount;
        private Integer left_amount;
        private Integer reservable_amount;
        private BigDecimal reserve_fee;
        private BigDecimal parking_fee;
        private Integer show;
        private Integer charge_type;
        private Integer packageState;
    }
}
