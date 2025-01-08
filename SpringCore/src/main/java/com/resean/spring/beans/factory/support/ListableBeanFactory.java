package com.resean.spring.beans.factory.support;

import com.resean.spring.beans.factory.BeanFactory;
import com.resean.spring.core.BeanException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    boolean containsBeanDefinition(String beanName);
    int getBeanDefinitionCount();
    String[] getBeanDefinitionNames();
    String[] getBeanNamesForType(Class<?> type);
    <T> Map<String,T> getBeansOfType(Class<T> type) throws BeanException;
}
