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
