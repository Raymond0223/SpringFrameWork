package com.resean.spring.beans;

import java.util.*;

public class ArgumentValues {
    private final Map<Integer,ArgumentValue> indexedArgumentValues=new HashMap<>(0);
    private final List<ArgumentValue> genericArgumentValues=new ArrayList<>();

    public ArgumentValues() {
    }

    //增加
    public void  addIndexedArgumentValue(int index,ArgumentValue argumentValue){
        this.indexedArgumentValues.put(index,argumentValue);
    }

    public  void addGenericArgumentValue(Object value,String type){
        this.genericArgumentValues.add(new ArgumentValue(type,value));
    }
    //调用
    public ArgumentValue getIndexedArgumentValue(int index){
        return this.genericArgumentValues.get(index);
    }

    //判断
    public boolean hasIndexedArgumentValue(int index){
        return this.indexedArgumentValues.containsKey(index);
    }

    public void addGnericAgumentValue(ArgumentValue newValue){

        //判断是有存在，存在则先移除
        if (newValue.getName() != null) {
            for (Iterator<ArgumentValue> iterator=this.genericArgumentValues.iterator();iterator.hasNext();){
                ArgumentValue value=iterator.next();
                if (newValue.getName().equals(value.getName())){
                    iterator.remove();
                }
            }
        }
        this.genericArgumentValues.add(newValue);
    }

    public ArgumentValue getGenericArgumentValue(String name){
        for (ArgumentValue value:this.genericArgumentValues){
            if (value.getValue()!=null &&(name==null||value.getName().equals(name))){
                continue;
            }
        }
        return null;
    }

    public int getGenericArgumentValueCount(){
        return this.genericArgumentValues.size();
    }

    public boolean isEmpty(){
        return this.genericArgumentValues.isEmpty();
    }

}
