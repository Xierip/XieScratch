package pl.xierip.xiescratch.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import pl.xierip.xiescratch.XieScratch;
import pl.xierip.xiescratch.enums.LogType;

import java.lang.reflect.Field;

/**
 * Created by xierip on 14.04.17.
 * Web: http://xierip.pl
 */
public class CommandUtil {

    private static CommandMap commandMap = null;

    public static CommandMap getCommandMap() {
        if (commandMap == null) {
            final Field field;
            try {
                field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                field.setAccessible(true);
                commandMap = (CommandMap) field.get(Bukkit.getServer());
            } catch (final Exception e) {
                XieScratch.getLogging().log(LogType.EXCEPTION, "Error with Commands Manager", e);
            }
        }
        return commandMap;
    }
}
