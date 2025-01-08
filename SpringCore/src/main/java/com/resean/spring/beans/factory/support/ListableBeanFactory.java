package com.resean.spring.beans.factory.support;

import com.resean.spring.beans.factory.BeanFactory;

public interface ListableBeanFactory extends BeanFactory {

    boolean containsBeanDefinition(String beanName);
    int getBeanDefinitionCount();
    String[] getBeanDefinitionNames();
    String[] getBeanNamesForType(Class<?> type);

}
