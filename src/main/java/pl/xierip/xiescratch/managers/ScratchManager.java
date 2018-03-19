package pl.xierip.xiescratch.managers;


import lombok.Getter;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.xierip.xiescratch.XieScratch;
import pl.xierip.xiescratch.configuration.MainConfig;
import pl.xierip.xiescratch.objects.Scratch;
import pl.xierip.xiescratch.objects.ScratchGui;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xierip on 29.03.17.
 * Web: http://xierip.pl
 */
public class ScratchManager {
    @Getter
    private static Map<String, Scratch> scratch = new HashMap<>();
    @Getter
    private static Map<UUID, ScratchGui> scratchGuiPlayers = new HashMap<>();

    public static void clickEvent(InventoryClickEvent event) {
        if (event.getInventory() == null) return;
        if (event.getInventory().getTitle() == null) {
            return;
        }
        if (MainConfig.getInventoryNameMain().equalsIgnoreCase(event.getInventory().getTitle())) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                if (MainConfig.getInventoryNameMain().equalsIgnoreCase(event.getClickedInventory().getTitle())) {
                    if (MainConfig.getInventories().containsKey(event.getCurrentItem())) {
                        event.getWhoClicked().closeInventory();
                        event.getWhoClicked().openInventory(MainConfig.getInventories().get(event.getCurrentItem()));
                    }
                }
            }

        } else if (event.getInventory().getTitle().startsWith(MainConfig.getInventoryNameSub())) {
            if (MainConfig.getBackButton().isSimilar(event.getCurrentItem())) {
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().openInventory(MainConfig.getInventory());
            }
            event.setCancelled(true);
        } else if (ScratchManager.getScratchGuiPlayers().containsKey(event.getWhoClicked().getUniqueId())) {
            event.setCancelled(true);
            if (event.getClickedInventory() == null) return;
            ScratchGui scratchGui = ScratchManager.getScratchGuiPlayers().get(event.getWhoClicked().getUniqueId());
            if (!scratchGui.getInventory().getTitle().equalsIgnoreCase(event.getClickedInventory().getTitle())) {
                return;
            }
            scratchGui.click(((Player) event.getWhoClicked()), event.getSlot());
        }
    }

    public static void closeEvent(InventoryCloseEvent event) {
        if (!ScratchManager.getScratchGuiPlayers().containsKey(event.getPlayer().getUniqueId())) {
            return;
        }
        ScratchGui scratchGui = ScratchManager.getScratchGuiPlayers().get(event.getPlayer().getUniqueId());

        ScratchManager.getScratchGuiPlayers().remove(event.getPlayer().getUniqueId());
        if (!scratchGui.isUsed()) {
            event.getPlayer().getInventory().addItem(scratchGui.getScratch().getItem());
        }
    }

    public static void damageEvent(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        if (!(event.getDamager() instanceof Firework)) {
            return;
        }
        if (event.getDamager().hasMetadata("xiescratchnodamage")) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }

    public static void interactEvent(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (event.getItem() == null) return;
        for (Scratch scratch : ScratchManager.getScratch().values()) {
            if (event.getItem().isSimilar(scratch.getItem())) {
                event.setCancelled(true);
                if (ScratchManager.getScratchGuiPlayers().containsKey(event.getPlayer().getUniqueId())) {
                    return;
                }
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!event.getPlayer().getInventory().containsAtLeast(scratch.getItem(), 1)) return;
                        event.getPlayer().getInventory().removeItem(scratch.getItem());
                        scratch.openInventory(event.getPlayer());
                    }
                }.runTask(XieScratch.getInstance());
            }
        }
    }
}
