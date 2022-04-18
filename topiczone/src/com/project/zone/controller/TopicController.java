package com.project.zone.controller;

import com.project.zone.bean.HostReply;
import com.project.zone.bean.Reply;
import com.project.zone.bean.Topic;
import com.project.zone.bean.UserBasic;
import com.project.zone.service.HostReplyService;
import com.project.zone.service.ReplyService;
import com.project.zone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TopicController
 * @Description 控制日志信息
 * @Author wk
 * @Date 2022/3/25 21:17
 * @Version 1.0
 */
public class TopicController {

    private TopicService topicService = null;
    private ReplyService replyService = null;
    private HostReplyService hostReplyService = null;

    /**
    * @author wk
    * @Description 日志详情界面
    * @Date 16:03 2022/3/26
    * @Param
    * @Return
    */

    public String topicDetail(Integer id,Integer visitId, HttpSession session) throws SQLException {
        Topic topic = topicService.getTopicById(id);
        // 日志
        session.setAttribute("topic",topic);
        // 用来记录评论的用户id
        session.setAttribute("visitId",visitId);
        return "detail";
    }


    /**
    * @author wk
    * @Description 携带作者id，进入发布日志页面
    * @Date 18:25 2022/3/26
    * @Param
    * @Return
    */

    public String publishPage(Integer id, HttpSession session){
        session.setAttribute("authorId",id);
        return "publish";
    }

    /**
     * @author wk
     * @Description 发布日志
     * @Date 16:07 2022/3/26
     * @Param
     * @Return
     */
    public String publish(String title, String content, HttpSession session) throws SQLException {
        Integer authorId = (Integer) session.getAttribute("authorId");
        UserBasic author = new UserBasic(authorId);

        // 对日期Date类的格式化和解析
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // 获取当前系统时间
        Date date = new Date();
        Topic topic = new Topic(title,content,date,author);
        Integer publish = topicService.publish(topic);
        if(publish > 0){
            return "redirect:user.do?choice=friend&id=" + authorId;
        }else {
            return "error";
        }
    }

    /**
    * @author wk
    * @Description 删除日志信息
    * @Date 22:44 2022/3/26
    * @Param
    * @Return
    */
    // 存在 bug 明日修改
    public String delete(Integer topicId,Integer authorId) throws SQLException {
        // 删除日志对应的评论
        Topic topic = topicService.getTopic(topicId);
        // 获取日志对应的评论
        List<Reply> replyList = replyService.getReplyList(topic);
        List<HostReply> hostReplyList = null;
        // 获取评论对应的回复
        for(int i = 0;i < replyList.size();i++){
           hostReplyList = hostReplyService.getHostReplyList(replyList.get(i).getId());
           if(hostReplyList != null){
                hostReplyService.deleteHostReplyByReplyId(replyList.get(i).getId());
           }
        }
        if(replyList != null){
            replyService.deleteReplyByTopicId(topicId);
        }
        // 删除日志
        Integer deleteTopic = topicService.deleteTopicById(topicId);
        if(deleteTopic > 0){
            return "redirect:user.do?choice=friend&id=" + authorId;
        }else {
            return "error";
        }
    }

}
