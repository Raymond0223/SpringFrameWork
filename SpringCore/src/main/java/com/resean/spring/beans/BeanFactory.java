package com.resean.spring.beans;

import com.resean.spring.core.BeanException;

public interface BeanFactory {

    Object getBean(String beanName) throws BeanException;
    boolean containsBean(String benaNae);
    void registerBean(String beanName,Object object);
    boolean isSingleton(String beanName);
    boolean isPrototype(String beanName);
    Class<?> getType(String beanName);

    /*#############v2 #################*/
//    void registerBeanDefinition(BeanDefinition beanDefinition);

}
