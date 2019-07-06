package com.babybloom.web.constant;

public interface CommonErrorId {

	int ERROR_NONE = 0;
	// DB Exception
	int ERROR_DB_EXCEPTION = -1;

	// Application Exception
	// 1-1000: WebApplicationCommon错误
	int ERROR_REMOTE_CALL = 1; // 远程调用失败
	int ERROR_PARAMETER = 2; // 参数值错误
	int ERROR_PROTOCOL_HEADER = 3; // 协议头字段错误
	int ERROR_ONLINE_STATUS = 4; // 服务时间外
	// 未知异常
	int ERROR_UNKNOWN = 999999;
}
