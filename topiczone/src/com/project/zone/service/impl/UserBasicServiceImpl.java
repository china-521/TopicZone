package com.project.zone.service.impl;

import com.project.myssm.util.JDBCUtils;
import com.project.zone.bean.UserBasic;
import com.project.zone.dao.impl.UserBasicDAOImpl;
import com.project.zone.service.UserBasicService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserBasicServiceImpl
 * @Description UserBasicService的实现类
 * @Author wk
 * @Date 2022/3/23 15:45
 * @Version 1.0
 */
public class UserBasicServiceImpl implements UserBasicService {

    private UserBasicDAOImpl userBasicDAO = null;

    /**
    * @author wk
    * @Description 注册
    * @Date 9:26 2022/4/2
    * @Param
    * @Return
    */

    @Override
    public int addUserBasic(UserBasic userBasic) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        int register = userBasicDAO.addUserBasic(connection, userBasic);
        return register;
    }

    /**
     * @author wk
     * @Description 登录
     * @Date 15:45 2022/3/23
     * @Param
     * @Return
     */

    @Override
    public UserBasic getUserBasic(String loginId, String password) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        UserBasic userBasic = userBasicDAO.getUserBasic(connection, loginId, password);
        return userBasic;
    }

    /**
     * @author wk
     * @Description 根据用户基础信息，获取好友列表
     * @Date 15:59 2022/3/23
     * @Param
     * @Return
     */

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        List<UserBasic> friendIdListObj = userBasicDAO.getFriendList(connection, userBasic);
        ArrayList<UserBasic> friendList = new ArrayList<>(friendIdListObj.size());
        UserBasic friend = null;
        for (int i = 0; i < friendIdListObj.size(); i++) {
            friend = userBasicDAO.getUserBasicById(connection,friendIdListObj.get(i).getId());
            friendList.add(friend);
        }
        return friendList;
    }

    /**
    * @author wk
    * @Description 根据页码和用户信息，获取好友列表
    * @Date 22:24 2022/4/14
    * @Param
    * @Return
    */

    @Override
    public List<UserBasic> getFriendListByPageNumber(UserBasic userBasic, Integer pageNumber) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        List<UserBasic> friendIdListObj = userBasicDAO.getFriendListByPageNumber(connection, userBasic,pageNumber);
        ArrayList<UserBasic> friendList = new ArrayList<>(friendIdListObj.size());
        UserBasic friend = null;
        for (int i = 0; i < friendIdListObj.size(); i++) {
            friend = userBasicDAO.getUserBasicById(connection,friendIdListObj.get(i).getId());
            friendList.add(friend);
        }
        return friendList;
    }

    /**
    * @author wk
    * @Description 根据好友id，获取用户id
    * @Date 21:15 2022/4/13
    * @Param
    * @Return
    */

    @Override
    public List<UserBasic> getUserListByFriendId(Integer friendId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        List<UserBasic> userList = userBasicDAO.getUserIdByFriendId(connection, friendId);
        return userList;
    }

    @Override
    public List<UserBasic> getUserBasicList() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        List<UserBasic> userBasicList = userBasicDAO.getUserBasicList(connection);
        return userBasicList;
    }

    /**
    * @author wk
    * @Description 好友总数
    * @Date 9:56 2022/4/2
    * @Param
    * @Return
    */

    @Override
    public Long getFriendCount(UserBasic userBasic) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Long friendCount = userBasicDAO.getFriendCount(connection, userBasic);
        return friendCount;
    }

    /**
    * @author wk
    * @Description 添加好友
    * @Date 15:01 2022/4/2
    * @Param
    * @Return
    */

    @Override
    public Integer addFriend(Integer userBasicId, Integer friendId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer addFriend = userBasicDAO.addFriend(connection, userBasicId, friendId);
        return addFriend;
    }

    /**
    * @author wk
    * @Description 根据好友id,删除好友
    * @Date 15:15 2022/4/2
    * @Param
    * @Return
    */

    @Override
    public Integer deleteFriend(Integer friendId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer deleteFriend = userBasicDAO.deleteFriend(connection, friendId);
        return deleteFriend;
    }

    /**
    * @author wk
    * @Description 根据用户id，删除用户基础信息
    * @Date 21:28 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteUserBasicByUserId(Integer userBasicId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer deleteUserBasic = userBasicDAO.deleteUserBasicByUserId(connection, userBasicId);
        return deleteUserBasic;
    }

    /**
    * @author wk
    * @Description 根据用户id，删除好友信息
    * @Date 21:29 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteFriendByUserId(Integer userBasicId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer deleteFriend = userBasicDAO.deleteFriendByUserId(connection,userBasicId);
        return deleteFriend;
    }


    /**
    * @author wk
    * @Description 根据用户id，查询特定用户信息
    * @Date 16:24 2022/3/23
    * @Param
    * @Return
    */

    @Override
    public UserBasic getUserBasicById(Integer id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        UserBasic userBasicById = userBasicDAO.getUserBasicById(connection, id);
        return userBasicById;
    }

    /**
     * @author wk
     * @Description 根据关键字，查询用户信息
     * @Date 9:27 2022/4/2
     * @Param
     * @Return
     */

    @Override
    public List<UserBasic> getUserBasicByKeyWord(String keyword, Integer pageNumber) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        List<UserBasic> userBasicByKeyWord = userBasicDAO.getUserBasicByKeyWord(connection, keyword, pageNumber);
        return userBasicByKeyWord;
    }

    /**
    * @author wk
    * @Description 根据关键字，查询用户总数
    * @Date 9:40 2022/4/2
    * @Param
    * @Return
    */

    @Override
    public Long getUserBasicCountByKeyWord(String keyword) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Long userBasicCount = userBasicDAO.getUserBasicCountByKeyWord(connection, keyword);
        return userBasicCount;
    }
}
