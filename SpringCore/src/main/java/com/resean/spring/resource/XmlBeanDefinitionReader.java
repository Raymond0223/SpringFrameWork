package com.resean.spring.resource;

import com.resean.spring.beans.BeanDefinition;
import com.resean.spring.beans.BeanFactory;
import org.dom4j.Element;

/**
 * 加载 xml资源类型的 bean 信息
 */
public class XmlBeanDefinitionReader {

    private BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeandefinitions(Reource reource){
        while (reource.hasNext()){
            Element element=(Element) reource.next();
            this.beanFactory.registerBeanDefinition(new BeanDefinition(element.attributeValue("id"),
                    element.attributeValue("class")));
        }
    }

}
