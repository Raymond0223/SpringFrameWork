package com.resean.spring;

import com.resean.spring.bean.BeanDefinition;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * s
 *
 */
public class ClassPathXmlApplicationContext {

    /**
     * 保存 bean 信息
     */
    private List<BeanDefinition> beanDefinitions=new ArrayList();

    /**
     * 保存单例对象
     */
    private Map<String,Object> singgleton=new HashMap<String, Object>();

    public ClassPathXmlApplicationContext(String fileName){
        readXml(fileName);
        initBeanDefinition();
    }


    /**
     * 读取文件资源
     * @param flieName
     */
    private void readXml(String flieName){
        SAXReader saxReader=new SAXReader();
        URL xmlPath=this.getClass().getClassLoader().getResource(flieName);

        try {
            Document document=saxReader.read(xmlPath);
            Element rootElement=document.getRootElement();

            for (Element element:rootElement.elements()){
                String id=element.attributeValue("id");
                String classNmae=element.attributeValue("class");
                beanDefinitions.add(new BeanDefinition(id,classNmae));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void initBeanDefinition(){
        for (BeanDefinition beanDefinition:beanDefinitions){
            try {
                Object bean=Class.forName(beanDefinition.getClassName()).newInstance();
                singgleton.put(beanDefinition.getId(),bean);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Object getBean(String beanName){
        return singgleton.get(beanName);
    }


}
