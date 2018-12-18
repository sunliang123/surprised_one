package com.wangbei.exception;

public class ServiceException extends RuntimeException {

	public static final String UNKNOW_EXCEPTION = "1001";
	public static final String MENU_ADDITION_EXCEPTION="2001";
	public static final String MENU_MODIFICATION_EXCEPTION = "2002";

	private String type;
	private String message;
	public ServiceException(String type) {
		super(type);
		this.type = type;
	}

	public ServiceException(String type, Throwable cause) {
		super(type, cause);
		this.type = type;
	}

	//提示错误信息
	public String message() {
		if (ExceptionMap.exceptionMap.containsKey(type)) {
			message = ExceptionMap.exceptionMap.get(type);
		}
		return message;
	}

	public String getType() {
		return type;
	}
}
