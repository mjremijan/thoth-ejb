package org.thoth.ejb.timerservice;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import org.apache.log4j.Logger;

@Startup
@Singleton
public class MultipleInfoTimer {

    private Logger log = Logger.getLogger(MultipleInfoTimer.class);
    
    @Resource
    protected TimerService timerService;
    
    @PostConstruct
    public void initialize() {
        // timer 1
        {
            TimerConfig config
                = new TimerConfig();
            config.setInfo("Timer 1 INFO");            
            
            ScheduleExpression expression 
                = new ScheduleExpression();
            expression.second("*/1").minute("*").hour("*");
            
            timerService.createCalendarTimer(expression, config);
        }
        // timer 2
        {
            TimerConfig config
                = new TimerConfig();
            config.setInfo("Timer 2 INFO");
            
            ScheduleExpression expression 
                = new ScheduleExpression();
            expression.second("*/5").minute("*").hour("*");
            
            timerService.createCalendarTimer(expression, config);
        }
        // timer 3
        {
            TimerConfig config
                = new TimerConfig();
            config.setInfo("Timer 3 INFO");
            
            ScheduleExpression expression 
                = new ScheduleExpression();
            expression.second("*/15").minute("*").hour("*");
            
            timerService.createCalendarTimer(expression, config);
        }
    }
    
    @Timeout
    public void timeout(Timer timer) {
        String millisStr = String.valueOf(System.currentTimeMillis());
        String infoStr = String.valueOf(timer.getInfo());
        
        System.out.printf(
            "MultipleInfoTimer! %s \"%s\" com.sun.aas.instanceRoot=\"%s\"%n"
            , millisStr, infoStr, System.getProperty("com.sun.aas.instanceRoot"));
        
        log.debug(String.format(
            "MultipleInfoTimer! %s \"%s\""
            , millisStr, infoStr
        ));
    }
}
