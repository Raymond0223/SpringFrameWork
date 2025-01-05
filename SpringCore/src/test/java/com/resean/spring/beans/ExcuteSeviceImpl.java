package com.resean.spring.beans;

public class ExcuteSeviceImpl implements ExcuteSevice{

    private String name;

    private int level;

    private String property1;
    private String property2;

    private RefSeviceImpl refSevice;


    public ExcuteSeviceImpl(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public void excute() {
        System.out.println(name+" "+level+" "+property1+" "+property2);
        refSevice.excute();
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getProperty1() {
        return property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public RefSeviceImpl getRefSevice() {
        return refSevice;
    }

    public void setRefSevice(RefSeviceImpl refSevice) {
        this.refSevice = refSevice;
    }
}
