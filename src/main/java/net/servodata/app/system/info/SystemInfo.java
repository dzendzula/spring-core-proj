/*
 * $Id$
 */
package net.servodata.app.system.info;

import java.io.IOException;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author <a href="stepan.marek@doxologic.com">Stepan Marek</a>
 */
public class SystemInfo implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(SystemInfo.class);

    // --- methods ---

    @Override
    public void afterPropertiesSet() throws Exception {
        if (log.isInfoEnabled()) {
            StringWriter writer = new StringWriter(1024);
            store(writer);
            writer.append("---");
            log.info(writer.toString());
        }
    }

    private void store(StringWriter writer) throws IOException {
        SortedProperties properties = new SortedProperties();
        for (String name : System.getProperties().stringPropertyNames()) {
            properties.setProperty(name, System.getProperty(name));
        }
        properties.store(writer, null);
    }

}
