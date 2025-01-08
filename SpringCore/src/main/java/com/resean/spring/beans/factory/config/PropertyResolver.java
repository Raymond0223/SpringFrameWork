package com.resean.spring.beans.factory.config;

public interface PropertyResolver {
    boolean containsProperty(String key);
    String getProperty(String key);
    String getProperty(String key, String defaultValue);
    <T> T getProperty(String key, Class<T> targetType);
    <T> T getProperty(String key, Class<T> targetType, T defaultValue);
    <T> T getRequiredProperty(String key, Class<T> targetType);
    String requireProperty(String key) throws IllegalStateException;
    <T> T requireProperty(String key, Class<T> targetType) throws IllegalStateException;

}
