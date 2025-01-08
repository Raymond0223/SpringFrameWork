package com.resean.spring.context;

import com.resean.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.resean.spring.beans.factory.config.Environment;
import com.resean.spring.beans.factory.config.BeanPostProcessor;
import com.resean.spring.beans.factory.support.ConfigurableListableBeanFactory;
import com.resean.spring.core.BeanException;
import com.resean.spring.event.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractApplicationContext implements ApplicationContext {
    private Environment environment;

    private List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<>();

    private long startupDate;

    private final AtomicBoolean active = new AtomicBoolean();

    private final AtomicBoolean closed = new AtomicBoolean();

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Object getBean(String name) throws BeanException {
            return getBeanFactory().getBean(name);
    }

    public void setBeanFactoryPostProcessors(List<BeanFactoryPostProcessor> beanFactoryPostProcessors) {
        this.beanFactoryPostProcessors = beanFactoryPostProcessors;
    }

    @Override
    public void refresh() throws BeanException, IllegalStateException {
        postProcessBeanFactory(getBeanFactory());
        registerBeanPostProcessors(getBeanFactory());
        initApplicationEventPluslticaster();
        onRefresh();
        registerListeners();
        finishRefresh();
    }


    abstract void registerListeners();
    abstract void initApplicationEventPluslticaster();
    abstract void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory);
    abstract void registerBeanPostProcessors(ConfigurableListableBeanFactory configurableListableBeanFactory);

    abstract void onRefresh();

    abstract void finishRefresh();

    @Override
    public String getApplicationName() {
        return "";
    }

    @Override
    public long getStartupDate() {
        return this.startupDate;
    }

    @Override
    public abstract ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;

    @Override
    public void addBeanFactoryPostProcessor(BeanFactoryPostProcessor beanFactoryPostProcessor) {
        this.beanFactoryPostProcessors.add(beanFactoryPostProcessor);
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isActive() {
        return true;
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public ApplicationEventPublisher getApplicationEventPublisher() {
        return applicationEventPublisher;
    }
}
