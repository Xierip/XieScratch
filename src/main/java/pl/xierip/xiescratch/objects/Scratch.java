package pl.xierip.xiescratch.objects;

import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.xierip.xiescratch.configuration.MainConfig;
import pl.xierip.xiescratch.managers.ScratchManager;
import pl.xierip.xiescratch.utils.RandomCollection;
import pl.xierip.xiescratch.utils.StringUtil;

import java.util.Random;

/**
 * Created by xierip on 29.03.17.
 * Web: http://xierip.pl
 */
public
@Data
class Scratch {
    private static Random random = new Random();
    private ItemStack display;
    private ItemStack item;
    private String name;
    private RandomCollection<ItemReward> rewards;
    private int size;

    public Scratch(final String name, final RandomCollection<ItemReward> rewards, final ItemStack listItem, int size) {
        this.name = name;
        this.rewards = rewards;
        this.item = listItem;
        this.size = size;
        this.display = item.clone();
        final ItemMeta itemMeta = this.display.getItemMeta();
        itemMeta.setLore(StringUtil.replace(MainConfig.getDisplayLore()));
        this.display.setItemMeta(itemMeta);
    }

    public void openInventory(Player player) {
        ScratchManager.getScratchGuiPlayers().put(player.getUniqueId(), new ScratchGui(player, this));
    }

    public ItemReward[] randomItems() {
        ItemReward[] rewards1 = new ItemReward[size];
        for (int i = 0; i < rewards1.length; i++) {
            rewards1[i] = rewards.next();
        }
        return rewards1;
    }
}
