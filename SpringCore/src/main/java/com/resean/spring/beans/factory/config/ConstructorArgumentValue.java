package com.resean.spring.beans.factory.config;

/**
 *
 */
public class ConstructorArgumentValue {
    private String name;

    private String type;

    private Object value;

    public ConstructorArgumentValue(String name, String type, Object value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public ConstructorArgumentValue(String type, Object value) {
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
