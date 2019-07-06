package com.babybloom.web.model.entity;

import com.babybloom.web.constant.CommonErrorId;

/**
 * 通用json返回类(所有http接口都要用此类进行封装返回)
 * 
 * @author ZZ
 *
 */
public class GeneralJsonResult {
	private int code;
	private String message;
	private Object data;

	public static GeneralJsonResult success() {
		return build(CommonErrorId.ERROR_NONE, "success", "");
	}

	public static GeneralJsonResult success(String message, Object object) {
		return build(CommonErrorId.ERROR_NONE, message, object);
	}

	public static GeneralJsonResult success(Object object) {
		return build(CommonErrorId.ERROR_NONE, "success", object);
	}

	public static GeneralJsonResult error() {
		return error(CommonErrorId.ERROR_UNKNOWN);
	}

	public static GeneralJsonResult error(String message) {
		return build(CommonErrorId.ERROR_UNKNOWN, message, null);
	}

	public static GeneralJsonResult error(int code) {
		return build(code, "error", null);
	}

	public static GeneralJsonResult error(int code, String message) {
		return build(code, message, null);
	}

	public static GeneralJsonResult build(int code, String message, Object object) {
		GeneralJsonResult result = new GeneralJsonResult();
		result.setCode(code);
		result.setMessage(message);
		result.setData(object);
		return result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
