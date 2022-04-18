package com.project.myssm.trans;



import com.project.myssm.util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName TransactionManager
 * @Description 进行事务的管理
 * @Author wk
 * @Date 2022/3/20 10:07
 * @Version 1.0
 */
public class TransactionManager {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();


    // 开启事务
    public static void beginTrans() throws SQLException {
        JDBCUtils.getConnection().setAutoCommit(false);
    }

    // 提交事务
    public static void commit() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        connection.commit();
        System.out.println("提交成功,开始关闭连接...");
        JDBCUtils.closeResource(connection,null,null);
    }

    // 回滚事务
    public static void rollback() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        connection.rollback();
        System.out.println("回滚成功，开始关闭连接...");
        JDBCUtils.closeResource(connection,null,null);
    }

}
