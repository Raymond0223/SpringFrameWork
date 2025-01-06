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

/**
 * 简单的 bean工厂实现类
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionResgistry {


    /*************** v2 ****************/
//    private Map<String,Object> sigletonMap=new HashMap<>();
//    private List<String> beanNames=new ArrayList<>();
//    private List<BeanDefinition> beanDefinitions=new ArrayList<>();


    private Map<String, BeanDefinition> beanDefinitions=new ConcurrentHashMap<>(256);

    private Map<String,Object> earlySingletonObjects=new ConcurrentHashMap<>(256);


    private List<String> beanNames=new ArrayList<>();

    public SimpleBeanFactory() {

    }
    @Override
    public Object getBean(String beanName) throws BeanException {
        //查看是否已经存在
        Object bean=this.getSingleton(beanName);
        if (bean==null){
            bean = this.earlySingletonObjects.get(beanName);
            if (bean ==null){
                BeanDefinition beanDefinition=this.beanDefinitions.get(beanName);
                if (beanDefinition==null){
                    throw  new BeanException("no Bean!");
                }
                try {
                    bean=createBean(beanDefinition);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                this.registerBean(beanName,bean);
            }
        }

        return bean;
    }

    @Override
    public boolean containsBean(String benaNae) {
        return this.constainsSingleton(benaNae);
    }

    @Override
    public void registerBean(String beanName, Object object) {
        this.registrySingleton(beanName,object);
    }

    @Override
    public boolean isSingleton(String beanName) {
        return getBeanDefinition(beanName).isSingleton();
    }

    @Override
    public boolean isPrototype(String beanName) {
        return getBeanDefinition(beanName).isPrototype();
    }

    @Override
    public Class<?> getType(String beanName) {
        return getBeanDefinition(beanName).getClass();
    }

    /*************** v2 ****************/
//    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitions.put(beanDefinition.getId(),beanDefinition);
        beanNames.add(beanDefinition.getId());
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitions.put(beanName,beanDefinition);
        this.beanNames.add(beanName);
        if (!beanDefinition.isLazyInit()){
            try {
                getBean(beanName);
            } catch (BeanException e) {
                throw new RuntimeException(e);
            }
        }

    }



    private Object createBean(BeanDefinition beanDefinition){
        Class<?> beanClass=null;
        Object bean=doCreateBean(beanDefinition);
        //存放到毛胚实例缓存中
        this.earlySingletonObjects.put(beanDefinition.getId(), bean);
        try {
            beanClass=Class.forName(beanDefinition.getClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //处理 属性参数
        handleProperty(beanDefinition, beanClass, bean);
        return bean;
    }

    private Object doCreateBean(BeanDefinition beanDefinition){
        Class<?> beanClass=null;
        Object bean=null;
        Constructor<?> constructor=null;
        try {
            beanClass=Class.forName(beanDefinition.getClassName());
            //处理构造器参数
            ConstructorArgumentValues constructorArgumentValues =beanDefinition.getConstructorArgumentValues();
            if (constructorArgumentValues !=null && !constructorArgumentValues.isEmpty()){
                //参数类型
                Class<?>[] paramType=new Class[constructorArgumentValues.getGenericArgumentValueCount()];
                //参数值
                Object[] paramValue=new Object[constructorArgumentValues.getGenericArgumentValueCount()];
                for (int i = 0; i< constructorArgumentValues.getGenericArgumentValueCount(); i++){
                    ConstructorArgumentValue constructorArgumentValue = constructorArgumentValues.getIndexedArgumentValue(i);
                    //根据不同类型判断
                    if ("String".equals(constructorArgumentValue.getType())||"java.lang.String".equals(constructorArgumentValue.getType())){
                        paramType[i]=String.class;
                        paramValue[i]= constructorArgumentValue.getValue();
                    }else if ("Integer".equals(constructorArgumentValue.getType())||"java.lang.Integer".equals(constructorArgumentValue.getType())){
                        paramType[i]=Integer.class;
                        paramValue[i]=Integer.valueOf((String) constructorArgumentValue.getValue());
                    }else if ("int".equals(constructorArgumentValue.getType())){
                        paramType[i]=int.class;
                        paramValue[i]=Integer.valueOf((String) constructorArgumentValue.getValue());
                    }else {
                        paramType[i]=String.class;
                        paramValue[i]= constructorArgumentValue.getValue();
                    }
                }
                //按照构造器创建实例
                constructor=beanClass.getConstructor(paramType);
                bean=constructor.newInstance(paramValue);

            }else {
                //有参构造器为空，直接创建实例
                bean=beanClass.newInstance();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return bean;
    }


    private  void handleProperty(BeanDefinition beanDefinition, Class<?> beanClass, Object bean) {

        System.out.println("handle properties for bean : " + beanDefinition.getId());

        PropertyValues propertyValues= beanDefinition.getPropertyValues();
        if (propertyValues!=null && !propertyValues.isEmpty()){
            for (int i =0;i< propertyValues.size();i++){
                PropertyValue propertyValue=propertyValues.getPropertyValueList().get(i);
                String name= propertyValue.getName();
                String type= propertyValue.getType();
                Object value=propertyValue.getValue();
                Class<?> paramType=String.class;
                Object paramValue=new Object();
                boolean isRef=propertyValue.isRef();
                if (!isRef){
                    if ("String".equals(type)||"java.lang.String".equals(type)){
                        paramType=String.class;
                    }else if ("Integer".equals(type)||"java.lang.Integer".equals(type)){
                        paramType=Integer.class;
                    }else if ("int".equals(type)){
                        paramType=int.class;
                    }
                    paramValue=value;
                }else {
                    try {
                        paramType=Class.forName(type);
                        paramValue=getBean(String.valueOf(value));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (BeanException e) {
                        throw new RuntimeException(e);
                    }
                }
                String methodName="set"+name.substring(0,1).toUpperCase()+name.substring(1);
                try {
                    Method method= beanClass.getMethod(methodName,paramType);
                    method.invoke(bean,paramValue);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void removeBeanDefinition(String beanName) {
        this.beanDefinitions.remove(beanName);
        this.beanNames.remove(beanName);
        this.rempveSingleton(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitions.get(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitions.containsKey(beanName);
    }

    public void refresh() {
        for (String beanName : this.beanNames) {
            try {
                getBean(beanName);
            } catch (BeanException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
