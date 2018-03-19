package pl.xierip.xiescratch.objects;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import pl.xierip.xiescratch.XieScratch;
import pl.xierip.xiescratch.configuration.MainConfig;
import pl.xierip.xiescratch.utils.ItemBuilder;
import pl.xierip.xiescratch.utils.RandomUtil;
import pl.xierip.xiescratch.utils.SendUtil;
import pl.xierip.xiescratch.utils.StringUtil;

import java.util.UUID;

/**
 * Created by xierip on 25.07.17.
 * Web: http://xierip.pl
 */
public @Data
class ScratchGui {
    private Inventory inventory;
    private ItemReward[] items;
    private Scratch scratch;
    private boolean used = false;
    private UUID uuid;

    public ScratchGui(Player player, Scratch scratch) {
        this.uuid = player.getUniqueId();
        this.scratch = scratch;
        this.items = scratch.randomItems();
        this.inventory = Bukkit.createInventory(null, scratch.getSize(), scratch.getName());
        for (int i = 0; i < this.inventory.getSize(); i++) {
            this.inventory.setItem(i, new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) RandomUtil.getIntBetween(0, 15))).setName(MainConfig.getClickItemName()).toItemStack());
        }
        player.closeInventory();
        player.openInventory(this.inventory);
    }

    public void click(Player player, int slot) {
        if (used) return;
        if ((items.length - 1) < slot) {
            return;
        }
        used = true;
        ItemReward item = items[slot];
        if (item.getFireworkEffect() != null) {
            Firework fw = player.getWorld().spawn(player.getEyeLocation(), Firework.class);
            fw.setMetadata("xiescratchnodamage", new NoDamageMetadata());
            FireworkMeta meta = fw.getFireworkMeta();
            meta.clearEffects();
            meta.addEffect(item.getFireworkEffect());
            meta.setPower(0);
            fw.setFireworkMeta(meta);
            new BukkitRunnable() {
                @Override
                public void run() {
                    fw.detonate();
                }
            }.runTask(XieScratch.getInstance());
        }
        item.execute(player);
        String name = (item.getDrop().hasItemMeta() && item.getDrop().getItemMeta().hasDisplayName()) ? item.getDrop().getItemMeta().getDisplayName() : StringUtil.replace(item.getDrop().getType().name().toLowerCase(), "_", " ");
        SendUtil.sendMessageParse(player, "info-open_scratch", "{NAME}", scratch.getName(), "{DROP}", name);
        SendUtil.broadcastParse("bc-open_scratch", "{NAME}", scratch.getName(), "{DROP}", name, "{PLAYER}", player.getName());
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, items[i].getDrop());
        }
    }
}
