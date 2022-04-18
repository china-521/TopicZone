package com.project.zone.controller;

import javax.servlet.http.HttpSession;

/**
 * @ClassName ErrorController
 * @Description 控制异常信息
 * @Author wk
 * @Date 2022/4/1 11:12
 * @Version 1.0
 */
public class ErrorController {

    /**
     * @author wk
     * @Description 进入异常页面
     * @Date 23:24 2022/3/31
     * @Param
     * @Return
     */

    public String error(String  exception, HttpSession session){
        session.setAttribute("error",exception);
        return "error";
    }
}
