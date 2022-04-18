package com.project.myssm.myspringmvc;

/**
 * @ClassName DispatcherException
 * @Description TODO
 * @Author wk
 * @Date 2022/3/20 15:29
 * @Version 1.0
 */
public class DispatcherException extends RuntimeException{
    static final long serialVersionUID = -703489719074576639L;
    public DispatcherException(String message){
        super(message);
    }
}
