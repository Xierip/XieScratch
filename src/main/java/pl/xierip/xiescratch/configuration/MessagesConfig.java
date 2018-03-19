package pl.xierip.xiescratch.configuration;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.bukkit.plugin.Plugin;
import pl.xierip.xiescratch.XieScratch;
import pl.xierip.xiescratch.enums.LogType;
import pl.xierip.xiescratch.exceptions.ConfigException;
import pl.xierip.xiescratch.objects.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xierip on 14.04.17.
 * Web: http://xierip.pl
 */
public class MessagesConfig {
    private static Config config;
    @Getter
    private static Map<String, String> messages = new HashMap<>();

    public static boolean enable(final Plugin plugin) {
        config = new Config(plugin, "messages.yml");
        return loadMessages(false);
    }

    public static boolean loadMessages(final boolean reload) {
        try {
            if (reload) {
                config.reload();
                messages.clear();
            }
            for (final String s : config.getSafeKeys("Xierip.XieScratch.Messages")) {
                String string = "off";
                final String path = "Xierip.XieScratch.Messages." + s;
                if (config.isInstanceOf(path, String.class)) {
                    string = config.getColoredString(path);
                } else if (config.isInstanceOf(path, List.class)) {
                    string = StringUtils.join(config.getColoredStringList(path), "\n");
                }
                if (!string.equalsIgnoreCase("off")) {
                    messages.put(s, string);
                }
            }
            return true;
        } catch (final ConfigException e) {
            XieScratch.getLogging().log(LogType.INFO, "Error with loading messages:");
            XieScratch.getLogging().log(LogType.INFO, " Error: " + e.getMessage());
            return false;
        }
    }
}
