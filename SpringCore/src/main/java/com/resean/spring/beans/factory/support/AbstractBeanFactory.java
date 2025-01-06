package com.resean.spring.beans.factory.support;

import com.resean.spring.beans.factory.BeanFactory;
import com.resean.spring.beans.factory.config.*;
import com.resean.spring.core.BeanException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory  extends DefaultSingletonBeanRegistry implements BeanFactory,BeanDefinitionResgistry {

    private Map<String, BeanDefinition> beanDefinitions=new ConcurrentHashMap<>(256);

    private Map<String,Object> earlySingletonObjects=new ConcurrentHashMap<>(256);

    private List<String> beanNames=new ArrayList<>();

    public AbstractBeanFactory() {
    }

    public void refresh() {
       for (String beanName:beanNames){
              getBean(beanName);
       }
    }

    @Override
    public Object getBean(String beanName) {

        Object bean=this.getSingleton(beanName);
        if(bean==null){
            bean=this.earlySingletonObjects.get(beanName);
            if(bean==null){
                BeanDefinition beanDefinition=this.beanDefinitions.get(beanName);
                if(beanDefinition==null){
                    throw new RuntimeException("no bean");
                }
                try {
                    bean=createBean(beanDefinition);
                    this.registerBean(beanName,bean);
                    applyBeanPostProcessorBeforeInitialization(bean, beanName);
                    if (beanDefinition.getInitMethodName() != null && !beanDefinition.equals("")) {
                        invokeInitMethod(beanDefinition, bean);
                    }
                    applyBeanPostProcessorAfterInitialization(bean, beanName);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                this.registerBean(beanName,bean);
            }
        }

        return bean;
    }

    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        Class<?> beanClass=null;
        Object bean=doCreateBean(beanDefinition);
        this.earlySingletonObjects.put(beanDefinition.getId(),bean);

        beanClass=Class.forName(beanDefinition.getClassName());
        populateBean(beanDefinition,beanClass,bean);
        return bean;
    }


    private Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
       Class<?> beanClass=null;
         Object bean=null;
         Constructor<?> constructor=null;

        beanClass=Class.forName(beanDefinition.getClassName());
        //处理构造器信息
        ConstructorArgumentValues constructorArgumentValues=beanDefinition.getConstructorArgumentValues();
        if (constructorArgumentValues!=null && !constructorArgumentValues.isEmpty()){
            Class<?> parameterTypes[]=new Class[constructorArgumentValues.getGenericArgumentValueCount()];
            Object[] paramValues=new Object[constructorArgumentValues.getGenericArgumentValueCount()];
            for (int i=0;i<constructorArgumentValues.getGenericArgumentValueCount();i++){
                ConstructorArgumentValue constructorArgumentValue=constructorArgumentValues.getIndexedArgumentValue(i);
                if ("String".equals(constructorArgumentValue.getType()) ||
                        "java.lang.String".equals(constructorArgumentValue.getType())) {
                    parameterTypes[i] = String.class;
                    paramValues[i] = constructorArgumentValue.getValue();
                } else if
                ("Integer".equals(constructorArgumentValue.getType()) ||
                                "java.lang.Integer".equals(constructorArgumentValue.getType())) {
                    parameterTypes[i] = Integer.class;
                    paramValues[i] = Integer.valueOf((String)
                            constructorArgumentValue.getValue());
                } else if ("int".equals(constructorArgumentValue.getType()))
                {
                    parameterTypes[i] = int.class;
                    paramValues[i] = Integer.valueOf((String)
                            constructorArgumentValue.getValue());
                } else {
                    parameterTypes[i] = String.class;
                    paramValues[i] = constructorArgumentValue.getValue();
                }
            }
            constructor=beanClass.getConstructor(parameterTypes);
            bean=constructor.newInstance(paramValues);
            System.out.println(beanDefinition.getId() + " bean created. " +
                    beanDefinition.getClassName() + " : " + bean.toString());
        }else {
            bean=beanClass.newInstance();
        }
        return bean;
    }

    private void populateBean(BeanDefinition beanDefinition, Class<?> clz, Object obj) {
        handleProperties(beanDefinition, clz, obj);
    }
    @Override
    public boolean containsBean(String name) {
        return constainsSingleton(name);
    }
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitions.put(beanName,beanDefinition);
        this.beanNames.remove(beanName);
    }

    @Override
    public void removeBeanDefinition(String name) {
        this.beanDefinitions.remove(name);
        this.beanNames.remove(name);
        this.rempveSingleton(name);
    }
    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitions.get(name);
    }
    @Override
    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitions.containsKey(name);
    }
    @Override
    public boolean isSingleton(String name) {
        return this.beanDefinitions.get(name).isSingleton();
    }
    @Override
    public boolean isPrototype(String name) {
        return this.beanDefinitions.get(name).isPrototype();
    }
    @Override
    public Class<?> getType(String name) {
        return this.beanDefinitions.get(name).getClass();
    }

    @Override
    public void registerBean(String beanName, Object bean) {
        this.registrySingleton(beanName, bean);
    }

    private void invokeInitMethod(BeanDefinition beanDefinition, Object obj) {
        Class<?> clz = beanDefinition.getClass();
        Method method = null;
        try {
            method = clz.getMethod(beanDefinition.getInitMethodName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        try {
            method.invoke(obj);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    private void handleProperties(BeanDefinition beanDefinition, Class<?> clz, Object obj) {
        // handle properties
        System.out.println("handle properties for bean : " + beanDefinition.getId());
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        //如果有属性
        if (!propertyValues.isEmpty()) {
            for (int i = 0; i < propertyValues.size(); i++) {
                PropertyValue propertyValue =
                        propertyValues.getPropertyValueList().get(i);
                String pType = propertyValue.getType();
                String pName = propertyValue.getName();
                Object pValue = propertyValue.getValue();
                boolean isRef = propertyValue.isRef();
                Class<?>[] paramTypes = new Class<?>[1];
                Object[] paramValues = new Object[1];
                if (!isRef) { //如果不是ref，只是普通属性
                    //对每一个属性，分数据类型分别处理
                    if ("String".equals(pType) || "java.lang.String".equals(pType)) {
                        paramTypes[0] = String.class;
                    } else if ("Integer".equals(pType) || "java.lang.Integer".equals(pType)) {
                        paramTypes[i] = Integer.class;
                    } else if ("int".equals(pType)) {
                        paramTypes[i] = int.class;
                    } else {
                        paramTypes[i] = String.class;
                    }
                    paramValues[0] = pValue;
                } else {//is ref, create the dependent beans
                    try {
                        paramTypes[0] = Class.forName(pType);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    //再次调用getBean创建ref的bean实例
                        paramValues[0] = getBean((String) pValue);

                }
                //按照setXxxx规范查找setter方法，调用setter方法设置属性
                String methodName = "set" + pName.substring(0, 1).toUpperCase()
                        + pName.substring(1);
                Method method = null;
                try {
                    method = clz.getMethod(methodName, paramTypes);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                try {
                    method.invoke(obj, paramValues);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    abstract public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeanException;
    abstract public Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeanException;

}
