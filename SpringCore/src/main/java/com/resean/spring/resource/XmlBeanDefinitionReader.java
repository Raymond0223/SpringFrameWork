package com.resean.spring.resource;

import com.resean.spring.beans.*;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * 加载 xml资源类型的 bean 信息
 */
public class XmlBeanDefinitionReader {

    private SimpleBeanFactory beanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeandefinitions(Reource reource){
        while (reource.hasNext()){
            Element element=(Element) reource.next();
            BeanDefinition beanDefinition=new BeanDefinition(element.attributeValue("id"),element.attributeValue("class"));

            //处理属性
            List<Element> propertyElements=element.elements("property");
            List<String> refs=new ArrayList<>();
            PropertyValues propertyValues=new PropertyValues();
            for (Element propertyElement:propertyElements){
                String name=propertyElement.attributeValue("name");
                String value=propertyElement.attributeValue("value");
                String type=propertyElement.attributeValue("type");
                String ref=propertyElement.attributeValue("ref");
                String pValue="";
                boolean isRef=false;
                if (value!=null||value.equals("")){
                    pValue=value;
                }else if (ref!=null||ref.equals("")){
                    isRef=true;
                    pValue=ref;
                    refs.add(ref);
                }
                propertyValues.addPropertyValue(new PropertyValue(name,pValue,type,isRef));
            }

            String[] refArray=refs.toArray(new String[refs.size()]);
            beanDefinition.setDependsOn(refArray);
            beanDefinition.setPropertyValues(propertyValues);

            //处理构造器
            List<Element> constructorElements=element.elements("constructor-arg");
            ArgumentValues argumentValues=new ArgumentValues();
            for (Element constructorElement:constructorElements){
                String name=constructorElement.attributeValue("name");
                String value=constructorElement.attributeValue("value");
                String type=constructorElement.attributeValue("type");
                argumentValues.addGnericAgumentValue(new ArgumentValue(name,value,type));
            }
            beanDefinition.setConstructorArgumentValues(argumentValues);

            this.beanFactory.registerBeanDefinition(beanDefinition.getId(),beanDefinition);
        }
    }

}
