package org.hyperburger.autopickup.configmanager;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.hyperburger.autopickup.AutoPickup;


public class ConfigManager {

    private final AutoPickup plugin;
    private final FileConfiguration config;


    public ConfigManager(AutoPickup plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public boolean isAutoPickupEnabled(Player player) {
        return config.getBoolean("auto-pickup." + player.getUniqueId(), false);
    }

    public void setAutoPickupEnabled(Player player, boolean enabled) {
        config.set("auto-pickup." + player.getUniqueId(), enabled);
        plugin.saveConfig();
    }

    public String getEnabledMessage() {
        return ChatColor.translateAlternateColorCodes('&', config.getString("messages.enabled"));
    }

    public String getDisabledMessage() {
        return ChatColor.translateAlternateColorCodes('&', config.getString("messages.disabled"));
    }
}