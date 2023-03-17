import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClassLogger {

    private static ClassLogger classLogger;
    private org.apache.logging.log4j.Logger logger;

    private ClassLogger() {}

    public static ClassLogger getInstance() {
        if (classLogger == null) {
            classLogger = new ClassLogger();
        }
        return classLogger;
    }

    public Logger getLogger() {
        if (logger == null) {
            logger = LogManager.getLogger();
        }
        return logger;
    }
}
