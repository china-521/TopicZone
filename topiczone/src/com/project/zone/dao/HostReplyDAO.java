package com.project.zone.dao;

import com.project.zone.bean.HostReply;

import java.sql.Connection;
import java.util.List;

/**
 * @ClassName HostReplyDAO
 * @Description TODO
 * @Author wk
 * @Date 2022/3/25 21:47
 * @Version 1.0
 */
public interface HostReplyDAO {

    /**
    * @author wk
    * @Description 添加评论的回复
    * @Date 14:42 2022/3/29
    * @Param
    * @Return
    */

    public abstract Integer addHostReply(Connection connection, HostReply hostReply);

    /**
    * @author wk
    * @Description  根据回复id,删除评论的回复
    * @Date 11:04 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteHostReply(Connection connection,Integer hostReplyId);

    /**
    * @author wk
    * @Description 根据用户id，删除评论的回复
    * @Date 21:56 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteHostReplyByUserId(Connection connection,Integer userBasicId);

    /**
    * @author wk
    * @Description 根据评论id，删除评论的回复
    * @Date 20:02 2022/4/16
    * @Param
    * @Return
    */

    public abstract Integer deleteHostReplyByReplyId(Connection connection,Integer replyId);

    /**
    * @author wk
    * @Description 根据回复id，查询相关联的用户回复列表
    * @Date 21:47 2022/3/25
    * @Param
    * @Return
    */

    public abstract List<HostReply> getHostReplyListByReplyId(Connection connection, Integer replyId);

    /**
    * @author wk
    * @Description 根据用户id，查询相关联的用户回复列表
    * @Date 19:48 2022/4/4
    * @Param
    * @Return
    */

    public abstract List<HostReply> getHostReplyListByUserId(Connection connection,Integer userBasicId);

    /**
    * @author wk
    * @Description 根据回复id，查询相关联的用户id
    * @Date 20:20 2022/3/29
    * @Param
    * @Return
    */

    public abstract Integer getHostReplyUserId(Connection connection,Integer hostReplyId);

}
