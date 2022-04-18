package com.project.zone.dao;

import com.project.zone.bean.UserBasic;

import java.sql.Connection;
import java.util.List;

/**
 *  用户基础信息接口
 *
 * */
public interface UserBasicDAO {

    /**
    * @author wk
    * @Description 向数据库中添加用户基础信息
    * @Date 22:10 2022/3/23
    * @Param
    * @Return
    */

    public abstract int addUserBasic(Connection connection,UserBasic userBasic);

    /**
    * @author wk
    * @Description 根据账号 和 密码 获取用户基础信息
    * @Date 15:38 2022/3/23
    * @Param
    * @Return
    */

    public abstract UserBasic getUserBasic(Connection connection,String loginId,String password);

    /**
    * @author wk
    * @Description 根据用户基础信息，获取好友列表
    * @Date 15:58 2022/3/23
    * @Param
    * @Return
    */

    public abstract List<UserBasic> getFriendList(Connection connection,UserBasic userBasic);

    /**
    * @author wk
    * @Description 根据用户基础信息，和页码获取好友列表
    * @Date 22:21 2022/4/14
    * @Param
    * @Return
    */

    public abstract List<UserBasic> getFriendListByPageNumber(Connection connection,UserBasic userBasic,Integer pageNumber);

    /**
    * @author wk
    * @Description 根据好友id，获取用户
    * @Date 21:11 2022/4/13
    * @Param
    * @Return
    */

    public abstract List<UserBasic> getUserIdByFriendId(Connection connection,Integer friendId);


    /**
    * @author wk
    * @Description 根据用户，获取用户好友总数
    * @Date 9:52 2022/4/2
    * @Param
    * @Return
    */

    public abstract Long getFriendCount(Connection connection,UserBasic userBasic);

    /**
    * @author wk
    * @Description 添加好友
    * @Date 14:58 2022/4/2
    * @Param
    * @Return
    */

    public abstract Integer addFriend(Connection connection,Integer userBasicId,Integer friendId);

    /**
    * @author wk
    * @Description 删除好友
    * @Date 15:13 2022/4/2
    * @Param
    * @Return
    */

    public abstract Integer deleteFriend(Connection connection,Integer friendId);

    /**
    * @author wk
    * @Description 根据用户id，删除用户基础信息
    * @Date 21:24 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteUserBasicByUserId(Connection connection,Integer userBasicId);

    /**
    * @author wk
    * @Description 根据用户id，删除好友信息
    * @Date 21:25 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteFriendByUserId(Connection connection,Integer userBasicId);


    /**
     * @author wk
     * @Description 根据用户id获取，用户基本信息
     * @Date 16:04 2022/3/23
     * @Param
     * @Return
     */

    public abstract UserBasic getUserBasicById(Connection connection,Integer userBasicId);

    /**
    * @author wk
    * @Description 获取全体用户信息
    * @Date 22:44 2022/4/14
    * @Param
    * @Return
    */

    public abstract List<UserBasic> getUserBasicList(Connection connection);

    /**
    * @author wk
    * @Description 根据关键字查询用户信息
    * @Date 9:18 2022/4/2
    * @Param
    * @Return
    */

    public abstract List<UserBasic> getUserBasicByKeyWord(Connection connection,String keyword,Integer pageNumber);

    /**
    * @author wk
    * @Description 根据关键字,查询用户总数
    * @Date 9:34 2022/4/2
    * @Param
    * @Return
    */

    public abstract Long getUserBasicCountByKeyWord(Connection connection,String keyword);

}
