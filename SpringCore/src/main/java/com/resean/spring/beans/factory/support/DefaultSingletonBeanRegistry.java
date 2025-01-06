package com.resean.spring.beans.factory.support;

import com.resean.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例bean 注册默认实现类
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private List<String> singletonName=new ArrayList<>();

    private Map<String,Object> singletontMap=new ConcurrentHashMap<>(256);

    @Override
    public void registrySingleton(String beanName, Object object) {
        synchronized (this.singletontMap){
            singletontMap.put(beanName,object);
            singletonName.add(beanName);
        }

    }

    @Override
    public Object getSingleton(String beanName) {
        return singletontMap.get(beanName);
    }

    @Override
    public boolean constainsSingleton(String beanName) {
        return singletonName.contains(beanName);
    }

    @Override
    public String[] getSingletonBeanName() {
        return (String[])singletonName.toArray();
    }

    protected void rempveSingleton(String beanName){
        synchronized (this.singletontMap){
            singletontMap.remove(beanName);
            singletonName.remove(beanName);
        }
    }

}
