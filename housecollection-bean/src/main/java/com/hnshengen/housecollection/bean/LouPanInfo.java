package com.hnshengen.housecollection.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("loupan_info")
public class LouPanInfo extends Model<LouPanInfo> implements Serializable {

    /**
     * id
     */
    @TableId(value = "loupan_id",type = IdType.ID_WORKER)
    private Long louPanId;

    /**
     * 楼盘名称
     */
    @TableField("loupan_name")
    private String louPanName;

    /**
     * 房间面积范围
     */
    @TableField("house_area")
    private String houseArea;

    /**
     *户型
     */
    @TableField("house_type")
    private String houseType;

    /**
     *装修类型
     */
    @TableField("fitment_type")
    private String fitmentType;

    /**
     *价格范围
     */
    @TableField("price")
    private String price;

    /**
     *项目地址
     */
    @TableField("address")
    private String address;

    /**
     *物业公司
     */
    @TableField("property_mana")
    private String propertyMana;

    /**
     *开发商
     */
    @TableField("developer")
    private String developer;

    /**
     *建筑面积
     */
    @TableField("covered_area")
    private String coveredArea;

    /**
     *占地面积
     */
    @TableField("floor_space")
    private int floorSpace;

    /**
     *户数
     */
    @TableField("house_amount")
    private int houseAmount;

    /**
     *车位数
     */
    @TableField("truck_space")
    private int truckSpace;

    /**
     *容积率
     */
    @TableField("plot_ratio")
    private BigDecimal plotRatio;

    /**
     *绿化率
     */
    @TableField("greening_rate")
    private BigDecimal greeningRate;

    /**
     *开盘时间
     */
    @TableField("open_time")
    private Date openTime;

    /**
     *封盘时间
     */
    @TableField("close_time")
    private Date closeTime;

    /**
     *交付时间
     */
    @TableField("delivery_time")
    private Date deliveryTime;

}
