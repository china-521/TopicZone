package com.project.zone.service;

import com.project.zone.bean.Reply;
import com.project.zone.bean.Topic;

import java.sql.SQLException;
import java.util.List;

/**
 *  回复接口
 *
 * */
public interface ReplyService {


    /**
    * @author wk
    * @Description 添加评论
    * @Date 16:45 2022/3/27
    * @Param
    * @Return
    */

    public abstract Integer addReply(Reply reply) throws SQLException;

    /**
    * @author wk
    * @Description 根据评论id,删除评论
    * @Date 10:16 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteReply(Integer replyId) throws SQLException;

    /**
    * @author wk
    * @Description 根据用户id，删除评论
    * @Date 21:15 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteReplyByUserId(Integer userBasicId) throws SQLException;

    /**
    * @author wk
    * @Description 根据评论id，删除评论
    * @Date 19:56 2022/4/16
    * @Param
    * @Return
    */

    public abstract Integer deleteReplyByTopicId(Integer topicId) throws SQLException;


    /**
    * @author wk
    * @Description 根据用户id，获取评论信息列表
    * @Date 19:57 2022/4/4
    * @Param
    * @Return
    */

    public abstract List<Reply> getReplyListByUserId(Integer userBasicId) throws SQLException;

    /**
    * @author wk
    * @Description 根据评论id,获取对应文章id
    * @Date 10:09 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer getTopicIdByReplyId(Integer replyId) throws SQLException;

    /**
    * @author wk
    * @Description 根据日志，获取日志的回复
    * @Date 21:30 2022/3/25
    * @Param
    * @Return
    */

    public abstract List<Reply> getReplyList(Topic topic) throws SQLException;

    /**
    * @author wk
    * @Description 根据评论id，获取发布评论的用户的id
    * @Date 22:23 2022/3/27
    * @Param
    * @Return
    */

    public abstract Integer getReplyUserId(Integer id) throws SQLException;

}
