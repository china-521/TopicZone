package com.project.zone.dao.impl;

import com.project.zone.bean.UserBasic;
import com.project.zone.dao.UserBasicDAO;
import com.project.zone.dao.basedao.BaseDAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserBasicDAOImpl
 * @Description UserBasicDAO的实现类
 * @Author wk
 * @Date 2022/3/23 15:33
 * @Version 1.0
 */
public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {

    private UserBasic userBasic = new UserBasic();

    /**
    * @author wk
    * @Description 向数据库添加用户基本信息
    * @Date 22:10 2022/3/23
    * @Param
    * @Return
    */

    @Override
    public int addUserBasic(Connection connection, UserBasic userBasic) {
        String sql = "insert into t_user_basic(login_id,nick_name,password,head_image) values(?,?,?,?)";
        int addUserBasic = update(connection, sql, userBasic.getLoginId(), userBasic.getNickName(), userBasic.getPassword(), userBasic.getHeadImage());
        return addUserBasic;
    }

    /**
     * @author wk
     * @Description 根据账号和密码获取用户基本信息
     * @Date 15:41 2022/3/23
     * @Param
     * @Return
     */

    @Override
    public UserBasic getUserBasic(Connection connection, String loginId, String password) {
        String sql = "select user_basic_id id, login_id loginId,nick_name nickName,password password,head_image headImage from t_user_basic where " +
                "login_id = ? and password = ?";
        UserBasic userBasic = getInstance(connection, sql, loginId, password);
        return userBasic;
    }

    /**
     * @author wk
     * @Description 根据用户基础信息，获取好友列表
     * @Date 15:58 2022/3/23
     * @Param
     * @Return
     * @return
     */

    @Override
    public List<UserBasic> getFriendList(Connection connection, UserBasic userBasic) {
        String sql = "select friend_id id from t_friends where user_id = ?";
        // 查询到的是好友id，不能直接作为UserBasic对象返回，要通过UserBasic构造器初始化后再返回
        List<UserBasic> friendIdList = getForList(connection, sql, userBasic.getId());
        ArrayList<UserBasic> friendIdListObj = new ArrayList<>(friendIdList.size());
        UserBasic userBasic1 = null;
        for(int i = 0;i< friendIdList.size();i++){
            userBasic1 = new UserBasic(friendIdList.get(i).getId());
            friendIdListObj.add(userBasic1);
        }
        return friendIdListObj;
    }

    /**
    * @author wk
    * @Description 根据页码和用户信息，查询好友列表
    * @Date 22:22 2022/4/14
    * @Param
    * @Return
    */

    @Override
    public List<UserBasic> getFriendListByPageNumber(Connection connection, UserBasic userBasic, Integer pageNumber) {
        String sql = "select friend_id id from t_friends where user_id = ? limit ?,5";
        // 查询到的是好友id，不能直接作为UserBasic对象返回，要通过UserBasic构造器初始化后再返回
        List<UserBasic> friendIdList = getForList(connection, sql, userBasic.getId(),(pageNumber - 1) * 5);
        ArrayList<UserBasic> friendIdListObj = new ArrayList<>(friendIdList.size());
        UserBasic userBasic1 = null;
        for(int i = 0;i< friendIdList.size();i++){
            userBasic1 = new UserBasic(friendIdList.get(i).getId());
            friendIdListObj.add(userBasic1);
        }
        return friendIdListObj;
    }

    /**
    * @author wk
    * @Description 根据好友id，获取用户id
    * @Date 21:12 2022/4/13
    * @Param
    * @Return
    */

    @Override
    public List<UserBasic> getUserIdByFriendId(Connection connection, Integer friendId) {
        String sql = "select user_id id from t_friends where friend_id = ?";
        List<UserBasic> userIdList = getForList(connection, sql, friendId);
        ArrayList<UserBasic> userList = new ArrayList<>(userIdList.size());
        UserBasic user = null;
        for(int i = 0;i < userIdList.size();i++){
            user = new UserBasic(userIdList.get(i).getId());
            userList.add(user);
        }
        return userList;
    }

    /**
    * @author wk
    * @Description 根据用户，获取好友总数
    * @Date 9:53 2022/4/2
    * @Param
    * @Return
    */

    @Override
    public Long getFriendCount(Connection connection, UserBasic userBasic) {
        String sql = "select count(*) from t_friends where user_id = ?";
        Long friendCount = getValue(connection, sql, userBasic.getId());
        return friendCount;
    }

    /**
    * @author wk
    * @Description 添加好友
    * @Date 14:58 2022/4/2
    * @Param
    * @Return
    */

    @Override
    public Integer addFriend(Connection connection, Integer userBasicId, Integer friendId) {
        String sql = "insert into t_friends(user_id,friend_id) values(?,?)";
        int addFriend = update(connection, sql, userBasicId, friendId);
        return addFriend;
    }

    /**
    * @author wk
    * @Description 删除好友
    * @Date 15:13 2022/4/2
    * @Param
    * @Return
    */

    @Override
    public Integer deleteFriend(Connection connection, Integer friendId) {
        String sql = "delete from t_friends where friend_id = ?";
        int deleteFriend = update(connection, sql, friendId);
        return deleteFriend;
    }

    /**
    * @author wk
    * @Description 根据用户id，删除用户基础信息
    * @Date 21:26 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteUserBasicByUserId(Connection connection, Integer userBasicId) {
        String sql = "delete from t_user_basic where user_basic_id = ?";
        Integer deleteUserBasic = update(connection, sql, userBasicId);
        return deleteUserBasic;
    }

    /**
    * @author wk
    * @Description 根据用户id，删除好友信息
    * @Date 21:26 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteFriendByUserId(Connection connection, Integer userBasicId) {
        String sql = "delete from t_friends where user_id = ?";
        Integer deleteFriend = update(connection,sql,userBasicId);
        return deleteFriend;
    }

    /**
     * @author wk
     * @Description 根据用户id，获取用户基础信息
     * @Date 16:06 2022/3/23
     * @Param
     * @Return
     */

    @Override
    public UserBasic getUserBasicById(Connection connection, Integer userBasicId) {
        String sql = "select user_basic_id id, login_id loginId,nick_name nickName,password password,head_image headImage from t_user_basic where " +
                "user_basic_id = ?";
        UserBasic user = getInstance(connection, sql, userBasicId);
        return user;
    }

    /**
    * @author wk
    * @Description 获取全部用户信息
    * @Date 22:45 2022/4/14
    * @Param
    * @Return
    */

    @Override
    public List<UserBasic> getUserBasicList(Connection connection) {
        String sql = "select user_basic_id id, login_id loginId,nick_name nickName,password password,head_image headImage from t_user_basic";
        List<UserBasic> userList = getForList(connection, sql);
        return userList;
    }

    /**
    * @author wk
    * @Description 根据关键字，查询用户信息
    * @Date 9:19 2022/4/2
    * @Param
    * @Return
    */

    @Override
    public List<UserBasic> getUserBasicByKeyWord(Connection connection, String keyword, Integer pageNumber) {
        String sql = "select user_basic_id id, login_id loginId,nick_name nickName,password password,head_image headImage from t_user_basic where " +
                "login_id like ? limit ?,5" ;
        String param = "%" + keyword + "%";
        Integer pageNumber1 = pageNumber;
        List<UserBasic> userBasicList = getForList(connection,sql, param, (pageNumber - 1) * 5);
        return userBasicList;
    }

    /**
    * @author wk
    * @Description 根据关键字,查询用户总数
    * @Date 9:37 2022/4/2
    * @Param
    * @Return
    */

    @Override
    public Long getUserBasicCountByKeyWord(Connection connection, String keyword) {
        String sql = "select count(*) from t_user_basic where login_id like ? ";
        String param = "%" + keyword + "%";
        Long userBasicCount = getValue(connection, sql, param);
        return userBasicCount;
    }
}
