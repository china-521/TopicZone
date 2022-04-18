package com.project.zone.controller;

import com.project.zone.bean.HostReply;
import com.project.zone.bean.Reply;
import com.project.zone.bean.Topic;
import com.project.zone.bean.UserBasic;
import com.project.zone.service.HostReplyService;
import com.project.zone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Date;

/**
 * @ClassName ReplyController
 * @Description 控制评论
 * @Author wk
 * @Date 2022/3/28 19:56
 * @Version 1.0
 */
public class ReplyController {

    private ReplyService replyService = null;
    private HostReplyService hostReplyService = null;

    /**
     * @author wk
     * @Description 发布评论
     * @Date 16:38 2022/3/27
     * @Param
     * @Return
     */

    public String reply(String content, HttpSession session) throws SQLException {
        // 1. 发布评论的用户
        Integer visitId = (Integer)session.getAttribute("visitId");
        UserBasic userBasic = new UserBasic(visitId);
        // 2.获取被评论的文章信息
        Topic topic = (Topic) session.getAttribute("topic");
        // 3. 获取发布评论的时间
        Date date = new Date();
        Reply reply1 = new Reply(content,date,userBasic,topic);
        Integer addReply = replyService.addReply(reply1);
        if(addReply > 0){
            return "redirect:topic.do?choice=topicDetail&id=" + topic.getId() + "&visitId=" + visitId;
        }
        return "error";
    }

    /**
    * @author wk
    * @Description 删除评论
    * @Date 10:03 2022/4/3
    * @Param
    * @Return
    */

    public String deleteReply(Integer replyId, HttpSession session) throws SQLException {
        // 1. 删除评论的用户
        Integer visitId = (Integer)session.getAttribute("visitId");
        // 2. 获取评论对应的文章id
        Topic topic = (Topic)session.getAttribute("topic");
        //Integer topicId = replyService.getTopicIdByReplyId(replyId);
        // 3. 删除评论
        Integer deleteReply = replyService.deleteReply(replyId);
        if(deleteReply > 0){
            return "redirect:topic.do?choice=topicDetail&id=" + topic.getId() + "&visitId=" + visitId;
        }
        return "error";
    }


    /**
    * @author wk
    * @Description 发布作者回复
    * @Date 14:49 2022/3/29
    * @Param
    * @Return
    */

    public String hostReply(String content,Integer replyId, HttpSession session) throws SQLException {
        // 回复评论的作者信息
        Integer visitId = (Integer)session.getAttribute("visitId");
        UserBasic userBasic = new UserBasic(visitId);
        // 获取文章信息
        Topic topic = (Topic) session.getAttribute("topic");
        // 被回复的评论信息
        Reply reply = new Reply(replyId);
        // 发布日期
        Date date = new Date();
        HostReply hostReply = new HostReply(content, date, userBasic, reply);
        Integer addHostReply = hostReplyService.addHostReply(hostReply);
        if(addHostReply > 0){
            return "redirect:topic.do?choice=topicDetail&id=" + topic.getId() + "&visitId=" + visitId;
        }
        return null;
    }

    /**
    * @author wk
    * @Description 删除评论的回复
    * @Date 11:09 2022/4/3
    * @Param
    * @Return
    */

    public String deleteHostReply(Integer hostReplyId, HttpSession session) throws SQLException {
        // 1. 删除评论的用户
        Integer visitId = (Integer)session.getAttribute("visitId");
        // 2. 获取对应的文章id
        Topic topic = (Topic) session.getAttribute("topic");
        // 3. 删除评论的回复
        Integer deleteHostReply = hostReplyService.deleteHostReply(hostReplyId);
        if(deleteHostReply > 0){
            return "redirect:topic.do?choice=topicDetail&id=" + topic.getId() + "&visitId=" + visitId;
        }
        return "error";
    }
}
