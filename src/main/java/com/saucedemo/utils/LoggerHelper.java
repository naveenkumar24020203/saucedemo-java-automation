package com.saucedemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoggerHelper {
    private LoggerHelper() {
    }

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
