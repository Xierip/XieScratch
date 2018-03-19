package pl.xierip.xiescratch.utils;

import java.util.Optional;

/**
 * Created by xierip on 30.03.17.
 * Web: http://xierip.pl
 */
public class NumberUtil {

    public static Optional<Integer> parseInt(final String string) {
        try {
            return Optional.ofNullable(Integer.parseInt(string));
        } catch (final NumberFormatException ignore) {
            return Optional.empty();
        }
    }
}
