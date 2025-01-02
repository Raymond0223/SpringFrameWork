package com.resean.spring.context;

import com.resean.spring.beans.BeanDefinition;
import com.resean.spring.beans.BeanFactory;
import com.resean.spring.beans.SimpleBeanFactory;
import com.resean.spring.core.BeanException;
import com.resean.spring.resource.ClassPathXmlResource;
import com.resean.spring.resource.Reource;
import com.resean.spring.resource.XmlBeanDefinitionReader;
import org.dom4j.Document;
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


   private BeanFactory beanFactory;

    /********************v1 ***********************/
//    /**
//     * 保存 bean 信息
//     */
//    private List<BeanDefinition> beanDefinitions=new ArrayList();
//
//    /**
//     * 保存单例对象
//     */
//    private Map<String,Object> singgleton=new HashMap<String, Object>();

    /********************v1 ***********************/


    public ClassPathXmlApplicationContext(String fileName){
        Reource classPathXmlResource=new ClassPathXmlResource(fileName);
        SimpleBeanFactory factory=new SimpleBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader=new XmlBeanDefinitionReader(factory);
        xmlBeanDefinitionReader.loadBeandefinitions(classPathXmlResource);
        this.beanFactory=factory;

        /********************v1 ***********************/
//        readXml(fileName);
//        initBeanDefinition();
    }


    /********************v1 ***********************/

//    /**
//     * 读取文件资源
//     * @param flieName
//     */
//    private void readXml(String flieName){
//        SAXReader saxReader=new SAXReader();
//        URL xmlPath=this.getClass().getClassLoader().getResource(flieName);
//
//        try {
//            Document document=saxReader.read(xmlPath);
//            Element rootElement=document.getRootElement();
//
//            for (Element element:rootElement.elements()){
//                String id=element.attributeValue("id");
//                String classNmae=element.attributeValue("class");
//                beanDefinitions.add(new BeanDefinition(id,classNmae));
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    private void initBeanDefinition(){
//        for (BeanDefinition beanDefinition:beanDefinitions){
//            try {
//                Object bean=Class.forName(beanDefinition.getClassName()).newInstance();
//                singgleton.put(beanDefinition.getId(),bean);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
    /********************v1 ***********************/

    public Object getBean(String beanName) throws BeanException {

        return beanFactory.getBean(beanName);

        /********************v1 ***********************/
//        return singgleton.get(beanName);
    }

//    public void registerBeanDefinition(BeanDefinition beanDefinition){
//        beanFactory.registerBeanDefinition(beanDefinition);
//    }


    public boolean constainBean(String beanName){
        return beanFactory.containsBean(beanName);
    }

    public void registerBean(String beanName,Object object){
        this.beanFactory.registerBean(beanName,object);
    }


}
