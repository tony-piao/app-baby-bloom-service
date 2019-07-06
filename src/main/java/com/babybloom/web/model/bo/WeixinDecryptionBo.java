package com.babybloom.web.model.bo;

import lombok.Data;

@Data
public class WeixinDecryptionBo {
    private String openId;
    private String unionId;
    private String nickName;
    private String avatarUrl;
    private Byte gender; // 性别 0：未知、1：男、2：女
    private String province;
    private String city;
    private String country;

}
