package com.project.myssm.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * @ClassName CharacterEncodingFilter
 * @Description 设置编码过滤器
 * @Author wk
 * @Date 2022/3/19 22:22
 * @Version 1.0
 */
@WebFilter(urlPatterns = "*.do",initParams = {
    @WebInitParam(name="requestEncoding",value="UTF-8"),
        @WebInitParam(name="responseEncoding",value="text/html;UTF-8")
})
public class CharacterEncodingFilter implements Filter {

    private String requestEncoding = "UTF-8"; // 默认的编码方式
    private String responseEncoding = "text/html;UTF-8";

    @Override
    public void init(FilterConfig filterConfig) {
        // 读取编码方式
        String requestEncoding = filterConfig.getInitParameter("requestEncoding");
        String responseEncoding = filterConfig.getInitParameter("responseEncoding");
        // 如果能够读取到编码方式，则修改默认的编码方式
        if(requestEncoding != null){
            this.requestEncoding = requestEncoding;
        }
        if(responseEncoding != null){
            this.responseEncoding = responseEncoding;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(requestEncoding);
        servletResponse.setContentType(responseEncoding);
        // 放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
