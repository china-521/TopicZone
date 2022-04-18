package com.project.zone.dao;

import com.project.zone.bean.Reply;

import java.sql.Connection;
import java.util.List;

/**
 *  回复接口
 *
 * */
public interface ReplyDAO {

    /**
    * @author wk
    * @Description 添加评论
    * @Date 16:41 2022/3/27
    * @Param
    * @Return
    */

    public abstract Integer addReply(Connection connection,Reply reply);

    /**
    * @author wk
    * @Description 根据评论id,删除评论
    * @Date 10:14 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteReply(Connection connection ,Integer replyId);

    /**
    * @author wk
    * @Description 根据用户id，删除评论
    * @Date 21:13 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteReplyByUserId(Connection connection,Integer userBasicId);

    /**
    * @author wk
    * @Description 根据日志id，删除评论
    * @Date 19:45 2022/4/16
    * @Param
    * @Return
    */

    public abstract Integer deleteReplyByTopicId(Connection connection,Integer topicId);


    /**
    * @author wk
    * @Description 根据用户id，获取评论信息列表
    * @Date 19:56 2022/4/4
    * @Param
    * @Return
    */

    public abstract List<Reply> getReplyListByUserId(Connection connection,Integer userBasicId);

    /**
    * @author wk
    * @Description 根据评论id，获取对应的日志id
    * @Date 10:07 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer getTopicIdByReplyId(Connection connection,Integer replyId);

    /**
    * @author wk
    * @Description 根据日志id，获取关于当前日志的所有评论
    * @Date 21:37 2022/3/25
    * @Param
    * @Return
    */

    public  abstract List<Reply> getReplyList(Connection connection,Integer topicId);

    /**
    * @author wk
    * @Description 根据评论id，获取发布评论的用户id
    * @Date 22:25 2022/3/27
    * @Param
    * @Return
    */

    public abstract Integer getReplyUserId(Connection connection,Integer replyId);
}
