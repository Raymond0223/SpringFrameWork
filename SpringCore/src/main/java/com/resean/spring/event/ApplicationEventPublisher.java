package com.resean.spring.event;

public interface ApplicationEventPublisher {
    void publisherEvent(ApplicationEvent applicationEvent);

    void addApplicationListener(ApplicationListener applicationListener);

}
