package com.resean.spring.beans.factory.config;


/**
 * 单例bean 管理接口
 */
public interface SingletonBeanRegistry {

    void registrySingleton(String beanName,Object object);

    Object getSingleton(String beanName);

    boolean constainsSingleton(String beanName);

    String[] getSingletonBeanName();


}
