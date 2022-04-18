package com.project.myssm.myspringmvc;


import com.project.myssm.ioc.BeanFactory;
import com.project.myssm.util.Tools;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @ClassName DispatcherServlet
 * @Description 中央控制器
 * @Author wk
 * @Date 2022/3/16 22:11
 * @Version 1.0
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    public DispatcherServlet() {
    }

    public void init() throws ServletException {
        super.init();
        // 之前是在此处主动创建IOC容器的
        // 现在优化为从application作用域去获取

        //beanFactory = new ClassPathXmlApplicationContext();
        ServletContext servletContext = getServletContext();
        Object beanFactoryObj = servletContext.getAttribute("beanFactory");
        if (beanFactoryObj != null) {
            beanFactory = (BeanFactory) beanFactoryObj;
        } else {
            throw new RuntimeException("IOC容器获取失败");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String servletPath = req.getServletPath();
        servletPath = servletPath.replaceAll("/", "");
        servletPath = servletPath.replaceAll(".do", "");

        // 根据 servletPath 获取 存储在Map集合中 对应的 controller 处理类
        Object controllerBeanObj = beanFactory.getBean(servletPath);

        // 获取操作方式
        String choice = req.getParameter("choice");
        if (Tools.isEmpty(choice)) {
            choice = "intoLogin";
        }
        try {
            // 获取所有的方法
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            if (methods != null && methods.length > 0) {
                for (Method method : methods) {
                    if (choice.equals(method.getName())) {
                        // 1. 统一获取请求参数
                        // 获取当前方法的参数，返回行参数组
                        Parameter[] parameters = method.getParameters();
                        // parameterValues 用来承载参数的值
                        Object[] parameterValues = new Object[parameters.length];
                        for (int i = 0; i < parameters.length; i++) {
                            Parameter parameter = parameters[i];
                            // 获取形参名称
                            String parameterName = parameter.getName();
                            // 如果参数名是request，response，session，那么就不是通过请求中获取的
                            if ("req".equals(parameterName) || "request".equals(parameterName)) {
                                parameterValues[i] = req;
                            } else if ("resp".equals(parameterName) || "response".equals(parameterName)) {
                                parameterValues[i] = resp;
                            } else if ("session".equals(parameterName)) {
                                parameterValues[i] = req.getSession();
                            } else {
                                // 从请求中获取参数值
                                String parameterValue = req.getParameter(parameterName);
                                String typeName = parameter.getType().getName();

                                Object parameterObj = parameterValue;

                                if (parameterObj != null) {
                                    if ("java.lang.Integer".equals(typeName)) {
                                        parameterObj = Integer.parseInt(parameterValue);
                                    } else if ("java.lang.Double".equals(typeName)) {
                                        parameterObj = Double.parseDouble(parameterValue);
                                    } else if("java.lang.Float".equals(typeName)){
                                        parameterObj = Float.parseFloat(parameterValue);
                                    } else {
                                        parameterObj = parameterValue;
                                    }
                                }
                                parameterValues[i] = parameterObj;  // 方法形参类型不一定是String类型，所以要对parameterValue进行处理
                            }
                        }
                        // 2. controller组件中的方法调用
                        method.setAccessible(true);
                        Object methodReturnObj = method.invoke(controllerBeanObj, parameterValues);
                        // 3. 视图处理
                        if (methodReturnObj != null) {
                            String methodReturnStr = (String) methodReturnObj;
                            if (methodReturnStr.startsWith("redirect:")) {            // 比如：redirect:user.do
                                String redirect = methodReturnStr.substring("redirect:".length());      // user.do
                                resp.sendRedirect(redirect);   // 重定向

                            } else {
                                super.processTemplate(methodReturnStr, req, resp); // thymeleaf 渲染页面 （比如edit页面，error页面等）
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DispatcherException("Dispatcher层出现问题了");
        }
    }
}