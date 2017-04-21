package org.thoth.ejb.schedule;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.apache.log4j.Logger;

@Startup
@Singleton
public class HelloWorldSchedule {

    private Logger log = Logger.getLogger(HelloWorldSchedule.class);
    
    @Schedule(second = "*/1", minute = "*", hour = "*", persistent = false)
    public void doWork() {
        System.out.printf("HelloWorldSchedule! com.sun.aas.instanceRoot=\"%s\"%n", System.getProperty("com.sun.aas.instanceRoot"));
        log.debug(String.format("Hello world! millis=%s", String.valueOf(System.currentTimeMillis())));
    }
}
