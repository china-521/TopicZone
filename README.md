# TopicZone开发文档



## 一、 业务需求

* 用户登录
  * 登录成功，显示主界面。
* 用户注册
  * 如果没有账号则进入登录界面

* 忘记密码
  * 通过手机号找回密码

* 主界面
  * 登录成功后进入主界面
  * 左侧显示好友列表
    * 通过点击好友昵称，进入好友空间

  * 上端显示欢迎词和子级功能按键，如果不是自己的空间，则显示超链接，返回自己的空间；
  * 右侧显示日志列表
    * 日志列表采用分页形式展示，每五条记录为一次展示
    * 点击日志名称，进入日志详情界面
    * 点击删除选项，可以删除对应日志
    * 点击发布按钮，发布日志
    
  * 子级菜单
    * 退出登录
    * 个人中心
      * 可以设置自己的详细信息
      * 可以注销当前账号（注销后将清空账户所有信息，包括日志、评论、个人详细信息等）
      * 返回个人主页
      
    * 用户管理
      * 可以根据账号检索所有用户
      * 可以关注指定用户和取消关注
      * 关注列表分页展示
      * 用户列表分页展示
      * 点击用户昵称，进入用户主页
      * 点击关注用户昵称，进入关注用户主页
  
* 查看日志详情：
  * 日志本身的信息（作者头像、昵称、日志标题、日志内容、日志的日期）
  * 回复列表（回复者的头像、昵称、恢复内容、恢复日期）
  * 作者回复信息（回复者的头像、昵称、恢复内容、恢复日期）
* 发布日志、发布评论、发布评论回复
* 删除日志、删除评论、删除评论回复



## 二、用到的技术栈

* **开发工具**
  * IDEA
  * SQLyog
  * jdk1.8
  * Chrome浏览器

* **后端**
  * javaSE
  * JDBC
  * MySQL8.0
  * Tomcat8.5.76
  * IOC
  * MVC
  * Thymeleaf
  
* **前端**
  * JavaScript
  * HTML
  * CSS




## 三、底层逻辑结构

![image](https://user-images.githubusercontent.com/72770576/163805882-22cf3db6-780c-40d3-a5e9-91ca9e5834b2.png)





## 四、页面展示

* 登录菜单界面

![image](https://user-images.githubusercontent.com/72770576/163805915-cea3cc77-a1d2-4fa7-ada9-1ed4ec3ac804.png)

* 注册界面

![image](https://user-images.githubusercontent.com/72770576/163805938-9394e7e6-d4aa-46d0-aa69-79b0c33bcbd1.png)

* 主页

![image](https://user-images.githubusercontent.com/72770576/163805951-b3d2d9f7-dbd9-4b83-82e9-4851c546310a.png)

* 发布日志界面
![image](https://user-images.githubusercontent.com/72770576/163806505-9b956238-f8de-423b-ab74-d278bbca27c0.png)

* 日志详情界面

![image](https://user-images.githubusercontent.com/72770576/163805975-fa3a0793-48cb-4516-8ede-2103fcb024b4.png)

![image](https://user-images.githubusercontent.com/72770576/163805992-e915337c-b373-4f4e-9090-31e1270be1e2.png)

* 用户管理界面

![image](https://user-images.githubusercontent.com/72770576/163806011-c49c3aa7-2f70-4eb0-a209-3d64b1cf9fb7.png)



* 个人中心界面

![image](https://user-images.githubusercontent.com/72770576/163806022-4144f175-9f9b-46ae-b367-bb8bc27693b6.png)





## 五、运行结果展示

[个人日志系统功能在线演示（哔哩哔哩）](https://www.bilibili.com/video/BV1fi4y1U7cq/)



## 六、 数据库设计

### 6.1 数据库表的设计

* 抽取实体（矩形）：用户登录信息、用户详情信息、日志、回帖、主人回复
* 分析其中的属性：
  * 用户登录信息：账号、密码、头像、昵称
  * 用户的详情信息：真实姓名、星座、血型、邮箱、手机号、生日
  * 日志：标题、内容、日期、作者
  * 评论：内容、日期、作者、日志
  * 作者评论：内容、日期、作者、回复
* 分析实体之间的关系
  * 用户登录信息 ：用户详情信息      1 ：1
  * 用户   ： 日志                                  1 ：N
  * 日志   ： 回复                                  1 :   N
  * 回复   ：他人回复                           1 ： N
  * 用户   ：好友                                   M ：N
* 个人日志系统ER图

![image](https://user-images.githubusercontent.com/72770576/163806182-b32f51f1-0819-4ecf-a02a-161e2c6da5f1.png)

* 用户基础信息表(t_user_basic)

|    字段名     | 说明 |    类型     | 主键 | 外键 | 非空 | 唯一 | 自增 |
| :-----------: | :--: | :---------: | :--: | :--: | :--: | :--: | :--: |
| user_basic_id | 编号 |   int(11)   |  是  |  否  |  是  |  是  |  是  |
|   login_id    | 账号 | varchar(20) |  否  |  否  |  是  |  是  |  否  |
|   nick_name   | 昵称 | varchar(50) |  否  |  否  |  是  |  否  |  否  |
|   password    | 密码 | varcahr(20) |  否  |  否  |  是  |  否  |  否  |
|  head_image   | 头像 | varchar(50) |  否  |  否  |  否  |  否  |  否  |

* 用户详细信息表(t_user_detail)

|     字段名     |   说明   |    类型     | 主键 | 外键 | 非空 | 唯一 | 自增 |
| :------------: | :------: | :---------: | :--: | :--: | :--: | :--: | :--: |
| user_detail_id |   编号   |   int(11)   |  是  |  是  |  是  |  是  |  否  |
|   user_name    |   姓名   | varchar(20) |  否  |  否  |  否  |  否  |  否  |
|    user_sex    |   性别   | varchar(4)  |  否  |  否  |  否  |  否  |  否  |
| user_id_number | 身份证号 | varchar(18) |  否  |  否  |  否  |  是  |  否  |
|   user_phone   |  手机号  | varchar(11) |  否  |  否  |  否  |  是  |  否  |
|   user_email   |   邮箱   | varcahr(50) |  否  |  否  |  否  |  是  |  否  |
|   user_birth   |   生日   |  timestamp  |  否  |  否  |  否  |  否  |  否  |
|   user_star    |   星座   | varcahr(10) |  否  |  否  |  否  |  否  |  否  |

* 日志表(t_topic)

|       字段名       |   说明   |     类型     | 主键 | 外键 | 非空 | 唯一 | 自增 |
| :----------------: | :------: | :----------: | :--: | :--: | :--: | :--: | :--: |
|      topic_id      |   编号   |   int(11)    |  是  |  否  |  是  |  是  |  是  |
|    topic_title     |   标题   | varchar(50)  |  否  |  否  |  是  |  否  |  否  |
|   topic_content    |   内容   | varchar(500) |  否  |  否  |  是  |  否  |  否  |
| topic_publish_date | 发布时间 |  timestamp   |  否  |  否  |  是  |  否  |  否  |
|   user_basic_id    |   作者   |   int(11)    |  否  |  否  |  是  |  否  |  否  |

* 评论表(t_reply)

|    字段名     |      说明      |     类型     | 主键 | 外键 | 非空 | 唯一 | 自增 |
| :-----------: | :------------: | :----------: | :--: | :--: | :--: | :--: | :--: |
|   reply_id    |      编号      |   int(11)    |  是  |  否  |  是  |  是  |  是  |
| reply_content |    评论内容    | varchar(100) |  否  |  否  |  是  |  否  |  否  |
| user_basic_id | 发布评论的作者 |   int(11)    |  否  |  否  |  是  |  否  |  否  |
|  reply_date   |    评论时间    |  timestamp   |  否  |  否  |  是  |  否  |  否  |
|   topic_id    |  被评论的日志  |   int(11)    |  否  |  否  |  是  |  否  |  否  |

* 作者回复表(t_host_reply)

|       字段名       |      说明      |    类型     | 主键 | 外键 | 非空 | 唯一 | 自增 |
| :----------------: | :------------: | :---------: | :--: | :--: | :--: | :--: | :--: |
|   host_reply_id    |      编号      |   int(11)   |  是  |  否  |  是  |  是  |  是  |
| host_reply_content |    评论内容    | varchar(50) |  否  |  否  |  是  |  否  |  否  |
|   user_basic_id    | 发布评论的作者 |   int(11)   |  否  |  否  |  是  |  否  |  否  |
|  host_reply_date   |    评论时间    |  timestamp  |  否  |  否  |  是  |  否  |  否  |
|      reply_id      |  被回复的评论  |   int(11)   |  否  |  否  |  是  |  否  |  否  |

* 好友表(t_friends)

|   字段名   |  说明  |  类型   | 主键 | 外键 | 非空 | 唯一 | 自增 |
| :--------: | :----: | :-----: | :--: | :--: | :--: | :--: | :--: |
| friends_id |  编号  | int(11) |  是  |  否  |  是  |  是  |  是  |
|  user_id   | 用户id | int(11) |  否  |  否  |  否  |  否  |  否  |
| friend_id  | 好友id | int(11) |  否  |  否  |  否  |  否  |  否  |



### 6.3 数据库的范式：

* 第一范式：列不可再分
* 第二范式：一张表只表达一层含义（只描述一件事情）
* 第三范式：表中的每一列和主键都是直接依赖关系，而不是间接依赖

### 6.4 数据库范式的选择：

* 数据库设计的范式和数据库的查询性能很多时候是相悖的，我们需要根据实际的业务情况来进行选择
* 查询频次不高的情况下，我们更倾向于提高数据库的设计范式，从而提高存储效率
* 查询频次较高情况下，我们更倾向于牺牲数据库的规范度，降低数据库设计的范式，允许特定的冗余，从而提高查询的性能



## 七、部分核心代码示例

**==由于本系统涉及到的代码量较多，所以这里仅展示部分核心代码==**



### 7.1 BeanFactory 接口：用来根据 id 获取 bean 标签中 class 对应的实例化对象

``` java
package com.project.myssm.ioc;

public interface BeanFactory {

    /**
    * @author wk
    * @Description 根据id 获取某一个 bean标签中 class 对应的实例化对象
    * @Date 16:55 2022/3/19
    * @Param
    * @Return
    */

    Object getBean(String id);
}

```



### 7.2 简易版 IOC 容器：用来实现控制反转和依赖注入，降低代码的耦合度

``` java
package com.project.myssm.ioc;

import com.project.myssm.util.Tools;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ClassPathXmlApplicationContext
 * @Description IOC 容器的简易实现
 * @Author wk
 * @Date 2022/3/19 16:57
 * @Version 1.0
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    private Map<String, Object> beanMap = new HashMap<>();
    private String path = "applicationContext.xml";

    public ClassPathXmlApplicationContext(){
        this("applicationContext.xml");
    }

    public ClassPathXmlApplicationContext(String path) {
        if(Tools.isEmpty(path)){
            throw new RuntimeException("IOC容器配置文件获取失败...");
        }
        try {
            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(path);
            // 1. 创建 DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            // 2. 创建 DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            // 3. 创建Document 对象
            Document document = documentBuilder.parse(resourceAsStream);
            // 4. 获取所有的bean节点
            NodeList bean = document.getElementsByTagName("bean");
            for (int i = 0; i < bean.getLength(); i++) {
                Node itemNode = bean.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) itemNode;
                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");
                    Class beanClass = Class.forName(className);
                    // 动态创建 bean 的实例对象
                    Object beanObj = beanClass.newInstance();
                    // 将 bean 实例对象保存到map容器中
                    beanMap.put(beanId, beanObj);
                }
            }
            // 5. 组装bean之间的依赖关系(依赖注入)
            for(int i = 0; i < bean.getLength(); i++){
                Node itemNode = bean.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element) itemNode;
                    String beanId = beanElement.getAttribute("id");
                    NodeList beanChildNodeList = beanElement.getChildNodes();
                    for(int j = 0;j < beanChildNodeList.getLength();j++){
                        Node beanChildNode = beanChildNodeList.item(j);
                        if(beanChildNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())){
                            Element propertyElement = (Element) beanChildNode;
                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");
                            // 1.找到 propertyRef 对应的实例
                            Object refObj = beanMap.get(propertyRef);
                            // 2. 将refObj 设置到 当前bean对应的实例的property属性上去
                            Object beanObj = beanMap.get(beanId);
                            Class beanClazz = beanObj.getClass();
                            Field propertyField = beanClazz.getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj,refObj);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}

```



### 7.3 DispatcherServlet：中央控制器，用来向Controller层分发Servlet，进行视图处理，获取 IOC 容器，降低代码耦合度

``` java
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
            choice = "login";
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
```



### 7.4 ViewBaseServlet：用来渲染 thymeleaf 语法

``` java
package com.project.myssm.myspringmvc;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ViewBaseServlet extends HttpServlet {

    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {

        // 1.获取ServletContext对象
        ServletContext servletContext = this.getServletContext();

        // 2.创建Thymeleaf解析器对象
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);

        // 3.给解析器对象设置参数
        // ①HTML是默认模式，明确设置是为了代码更容易理解
        templateResolver.setTemplateMode(TemplateMode.HTML);

        // ②设置前缀
        String viewPrefix = servletContext.getInitParameter("view-prefix");

        templateResolver.setPrefix(viewPrefix);

        // ③设置后缀
        String viewSuffix = servletContext.getInitParameter("view-suffix");

        templateResolver.setSuffix(viewSuffix);

        // ④设置缓存过期时间（毫秒）
        templateResolver.setCacheTTLMs(60000L);

        // ⑤设置是否缓存
        templateResolver.setCacheable(true);

        // ⑥设置服务器端编码方式
        templateResolver.setCharacterEncoding("utf-8");

        // 4.创建模板引擎对象
        templateEngine = new TemplateEngine();

        // 5.给模板引擎对象设置模板解析器
        templateEngine.setTemplateResolver(templateResolver);

    }

    protected void processTemplate(String templateName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1.设置响应体内容类型和字符集
        resp.setContentType("text/html;charset=UTF-8");

        // 2.创建WebContext对象
        WebContext webContext = new WebContext(req, resp, getServletContext());

        // 3.处理模板数据
        templateEngine.process(templateName, webContext, resp.getWriter());
    }
}

```



### 7.5  TransactionManager：用来进行数据库的事务管理

``` java
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

```



### 7.6 CharacterEncodingFilter：设置请求字符集和响应字符集的过滤器

``` java
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

```



### 7.7 OpenSessionInViewFilter：进行数据库事务的处理的过滤器

``` java
package com.project.myssm.filter;


import com.project.myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
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
                response.sendRedirect("http://localhost:8080/qqzone/error.do?choice=error&exception=" + e.toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}

```



### 7.8 ContextLoaderListener：监听上下文，在上下文启动的时候，创建IOC容器，然后将其保存到application作用域

``` java
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

```

### 7.9 IOC 配置文件

``` xml
<?xml version="1.0" encoding="utf-8" ?>

<!--文档类型定义-->
<!DOCTYPE beans [
        <!ELEMENT beans (bean*)>
        <!ELEMENT bean (property*)>
        <!ELEMENT property (#PCDATA)>

        <!ATTLIST bean id ID #REQUIRED>
        <!ATTLIST bean class CDATA #IMPLIED>
        <!ATTLIST property name CDATA #IMPLIED>
        <!ATTLIST property ref IDREF #IMPLIED>
        ]>
<beans>

    <bean id="userBasicDAO" class="com.project.zone.dao.impl.UserBasicDAOImpl"/>
    <bean id="userDetailDAO" class="com.project.zone.dao.impl.UserDetailDAOImpl"></bean>
    <bean id="topicDAO" class="com.project.zone.dao.impl.TopicDAOImpl"/>
    <bean id="replyDAO" class="com.project.zone.dao.impl.ReplyDAOImpl"></bean>
    <bean id="hostReplyDAO" class="com.project.zone.dao.impl.HostReplyDAOImpl"></bean>

    <bean id="userBasicService" class="com.project.zone.service.impl.UserBasicServiceImpl">
        <property name="userBasicDAO" ref="userBasicDAO"></property>
    </bean>
    <bean id="userDetailService" class="com.project.zone.service.impl.UserDetailServiceImpl">
        <property name="userDetailDAO" ref="userDetailDAO"></property>
    </bean>
    <bean id="hostReplyService" class="com.project.zone.service.impl.HostReplyServiceImpl">
        <property name="hostReplyDAO" ref="hostReplyDAO"></property>
        <property name="userBasicService" ref="userBasicService"></property>
    </bean>
    <bean id="replyService" class="com.project.zone.service.impl.ReplyServiceImpl">
        <property name="replyDAO" ref="replyDAO"></property>
        <property name="hostReplyService" ref="hostReplyService"></property>
        <property name="userBasicService" ref="userBasicService"></property>
        <property name="topicService" ref="topicService"></property>
    </bean>
    <bean id="topicService" class="com.project.zone.service.impl.TopicServiceImpl">
        <property name="topicDAO" ref="topicDAO"></property>
        <property name="replyService" ref="replyService"></property>
        <property name="userBasicService" ref="userBasicService"></property>
    </bean>

    <bean id="user" class="com.project.zone.controller.UserController">
        <property name="userBasicService" ref="userBasicService"></property>
        <property name="userDetailService" ref="userDetailService"></property>
        <property name="topicService" ref="topicService"></property>
        <property name="replyService" ref="replyService"></property>
        <property name="hostReplyService" ref="hostReplyService"></property>
    </bean>

    <bean id="topic" class="com.project.zone.controller.TopicController">
        <property name="topicService" ref="topicService"></property>
        <property name="replyService" ref="replyService"></property>
        <property name="hostReplyService" ref="hostReplyService"></property>
    </bean>

    <bean id="reply" class="com.project.zone.controller.ReplyController">
        <property name="replyService" ref="replyService"></property>
        <property name="hostReplyService" ref="hostReplyService"></property>
    </bean>

    <bean id="error" class="com.project.zone.controller.ErrorController"/>
</beans>
```



## 八、在运行此项目时如果出现反射获取不到方法中的真实参数名的错误时，可以按照以下方法解决

[解决反射中无法获取发方法真实参数名问题](https://blog.csdn.net/m0_47214030/article/details/124086293?spm=1001.2014.3001.5501)



## 九、相关文章

[基于 JavaSE + MySQL+ JDBC 的学生信息管理系统(简易版)](https://blog.csdn.net/m0_47214030/article/details/123459996?spm=1001.2014.3001.5501)



## 十、最后的话

**点赞评论加关注，你的支持是我创作的动力**

![img](https://img-blog.csdnimg.cn/851df1413ed044778cd940113db4f54b.png#pic_center)
