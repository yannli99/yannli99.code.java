package com.example.learning.demo.java.util.log;

import java.util.logging.Logger;

/**
 * @author liyan
 */
public class LogDemo {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(LogDemo.class.getName());
        logger.info("Hello World");
    }
}
