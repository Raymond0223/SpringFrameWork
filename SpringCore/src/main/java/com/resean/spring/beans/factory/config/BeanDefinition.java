package com.resean.spring.beans.factory.config;

/**
 * Bean定义，用于描述Bean的属性，包括Bean的类名、Bean的属性、Bean的依赖等
 */
public class BeanDefinition {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    private boolean lazyInit = false;
    private String[] dependsOn;
    private ConstructorArgumentValues constructorArgumentValues;
    private PropertyValues propertyValues;
    private String initMethodName;
    private volatile Object beanClass;
    private String id;
    private String className;
    private String scope = SCOPE_SINGLETON;

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSCOPE_SINGLETON() {
        return SCOPE_SINGLETON;
    }

    public String getSCOPE_PROTOTYPE() {
        return SCOPE_PROTOTYPE;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public String[] getDependsOn() {
        return dependsOn;
    }

    public ConstructorArgumentValues getConstructorArgumentValues() {
        return constructorArgumentValues;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public Object getBeanClass() {
        return beanClass;
    }

    public String getScope() {
        return scope;
    }

    public void setSCOPE_SINGLETON(String SCOPE_SINGLETON) {
        this.SCOPE_SINGLETON = SCOPE_SINGLETON;
    }

    public void setSCOPE_PROTOTYPE(String SCOPE_PROTOTYPE) {
        this.SCOPE_PROTOTYPE = SCOPE_PROTOTYPE;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public void setDependsOn(String[] dependsOn) {
        this.dependsOn = dependsOn;
    }

    public void setConstructorArgumentValues(ConstructorArgumentValues constructorArgumentValues) {
        this.constructorArgumentValues = constructorArgumentValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public void setBeanClass(Object beanClass) {
        this.beanClass = beanClass;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
    public boolean isSingleton(){
        return SCOPE_SINGLETON.equals(scope);
    }

    public boolean isPrototype(){
        return SCOPE_PROTOTYPE.equals(scope);
    }
}
