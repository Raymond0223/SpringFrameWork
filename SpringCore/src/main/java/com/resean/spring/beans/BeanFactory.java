package com.resean.spring.beans;

import com.resean.spring.core.BeanException;

public interface BeanFactory {

    Object getBean(String beanName) throws BeanException;

    boolean containsBean(String benaNae);

    void registerBean(String beanName,Object object);

    /*#############v2 #################*/
//    void registerBeanDefinition(BeanDefinition beanDefinition);

}
