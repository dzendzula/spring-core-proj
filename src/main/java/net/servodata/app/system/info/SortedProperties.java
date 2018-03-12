/*
 * $Id$
 */
package net.servodata.app.system.info;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * @author <a href="stepan.marek@doxologic.com">Stepan Marek</a>
 */
class SortedProperties extends Properties {

    // --- constructor ---

    public SortedProperties() {
    }

    public SortedProperties(Properties defaults) {
        super(defaults);
    }

    // --- methods ---

    /*
    public Enumeration<Object> keys() {
        return Collections.enumeration(keys);
    }

    public Object put(Object key, Object value) {
        keys.add(key);
        return super.put(key, value);
    }
    */

    @Override
    public synchronized Enumeration keys() {
        Enumeration keysEnum = super.keys();
        List<String> keys = new ArrayList<>(size());
        while (keysEnum.hasMoreElements()) {
            keys.add((String) keysEnum.nextElement());
        }
        // razeni je alfabeticke (mozno dodelat komparator pro specialni znaky jako '.', '_', atd.)
        Collections.sort(keys);
        return Collections.enumeration(keys);
    }

}
