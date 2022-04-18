package com.project.zone.service;

import com.project.zone.bean.UserDetail;

import java.sql.SQLException;

/**
 *  用户详情业务接口
 *
 * */
public interface UserDetailService {

    /**
    * @author wk
    * @Description 添加用户详情信息
    * @Date 22:35 2022/3/30
    * @Param
    * @Return
    */

    public abstract Integer updateUserDetail(UserDetail userDetail) throws SQLException;

    /**
    * @author wk
    * @Description 添加用户详情id
    * @Date 11:24 2022/3/31
    * @Param
    * @Return
    */

    public abstract Integer addUserDetailId(Integer userBasicId) throws SQLException;

    /**
    * @author wk
    * @Description 根据用户id获取用户信息
    * @Date 15:25 2022/3/31
    * @Param
    * @Return
    */

    public abstract UserDetail getUserDetailById(Integer userBasicId) throws SQLException;

    /**
    * @author wk
    * @Description 根据用户手机号，获取用户id
    * @Date 22:12 2022/3/31
    * @Param
    * @Return
    */

    public abstract Integer getUserDetailIdByPhone(String phone) throws SQLException;

    /**
    * @author wk
    * @Description 根据用户邮箱，获取用户id
    * @Date 22:07 2022/4/8
    * @Param
    * @Return
    */

    public abstract Integer getUserDetailIdByEmail(String email) throws SQLException;

    /**
    * @author wk
    * @Description 根据用户身份证号，获取用户id
    * @Date 22:11 2022/4/8
    * @Param
    * @Return
    */

    public abstract Integer getUserDetailIdByCardId(String cardId) throws SQLException;


    /**
    * @author wk
    * @Description 根据用户id，删除用户详情信息
    * @Date 21:31 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteUserDetail(Integer userBasicId) throws SQLException;

}
