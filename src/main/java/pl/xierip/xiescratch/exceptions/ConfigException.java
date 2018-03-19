package pl.xierip.xiescratch.exceptions;

/**
 * Created by Xierip on 2016-12-22.
 * Web: http://xierip.pl
 */
public class ConfigException extends Exception {
    public ConfigException() {
    }

    public ConfigException(final String message) {
        super(message);
    }

    public ConfigException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ConfigException(final Throwable cause) {
        super(cause);
    }

    public ConfigException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
