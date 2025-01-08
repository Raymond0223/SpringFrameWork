package com.resean.spring.beans.factory.config;

import com.resean.spring.beans.factory.support.AbstracAutoWriedCapableBeanFactory;
import com.resean.spring.core.BeanException;

public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(AbstracAutoWriedCapableBeanFactory beanFactory) throws BeanException;
}
