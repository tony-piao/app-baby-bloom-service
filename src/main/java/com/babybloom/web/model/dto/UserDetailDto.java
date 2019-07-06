package com.babybloom.web.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDetailDto {
    private Long guid;

    private String userNumber;

    private String nickName;

    private String imgUrl;

    private Byte gender;

    private String city;

    private String province;

    private String country;

    private Date registrationTime;

    private String token;   //登录的token标识

}
