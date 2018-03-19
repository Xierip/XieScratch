package pl.xierip.xiescratch;

import lombok.Getter;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import pl.xierip.xiescratch.commands.ScratchAdminCommand;
import pl.xierip.xiescratch.commands.ScratchCommand;
import pl.xierip.xiescratch.configuration.MainConfig;
import pl.xierip.xiescratch.enums.LogType;
import pl.xierip.xiescratch.listeners.EntityDamageByEntityListener;
import pl.xierip.xiescratch.listeners.InventoryClickListener;
import pl.xierip.xiescratch.listeners.InventoryCloseListener;
import pl.xierip.xiescratch.listeners.PlayerInteractListener;
import pl.xierip.xiescratch.managers.ScratchManager;
import pl.xierip.xiescratch.objects.Logging;
import pl.xierip.xiescratch.utils.CommandUtil;
import pl.xierip.xiescratch.utils.Enchantments;

/**
 * Created by xierip on 28.03.17.
 * Web: http://xierip.pl
 */
public class XieScratch extends JavaPlugin {
    @Getter
    private static XieScratch instance;
    @Getter
    private static Logging logging = new Logging("XieScratch");
    @Getter
    private static Permission perms = null;

    public XieScratch() {
        XieScratch.instance = this;
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        logging.log(LogType.INFO, "Darmowy plugin na zdrapki!");
        logging.log(LogType.INFO, "by. Xierip");
        logging.log(LogType.INFO, "Sponsorowany przez serwer mcwar.pl");
        if (!this.getDescription().getAuthors().get(0).equalsIgnoreCase("Xierip")) {
            this.getPluginLoader().disablePlugin(this);
            return;
        }
        if (!this.getDescription().getWebsite().equalsIgnoreCase("http://xierip.pl/")) {
            this.getPluginLoader().disablePlugin(this);
            return;
        }
        if (!this.getDescription().getName().equalsIgnoreCase("XieScratch")) {
            this.getPluginLoader().disablePlugin(this);
            return;
        }
        if (setupPermissions()) {
            logging.log(LogType.INFO, "Enabled Permissions support!");
        }
        Enchantments.enable();
        if (!MainConfig.enable(this)) {
            this.getPluginLoader().disablePlugin(this);
            return;
        }
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        final CommandMap commandMap = CommandUtil.getCommandMap();
        commandMap.register("xiescratch", new ScratchCommand());
        commandMap.register("xiescratch", new ScratchAdminCommand());
        Metrics metrics = new Metrics(this);
        metrics.addCustomChart(new Metrics.SimplePie("scratch") {
            @Override
            public String getValue() {
                return String.valueOf(ScratchManager.getScratch().size());
            }
        });
    }

    @Override
    public void onLoad() {

    }

    private boolean setupPermissions() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        final RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
}