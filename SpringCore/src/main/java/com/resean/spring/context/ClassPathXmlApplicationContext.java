package com.resean.spring.context;

import com.resean.spring.beans.BeanFactory;
import com.resean.spring.beans.SimpleBeanFactory;
import com.resean.spring.core.BeanException;
import com.resean.spring.event.ApplicationEventPublisher;
import com.resean.spring.resource.ClassPathXmlResource;
import com.resean.spring.resource.Reource;
import com.resean.spring.resource.XmlBeanDefinitionReader;

/**
 * s
 *
 */
public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {


   private SimpleBeanFactory beanFactory;

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
        SimpleBeanFactory factory=new SimpleBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader=new XmlBeanDefinitionReader(factory);
        xmlBeanDefinitionReader.loadBeandefinitions(classPathXmlResource);
        this.beanFactory=factory;

        if (refresh){
            this.beanFactory.refresh();
        }

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
}
