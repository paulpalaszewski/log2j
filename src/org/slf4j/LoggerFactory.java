package org.slf4j;

import org.apache.commons.logging.Log;

/**
 * Drop-in replacement for simple logging facade 4 java (SLF4J). Not 100% complete/compatible with the original,
 * jet it covers the most commonly used functionality so you can compile + run almost any java software depending on slf4j.
 * @author Paul Palaszewski
 * @since 2020-01-14
 */
public final class LoggerFactory {
    public static Logger getLogger(Class<?> cl) {
        return new Log(cl.getName());
    }

    public static Logger getLogger(String name) {
        return new Log(name);
    }
}
