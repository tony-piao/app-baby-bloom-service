package com.babybloom.web.model.dto;

import lombok.Data;

@Data
public class WeixinLoginUpDto {
    private String jsCode;      //获取openId的js_code
    private String rawData;     //不包括敏感信息的原始数据字符串，用于计算签名
    private String signature;   //使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息
    private String encryptedData;//包括敏感数据在内的完整用户信息的加密数据
    private String iv;          //加密算法的初始向量
}
