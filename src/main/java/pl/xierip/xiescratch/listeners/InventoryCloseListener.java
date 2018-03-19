package pl.xierip.xiescratch.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import pl.xierip.xiescratch.managers.ScratchManager;

/**
 * Created by xierip on 25.07.17.
 * Web: http://xierip.pl
 */
public class InventoryCloseListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    protected void onEvent(final InventoryCloseEvent event) {
        ScratchManager.closeEvent(event);
    }
}