package com.resean.spring.beans.factory.config;

import com.resean.spring.beans.factory.support.AbstracAutoWriedCapableBeanFactory;
import com.resean.spring.beans.factory.support.ConfigurableListableBeanFactory;
import com.resean.spring.core.BeanException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstracAutoWriedCapableBeanFactory implements ConfigurableListableBeanFactory {

    @Override
    public int getBeanDefinitionCount() {
        return this.beanDefinitions.size();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return (String[]) this.beanNames.toArray();
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {

        List<String> result = new ArrayList<>();
        for (String beanName : this.beanNames) {
            boolean matchFound = false;
            BeanDefinition beanDefinition = getBeanDefinition(beanName);
            Class<?> classTomatch = beanDefinition.getClass();
            if (type.isAssignableFrom(classTomatch)) {
                matchFound = true;
            }
            if (matchFound) {
                result.add(beanName);
            }
        }
        return (String[]) result.toArray();
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        String[] beanNames = getBeanNamesForType(type);
        Map<String, T> result = new LinkedHashMap<>(beanNames.length);
        for (String beanName : beanNames) {
            Object bean = getBean(beanName);
            result.put(beanName, (T) bean);
        }
        return result;
    }


    @Override
    public int getBeanPostprocessorCount() {
        return super.getBeanPostProcessorCount();
    }

    @Override
    public void registerDependentBean(String beanName, String dependentBeanName) {

    }

    @Override
    public String[] getDependentBeans(String beanName) {
        return new String[0];
    }

    @Override
    public String[] getDependenciesForBean(String beanName) {
        return new String[0];
    }


}
