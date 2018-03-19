package pl.xierip.xiescratch.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pl.xierip.xiescratch.managers.ScratchManager;

/**
 * Created by xierip on 14.04.17.
 * Web: http://xierip.pl
 */
public class InventoryClickListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    protected void onEvent(final InventoryClickEvent event) {
        ScratchManager.clickEvent(event);
    }
}