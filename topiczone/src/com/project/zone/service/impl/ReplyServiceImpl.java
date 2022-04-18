package com.project.zone.service.impl;

import com.project.myssm.util.JDBCUtils;
import com.project.zone.bean.HostReply;
import com.project.zone.bean.Reply;
import com.project.zone.bean.Topic;
import com.project.zone.bean.UserBasic;
import com.project.zone.dao.ReplyDAO;
import com.project.zone.service.HostReplyService;
import com.project.zone.service.ReplyService;
import com.project.zone.service.TopicService;
import com.project.zone.service.UserBasicService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ReplyServiceImpl
 * @Description TODO
 * @Author wk
 * @Date 2022/3/25 21:35
 * @Version 1.0
 */
public class ReplyServiceImpl implements ReplyService {
    private ReplyDAO replyDAO = null;
    private HostReplyService hostReplyService = null;
    private UserBasicService userBasicService = null;
    private TopicService topicService = null;

    /**
     * @author wk
     * @Description 添加评论
     * @Date 16:45 2022/3/27
     * @Param
     * @Return
     */

    @Override
    public Integer addReply(Reply reply) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer addReply = replyDAO.addReply(connection, reply);
        return addReply;
    }

    /**
    * @author wk
    * @Description 根据评论id,删除评论
    * @Date 10:17 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteReply(Integer replyId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer deleteReply = replyDAO.deleteReply(connection, replyId);
        return deleteReply;
    }

    /**
    * @author wk
    * @Description 根据用户id，删除评论
    * @Date 21:16 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteReplyByUserId(Integer userBasicId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer deleteReply = replyDAO.deleteReplyByUserId(connection, userBasicId);
        return deleteReply;
    }

    /**
    * @author wk
    * @Description 根据日志id，删除评论
    * @Date 19:57 2022/4/16
    * @Param
    * @Return
    */

    @Override
    public Integer deleteReplyByTopicId(Integer topicId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer deleteReply = replyDAO.deleteReplyByTopicId(connection, topicId);
        return deleteReply;
    }

    /**
    * @author wk
    * @Description 根据用户id，获取评论信息
    * @Date 20:02 2022/4/4
    * @Param
    * @Return
    */

    @Override
    public List<Reply> getReplyListByUserId(Integer userBasicId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        List<Reply> replyList = replyDAO.getReplyListByUserId(connection, userBasicId);
        return replyList;
    }

    /**
    * @author wk
    * @Description 根据评论id，获取对应日志id
    * @Date 10:10 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer getTopicIdByReplyId(Integer replyId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer topicId = replyDAO.getTopicIdByReplyId(connection, replyId);
        return topicId;
    }

    /**
     * @author wk
     * @Description 根据日志id，获取所有评论列表
     * @Date 16:44 2022/3/27
     * @Param
     * @Return
     */

    @Override
    public List<Reply> getReplyList(Topic topic) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        // 这里获取的replyList中的 user_basic_id（author） 和 topic_id(topic) 均为null
        List<Reply> replyList = replyDAO.getReplyList(connection, topic.getId());
        for (int i = 0; i < replyList.size(); i++) {
            Reply reply = replyList.get(i);
            // 获取 发布评论的用户信息
            Integer replyUserId = getReplyUserId(reply.getId());
            UserBasic replyUser = userBasicService.getUserBasicById(replyUserId);
            // 1. 将关联的用户设置进去
            reply.setAuthor(replyUser);
            // 2. 将关联的HostReply设置进去
            List<HostReply> hostReplyList = hostReplyService.getHostReplyList(reply.getId());
            reply.setHostReply(hostReplyList);
        }
        return replyList;
    }

    /**
     * @author wk
     * @Description 根据评论id，获取发布评论的用户id
     *
     * @Date 22:27 2022/3/27
     * @Param
     * @Return
     */

    @Override
    public Integer getReplyUserId(Integer id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer replyUserId = replyDAO.getReplyUserId(connection, id);
        return replyUserId;
    }
}
