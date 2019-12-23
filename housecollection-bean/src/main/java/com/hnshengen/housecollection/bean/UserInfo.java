package com.hnshengen.housecollection.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user_info")
public class UserInfo extends Model<UserInfo> implements Serializable{

    @TableId(value = "user_info_id", type = IdType.ID_WORKER)
    private Long userInfoId;

    @TableField("nick_name")
    private String nickName;

    private String phone;

    @TableField("open_id")
    private String openId;

    @TableField("pic_url")
    private String picUrl;


}
