package com.resean.spring.beans.factory.support;

import com.resean.spring.beans.factory.BeanFactory;
import com.resean.spring.core.BeanException;

import java.util.ArrayList;
import java.util.List;

public interface AutoWriedCapableBeanFactory extends BeanFactory {

    int AUTOWIRE_NO = 0;
    int AUTOWIRE_BY_NAME = 1;
    int AUTOWIRE_BY_TYPE = 2;
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeanException;
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeanException;



}
