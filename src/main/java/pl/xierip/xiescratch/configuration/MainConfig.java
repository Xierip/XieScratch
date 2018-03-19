package pl.xierip.xiescratch.configuration;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import pl.xierip.xiescratch.XieScratch;
import pl.xierip.xiescratch.exceptions.ConfigException;
import pl.xierip.xiescratch.managers.ScratchManager;
import pl.xierip.xiescratch.objects.Config;
import pl.xierip.xiescratch.objects.ItemReward;
import pl.xierip.xiescratch.objects.Scratch;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.xierip.xiescratch.enums.LogType.INFO;

/**
 * Created by xierip on 28.03.17.
 * Web: http://xierip.pl
 */
public class MainConfig {
    @Getter
    private static ItemStack backButton;
    private static Config config;
    @Getter
    private static List<String> displayLore, subDisplayLore;
    @Getter
    private static Map<ItemStack, Inventory> inventories = new HashMap<>();
    @Getter
    private static Inventory inventory;
    @Getter
    private static String inventoryNameMain;
    @Getter
    private static String inventoryNameSub, clickItemName;

    public static boolean enable(final Plugin plugin) {
        config = new Config(plugin, "config.yml");
        return loadMain(false) && ScratchConfig.enable(plugin) && MessagesConfig.enable(plugin);
    }

    public static boolean loadMain(final boolean reload) {
        if (reload) {
            config.reload();
        }
        try {
            clickItemName = config.getColoredString("Xierip.XieScratch.Open.clickItemName");
            displayLore = config.getColoredStringList("Xierip.XieScratch.Display.guiLore");
            subDisplayLore = config.getColoredStringList("Xierip.XieScratch.Display.subGuiLore");
            return true;
        } catch (final ConfigException e) {
            XieScratch.getLogging().log(INFO, "Error with loading config:");
            XieScratch.getLogging().log(INFO, " Error: " + e.getMessage());
            return false;
        }
    }

    public static boolean prepareInventory(final boolean reload) {
        try {
            if (reload) {
                config.reload();
                inventories.clear();
            }
            backButton = config.parseItemStack("Xierip.XieScratch.Inventory.backButton");
            inventoryNameMain = config.getColoredString("Xierip.XieScratch.Inventory.name");
            inventoryNameSub = config.getColoredString("Xierip.XieScratch.Inventory.nameSub");
            final Collection<Scratch> all = ScratchManager.getScratch().values();
            inventory = Bukkit.createInventory(null, ((all.size() + 1) % 9 == 0 ? (all.size() + 1) : ((all.size() + 1) / 9 * 9) + 9), inventoryNameMain);
            for (final Scratch scratch : all) {
                final ItemStack itemStack = scratch.getDisplay();
                if (itemStack == null) continue;
                inventory.addItem(itemStack);
                final Inventory inv1 = Bukkit.createInventory(null, (scratch.getRewards().size() + 1) % 9 == 0 ? (scratch.getRewards().size() + 1) : ((scratch.getRewards().size() + 1) / 9 * 9) + 9, inventoryNameSub + scratch.getName());
                for (final ItemReward reward : scratch.getRewards().getAll().values()) {
                    final ItemStack display = reward.getDisplay();
                    if (display == null) {
                        continue;
                    }
                    inv1.addItem(display);
                }
                inv1.setItem(inv1.getSize() - 1, backButton);
                inventories.put(itemStack, inv1);
            }
            return true;
        } catch (final ConfigException e) {
            XieScratch.getLogging().log(INFO, "Error with creating inventory:");
            XieScratch.getLogging().log(INFO, " Error: " + e.getMessage());
            return false;
        }
    }
}
