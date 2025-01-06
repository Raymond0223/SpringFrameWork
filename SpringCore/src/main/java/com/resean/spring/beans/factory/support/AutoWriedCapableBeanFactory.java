package com.resean.spring.beans.factory.support;

import com.resean.spring.core.BeanException;

import java.util.ArrayList;
import java.util.List;

public class AutoWriedCapableBeanFactory extends AbstractBeanFactory{


    private final List<AutoWriedAnnotationBeanPostProcessor> processors=new ArrayList<>();

    public void addBeanPostProcessor(AutoWriedAnnotationBeanPostProcessor processor){
        processors.remove(processor);
        processors.add(processor);
    }

    public  int getProcessorsCount(){
        return processors.size();
    }

    public List<AutoWriedAnnotationBeanPostProcessor> getProcessors(){
        return processors;
    }



    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeanException {
        Object bean=existingBean;
        for (AutoWriedAnnotationBeanPostProcessor processor:processors){
            processor.setBeanFactory(this);
            bean=processor.postProcessBeforeInitialization(bean,beanName);
            if (bean==null){
                return null;
            }
        }
        return bean;
    }

    @Override
    public Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeanException {
        Object bean=existingBean;
        for (AutoWriedAnnotationBeanPostProcessor processor:processors){
            processor.setBeanFactory(this);
            bean=processor.postProcessAfterInitialization(bean,beanName);
            if (bean==null){
                return null;
            }
        }
        return bean;
    }




}
