package com.project.zone.dao.impl;

import com.project.zone.bean.Topic;
import com.project.zone.bean.UserBasic;
import com.project.zone.dao.TopicDAO;
import com.project.zone.dao.basedao.BaseDAO;

import java.sql.Connection;
import java.util.List;

/**
 * @ClassName TopicDAOImpl
 * @Description TopicDAO的实现类
 * @Author wk
 * @Date 2022/3/23 15:37
 * @Version 1.0
 */
public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {


    /**
    * @author wk
    * @Description 添加日志
    * @Date 13:54 2022/3/27
    * @Param
    * @Return
    */

    @Override
    public Integer addTopic(Connection connection, Topic topic) {
        String sql = "insert into t_topic(topic_title,topic_content,topic_publish_date,user_basic_id) values(?,?,?,?)";
        int addTopic = update(connection, sql, topic.getTitle(), topic.getContent(), topic.getPublishDate(), topic.getAuthor().getId());
        return addTopic;
    }

    /**
    * @author wk
    * @Description 根据用户获取日志列表(不带分页)
    * @Date 15:49 2022/3/23
    * @Param
    * @Return
    */

    @Override
    public List<Topic> getTopicList(Connection connection, UserBasic userBasic) {
        String sql = "select topic_id id, topic_title title,topic_content content,topic_publish_date publishDate from t_topic where user_basic_id = ?";
        List<Topic> topicList = getForList(connection, sql, userBasic.getId());
        return topicList;
    }

    /**
    * @author wk
    * @Description 根据用户和页码数获取日志列表，进行分页查询
    * @Date 13:35 2022/4/1
    * @Param
    * @Return
    */

    @Override
    public List<Topic> getTopicListByPage(Connection connection, UserBasic userBasic, Integer pageNumber) {
        String sql = "select topic_id id, topic_title title,topic_content content,topic_publish_date publishDate from t_topic where user_basic_id = ? limit " +
                "?,5";
        List<Topic> topicList = getForList(connection, sql, userBasic.getId(),(pageNumber - 1) * 5);
        return topicList;
    }

    /**
    * @author wk
    * @Description  根据日志id，删除日志信息
    * @Date 22:41 2022/3/26
    * @Param
    * @Return
    */

    @Override
    public Integer deleteTopicById(Connection connection, Integer id) {
        String sql = "delete from t_topic where topic_id = ?";
        int deleteTopic = update(connection, sql, id);
        return deleteTopic;
    }

    /**
    * @author wk
    * @Description 根据用户id，删除日志信息
    * @Date 21:18 2022/4/3
    * @Param
    * @Return
    */

    @Override
    public Integer deleteTopicByUserId(Connection connection, Integer userBasicId) {
        String sql = "delete from t_topic where user_basic_id = ?";
        Integer deleteTopic = update(connection, sql, userBasicId);
        return deleteTopic;
    }

    /**
    * @author wk
    * @Description 根据日志id，获取特定日志
    * @Date 21:21 2022/3/25
    * @Param
    * @Return
    */

    @Override
    public Topic getTopicById(Connection connection, Integer id) {
        String sql = "select topic_id id, topic_title title,topic_content content,topic_publish_date publishDate from t_topic where topic_id = ?";
        Topic topic = getInstance(connection, sql, id);
        return topic;
    }

    /**
    * @author wk
    * @Description 根据日志id,获取对应的作者 id
    * @Date 15:06 2022/3/27
    * @Param
    * @Return
    */

    @Override
    public Integer getAuthorId(Connection connection, Integer id) {
        String sql = "select user_basic_id from t_topic where topic_id = ?";
        Integer value = getValue(connection, sql, id);
        return value;
    }

    /**
    * @author wk
    * @Description 根据用户，查询用户的日志总数
    * @Date 17:15 2022/4/1
    * @Param
    * @Return
    */

    @Override
    public Long getTopicCount(Connection connection, UserBasic userBasic) {
        String sql = "select count(*) from t_topic where user_basic_id = ?";
        UserBasic user = userBasic;
        Integer id = user.getId();
        Long getTopicCount =  getValue(connection, sql, userBasic.getId());
        return getTopicCount;
    }
}
