package com.project.zone.dao.impl;

import com.project.zone.bean.Reply;
import com.project.zone.dao.ReplyDAO;
import com.project.zone.dao.basedao.BaseDAO;

import java.sql.Connection;
import java.util.List;

/**
 * @ClassName ReplyDAOImpl
 * @Description TODO
 * @Author wk
 * @Date 2022/3/25 21:37
 * @Version 1.0
 */
public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {

    /**
     * @author wk
     * @Description 添加评论
     * @Date 16:42 2022/3/27
     * @Param
     * @Return
     */

    @Override
    public Integer addReply(Connection connection, Reply reply) {
        String sql = "insert into t_reply(reply_content,reply_date,user_basic_id,topic_id) values(?,?,?,?)";
        int addReply = update(connection, sql, reply.getContent(), reply.getReplyDate(), reply.getAuthor().getId(), reply.getTopic().getId());
        return addReply;
    }

    /**
    * @author wk
    * @Description 根据评论id,删除评论
    * @Date 10:14 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteReply(Connection connection, Integer replyId) {
        String sql = "delete from t_reply where reply_id = ?";
        Integer deleteReply = update(connection, sql, replyId);
        return deleteReply;
    }

    /**
    * @author wk
    * @Description 根据用户id，删除评论
    * @Date 21:13 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteReplyByUserId(Connection connection, Integer userBasicId) {
        String sql = "delete from t_reply where user_basic_id = ?";
        Integer deleteReply = update(connection, sql, userBasicId);
        return deleteReply;
    }

    /**
    * @author wk
    * @Description 根据日志id，删除评论
    * @Date 19:46 2022/4/16
    * @Param
    * @Return
    */

    @Override
    public Integer deleteReplyByTopicId(Connection connection, Integer topicId) {
        String sql = "delete from t_reply where topic_id = ?";
        Integer deleteReply = update(connection, sql, topicId);
        return deleteReply;
    }


    /**
    * @author wk
    * @Description 根据评论id，获取对应的日志id
    * @Date 10:07 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer getTopicIdByReplyId(Connection connection, Integer replyId) {
        String sql = "select topic_id from t_reply where reply_id = ?";
        Integer topicId = getValue(connection, sql, replyId);
        return topicId;
    }


    /**
     * @author wk
     * @Description 根据日志id，获取关于当前日志的所有评论
     * @Date 21:38 2022/3/25
     * @Param
     * @Return
     */

    @Override
    public List<Reply> getReplyList(Connection connection, Integer topicId) {
        String sql = "select reply_id id,reply_content content,reply_date replyDate from t_reply where topic_id = ?";
        List<Reply> replyList = getForList(connection, sql, topicId);
        return replyList;
    }

    /**
    * @author wk
    * @Description 根据用户id，获取评论信息
    * @Date 19:55 2022/4/4
    * @Param
    * @Return
    */

    @Override
    public List<Reply> getReplyListByUserId(Connection connection, Integer userBasicId) {
        String sql = "select reply_id id,reply_content content,reply_date replyDate from t_reply where user_basic_id = ?";
        List<Reply> replyList = getForList(connection, sql, userBasicId);
        return replyList;
    }

    /**
     * @author wk
     * @Description 根据评论id，获取发布评论的用户id
     * @Date 15:18 2022/3/28
     * @Param
     * @Return
     */

    @Override
    public Integer getReplyUserId(Connection connection, Integer replyId) {
        String sql = "select user_basic_id from t_reply where reply_id = ?";
        Integer replyUserId = getValue(connection, sql, replyId);
        return replyUserId;
    }
}
