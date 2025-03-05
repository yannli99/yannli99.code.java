package com.example.learning.demo.log.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liyan
 */
public class LogbackDemo {

    // 获取Logger实例（推荐使用类.class方式）
    private static final Logger logger = LoggerFactory.getLogger(LogbackDemo.class);

    public static void main(String[] args) {
        // 不同日志级别示例
        logger.trace("This is TRACE level message");
        logger.debug("This is DEBUG level message");
        logger.info("This is INFO level message");
        logger.warn("This is WARN level message");
        logger.error("This is ERROR level message", new RuntimeException("Test Error"));

        // 参数化日志示例（推荐用法）
        String user = "Alice";
        int age = 30;
        logger.info("User {} is {} years old", user, age);

        // 性能敏感场景使用判断
        if (logger.isDebugEnabled()) {
            logger.debug("Expensive operation result: {}", expensiveOperation());
        }
    }

    private static String expensiveOperation() {
        // 模拟耗时操作
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("Operation interrupted", e);
        }
        return "Result";
    }
}
