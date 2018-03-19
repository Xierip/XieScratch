package pl.xierip.xiescratch.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.xierip.xiescratch.XieScratch;
import pl.xierip.xiescratch.configuration.MainConfig;
import pl.xierip.xiescratch.configuration.MessagesConfig;
import pl.xierip.xiescratch.configuration.ScratchConfig;
import pl.xierip.xiescratch.managers.ScratchManager;
import pl.xierip.xiescratch.objects.Scratch;
import pl.xierip.xiescratch.utils.ItemStackUtil;
import pl.xierip.xiescratch.utils.NumberUtil;
import pl.xierip.xiescratch.utils.SendUtil;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * Created by xierip on 14.04.17.
 * Web: http://xierip.pl
 */
public class ScratchAdminCommand extends Command {
    public ScratchAdminCommand() {
        super("xiescratchadmin", "Scratch admin command", "", Arrays.asList("xiescratchadmin", "xsa", "xiesa", "scratchadmin"));
    }

    @Override
    public boolean execute(final CommandSender commandSender, final String s, final String[] args) {
        if (!commandSender.hasPermission("xiescratch.command.admin")) {
            SendUtil.sendMessage(commandSender, "&8>> &cBrak uprawnien! &8(&7xiescratch.command.admin&8)");
            return true;
        }
        if (args.length != 0) {
            switch (args[0].toLowerCase()) {
                case "give":
                    if (args.length >= 4) {
                        final Optional<Integer> integer = NumberUtil.parseInt(args[2]);
                        if (integer.isPresent()) {
                            final Integer amount = integer.get();
                            if (!ScratchManager.getScratch().containsKey(args[1].toLowerCase())) {
                                SendUtil.sendMessage(commandSender, "&8>> &cNie znaleziono zdrapki:&6 " + args[1]);
                                return true;
                            }
                            Scratch scratch = ScratchManager.getScratch().get(args[1].toLowerCase());
                            ItemStack clone = scratch.getItem().clone();
                            clone.setAmount(amount);
                            switch (args[3].toLowerCase()) {
                                case "all":
                                    for (final Player player : Bukkit.getOnlinePlayers()) {
                                        ItemStackUtil.giveOrDrop(player, player.getLocation(), clone);
                                        SendUtil.sendMessageParse(player, "info-give_all", "{AMOUNT}", args[2], "{NAME}", scratch.getName());
                                    }
                                    SendUtil.broadcastParse("bc-give_all", "{AMOUNT}", args[2], "{NAME}", scratch.getName());
                                    SendUtil.sendMessage(commandSender, "&8>> &aWszyscy na serwerze otrzymali po&6 " + amount + "szt &6" + scratch.getName() + "&a!");
                                    return true;
                                case "group":
                                    if (args.length != 5) {
                                        SendUtil.sendMessage(commandSender, " &8>> &c/xsa give &6<nazwa> <ilosc> &cgroup &6<grupa>");
                                        return true;
                                    }
                                    if (XieScratch.getPerms() == null) {
                                        SendUtil.sendMessage(commandSender, "&8>> &cAby dawac zdrapki grupie \n&8>> &cwymagany plugin &6Vault &c+&6 plugin na uprawnienia&7.");
                                        return true;
                                    }
                                    final String[] groups = XieScratch.getPerms().getGroups();
                                    if (!Arrays.asList(groups).contains(args[4])) {
                                        SendUtil.sendMessage(commandSender, "&8>> &cNie znaleziono grupy:&6 " + args[4]);
                                        SendUtil.sendMessage(commandSender, "&8>> &aDostepne grupy: &6" + StringUtils.join(groups, ", "));
                                        return true;
                                    }
                                    int gived = 0;
                                    for (final Player player : Bukkit.getOnlinePlayers()) {
                                        if (Arrays.asList(XieScratch.getPerms().getPlayerGroups(player)).contains(args[4])) {
                                            SendUtil.sendMessageParse(player, "info-give_group", "{AMOUNT}", args[2], "{NAME}", scratch.getName());
                                            ItemStackUtil.giveOrDrop(player, player.getLocation(), clone);
                                            gived++;
                                        }
                                    }
                                    if (gived != 0) {
                                        SendUtil.broadcastParse("bc-give_group", "{AMOUNT}", args[1], "{GROUP}", args[4], "{NAME}", scratch.getName());
                                        SendUtil.sendMessage(commandSender, "&8>> &6" + gived + " &aczlonkow grupy &6" + args[4] + " &aotrzymalo po&6 " + amount + "szt&6 " + scratch.getName() + "&a!");
                                    } else {
                                        SendUtil.sendMessage(commandSender, "&8>> &cZaden czlonek grupy&6 " + args[4] + "&c nie jest online!");
                                    }
                                    return true;
                                case "player":
                                    if (args.length != 5) {
                                        SendUtil.sendMessage(commandSender, "&8>> &c/xsa give &6<nazwa> <ilosc> &cplayer &6<nick>");
                                        return true;
                                    }
                                    final Player player = Bukkit.getPlayer(args[4]);
                                    if (player == null) {
                                        SendUtil.sendMessage(commandSender, "&cGracz '" + args[4] + "' jest offline!");
                                        return true;
                                    }
                                    ItemStackUtil.giveOrDrop(player, player.getLocation(), clone);
                                    SendUtil.sendMessageParse(player, "info-give_player", "{AMOUNT}", args[2], "{NAME}", scratch.getName());
                                    SendUtil.sendMessage(commandSender, "&aGracz '" + args[4] + "' otrzymal " + amount + "szt " + scratch.getName() + "!");
                                    return true;
                            }
                        }
                    }
                    SendUtil.sendMessage(commandSender, "    &8&l[&4&lXieScratch&8&l]&8:");
                    SendUtil.sendMessage(commandSender, "&8>> &c/xsa give &6<nazwa> <ilosc> &call");
                    SendUtil.sendMessage(commandSender, "&8>> &c/xsa give &6<nazwa> <ilosc> &cgroup &6<grupa>");
                    SendUtil.sendMessage(commandSender, "&8>> &c/xsa give &6<nazwa> <ilosc> &cplayer &6<nick>");
                    return true;
                case "list":
                    if (ScratchManager.getScratch().size() == 0) {
                        SendUtil.sendMessage(commandSender, "&8>> &c&cBrak zdrapek!");
                        return true;
                    }
                    SendUtil.sendMessage(commandSender, "&8>> &aZdrapki:");
                    for (Map.Entry<String, Scratch> entry : ScratchManager.getScratch().entrySet()) {
                        SendUtil.sendMessage(commandSender, "&8>> &aZwykla nazwa:&6 " + entry.getKey() + " &7>> &aWyswietlana nazwa:&6 " + entry.getValue().getName());
                    }
                    return true;
                case "reload":
                    if (MainConfig.loadMain(true) && ScratchConfig.loadDrop(true) && MainConfig.prepareInventory(true) && MessagesConfig.enable(XieScratch.getInstance())) {
                        SendUtil.sendMessage(commandSender, "&8>> &aPrzeladowano wszystko!");
                    } else {
                        SendUtil.sendMessage(commandSender, "&8>> &4Wystapil blad podczas przeladowywania wszystkiego, sprawdz konsole!");
                    }
                    return true;

            }
        }

        SendUtil.sendMessage(commandSender, "    &8&l[&4&lXieScratch&8&l]&8:");
        SendUtil.sendMessage(commandSender, "&8>> &c/xsa give &7- &aDawanie zdrapek");
        SendUtil.sendMessage(commandSender, "&8>> &c/xsa list &7- &aLista zdrapek");
        SendUtil.sendMessage(commandSender, "&8>> &c/xsa reload &7- &aPrzeladowywanie pluginu");
        return true;
    }

}