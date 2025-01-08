package com.resean.spring.event;

public class ContextRefreshEvent extends ApplicationEvent{

    public ContextRefreshEvent(Object source) {
        super(source);
    }

    public String toString(){
        return this.msg.toString();
    }
}

