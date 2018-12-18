package com.wangbei.exception;

import java.util.HashMap;
import java.util.Map;

public class ExceptionMap {

    public static  Map<String, String> exceptionMap = new HashMap<String, String>();
    
    static{
        exceptionMap.put(ServiceException.UNKNOW_EXCEPTION, "服务器未知异常");
        exceptionMap.put(ServiceException.MENU_ADDITION_EXCEPTION, "菜单添加异常");
        exceptionMap.put(ServiceException.MENU_MODIFICATION_EXCEPTION, "菜单修改异常");
    }
}
