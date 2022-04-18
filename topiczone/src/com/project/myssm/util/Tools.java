package com.project.myssm.util;

/**
 * @ClassName Tools
 * @Description 工具类
 * @Author wk
 * @Date 2022/3/14 21:30
 * @Version 1.0
 */
public class Tools {

    /**
    * @author wk
    * @Description 判断字符串是否为 null 或 ""
    * @Date 21:30 2022/3/14
    * @Param
    * @Return
    */

    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }

    /**
    * @author wk
    * @Description 判断字符串不是 null 或 ""
    * @Date 21:32 2022/3/14
    * @Param
    * @Return
    */

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
