package com.hnshengen.housecollection.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("broker_info")
public class BrokerInfo extends Model<BrokerInfo> implements Serializable {

    @TableId(value = "broker_id", type = IdType.ID_WORKER)
    private Long brokerId;

    private String name;

    private String phone;

    /**
     * 头像的链接
     */
    @TableField("head_portrait_url")
    private String headPortraitUrl;

    @TableField("person_describe")
    private String personDescribe;

}
