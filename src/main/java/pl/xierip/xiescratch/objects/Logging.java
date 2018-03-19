package pl.xierip.xiescratch.objects;

import org.bukkit.Bukkit;
import pl.xierip.xiescratch.enums.LogType;
import pl.xierip.xiescratch.utils.StringUtil;

/**
 * @author Xierip
 */
public final class Logging {
    private final String pluginName;

    public Logging(final String pluginName) {
        this.pluginName = pluginName;
    }

    public void log(final LogType logType, final String message) {
        switch (logType) {
            case NORMAL:
                logConsole(message);
                break;
            case INFO:
                logConsole("&e[INFO] &f" + message);
                break;
            case ERROR:
                logConsole("&c[ERROR] &f" + message);
                break;
            case DEBUG:
                logConsole("&c[DEBUG] &f" + message);
                break;
            case EXCEPTION:
                logConsole("&c[EXCEPTION] &f" + message);
                break;
            case CLEAR:
                logConsoleClear(message);
                break;

        }

    }

    public void log(final LogType logType, final String message, final Exception exception) {
        if (exception == null) {
            log(logType, message);
        } else {
            log(LogType.EXCEPTION, "EXCEPTION: " + exception.toString());
            log(LogType.EXCEPTION, "");
            log(LogType.EXCEPTION, "System:");
            log(LogType.EXCEPTION, "  Name: " + pluginName);
            log(LogType.EXCEPTION, "  Java Version: " + System.getProperty("java.version"));
            log(LogType.EXCEPTION, "");
            log(LogType.EXCEPTION, "ERROR: " + message);
            log(LogType.EXCEPTION, "  Message: " + exception.getMessage());
            log(LogType.EXCEPTION, "  Localized Message: " + exception.getLocalizedMessage());
            if (exception.getStackTrace() != null) {
                log(LogType.EXCEPTION, "  StackTrace: ");
                exception.printStackTrace(System.out);
            }
        }
    }

    private void logConsole(final String message) {
        Bukkit.getConsoleSender().sendMessage(StringUtil.fixColors("[" + pluginName + "] " + message + "&r"));
    }

    private void logConsoleClear(final String message) {
        Bukkit.getConsoleSender().sendMessage(StringUtil.fixColors(message + "&r"));
    }

}
