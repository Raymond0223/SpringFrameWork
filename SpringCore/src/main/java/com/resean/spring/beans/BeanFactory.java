package com.resean.spring.beans;

public interface BeanFactory {

    Object getBean(String beanName);

    void registerBeanDefinition(BeanDefinition beanDefinition);

}
