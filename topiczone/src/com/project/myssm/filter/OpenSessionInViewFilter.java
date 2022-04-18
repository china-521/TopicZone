package com.project.myssm.filter;


import com.project.myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @ClassName OpenSessionInViewFilter
 * @Description 过滤器
 * @Author wk
 * @Date 2022/3/20 10:06
 * @Version 1.0
 */
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            System.out.println("开启事务");
            TransactionManager.beginTrans();
            filterChain.doFilter(servletRequest,servletResponse);
            TransactionManager.commit();
            System.out.println("提交事务");
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("回滚事务");
                System.out.println("error:-->" + e.getMessage());
                System.out.println("error:-->" + e.getLocalizedMessage());
                TransactionManager.rollback();
                // 跳转到错误页面
                HttpServletResponse response = (HttpServletResponse)servletResponse;
                response.sendRedirect("http://localhost:8080/topiczone/error.do?choice=error&exception=" + e.toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
