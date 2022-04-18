package com.project.zone.service.impl;

import com.project.myssm.util.JDBCUtils;
import com.project.zone.bean.HostReply;
import com.project.zone.bean.UserBasic;
import com.project.zone.dao.HostReplyDAO;
import com.project.zone.service.HostReplyService;
import com.project.zone.service.UserBasicService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName HostReplyServiceImpl
 * @Description TODO
 * @Author wk
 * @Date 2022/3/25 21:45
 * @Version 1.0
 */
public class HostReplyServiceImpl implements HostReplyService {

    private HostReplyDAO hostReplyDAO = null;
    private UserBasicService userBasicService = null;

    /**
    * @author wk
    * @Description 添加评论的回复
    * @Date 14:46 2022/3/29
    * @Param
    * @Return
    */

    @Override
    public Integer addHostReply(HostReply hostReply) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer addHostReply = hostReplyDAO.addHostReply(connection, hostReply);
        return addHostReply;
    }

    /**
    * @author wk
    * @Description 删除评论的回复
    * @Date 11:08 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteHostReply(Integer hostReplyId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer deleteHostReply = hostReplyDAO.deleteHostReply(connection, hostReplyId);
        return deleteHostReply;
    }

    /**
    * @author wk
    * @Description 根据用户id，删除评论的回复
    * @Date 21:59 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteHostReplyByUserId(Integer userBasicId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer deleteHostReply = hostReplyDAO.deleteHostReplyByUserId(connection, userBasicId);
        return deleteHostReply;
    }

    /**
    * @author wk
    * @Description 根据评论id，删除评论的回复
    * @Date 20:05 2022/4/16
    * @Param
    * @Return
    */

    @Override
    public Integer deleteHostReplyByReplyId(Integer replyId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer deleteHostReply = hostReplyDAO.deleteHostReplyByReplyId(connection, replyId);
        return deleteHostReply;
    }

    /**
    * @author wk
    * @Description 根据评论id，获取用户回复
    * @Date 21:46 2022/3/25
    * @Param
    * @Return
    */

    @Override
    public List<HostReply> getHostReplyList(Integer replyId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        List<HostReply> hostReplyList = hostReplyDAO.getHostReplyListByReplyId(connection, replyId);
        Integer hostReplyUserId = null;
        UserBasic hostReplyUser = null;
        // 将发布回复的用户信息设置到 hostReply 类中
        if(!hostReplyList.isEmpty()){
            for(int i = 0;i < hostReplyList.size();i++){
                hostReplyUserId = getHostReplyUserId(hostReplyList.get(i).getId());
                hostReplyUser = userBasicService.getUserBasicById(hostReplyUserId);
                if(hostReplyUser != null){
                    hostReplyList.get(i).setAuthor(hostReplyUser);
                }
            }
        }
        return hostReplyList;
    }

    /**
    * @author wk
    * @Description 根据用户id，查询评论的回复列表
    * @Date 19:51 2022/4/4
    * @Param
    * @Return
    */

    @Override
    public List<HostReply> getHostReplyByUserId(Integer userBasicId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        List<HostReply> hostReplyList = hostReplyDAO.getHostReplyListByUserId(connection, userBasicId);
        return hostReplyList;
    }

    /**
    * @author wk
    * @Description 根据评论id，查询相关联的用户id
    * @Date 20:24 2022/3/29
    * @Param
    * @Return
    */

    @Override
    public Integer getHostReplyUserId(Integer replyId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer hostReplyUserId = hostReplyDAO.getHostReplyUserId(connection, replyId);
        return hostReplyUserId;
    }
}
