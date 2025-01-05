package com.resean.spring.beans;

public class RefSeviceImpl {

    private String name;

    private String type;

    public void excute(){
        System.out.println("RefSeviceImpl-name:"+name+" RefSeviceImpl-type:"+type);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
