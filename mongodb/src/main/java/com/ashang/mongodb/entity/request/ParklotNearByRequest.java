package com.ashang.mongodb.entity.request;

import lombok.Data;



@Data
public class ParklotNearByRequest {


    /**
     * map : {"lat":"xxx","lng":"xxx"}
     * locate : {"lat":"xx","lng":"xx"}
     * radius : xx
     * timestamp : xxxx
     */

    private MapBean map;
    private LocateBean locate;
    private Integer radius;
    private String timestamp;


    @Data
    public static class MapBean {
        /**
         * lat : xxx
         * lng : xxx
         */

        private Double lat;
        private Double lng;

    }

    @Data
    public static class LocateBean {
        /**
         * lat : xx
         * lng : xx
         */

        private Double lat;
        private Double lng;

    }
}
