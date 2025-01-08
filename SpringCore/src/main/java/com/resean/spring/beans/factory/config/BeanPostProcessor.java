package com.resean.spring.beans.factory.config;

import com.resean.spring.beans.factory.support.AbstracAutoWriedCapableBeanFactory;
import com.resean.spring.core.BeanException;

public interface BeanPostProcessor {

    /**
     * 在bean初始化之前执行
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessBeforeInitialization(Object bean, String beanName)throws BeanException;

    /**
     * 在bean初始化之后执行
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessAfterInitialization(Object bean, String beanName)throws BeanException;

    void setBeanFactory(AbstracAutoWriedCapableBeanFactory beanFactory);

}
