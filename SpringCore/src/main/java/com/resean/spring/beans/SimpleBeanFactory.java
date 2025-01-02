package com.resean.spring.beans;

import com.resean.spring.core.BeanException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简单的 bean工厂实现类
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory{


    /*************** v2 ****************/
//    private Map<String,Object> sigletonMap=new HashMap<>();
//    private List<String> beanNames=new ArrayList<>();
//    private List<BeanDefinition> beanDefinitions=new ArrayList<>();

    public SimpleBeanFactory() {
    }
    private Map<String,BeanDefinition> beanDefinitions=new ConcurrentHashMap<>(256);

    @Override
    public Object getBean(String beanName) throws BeanException {

        Object bean=this.getSingleton(beanName);
        if (bean==null){
            BeanDefinition beanDefinition=this.beanDefinitions.get(beanName);
            if (beanDefinition==null){
                throw  new BeanException("no Bean!");
            }
            try {
                bean=Class.forName(beanDefinition.getClassName()).newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.registerBean(beanName,bean);
        }


        /*************** v2 ****************/
//        Object bean=sigletonMap.get(beanName);
//        if (bean==null){
//            int index=beanNames.indexOf(beanName);
//            if (index!=-1){
//                BeanDefinition beanDefinition=beanDefinitions.get(index);
//                try {
//                    bean=Class.forName(beanDefinition.getClassName()).newInstance();
//                    sigletonMap.put(beanName,beanName);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }

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

    /*************** v2 ****************/
//    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitions.put(beanDefinition.getId(),beanDefinition);
//        beanNames.add(beanDefinition.getId());
    }
}
