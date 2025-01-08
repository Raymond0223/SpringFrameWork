package com.resean.spring.beans.factory.support;

import com.resean.spring.beans.factory.BeanFactory;
import com.resean.spring.beans.factory.config.SingletonBeanRegistry;

public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    int getBeanPostprocessorCount();
    void registerDependentBean(String beanName,String dependentBeanName);
    String[] getDependentBeans(String beanName);
    String[] getDependenciesForBean(String beanName);


}
