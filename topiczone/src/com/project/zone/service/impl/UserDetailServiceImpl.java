package com.project.zone.service.impl;

import com.project.myssm.util.JDBCUtils;
import com.project.zone.bean.UserDetail;
import com.project.zone.dao.UserDetailDAO;
import com.project.zone.service.UserDetailService;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName UserDetailServiceImpl
 * @Description TODO
 * @Author wk
 * @Date 2022/3/30 22:35
 * @Version 1.0
 */
public class UserDetailServiceImpl implements UserDetailService {

    private UserDetailDAO userDetailDAO = null;

    /**
    * @author wk
    * @Description 更新用户详情信息
    * @Date 22:36 2022/3/30
    * @Param
    * @Return
    */

    @Override
    public Integer updateUserDetail(UserDetail userDetail) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer addUserDetail = userDetailDAO.updateUserDetail(connection, userDetail);
        return addUserDetail;
    }

    /**
    * @author wk
    * @Description 添加用户详情id
    * @Date 11:26 2022/3/31
    * @Param
    * @Return
    */

    @Override
    public Integer addUserDetailId(Integer userBasicId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer addUserDetailId = userDetailDAO.addUserDetailId(connection, userBasicId);
        return addUserDetailId;
    }

    /**
    * @author wk
    * @Description 根据用户id，获取用户详细信息
    * @Date 15:26 2022/3/31
    * @Param
    * @Return
    */

    @Override
    public UserDetail getUserDetailById(Integer userBasicId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        UserDetail userDetail = userDetailDAO.getUserDetailById(connection, userBasicId);
        return userDetail;
    }

    /**
    * @author wk
    * @Description 根据用户身份证号，获取用户id
    * @Date 22:12 2022/4/8
    * @Param
    * @Return
    */

    @Override
    public Integer getUserDetailIdByCardId(String cardId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer userDetailId = userDetailDAO.getUserDetailIdByCardId(connection, cardId);
        return userDetailId;
    }

    /**
    * @author wk
    * @Description 根据用户邮箱，获取用户id
    * @Date 22:07 2022/4/8
    * @Param
    * @Return
    */

    @Override
    public Integer getUserDetailIdByEmail(String email) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer userDetailId = userDetailDAO.getUserDetailIdByEmail(connection, email);
        return userDetailId;
    }

    /**
    * @author wk
    * @Description 根据用户手机号，获取用户id
    * @Date 22:13 2022/3/31
    * @Param
    * @Return
    */

    @Override
    public Integer getUserDetailIdByPhone(String phone) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer userDetailId = userDetailDAO.getUserDetailIdByPhone(connection, phone);
        return userDetailId;
    }

    /**
    * @author wk
    * @Description 根据用户id，删除用户详情信息
    * @Date 21:32 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteUserDetail(Integer userBasicId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer deleteUserDetail = userDetailDAO.deleteUserDetailByUserId(connection, userBasicId);
        return deleteUserDetail;
    }
}
