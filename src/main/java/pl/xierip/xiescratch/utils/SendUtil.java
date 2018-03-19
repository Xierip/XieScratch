package pl.xierip.xiescratch.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import pl.xierip.xiescratch.configuration.MessagesConfig;

/**
 * Created by xierip on 10.04.17.
 * Web: http://xierip.pl
 */
public class SendUtil {
    public static void broadcast(final String message) {
        Bukkit.broadcastMessage(StringUtil.fixColors(message));
    }

    public static void broadcastParse(final String key, final String... replace) {
        if (MessagesConfig.getMessages().containsKey(key)) {
            broadcast(StringUtil.replace(MessagesConfig.getMessages().get(key), replace));
        }
    }

    public static void sendMessage(final CommandSender commandSender, final String message) {
        commandSender.sendMessage(StringUtil.fixColors(message));
    }

    public static void sendMessageParse(final CommandSender commandSender, final String key, final String... replace) {
        if (MessagesConfig.getMessages().containsKey(key)) {
            sendMessage(commandSender, StringUtil.replace(MessagesConfig.getMessages().get(key), replace));
        }
    }


}
