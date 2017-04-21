package org.thoth.ejb.timerservice;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import org.apache.log4j.Logger;

@Startup
@Singleton
public class HelloWorldTimer {

    private Logger log = Logger.getLogger(HelloWorldTimer.class);
    
    @Resource
    protected TimerService timerService;
    
    @PostConstruct
    public void initialize(){
        ScheduleExpression expression = new ScheduleExpression();
        expression.second("*/1").minute("*").hour("*");
        timerService.createCalendarTimer(expression);
    }
    
    @Timeout
    public void timeout(Timer timer) {
        String millisStr = String.valueOf(System.currentTimeMillis());
        String timerStr = String.valueOf(timer);
        
        System.out.printf(
            "HelloWorldTimer! %s %s com.sun.aas.instanceRoot=\"%s\"%n"
            , millisStr, timerStr, System.getProperty("com.sun.aas.instanceRoot"));
        
        log.debug(String.format(
            "HelloWorldTimer! %s %s"
            , millisStr, timerStr
        ));
    }
}
