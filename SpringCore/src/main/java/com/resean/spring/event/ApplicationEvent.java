package com.resean.spring.event;

import java.util.EventObject;

public class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    protected String msg;
    public ApplicationEvent(Object source) {
        super(source);
        this.msg=source.toString();
    }
}
