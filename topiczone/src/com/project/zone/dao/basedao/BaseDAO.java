package com.project.zone.dao.basedao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName BaseDAO
 * @Description 封装了针对于数据表的通用的操作
 * @Author wk
 * @Date 2022/3/2 21:06
 * @Version 1.0
 */
public abstract class BaseDAO<T> {

    private Class<T> clazz = null;

    // 获取当前BaseDAO的子类继承的父类的泛型
    {
        // 获取当前BaseDAO的子类继承的父类中的泛型
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;

        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();// 获取了父类的泛型数组
        clazz = (Class<T>) actualTypeArguments[0];// 泛型的第一个参数
    }

    /**
     * @author wk
     * @Description 通用的增删改操作
     * @Date 21:34 2022/3/2
     * @Param
     * @Return
     */
    public int update(Connection connection, String sql, Object... args) {
        try {
            QueryRunner queryRunner = new QueryRunner();
            int update = queryRunner.update(connection, sql, args);
            return update;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("BaseDAO层的update出现问题");
        }
    }

    /**
     * @author wk
     * @Description 通用的查询操作，返回一条数据
     * @Date 21:41 2022/3/2
     * @Param
     * @Return
     */
    public T getInstance(Connection connection, String sql, Object... args) {
        try {
            QueryRunner queryRunner = new QueryRunner();
            BeanHandler<T> studentBeanHandler = new BeanHandler<>(clazz);
            T t = queryRunner.query(connection, sql, studentBeanHandler, args);
            return t;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("BaseDAO层的getInstance出现问题");
        }
    }

    /**
     * @author wk
     * @Description 通用的查询操作，返回多条数据
     * @Date 21:48 2022/3/2
     * @Param
     * @Return
     */

    public List<T> getForList(Connection connection, String sql, Object... args) {
        try {
            QueryRunner queryRunner = new QueryRunner();
            BeanListHandler<T> studentBeanListHandler = new BeanListHandler<T>(clazz);
            List<T> list = queryRunner.query(connection, sql, studentBeanListHandler, args);
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("BaseDAO层的getForList出现问题");
        }
    }


    /**
     * @author wk
     * @Description 通用的查询特殊值的方法
     * @Date 21:53 2022/3/2
     * @Param
     * @Return
     */

    public <E> E getValue(Connection connection, String sql, Object... args) {
        try {
            QueryRunner queryRunner = new QueryRunner();
            ScalarHandler scalarHandler = new ScalarHandler();
            Object value = queryRunner.query(connection, sql, scalarHandler, args);
            return (E) value;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("BaseDAO层的getValue出现问题");
        }
    }
}
