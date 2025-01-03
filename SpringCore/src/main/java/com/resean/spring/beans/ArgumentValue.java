package com.resean.spring.beans;

/**
 *
 */
public class ArgumentValue {
    private String name;

    private String type;

    private Object value;

    public ArgumentValue(String name, String type, Object value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public ArgumentValue(String type, Object value) {
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
