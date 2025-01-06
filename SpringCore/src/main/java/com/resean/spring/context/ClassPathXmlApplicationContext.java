package com.resean.spring.context;

import com.resean.spring.beans.factory.BeanFactory;
import com.resean.spring.beans.factory.support.AutoWriedAnnotationBeanPostProcessor;
import com.resean.spring.beans.factory.support.AutoWriedCapableBeanFactory;
import com.resean.spring.beans.factory.support.SimpleBeanFactory;
import com.resean.spring.core.BeanException;
import com.resean.spring.event.ApplicationEventPublisher;
import com.resean.spring.resource.ClassPathXmlResource;
import com.resean.spring.resource.Reource;
import com.resean.spring.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.List;

/**
 * s
 *
 */
public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {


   private AutoWriedCapableBeanFactory beanFactory;

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


    public ClassPathXmlApplicationContext(String fileName) {
        this(fileName,true);
    }


    public ClassPathXmlApplicationContext(String fileName,boolean refresh) {
        Reource classPathXmlResource=new ClassPathXmlResource(fileName);
        AutoWriedCapableBeanFactory factory=new AutoWriedCapableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader=new XmlBeanDefinitionReader(factory);
        xmlBeanDefinitionReader.loadBeandefinitions(classPathXmlResource);
        this.beanFactory=factory;
        if (refresh){
            refresh();
        }

        /********************v1 ***********************/
//        readXml(fileName);
//        initBeanDefinition();
    }

    public void refresh(){
        registerBeanPostProcessors(this.beanFactory);
        onRefresh();
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

    @Override
    public boolean containsBean(String benaNae) {
        return false;
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

    @Override
    public boolean isSingleton(String beanName) {
        return false;
    }

    @Override
    public boolean isPrototype(String beanName) {
        return false;
    }

    @Override
    public Class<?> getType(String beanName) {
        return null;
    }


    @Override
    public void publisherEvent() {

    }

    public List<AutoWriedAnnotationBeanPostProcessor> getProcessors(){
        return this.beanFactory.getProcessors();
    }

    public void addBeanPostProcessor(AutoWriedAnnotationBeanPostProcessor processor){
        this.beanFactory.addBeanPostProcessor(processor);
    }

    private void registerBeanPostProcessors(AutoWriedCapableBeanFactory beanFactory){
        beanFactory.addBeanPostProcessor(new AutoWriedAnnotationBeanPostProcessor());
    }

    private void onRefresh(){
        this.beanFactory.refresh();
    }

}
