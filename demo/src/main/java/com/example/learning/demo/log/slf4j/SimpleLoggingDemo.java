package com.example.learning.demo.log.slf4j;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * slf4j mean simple logging facade for java
 * @author liyan
 */
public class SimpleLoggingDemo {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(SimpleLoggingDemo.class);
        logger.info("Hello World");
    }
}
