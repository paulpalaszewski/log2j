package org.slf4j;

/**
 * Drop-in replacement for simple logging facade 4 java (SLF4J). Not 100% complete/compatible with the original,
 * jet it covers the most commonly used functionality so you can compile + run almost any java software depending on slf4j.
 * @author Paul Palaszewski
 * @since 2020-01-14
 */
public interface Logger {
    String getName();

    boolean isTraceEnabled();

    boolean isDebugEnabled();

    void error(String msg);
    void error(String msg, Throwable t);
    void error(String format, Object arg);
    void error(String format, Object arg, Object arg2);
    void error(String msg, Object... params);

    void warn(String msg);
    void warn(String msg, Throwable t);
    void warn(String format, Object arg);
    void warn(String format, Object arg, Object arg2);
    void warn(String msg, Object... params);

    void info(String msg);
    void info(String msg, Throwable t);
    void info(String format, Object arg);
    void info(String format, Object arg, Object arg2);
    void info(String msg, Object... params);

    void debug(String msg);
    void debug(String msg, Throwable t);
    void debug(String format, Object arg);
    void debug(String format, Object arg, Object arg2);
    void debug(String format, Object... args);

    void trace(String s);
    void trace(String s, Throwable t);
    void trace(String format, Object arg);
    void trace(String format, Object arg, Object arg2);
    void trace(String s, Object... params);
}
