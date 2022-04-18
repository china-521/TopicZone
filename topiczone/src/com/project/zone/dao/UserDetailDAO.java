package com.project.zone.dao;

import com.project.zone.bean.UserDetail;

import java.sql.Connection;

/**
 *
 *  用户详情接口
 *
 * */
public interface UserDetailDAO {

    /**
    * @author wk
    * @Description 添加用户详情
    * @Date 22:30 2022/3/30
    * @Param
    * @Return
    */

    public abstract Integer updateUserDetail(Connection connection, UserDetail userDetail);

    /**
    * @author wk
    * @Description 添加用户详情id
    * @Date 11:22 2022/3/31
    * @Param
    * @Return
    */

    public abstract Integer addUserDetailId(Connection connection, Integer userBasicId);

    /**
    * @author wk
    * @Description 根据用户id，删除用户详情信息
    * @Date 21:33 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteUserDetailByUserId(Connection connection,Integer userBasicId);

    /**
    * @author wk
    * @Description 根据id，获取用户详情信息
    * @Date 13:32 2022/3/31
    * @Param
    * @Return
    */

    public abstract UserDetail getUserDetailById(Connection connection,Integer userBasicId);

    /**
    * @author wk
    * @Description 根据电话号码，查询用户id
    * @Date 22:09 2022/3/31
    * @Param
    * @Return
    */

    public abstract Integer getUserDetailIdByPhone(Connection connection,String phone);

    /**
    * @author wk
    * @Description 根据邮箱，查询用户id
    * @Date 22:05 2022/4/8
    * @Param
    * @Return
    */

    public abstract Integer getUserDetailIdByEmail(Connection connection,String email);

    /**
    * @author wk
    * @Description 根据用户身份证号，查询用户id
    * @Date 22:08 2022/4/8
    * @Param
    * @Return
    */

    public abstract Integer getUserDetailIdByCardId(Connection connection,String cardId);


}
