package com.resean.spring.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {


    private final List<PropertyValue> propertyValueList;

    public PropertyValues() {
        this.propertyValueList = new ArrayList<>(0);
    }

    public List<PropertyValue> getPropertyValueList(){
        return this.propertyValueList;
    }

    public int size(){
        return  this.propertyValueList.size();
    }

    public void addPropertyValue(PropertyValue pv){
        this.propertyValueList.add(pv);
    }

    public void addPropertyValue(String name,Object value){
        this.addPropertyValue(new PropertyValue(name,value,Object.class.getTypeName(),false));
    }

    public void removePropertyValue(PropertyValue propertyValue){
        this.propertyValueList.remove(propertyValue);
    }

    public void removePropertyValue(String propertyName){
        this.propertyValueList.remove(getPropertyValue(propertyName));
    }


    public PropertyValue[] getPropertyValues(){
        return this.propertyValueList.toArray(new PropertyValue[size()]);
    }

    public PropertyValue getPropertyValue(String propertyName){
        for (PropertyValue pv:this.propertyValueList){
            if (pv.getName().equals(propertyName)){
                return pv;
            }
        }
        return null;
    }

    public boolean contains(String propertyName){
        return getPropertyValue(propertyName)!=null;
    }
    public Object getPropertyValues(String propertyName){
        PropertyValue pv=getPropertyValue(propertyName);
        return pv!=null?pv.getValue():null;
    }

    public boolean isEmpty(){
        return this.propertyValueList.isEmpty();
    }

}
