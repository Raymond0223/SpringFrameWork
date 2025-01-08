package com.resean.spring.context;

import com.resean.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.resean.spring.beans.factory.config.Environment;
import com.resean.spring.beans.factory.config.EnvironmentCapable;
import com.resean.spring.beans.factory.config.BeanPostProcessor;
import com.resean.spring.beans.factory.support.ConfigurableBeanFactory;
import com.resean.spring.beans.factory.support.ConfigurableListableBeanFactory;
import com.resean.spring.beans.factory.support.ListableBeanFactory;
import com.resean.spring.core.BeanException;
import com.resean.spring.event.ApplicationEventPublisher;

public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, ConfigurableBeanFactory, ApplicationEventPublisher {

    String getApplicationName();

    long getStartupDate();

    ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;

    void setEnvironment(Environment environment);

    Environment getEnvironment();

    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor beanFactoryPostProcessor);

    void refresh() throws BeanException, IllegalStateException;

    void close();

    boolean isActive();

}
