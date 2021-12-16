package org.apache.commons.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple replacement for log4j to resolve dependency issues.
 * Redirects everything hardwired to java.util.logging.
 * It does not cover 100% of the original APIs! Jet it covers enough to run + compile most projects, extend on demand.
 * @author Paul Palaszewski
 * @since 15.09.2015
 */
public class Log implements org.slf4j.Logger {
    private final Logger logger;

    public Log(String name) {
        this.logger = Logger.getLogger(name);
    }

    public String getName() {
        return logger.getName();
    }

    // for a 3rd party library? don't care!
    public boolean isDebugEnabled() {
        return false;
    }

    // for a 3rd party library? no!
    public boolean isTraceEnabled() {
        return false;
    }


    public void error(String msg) {
        logger.severe(msg);
    }

    public void error(String msg, Throwable t) {
        logger.log(Level.SEVERE, msg, t);
    }

    public void error(String format, Object arg) {
        logger.log(Level.SEVERE, () -> slf4jFmt(format, arg));
    }

    public void error(String format, Object arg, Object arg2) {
        logger.log(Level.SEVERE, () -> slf4jFmt(format, arg, arg2));
    }

    public void error(String format, Object... params) {
        logger.log(Level.SEVERE, () -> slf4jFmt(format, params));
    }

    public void warn(String msg) {
        logger.warning(msg);
    }

    public void warn(String msg, Throwable t) {
        logger.log(Level.WARNING, msg, t);
    }

    public void warn(String format, Object arg) {
        logger.log(Level.WARNING, () -> slf4jFmt(format, arg));
    }

    public void warn(String format, Object arg, Object arg2) {
        logger.log(Level.WARNING, () -> slf4jFmt(format, arg, arg2));
    }

    public void warn(String format, Object... params) {
        logger.log(Level.WARNING, () -> slf4jFmt(format, params));
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void info(String msg, Throwable t) {
        logger.log(Level.INFO, msg, t);
    }

    public void info(String format, Object arg) {
        logger.log(Level.INFO, () -> slf4jFmt(format, arg));
    }

    public void info(String format, Object arg, Object arg2) {
        logger.log(Level.INFO, () -> slf4jFmt(format, arg, arg2));
    }

    public void info(String format, Object... params) {
        logger.log(Level.INFO, () -> slf4jFmt(format, params));
    }

    public void debug(String msg) {
        logger.finest(msg);
    }

    public void debug(String msg, Throwable t) {
        logger.log(Level.FINEST, msg, t);
    }

    public void debug(String format, Object arg) {
        logger.log(Level.FINEST, () -> slf4jFmt(format, arg));
    }

    public void debug(String format, Object arg, Object arg2) {
        logger.log(Level.FINEST, () -> slf4jFmt(format, arg, arg2));
    }

    public void debug(String format, Object... params) {
        logger.log(Level.FINEST, () -> slf4jFmt(format, params));
    }

    public void trace(String s) {
        debug(s);
    }

    public void trace(String format, Object arg) {
        logger.log(Level.FINEST, () -> slf4jFmt(format, arg));
    }

    public void trace(String format, Object arg, Object arg2) {
        logger.log(Level.FINEST, () -> slf4jFmt(format, arg, arg2));
    }

    public void trace(String format, Object... args) {
        logger.log(Level.FINEST, () -> slf4jFmt(format, args));
    }

    public void trace(String msg, Throwable t) {
        logger.log(Level.FINEST, msg, t);
    }

    // slf4j has {} as parameter placeholder. jdk Logger uses {1} {2} and so on
    private static String slf4jFmt(String s, Object... args) {
        if (args == null || args.length == 0)
            return s;
        int next = s.indexOf("{}");
        if (next < 0)
            return s;
        int i = 0;
        StringBuilder b = new StringBuilder(s.length() + 50);
        for (int k = 0; k < args.length; ++k) {
            int j = s.indexOf("{}", i);
            if (j == -1) {
                b.append(s, i, s.length());
                return b.toString();
            }
            if (j > 0 && s.charAt(j-1) == '\\') {
                if (j < 2 || s.charAt(j-2) != '\\') {
                    --k;
                    b.append(s, i, j - 1);
                    b.append('{');
                    i = j + 1;
                } else {
                    b.append(s, i, j - 1);
                    append(b, args[k]);
                    i = j + 2;
                }
            } else {
                b.append(s, i, j);
                append(b, args[k]);
                i = j + 2;
            }
        }
        b.append(s, i, s.length());
        return b.toString();
    }

    private static void append(StringBuilder b, Object o) {
        if (o == null) {
            b.append("null");
        } else {
            if (!o.getClass().isArray()) {
                b.append(o);
            } else {
                b.append('[');
                String sep = "";
                if (o instanceof byte[]) {
                    for (byte v : ((byte[])o)) {
                        b.append(sep).append(v);
                        sep = ", ";
                    }
                } else if (o instanceof char[]) {
                    for (char v : ((char[])o)) {
                        b.append(sep).append(v);
                        sep = ", ";
                    }
                } else if (o instanceof int[]) {
                    for (int v : ((int[])o)) {
                        b.append(sep).append(v);
                        sep = ", ";
                    }
                } else if (o instanceof long[]) {
                    for (long v : ((long[])o)) {
                        b.append(sep).append(v);
                        sep = ", ";
                    }
                } else if (o instanceof double[]) {
                    for (double v : ((double[])o)) {
                        b.append(sep).append(v);
                        sep = ", ";
                    }
                } else if (o instanceof boolean[]) {
                    for (boolean v : ((boolean[])o)) {
                        b.append(sep).append(v);
                        sep = ", ";
                    }
                } else if (o instanceof short[]) {
                    for (short v : ((short[])o)) {
                        b.append(sep).append(v);
                        sep = ", ";
                    }
                } else if (o instanceof float[]) {
                    for (float v : ((float[])o)) {
                        b.append(sep).append(v);
                        sep = ", ";
                    }
                } else {
                    for (Object v : ((Object[])o)) {
                        b.append(sep);
                        append(b, v);
                        sep = ", ";
                    }
                }
                b.append(']');
            }
        }
    }
}
