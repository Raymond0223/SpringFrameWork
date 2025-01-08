package com.resean.spring.beans.factory.config;

import com.resean.spring.core.BeanException;

public interface PropertyResolver {
    boolean containsProperty(String key);
    String getProperty(String key);
    String getProperty(String key, String defaultValue);
    <T> T getProperty(String key, Class<T> targetType);
    <T> T getProperty(String key, Class<T> targetType, T defaultValue);
    <T> T getRequiredProperty(String key, Class<T> targetType);
    String requireProperty(String key) throws IllegalStateException;
    <T> T requireProperty(String key, Class<T> targetType) throws IllegalStateException;

    String resolvePlaceholders(String text);

    String resolveRequiredPlaceholders(String text) throws IllegalStateException;

}
