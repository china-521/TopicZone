package com.project.zone.dao;

import com.project.zone.bean.Topic;
import com.project.zone.bean.UserBasic;

import java.sql.Connection;
import java.util.List;

/**
 * 日志管理接口
 * */
public interface TopicDAO {

    /**
    * @author wk
    * @Description 添加日志信息
    * @Date 15:25 2022/3/26
    * @Param
    * @Return
    */

    public abstract Integer addTopic(Connection connection,Topic topic);


    /**
    * @author wk
    * @Description 根据用户，获取日志列表
    * @Date 15:49 2022/3/23
    * @Param
    * @Return
    */

    public abstract List<Topic> getTopicList(Connection connection, UserBasic userBasic);

    /**
    * @author wk
    * @Description 根据用户，和页面，进行分页查询
    * @Date 13:34 2022/4/1
    * @Param
    * @Return
    */

    public abstract List<Topic> getTopicListByPage(Connection connection, UserBasic userBasic,Integer pageNumber);


    /**
    * @author wk
    * @Description 根据日志 id 获取特定日志信息
    * @Date 21:20 2022/3/25
    * @Param
    * @Return
    */

    public abstract Topic getTopicById(Connection connection,Integer id);

    /**
    * @author wk
    * @Description 根据日志 id ，删除对应日志信息
    * @Date 22:40 2022/3/26
    * @Param
    * @Return
    */

    public abstract Integer deleteTopicById(Connection connection,Integer id);

    /**
    * @author wk
    * @Description 根据用户id，删除日志信息
    * @Date 21:17 2022/4/3
    * @Param
    * @Return
    */

    public abstract Integer deleteTopicByUserId(Connection connection,Integer userBasicId);

    /**
    * @author wk
    * @Description 根据日志id，获取对应的作者id
    * @Date 15:07 2022/3/27
    * @Param
    * @Return
    */

    public abstract Integer getAuthorId(Connection connection,Integer id);

    /**
    * @author wk
    * @Description 根据用户，查询该用户的日志总数
    * @Date 17:13 2022/4/1
    * @Param
    * @Return
    */

    public abstract Long getTopicCount(Connection connection,UserBasic userBasic);

}
