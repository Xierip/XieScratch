package pl.xierip.xiescratch.configuration;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import pl.xierip.xiescratch.XieScratch;
import pl.xierip.xiescratch.enums.LogType;
import pl.xierip.xiescratch.exceptions.ConfigException;
import pl.xierip.xiescratch.managers.ScratchManager;
import pl.xierip.xiescratch.objects.Config;
import pl.xierip.xiescratch.objects.ItemReward;
import pl.xierip.xiescratch.objects.Scratch;
import pl.xierip.xiescratch.utils.ColorUtil;
import pl.xierip.xiescratch.utils.RandomCollection;

/**
 * Created by xierip on 14.04.17.
 * Web: http://xierip.pl
 */
public class ScratchConfig {
    private static Config config;

    public static boolean enable(final Plugin plugin) {
        config = new Config(plugin, "scratch.yml");
        return loadDrop(false) && MainConfig.prepareInventory(false);
    }

    public static boolean loadDrop(final boolean reload) {
        if (reload) {
            config.reload();
            ScratchManager.getScratch().clear();
        }
        for (final String key : config.getSafeKeys("Xierip.XieScratch.Scratch")) {
            try {
                final String path = "Xierip.XieScratch.Scratch." + key;
                final ItemStack item = config.parseItemStack("Xierip.XieScratch.Scratch." + key + ".item");
                final String name = config.getColoredString(path + ".name");
                final RandomCollection<ItemReward> rewards = new RandomCollection<>();
                for (final String s : config.getKeys(path + ".drop")) {
                    final String path1 = path + ".drop." + s;
                    try {
                        final double chanceItem = config.getDouble(path1 + ".chance");
                        final ItemStack drop = config.parseItemStack(path1 + ".item");
                        ItemReward itemReward = new ItemReward(chanceItem, drop);
                        if (config.isSet(path1 + ".firework")) {
                            String colorString = config.getString(path1 + ".firework");
                            Color color = ColorUtil.getColorByName(colorString);
                            if (color == null) {
                                XieScratch.getLogging().log(LogType.INFO, "Not found color: '" + colorString + "'");
                            }
                            itemReward.setColor(color);
                        }
                        rewards.add(chanceItem, itemReward);
                    } catch (final ConfigException e) {
                        XieScratch.getLogging().log(LogType.INFO, "Error with loading drop: '" + key + "' reward: '" + s + "'");
                        XieScratch.getLogging().log(LogType.INFO, " Error: " + e.getMessage());
                    }
                }
                final int size = config.getInt("Xierip.XieScratch.Scratch." + key + ".size") * 9;
                ScratchManager.getScratch().put(key, new Scratch(name, rewards, item, size));
            } catch (final ConfigException e) {
                XieScratch.getLogging().log(LogType.INFO, "Error with loading drop: '" + key + "'");
                XieScratch.getLogging().log(LogType.INFO, " Error: " + e.getMessage());
            }
        }
        XieScratch.getLogging().log(LogType.INFO, "Loaded " + ScratchManager.getScratch().size() + " scratch");
        return true;
    }

}
