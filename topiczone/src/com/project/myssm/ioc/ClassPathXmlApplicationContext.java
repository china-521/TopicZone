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
 * @Description IOC 容器
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
                    // 到目前为止，此处需要注意的是，bean 和 bean 之间的依赖关系还没有设置
                }
            }
            // 5. 组装bean之间的依赖关系
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
