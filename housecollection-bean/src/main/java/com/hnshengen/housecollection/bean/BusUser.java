package com.hnshengen.housecollection.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by MYP on 2019-12-26.
 */
@Data
public class BusUser extends Model<BusUser> implements Serializable {

    @TableId(value = "open_id", type = IdType.ID_WORKER)
    private Long openId;

    @TableField("name")
    private String name;

    private String phone;

    private String sex;

    @TableField("pic_url")
    private String picUrl;

    private Date birthday;

    @TableField("user_type")
    private String userType;

    @TableField("resgister_time")
    private Date resgisterTime;


    @TableField("update_time")
    private Date updateTime;
}
