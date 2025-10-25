package me.kanuunankuula.debugStick.listeners;

import me.kanuunankuula.debugStick.DebugStick;
import me.kanuunankuula.debugStick.listeners.properties.BasicBlockListener;
import me.kanuunankuula.debugStick.listeners.properties.RedstoneListener;
import me.kanuunankuula.debugStick.listeners.properties.SpecificBlockListener;
import me.kanuunankuula.debugStick.listeners.properties.ExtendedPropertiesListener;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class DebugStickListener implements Listener {

    private final DebugStick plugin;
    private final BasicBlockListener basicBlockListener;
    private final RedstoneListener redstoneListener;
    private final SpecificBlockListener specificBlockListener;
    private final ExtendedPropertiesListener extendedPropertiesListener;

    public DebugStickListener(DebugStick plugin) {
        this.plugin = plugin;
        this.basicBlockListener = new BasicBlockListener(plugin);
        this.redstoneListener = new RedstoneListener(plugin);
        this.specificBlockListener = new SpecificBlockListener(plugin);
        this.extendedPropertiesListener = new ExtendedPropertiesListener(plugin);
    }

    @EventHandler
    public void onDebugStickUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Block block = event.getClickedBlock();

        if (event.getHand() != EquipmentSlot.HAND ||
                item == null ||
                item.getType() != Material.DEBUG_STICK ||
                block == null) {
            return;
        }
        if (!plugin.getConfigManager().isBlockAllowed(block.getType())) {
            event.setCancelled(true);
            player.sendMessage(plugin.getConfigManager().getBlockedBlockMessage());
            return;
        }

        basicBlockListener.checkBasicProperties(player, block);
        redstoneListener.checkRedstoneProperties(player, block);
        specificBlockListener.checkSpecificBlockProperties(player, block);
        extendedPropertiesListener.checkExtendedProperties(player, block);
    }

    @EventHandler
    public void onDebugStickDrop(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();

        if (item.getType() == Material.DEBUG_STICK) {
            if (plugin.getConfigManager().isDeleteDropped()) {
                event.getItemDrop().remove();
                if (plugin.getConfigManager().showDebugStickDroppedMessage()) {
                    event.getPlayer().sendMessage("§cThe debug stick was deleted.");
                }
            }
        }
    }
}