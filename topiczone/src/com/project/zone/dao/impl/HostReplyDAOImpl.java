package com.project.zone.dao.impl;

import com.project.zone.bean.HostReply;
import com.project.zone.dao.HostReplyDAO;
import com.project.zone.dao.basedao.BaseDAO;

import java.sql.Connection;
import java.util.List;

/**
 * @ClassName HostReplyDAOImpl
 * @Description TODO
 * @Author wk
 * @Date 2022/3/25 21:49
 * @Version 1.0
 */
public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {

    /**
    * @author wk
    * @Description 添加评论的回复
    * @Date 14:43 2022/3/29
    * @Param
    * @Return
    */

    @Override
    public Integer addHostReply(Connection connection, HostReply hostReply) {
        String sql = "insert into t_host_reply(host_reply_content,host_reply_date,user_basic_id,reply_id) " +
                "values(?,?,?,?)";
        int addHostReply = update(connection, sql, hostReply.getContent(), hostReply.getHostReplyDate(), hostReply.getAuthor().getId(), hostReply.getReply().getId());
        return addHostReply;
    }

    /**
    * @author wk
    * @Description 删除评论的回复
    * @Date 11:05 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteHostReply(Connection connection, Integer hostReplyId) {
        String sql = "delete from t_host_reply where host_reply_id = ?";
        Integer deleteHostReply = update(connection, sql, hostReplyId);
        return deleteHostReply;
    }

    /**
    * @author wk
    * @Description 根据用户id，删除评论的回复
    * @Date 21:57 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteHostReplyByUserId(Connection connection, Integer userBasicId) {
        String sql = "delete from t_host_reply where user_basic_id = ?";
        Integer deleteHostReply = update(connection,sql,userBasicId);
        return deleteHostReply;
    }

    /**
    * @author wk
    * @Description 根据评论id，删除评论的回复
    * @Date 20:03 2022/4/16
    * @Param
    * @Return
    */

    @Override
    public Integer deleteHostReplyByReplyId(Connection connection, Integer replyId) {
        String sql = "delete from t_host_reply where reply_id = ?";
        Integer deleteHostReply = update(connection,sql,replyId);
        return deleteHostReply;
    }

    /**
   * @author wk
   * @Description  根据回复id，查询相关联的作者回复
   * @Date 21:49 2022/3/25
   * @Param
   * @Return
   */

    @Override
    public List<HostReply> getHostReplyListByReplyId(Connection connection, Integer replyId) {
        String sql = "select host_reply_id id,host_reply_content content,host_reply_date hostReplyDate,user_basic_id,reply_id from t_host_reply where " +
                "reply_id = ?";
        List< HostReply> hostReplyList = getForList(connection, sql, replyId);
        return hostReplyList;
    }

    /**
    * @author wk
    * @Description 根据用户id，查询相关联的评论的回复列表
    * @Date 19:49 2022/4/4
    * @Param
    * @Return
    */

    @Override
    public List<HostReply> getHostReplyListByUserId(Connection connection, Integer userBasicId) {
        String sql = "select host_reply_id id,host_reply_content content,host_reply_date hostReplyDate,user_basic_id,reply_id from t_host_reply where " +
                "user_basic_id = ?";
        List<HostReply> hostReplyList = getForList(connection, sql, userBasicId);
        return hostReplyList;
    }

    /**
    * @author wk
    * @Description 根据回复id，查询相关联的用户id
    * @Date 20:21 2022/3/29
    * @Param
    * @Return
    */

    @Override
    public Integer getHostReplyUserId(Connection connection, Integer hostReplyId) {
        String sql = "select user_basic_id from t_host_reply where host_reply_id = ?";
        Integer hostReplyUserId = getValue(connection, sql, hostReplyId);
        return hostReplyUserId;
    }
}
