package com.resean.spring.context;

import com.resean.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.resean.spring.beans.factory.config.DefaultListableBeanFactory;
import com.resean.spring.beans.factory.config.Environment;
import com.resean.spring.beans.factory.support.AutoWriedAnnotationBeanPostProcessor;
import com.resean.spring.beans.factory.config.BeanPostProcessor;
import com.resean.spring.beans.factory.support.ConfigurableListableBeanFactory;
import com.resean.spring.core.BeanException;
import com.resean.spring.event.ApplicationEvent;
import com.resean.spring.event.ApplicationEventPublisher;
import com.resean.spring.event.ApplicationListener;
import com.resean.spring.event.SimpleApplicationEventPulisher;
import com.resean.spring.resource.ClassPathXmlResource;
import com.resean.spring.resource.Reource;
import com.resean.spring.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * s
 *
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {


   DefaultListableBeanFactory beanFactory;

   private final List<BeanPostProcessor> beanPostProcessors=new ArrayList<>();

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
        DefaultListableBeanFactory factory=new DefaultListableBeanFactory();
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

    @Override
    void registerListeners() {
        ApplicationListener listener=new ApplicationListener();
        this.addApplicationListener(listener);
    }

    @Override
    void initApplicationEventPluslticaster() {
        ApplicationEventPublisher applicationEventPublisher=new SimpleApplicationEventPulisher();
        this.setApplicationEventPublisher(applicationEventPublisher);
    }

    @Override
    void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) {

    }

    @Override
    void registerBeanPostProcessors(ConfigurableListableBeanFactory configurableListableBeanFactory) {
        this.beanFactory.addBeanPostProcessor(new AutoWriedAnnotationBeanPostProcessor());
    }

    @Override
    void onRefresh() {
        this.beanFactory.refresh();
    }

    @Override
    void finishRefresh() {
        publisherEvent(new ApplicationEvent("Context Refreshed......"));

    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {

    }

    @Override
    public Environment getEnvironment() {
        return null;
    }

    public Object getBean(String beanName) throws BeanException {

        return beanFactory.getBean(beanName);

        /********************v1 ***********************/
//        return singgleton.get(beanName);
    }

    @Override
    public boolean containsBean(String benaNae) {
        return false;
    }


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




    public void addBeanPostProcessor(AutoWriedAnnotationBeanPostProcessor processor){
        this.beanFactory.addBeanPostProcessor(processor);
    }

    @Override
    public void publisherEvent(ApplicationEvent applicationEvent) {
        this.getApplicationEventPublisher().publisherEvent(applicationEvent);
    }

    @Override
    public void addApplicationListener(ApplicationListener applicationListener) {
        this.getApplicationEventPublisher().addApplicationListener(applicationListener);
    }


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {

    }


    @Override
    public int getBeanPostprocessorCount() {
        return 0;
    }

    @Override
    public void registerDependentBean(String beanName, String dependentBeanName) {

    }

    @Override
    public String[] getDependentBeans(String beanName) {
        return new String[0];
    }

    @Override
    public String[] getDependenciesForBean(String beanName) {
        return new String[0];
    }

    @Override
    public void registrySingleton(String beanName, Object object) {

    }

    @Override
    public Object getSingleton(String beanName) {
        return null;
    }

    @Override
    public boolean constainsSingleton(String beanName) {
        return false;
    }

    @Override
    public String[] getSingletonBeanName() {
        return new String[0];
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return false;
    }

    @Override
    public int getBeanDefinitionCount() {
        return 0;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        return new String[0];
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        return null;
    }
}
