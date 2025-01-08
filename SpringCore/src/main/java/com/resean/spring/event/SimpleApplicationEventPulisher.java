package com.resean.spring.event;

import java.util.ArrayList;
import java.util.List;

public class SimpleApplicationEventPulisher implements ApplicationEventPublisher{


    List<ApplicationListener> listeners = new ArrayList<>();

    @Override
    public void publisherEvent(ApplicationEvent applicationEvent) {
        for (ApplicationListener listener : listeners) {
            listener.onApplicationEvent(applicationEvent);
        }

    }

    @Override
    public void addApplicationListener(ApplicationListener applicationListener) {
        this.listeners.add(applicationListener);
    }
}
