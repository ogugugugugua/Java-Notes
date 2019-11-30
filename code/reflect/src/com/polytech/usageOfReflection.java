package com.polytech;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

public class usageOfReflection {
    public static void main(String[] args) {
        try {
            File file = new File("C:\\java笔记\\code\\reflect\\src\\com\\polytech\\object.config");
            Properties config = new Properties();
            config.load(new FileInputStream(file));
            String className = config.getProperty("class");
            String function = config.getProperty("function");

            Class classType = Class.forName(className);
            Constructor constructor = classType.getConstructor();
            Method method = classType.getMethod(function);
            Object object = constructor.newInstance();
            method.invoke(object);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
