package com.resean.spring.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简单的 bean工厂实现类
 */
public class SimpleBeanFactory implements BeanFactory{

    private Map<String,Object> sigletonMap=new HashMap<>();
    private List<BeanDefinition> beanDefinitions=new ArrayList<>();
    private List<String> beanNames=new ArrayList<>();

    @Override
    public Object getBean(String beanName) {
        Object bean=sigletonMap.get(beanName);
        if (bean==null){
            int index=beanNames.indexOf(beanName);
            if (index!=-1){
                BeanDefinition beanDefinition=beanDefinitions.get(index);
                try {
                    bean=Class.forName(beanDefinition.getClassName()).newInstance();
                    sigletonMap.put(beanName,beanName);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }


        return bean;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitions.add(beanDefinition);
        beanNames.add(beanDefinition.getId());
    }
}
