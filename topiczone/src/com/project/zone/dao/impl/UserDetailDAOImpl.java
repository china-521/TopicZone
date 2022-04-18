package com.project.zone.dao.impl;

import com.project.zone.bean.UserDetail;
import com.project.zone.dao.UserDetailDAO;
import com.project.zone.dao.basedao.BaseDAO;

import java.sql.Connection;

/**
 * @ClassName UserDetailDAOImpl
 * @Description TODO
 * @Author wk
 * @Date 2022/3/30 22:30
 * @Version 1.0
 */
public class UserDetailDAOImpl extends BaseDAO<UserDetail> implements UserDetailDAO {

    /**
    * @author wk
    * @Description 更新用户详情
    * @Date 22:31 2022/3/30
    * @Param
    * @Return
    */

    @Override
    public Integer updateUserDetail(Connection connection, UserDetail userDetail) {
        String sql = "update t_user_detail set user_name = ?,user_sex = ?,user_id_number = ?,user_phone = ?,user_email = ?,user_birth = ?,user_star = ? " +
                "where user_detail_id = ?";
        int addUserDetail = update(connection, sql, userDetail.getRealName(), userDetail.getSex(), userDetail.getIDNumber(), userDetail.getPhone(), userDetail.getEmail(), userDetail.getBirth(), userDetail.getStar(),userDetail.getId());
        return addUserDetail;
    }

    /**
    * @author wk
    * @Description 添加用户详情id
    * @Date 11:24 2022/3/31
    * @Param
    * @Return
    */

    @Override
    public Integer addUserDetailId(Connection connection, Integer userBasicId) {
        String sql = "insert into t_user_detail(user_detail_id) values(?)";
        int addUserDetailId = update(connection, sql, userBasicId);
        return addUserDetailId;
    }

    /**
    * @author wk
    * @Description 根据用户id，删除用户详情信息
    * @Date 21:34 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteUserDetailByUserId(Connection connection,Integer userBasicId) {
        String sql = "delete from t_user_detail where user_detail_id = ?";
        Integer deleteUserDetail = update(connection, sql, userBasicId);
        return deleteUserDetail;
    }

    /**
    * @author wk
    * @Description 根据用户id，获取用户详细信息
    * @Date 15:11 2022/3/31
    * @Param
    * @Return
    */

    @Override
    public UserDetail getUserDetailById(Connection connection, Integer userBasicId) {
        String sql = "select user_detail_id id,user_name realName,user_sex sex,user_id_number IDNumber,user_phone phone,user_email email " +
                ",user_birth birth,user_star star from t_user_detail where user_detail_id = ?";
        UserDetail userDetail = getInstance(connection, sql, userBasicId);
        return userDetail;
    }

    /**
    * @author wk
    * @Description 根据用户手机号，获取用户id
    * @Date 22:09 2022/3/31
    * @Param
    * @Return
    */

    @Override
    public Integer getUserDetailIdByPhone(Connection connection, String phone) {
        String sql = "select user_detail_id from t_user_detail where user_phone = ?";
        Integer userDetailId = getValue(connection, sql, phone);
        return userDetailId;
    }

    /**
    * @author wk
    * @Description 根据用户身份证号，获取用户id
    * @Date 22:09 2022/4/8
    * @Param
    * @Return
    */

    @Override
    public Integer getUserDetailIdByCardId(Connection connection, String cardId) {
        String sql = "select user_detail_id from t_user_detail where user_id_number = ?";
        Integer userDetailId = getValue(connection, sql, cardId);
        return userDetailId;
    }

    /**
    * @author wk
    * @Description 根据用户邮箱，获取用户id
    * @Date 22:06 2022/4/8
    * @Param
    * @Return
    */

    @Override
    public Integer getUserDetailIdByEmail(Connection connection, String email) {
        String sql = "select user_detail_id from t_user_detail where user_email = ?";
        Integer userDetailId = getValue(connection, sql, email);
        return userDetailId;
    }
}
