package com.babybloom.web.exception;

/**
 * 业务异常类
 * 
 * @author ZZ
 *
 */
public class ApplicationException extends RuntimeException {

	private int errorId;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationException(int errorId) {
		this.setErrorId(errorId);
	}

	public ApplicationException(int errorId, String message) {
		super(message);
		this.setErrorId(errorId);
	}

	public ApplicationException(int errorId, Throwable e) {
		super(e);
		this.errorId = errorId;
	}

	public ApplicationException(int errorId, String message, Throwable cause) {
		super(message, cause);
		this.setErrorId(errorId);
	}

	public static ApplicationException build(int errorId) {
		return new ApplicationException(errorId);
	}

	public static ApplicationException build(int errorId, String message) {
		return new ApplicationException(errorId, message);
	}

	public static ApplicationException build(int errorId, Throwable cause) {
		return new ApplicationException(errorId, cause);
	}

	public static ApplicationException build(int errorId, String message, Throwable cause) {
		return new ApplicationException(errorId, message, cause);
	}

	public int getErrorId() {
		return errorId;
	}

	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}

}
