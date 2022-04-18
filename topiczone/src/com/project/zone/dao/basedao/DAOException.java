package com.project.zone.dao.basedao;

/**
 * @ClassName DAOException
 * @Description 自定义异常类
 * @Author wk
 * @Date 2022/3/20 15:15
 * @Version 1.0
 */
public class DAOException extends RuntimeException{
    static final long serialVersionUID = -703489719074576693L;
    public DAOException(String message){
        super(message);
    }
}
