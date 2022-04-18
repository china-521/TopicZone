package com.project.zone.service;

import com.project.zone.bean.HostReply;

import java.sql.SQLException;
import java.util.List;

public interface HostReplyService {

    /**
    * @author wk
    * @Description 添加评论的回复
    * @Date 14:46 2022/3/29
    * @Param
    * @Return
    */

    public abstract Integer addHostReply(HostReply hostReply) throws SQLException;

    /**
    * @author wk
    * @Description 根据回复id，删除评论的回复
    * @Date 11:07 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteHostReply(Integer hostReplyId) throws SQLException;

    /**
    * @author wk
    * @Description 根据用户id，删除评论的回复
    * @Date 21:58 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteHostReplyByUserId(Integer userBasicId) throws SQLException;

    /**
    * @author wk
    * @Description 根据评论id，删除评论的回复
    * @Date 20:04 2022/4/16
    * @Param
    * @Return
    */

    public abstract Integer deleteHostReplyByReplyId(Integer replyId) throws SQLException;

    /**
    * @author wk
    * @Description 根据评论id，获取用户回复列表
    * @Date 21:45 2022/3/25
    * @Param
    * @Return
    */

    public abstract List<HostReply> getHostReplyList(Integer replyId) throws SQLException;

    /**
    * @author wk
    * @Description 根据用户id，获取用户回复列表
    * @Date 19:50 2022/4/4
    * @Param
    * @Return
    */

    public abstract List<HostReply> getHostReplyByUserId(Integer userBasicId) throws SQLException;

    /**
    * @author wk
    * @Description 根据回复di，查询相关联的用户id
    * @Date 20:24 2022/3/29
    * @Param
    * @Return
    */

    public  abstract Integer getHostReplyUserId(Integer replyId) throws SQLException;
}
