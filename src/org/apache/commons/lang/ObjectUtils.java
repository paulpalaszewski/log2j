package org.apache.commons.lang;

/**
 * Small drop-in replacement that provides the most commonly used apache commons functions.
 * @author Paul Palaszewski
 * @since 15.09.2015
 */
public class ObjectUtils {
    public static boolean equals(Object o1, Object o2) {
        if (o1 == o2)
            return true;
        return o1 != null && o2 != null && o1.equals(o2);
    }
}
