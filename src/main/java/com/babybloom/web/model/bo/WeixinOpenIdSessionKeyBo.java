package com.babybloom.web.model.bo;

import lombok.Data;

@Data
public class WeixinOpenIdSessionKeyBo {
	private String session_key;
	private String openid;
	private Integer errcode;
	private String errmsg;
	private String unionid;
}
