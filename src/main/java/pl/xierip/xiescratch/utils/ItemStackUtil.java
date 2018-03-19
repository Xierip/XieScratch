package pl.xierip.xiescratch.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by xierip on 30.03.17.
 * Web: http://xierip.pl
 */
public class ItemStackUtil {
    public static void giveOrDrop(final Player player, final Location location, final ItemStack itemStack) {
        for (final ItemStack stack : player.getInventory().addItem(itemStack).values()) {
            location.getWorld().dropItemNaturally(location, stack);
        }
    }
}
