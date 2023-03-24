package org.hyperburger.autopickup;

import org.bukkit.plugin.java.JavaPlugin;
import org.hyperburger.autopickup.commands.AutoPickupCommand;
import org.hyperburger.autopickup.configmanager.ConfigManager;
import org.hyperburger.autopickup.listeners.AutoPickupListener;

public final class AutoPickup extends JavaPlugin {

    private ConfigManager configManager;
    @Override
    public void onEnable() {

        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        // Initialize the configuration manager
        configManager = new ConfigManager(this);

        // Register event listener
        getServer().getPluginManager().registerEvents(new AutoPickupListener(configManager, this), this);

        // Register command executor
        getCommand("autopickup").setExecutor(new AutoPickupCommand(this, configManager));
    }

    @Override
    public void onDisable() {

    }
}
