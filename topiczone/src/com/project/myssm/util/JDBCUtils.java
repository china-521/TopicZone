package com.project.myssm.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @ClassName JDBCUtils
 * @Description TODO
 * @Author wk
 * @Date 2022/2/24 20:31
 * @Version 1.0
 */
public class JDBCUtils {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    /**
    * @author wk
    * @Description 使用Druid数据库连接池技术
    * @Date 20:38 2022/3/1
    * @Param
    * @Return
    */
    private static DataSource source1;
    static{
        try {
            Properties properties = new Properties();
            //InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");

            // 加载配置文件
            properties.load(resourceAsStream);

            source1 = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection == null){
            connection = source1.getConnection();
            threadLocal.set(connection);
        }
        return threadLocal.get();
    }


    /**
     * @author wk
     * @Description 获取数据库的连接
     * @Date 20:40 2022/2/24
     * @Param
     * @Return
     */

    public static Connection getConnection1() throws Exception {
        // 获取数据库连接

        // 1. 读取配置文件中的四个基本信息
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driverClass = properties.getProperty("driverClass");

        // 2. 加载驱动
        Class.forName(driverClass);

        // 3. 获取连接
        Connection connection = DriverManager.getConnection(url, user, password);

        return connection;
    }

    /**
     * @author wk
     * @Description 关闭连接 和 Statement的操作
     * @Date 20:39 2022/2/24
     * @Param
     * @Return
     */
    public static void closeResource(Connection connection, Statement ps, ResultSet resultSet) throws SQLException {
        if(connection == null){
            return;
        }
        // 如果数据库连接没有关闭
        if(!connection.isClosed()){
            System.out.println("数据库连接没有关闭");
            // 7.关闭资源
            DbUtils.closeQuietly(connection);
            //threadLocal.set(null);
            threadLocal.remove();
            System.out.println("数据库连接成功关闭");
        }
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(resultSet);
    }

}
