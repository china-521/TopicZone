package com.project.myssm.listener;


import com.project.myssm.ioc.BeanFactory;
import com.project.myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @ClassName ContextLoaderListener
 * @Description 监听上下文，在上下文启动的时候，创建IOC容器，然后将其保存到application作用域
 *              后面中央控制器在application作用域中获取 IOC 容器,进行属性注入
 * @Author wk
 * @Date 2022/3/20 16:34
 * @Version 1.0
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // 1. 获取ServletContext对象
        ServletContext servletContext = servletContextEvent.getServletContext();
        // 2. 获取上下文的初始化参数
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        // 3. 创建IOC容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(contextConfigLocation);
        // 4. 将IOC容器保存到application作用域
        servletContext.setAttribute("beanFactory",beanFactory);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
