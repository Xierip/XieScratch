package pl.xierip.xiescratch.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.xierip.xiescratch.managers.ScratchManager;


/**
 * Created by xierip on 28.03.17.
 * Web: http://xierip.pl
 */
public class PlayerInteractListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    protected void onEvent(final PlayerInteractEvent event) {
        ScratchManager.interactEvent(event);
    }
}