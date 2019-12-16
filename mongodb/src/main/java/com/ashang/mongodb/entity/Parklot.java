package com.ashang.mongodb.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;


@Data
@Document(collection = "parklot")
public class Parklot {

    public static final Integer PARKLOT_TYPE_LIMIT = 3;

    @Id
    private String id;

    @Field("parklot_id")
    private Integer parklotId;

    /**
     * 停车区名称
     */
    private String name;

    /**
     * 停车区别名
     */
    private String alias;

    /**
     * 停车区描述
     */
    private String intro;

    /**
     * 收费标准说明
     */
    @Field("fee_intro")
    private String feeIntro;

    /**
     * 停车区地址
     */
    private String address;

    /**
     * 停车区经度
     */
    private Double lng;

    /**
     * 停车区纬度
     */
    private Double lat;

    /**
     *  地理位置索引
     */
    @GeoSpatialIndexed(type= GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;

    /**
     * 停车区入口经度
     */
    @Field("navi_lng")
    private Double naviLng;

    /**
     * 停车区入口纬度
     */
    @Field("navi_lat")
    private Double naviLat;

    /**
     * 停车区所在地域id
     */
    @Field("area_id")
    private String areaId;

    /**
     * 停车区运营商id
     */
    @Field("operator_id")
    private String operatorId;
    /**
     * 移动用户id
     */
    @Field("mobile_user_id")
    private String mobileUserId;

    /**
     * 停车区等级id
     */
    @Field("parklot_rank_id")
    private String parklotRankId;

    /**
     * 紧急联系人姓名
     */
    @Field("contact_name")
    private String contactName;

    /**
     * 紧急联系人号码
     */
    @Field("contact_phone")
    private String contactPhone;

    /**
     * 创建时间
     */
    @Field("create_time")
    private Long createTime;

    /**
     * 编辑时间
     */
    @Field("modify_time")
    private Long modifyTime;

    /**
     * 停车区类型，0是静态车场，1是约车场，2是约车位
     */
    private String type;

    /**
     * 类型，0:室内，1:室外，2:室内+室外
     */
    private String kind;

    /**
     * 是否安装继电器，0：没安装 1：安装
     */
    @Field("has_relay")
    private String hasRelay;

    /**
     * 状态
     */
    private String state;


    /**
     * 道闸厂商id，0：博思高，1：科拓  2:博昂
     */
    @Field( "barrier_manufacturer")
    private String barrierManufacturer;

    /**
     * 第三方的停车区id
     */
    @Field("extra_parklot_id")
    private String extraParklotId;

    /**
     * 是否收取停车费
     */
    private String allocable;

    /**
     * 0是普通，1是内部共享
     */
    private String innershare;

    /**
     * 计费类型（0是道闸计费，1是地锁计费）
     */
    @Field("charge_type")
    private String chargeType;

    @Field("tenant_id")
    private String tenantId;
    /**
     * 与当前定位位置的距离
     */
    @Transient
    private Integer locateDistance;

    /**
     * 总车位数量
     */
    @Transient
    private Integer totalAmount;

    /**
     * 签约车位数量
     */

    private String signedAmount;

    @Transient
    private String areaName;

    @Transient
    private String provinceName;

    @Transient
    private String cityName;

    @Transient
    private String typeStr;

    @Transient
    private String innershareStr;

    @Transient
    private String tenantName;

/*    public String getTypeStr(){
        return Status.ParklotType.find(this.getType());
    }
    public String getInnershareStr(){
        return Status.ParkingInner.find(this.getInnershare());
    }*/

}