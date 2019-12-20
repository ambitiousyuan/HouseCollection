package com.hnshengen.housecollection.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserInfo implements Serializable{

    private String nickName;

    private String phone;

    private String openId;

    private String picUrl;


}
