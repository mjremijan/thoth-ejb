package org.thoth.ejb.systemproperty;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.apache.log4j.Logger;

@Startup
@Singleton
public class SystemPropertyEjb {

    private Logger log = Logger.getLogger(SystemPropertyEjb.class);
    
    @PostConstruct
    public void initialize() {
        String val
            = System.getProperty("test_property");
        System.out.printf("******** System property val=\"%s\"%n", val);
    }
}
