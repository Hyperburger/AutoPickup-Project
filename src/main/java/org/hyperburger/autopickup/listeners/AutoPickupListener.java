package org.hyperburger.autopickup.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.hyperburger.autopickup.AutoPickup;
import org.hyperburger.autopickup.configmanager.ConfigManager;

public class AutoPickupListener implements Listener {

    private final ConfigManager configManager;
    private final AutoPickup pickup;

    public AutoPickupListener(ConfigManager configManager, AutoPickup pickup) {
        this.configManager = configManager;
        this.pickup = pickup;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        // Check if auto-pickup is enabled for the player
        if (!configManager.isAutoPickupEnabled(event.getPlayer())) {
            return;
        }

        // Remove the block
        event.setDropItems(false);

        // Add items to player's inventory
        ItemStack drop = new ItemStack(event.getBlock().getType());
        Item item = event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), drop);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!item.isDead()) {
                    event.getPlayer().getInventory().addItem(drop);
                    item.remove();
                }
            }
        }.runTask(pickup);
    }

}