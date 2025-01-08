package com.resean.spring.beans.factory.support;

import com.resean.spring.beans.annotation.Autowired;
import com.resean.spring.beans.factory.BeanFactory;
import com.resean.spring.core.BeanException;

import java.lang.reflect.Field;


/**
 * 自动注入注解处理器
 */
public class AutoWriedAnnotationBeanPostProcessor implements BeanPostProcessor {


    private AbstracAutoWriedCapableBeanFactory beanFactory;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        Class<?> beanClass=bean.getClass();
        Object object=bean;
        Field[] fields=beanClass.getDeclaredFields();
        if (fields!=null){
            for (Field field:fields){
                if (field.isAnnotationPresent(Autowired.class)){
                    String fieldName=field.getName();
                    Object value=this.beanFactory.getBean(fieldName);

                    try {
                        field.setAccessible(true);
                        field.set(object,value);
                        System.out.println("autowire " + fieldName + " for bean " + beanName);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return object;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return null;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(AbstracAutoWriedCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
