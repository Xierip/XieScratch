package pl.xierip.xiescratch.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import pl.xierip.xiescratch.configuration.MainConfig;

import java.util.Arrays;

/**
 * Created by xierip on 12.04.17.
 * Web: http://xierip.pl
 */
public class ScratchCommand extends BukkitCommand {
    public ScratchCommand() {
        super("xiescratch", "Scratch command", "", Arrays.asList("scratch", "xscratch", "zdrapka", "zdrapki"));
    }

    @Override
    public boolean execute(final CommandSender commandSender, final String s, final String[] strings) {
        ((Player) commandSender).openInventory(MainConfig.getInventory());
        return true;
    }
}
