package com.project.zone.service;

import com.project.zone.bean.Topic;
import com.project.zone.bean.UserBasic;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *  日志管理业务模型接口
 *
 * */
public interface TopicService {

    /**
    * @author wk
    * @Description 发布日志
    * @Date 16:05 2022/3/26
    * @Param
    * @Return
    */

    public abstract Integer publish(Topic topic) throws SQLException;

    /**
    * @author wk
    * @Description 查询特定用户的日志列表
    * @Date 16:19 2022/3/23
    * @Param
    * @Return
    */

    public abstract List<Topic> getTopicList(UserBasic userBasic) throws SQLException, ParseException;

    /**
    * @author wk
    * @Description 根据用户和页码数获取日志列表，进行分页查询
    * @Date 15:53 2022/4/1
    * @Param
    * @Return
    */

    public abstract List<Topic> getTopicListByPage(UserBasic userBasic,Integer pageNumber) throws SQLException;

    /**
    * @author wk
    * @Description 根据日志id查询特定日志
    * @Date 21:19 2022/3/25
    * @Param
    * @Return
    */

    public abstract Topic getTopicById(Integer topicId) throws SQLException;

    /**
    * @author wk
    * @Description 根据日志id，删除日志信息
    * @Date 22:42 2022/3/26
    * @Param
    * @Return
    */

    public abstract Integer deleteTopicById(Integer id) throws SQLException;

    /**
    * @author wk
    * @Description 根据用户id，删除日志信息
    * @Date 21:19 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteTopicByUserId(Integer userBasicId) throws SQLException;

    /**
    * @author wk
    * @Description  根据 id获取指定的topic信息，包含这个topic关联的作者信息
    * @Date 11:22 2022/3/27
    * @Param
    * @Return
    */

    public abstract Topic getTopic(Integer topicId) throws SQLException;

    /**
    * @author wk
    * @Description 根据用户，查询该用户的日志总数
    * @Date 17:16 2022/4/1
    * @Param
    * @Return
    */

    public abstract Long getTopicCount(UserBasic userBasic) throws SQLException;
}
