package com.ashang.mongodb.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import java.beans.Transient;


@Data
@Document(collection = "parklot")
public class Parklot {

    public static final Integer PARKLOT_TYPE_LIMIT = 3;

    @Id
    private Integer id;

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
     * 停车区入口经度
     */
    private Double naviLng;

    /**
     * 停车区入口纬度
     */
    private Double naviLat;

    /**
     * 停车区所在地域id
     */
    private Integer areaId;

    /**
     * 停车区运营商id
     */
    private Integer operatorId;
    /**
     * 移动用户id
     */
    private Integer mobileUserId;

    /**
     * 停车区等级id
     */
    private Integer parklotRankId;

    /**
     * 紧急联系人姓名
     */
    private String contactName;

    /**
     * 紧急联系人号码
     */
    private String contactPhone;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 编辑时间
     */
    private Long modifyTime;

    /**
     * 停车区类型，0是静态车场，1是约车场，2是约车位
     */
    private Integer type;

    /**
     * 类型，0:室内，1:室外，2:室内+室外
     */
    private Integer kind;

    /**
     * 是否安装继电器，0：没安装 1：安装
     */
    private Integer hasRelay;

    /**
     * 状态
     */
    private Integer state;


    /**
     * 道闸厂商id，0：博思高，1：科拓  2:博昂
     */
    private Integer barrierManufacturer;

    /**
     * 第三方的停车区id
     */
    private String extraParklotId;

    /**
     * 是否收取停车费
     */
    private Integer allocable;

    /**
     * 0是普通，1是内部共享
     */
    private Integer innershare;

    /**
     * 计费类型（0是道闸计费，1是地锁计费）
     */
    private Integer chargeType;

    private Integer tenantId;

    /**
     * 与当前定位位置的距离
     */
    private Integer locateDistance;

    /**
     * 总车位数量
     */
    private Integer totalAmount;

    /**
     * 签约车位数量
     */

    private Integer signedAmount;

    private String areaName;


    private String provinceName;


    private String cityName;


    private String typeStr;


    private String innershareStr;


    private String tenantName;

/*    public String getTypeStr(){
        return Status.ParklotType.find(this.getType());
    }
    public String getInnershareStr(){
        return Status.ParkingInner.find(this.getInnershare());
    }*/

}