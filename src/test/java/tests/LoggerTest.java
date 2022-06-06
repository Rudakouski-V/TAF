package tests;

import baseEntities.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class LoggerTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(LoggerTest.class);

    @Test
    public void logLevelsTest() {
        logger.trace("Trace MSG: Начало теста...");
        logger.debug("Debug MSG: ...");
        logger.info("Info MSG: ...");
        logger.warn("Warn MSG: ...");
        logger.error("Error MSG: ...");
        logger.fatal("Fatal MSG: ...");
    }

    @Test
    public void browserTest() {
        logger.trace("Начало теста...");

        logger.trace("Browser's resolution is " + driver.manage().window().getSize());
    }
}
