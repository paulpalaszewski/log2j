package org.apache.commons.logging;

/**
 * @author Paul Palaszewski
 * @since 15.09.2015
 */
public class LogFactory {
    public static Log getLog(Class cl) {
        return new Log(cl.getName());
    }
}
