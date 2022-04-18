package com.project.zone.service;

import com.project.zone.bean.UserBasic;

import java.sql.SQLException;
import java.util.List;

/**
 *  用户基本信息的业务模型接口
 *
 * */
public interface UserBasicService {

    /**
    * @author wk
    * @Description 添加用户基础信息（注册）
    * @Date 22:13 2022/3/23
    * @Param
    * @Return
    */
    public  abstract int addUserBasic(UserBasic userBasic) throws SQLException;

    /**
    * @author wk
    * @Description  通过密码和账户查询用户信息,可用于登录功能
     * @Date 15:43 2022/3/23
    * @Param
    * @Return
    */

    public abstract UserBasic getUserBasic(String loginId,String password) throws SQLException;

    /**
    * @author wk
    * @Description 好友列表
    * @Date 15:59 2022/3/23
    * @Param
    * @Return
    */

    public abstract List<UserBasic> getFriendList(UserBasic userBasic) throws SQLException;


    /**
    * @author wk
    * @Description 根据页码和用户信息，返回好友列表
    * @Date 22:24 2022/4/14
    * @Param
    * @Return
    */

    public abstract List<UserBasic> getFriendListByPageNumber(UserBasic userBasic,Integer pageNumber) throws SQLException;
    /**
    * @author wk
    * @Description 根据好友id，获取用户
    * @Date 21:15 2022/4/13
    * @Param
    * @Return
    */

    public abstract List<UserBasic> getUserListByFriendId(Integer friendId) throws SQLException;

    /**
    * @author wk
    * @Description 获取所有用户信息
    * @Date 22:46 2022/4/14
    * @Param
    * @Return
    */

    public abstract List<UserBasic> getUserBasicList() throws SQLException;

    /**
    * @author wk
    * @Description 好友总数
    * @Date 9:55 2022/4/2
    * @Param
    * @Return
    */

    public abstract Long getFriendCount(UserBasic userBasic) throws SQLException;

    /**
    * @author wk
    * @Description 添加好友
    * @Date 15:01 2022/4/2
    * @Param
    * @Return
    */

    public abstract Integer addFriend(Integer userBasicId,Integer friendId) throws SQLException;

    /**
    * @author wk
    * @Description 删除好友
    * @Date 15:15 2022/4/2
    * @Param
    * @Return
    */

    public abstract Integer deleteFriend(Integer friendId) throws SQLException;

    /**
    * @author wk
    * @Description 根据用户id，删除用户基础信息
    * @Date 21:22 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteUserBasicByUserId(Integer userBasicId) throws SQLException;

    /**
    * @author wk
    * @Description 根据用户id，删除用户好友信息
    * @Date 21:24 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteFriendByUserId(Integer userBasicId) throws SQLException;

    /**
    * @author wk
    * @Description 根据用户id，获取用户基本信息
    * @Date 16:07 2022/3/23
    * @Param
    * @Return
    */

    public abstract UserBasic getUserBasicById(Integer userBasicId) throws SQLException;

    /**
    * @author wk
    * @Description 根据关键字，查询用户信息
    * @Date 9:24 2022/4/2
    * @Param
    * @Return
    */

    public abstract List<UserBasic> getUserBasicByKeyWord(String keyword,Integer pageNumber) throws SQLException;

    /**
    * @author wk
    * @Description 根据关键字，查询用户总数
    * @Date 9:39 2022/4/2
    * @Param
    * @Return
    */

    public abstract Long getUserBasicCountByKeyWord(String keyword) throws SQLException;
}
