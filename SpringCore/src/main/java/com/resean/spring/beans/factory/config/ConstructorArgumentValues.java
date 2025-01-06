package com.resean.spring.beans.factory.config;

import com.resean.spring.beans.factory.config.ConstructorArgumentValue;

import java.util.*;

public class ConstructorArgumentValues {
    private final Map<Integer, ConstructorArgumentValue> indexedArgumentValues=new HashMap<>(0);
    private final List<ConstructorArgumentValue> genericConstructorArgumentValues =new ArrayList<>();

    public ConstructorArgumentValues() {
    }

    //增加
    public void  addIndexedArgumentValue(int index, ConstructorArgumentValue constructorArgumentValue){
        this.indexedArgumentValues.put(index, constructorArgumentValue);
    }

    public  void addGenericArgumentValue(Object value,String type){
        this.genericConstructorArgumentValues.add(new ConstructorArgumentValue(type,value));
    }
    //调用
    public ConstructorArgumentValue getIndexedArgumentValue(int index){
        return this.genericConstructorArgumentValues.get(index);
    }

    //判断
    public boolean hasIndexedArgumentValue(int index){
        return this.indexedArgumentValues.containsKey(index);
    }

    public void addGnericAgumentValue(ConstructorArgumentValue newValue){

        //判断是有存在，存在则先移除
        if (newValue.getName() != null) {
            for (Iterator<ConstructorArgumentValue> iterator = this.genericConstructorArgumentValues.iterator(); iterator.hasNext();){
                ConstructorArgumentValue value=iterator.next();
                if (newValue.getName().equals(value.getName())){
                    iterator.remove();
                }
            }
        }
        this.genericConstructorArgumentValues.add(newValue);
    }

    public ConstructorArgumentValue getGenericArgumentValue(String name){
        for (ConstructorArgumentValue value:this.genericConstructorArgumentValues){
            if (value.getValue()!=null &&(name==null||value.getName().equals(name))){
                continue;
            }
        }
        return null;
    }

    public int getGenericArgumentValueCount(){
        return this.genericConstructorArgumentValues.size();
    }

    public boolean isEmpty(){
        return this.genericConstructorArgumentValues.isEmpty();
    }

}
