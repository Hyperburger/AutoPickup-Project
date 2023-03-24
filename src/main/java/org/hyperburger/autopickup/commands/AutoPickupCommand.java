package org.hyperburger.autopickup.commands;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.hyperburger.autopickup.AutoPickup;
import org.hyperburger.autopickup.configmanager.ConfigManager;

public class AutoPickupCommand implements CommandExecutor {

    // Keep a reference to the main plugin class
    private final AutoPickup plugin;
    private final ConfigManager configManager;

    public AutoPickupCommand(AutoPickup plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }

        Player player = (Player) sender;
        if (player.hasPermission("autopick.command")) {
            if (args.length == 0) {
                // Toggle auto-replant for the player
                boolean enabled = !configManager.isAutoPickupEnabled(player);
                configManager.setAutoPickupEnabled(player, enabled);
                player.sendMessage(enabled ? configManager.getEnabledMessage() : configManager.getDisabledMessage());
                return true;

            } else if (args.length == 1 && args[0].equalsIgnoreCase("enable")) {
                // Enable auto-pickup for the player
                configManager.setAutoPickupEnabled(player, true);
                player.sendMessage(configManager.getEnabledMessage());
                return true;

            } else if (args.length == 1 && args[0].equalsIgnoreCase("disable")) {
                // Disable auto-pickup for the player
                configManager.setAutoPickupEnabled(player, false);
                player.sendMessage(configManager.getDisabledMessage());
                return true;

            } else {
                player.sendMessage("Usage: /autopickup [enable|disable]");
                return true;
            }
        } else {
            player.sendMessage(ChatColor.RED + "You are now allowed to use this command.");
        }
        return true;
    }
}