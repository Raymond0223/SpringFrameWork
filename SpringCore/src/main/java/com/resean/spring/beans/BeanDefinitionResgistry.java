package com.resean.spring.beans;

/**
 * BeanDefinition 规范管理接口
 * 用于规范管理 BeanDefinition 的所有操作
 */
public interface BeanDefinitionResgistry {

    //注册
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    //移除
    void removeBeanDefinition(String beanName);
    //获取
    BeanDefinition getBeanDefinition(String beanName);
    //判断是否存在
    boolean containsBeanDefinition(String beanName);

}
