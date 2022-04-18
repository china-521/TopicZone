package com.project.zone.service.impl;

import com.project.myssm.util.JDBCUtils;
import com.project.zone.bean.Reply;
import com.project.zone.bean.Topic;
import com.project.zone.bean.UserBasic;
import com.project.zone.dao.impl.TopicDAOImpl;
import com.project.zone.service.ReplyService;
import com.project.zone.service.TopicService;
import com.project.zone.service.UserBasicService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName TopicServiceImpl
 * @Description TODO
 * @Author wk
 * @Date 2022/3/23 16:19
 * @Version 1.0
 */
public class TopicServiceImpl implements TopicService {

    private TopicDAOImpl topicDAO = null;
    private ReplyService replyService = null;
    private UserBasicService userBasicService = null;

    /**
    * @author wk
    * @Description 发布日志
    * @Date 16:05 2022/3/26
    * @Param
    * @Return
    */

    @Override
    public Integer publish(Topic topic) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer addTopic = topicDAO.addTopic(connection, topic);
        return addTopic;
    }

    /**
    * @author wk
    * @Description 查询特定用户的日志列表
    * @Date 16:20 2022/3/23
    * @Param
    * @Return
    */

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) throws SQLException{
        Connection connection = JDBCUtils.getConnection();
        // 当前查询到的topic中的作者信息为null，因为我们查询到的author是一个Integer类型，无法赋值给UserBasic类型(在topic中的author属性是UserBasic类型)
        List<Topic> topicList = topicDAO.getTopicList(connection, userBasic);
        // 我们可以通过当前用户id，查询到当前用户信息，将其设置到Topic类中的author属性上
        UserBasic userBasicObj = userBasicService.getUserBasicById(userBasic.getId());
        for(int i = 0;i< topicList.size();i++){
            topicList.get(i).setAuthor(userBasicObj);
        }
        return topicList;
    }

    /**
    * @author wk
    * @Description 根据用户和页码数获取日志列表，进行分页查询
    * @Date 15:54 2022/4/1
    * @Param
    * @Return
    */

    @Override
    public List<Topic> getTopicListByPage(UserBasic userBasic, Integer pageNumber) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        List<Topic> topicListByPage = topicDAO.getTopicListByPage(connection, userBasic, pageNumber);
        UserBasic userBasicObj = userBasicService.getUserBasicById(userBasic.getId());
        for(int i = 0;i < topicListByPage.size();i++){
            // 将对应的作者信息设置到文章中
            topicListByPage.get(i).setAuthor(userBasicObj);
        }
        return topicListByPage;
    }


    /**
    * @author wk
    * @Description 根据日志 id获取指定的 topic 信息，包含这个topic关联的作者信息
    * @Date 11:15 2022/3/27
    * @Param
    * @Return
    */
    @Override
    public Topic getTopic(Integer topicId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        // 当前查询到的topic中的作者信息为null，因为我们查询到的author是一个Integer类型，无法赋值给UserBasic类型(在topic中的author属性时UserBasic类型)
        Topic topic = topicDAO.getTopicById(connection,topicId);
        // 当前 author为null，我们通过日志id，查询到作者id，然后通过作者id，查询到作者信息(对象),将其设置到Topic类中的author属性上
        Integer authorId = topicDAO.getAuthorId(connection,topicId);
        UserBasic author = userBasicService.getUserBasicById(authorId);
        topic.setAuthor(author);
        return topic;
    }

    /**
    * @author wk
    * @Description 根据日志id，获取特定日志回复信息
    * @Date 21:22 2022/3/25
    * @Param
    * @Return
    */

    @Override
    public Topic getTopicById(Integer topicId) throws SQLException {
        Topic topic = getTopic(topicId);
        List<Reply> replyList = replyService.getReplyList(topic);
        topic.setReplyList(replyList);
        return topic;
    }

    /**
    * @author wk
    * @Description 根据日志id删除日志信息
    * @Date 11:12 2022/3/27
    * @Param
    * @Return
    */

    @Override
    public Integer deleteTopicById(Integer id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer deleteTopic = topicDAO.deleteTopicById(connection, id);
        return deleteTopic;
    }

    /**
    * @author wk
    * @Description 根据用户id，删除日志信息
    * @Date 21:20 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteTopicByUserId(Integer userBasicId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Integer deleteTopic = topicDAO.deleteTopicByUserId(connection, userBasicId);
        return deleteTopic;
    }

    /**
    * @author wk
    * @Description 根据用户，获取该用户的日志总数
    * @Date 17:17 2022/4/1
    * @Param
    * @Return
    */

    @Override
    public Long getTopicCount(UserBasic userBasic) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Long topicCount = topicDAO.getTopicCount(connection, userBasic);
        return topicCount;
    }
}
