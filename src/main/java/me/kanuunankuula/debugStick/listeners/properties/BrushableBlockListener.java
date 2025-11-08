package me.kanuunankuula.debugStick.listeners.properties;

import me.kanuunankuula.debugStick.DebugStick;
import me.kanuunankuula.debugStick.permissions.PropertyPermissions;
import me.kanuunankuula.debugStick.util.SchedulerUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class BrushableBlockListener {

    private final DebugStick plugin;
    private Class<?> brushableClass;
    private Method getDustedMethod;
    private Method setDustedMethod;
    private boolean brushableSupported;

    public BrushableBlockListener(DebugStick plugin) {
        this.plugin = plugin;
        initializeReflection();
    }

    private void initializeReflection() {
        try {
            brushableClass = Class.forName("org.bukkit.block.data.Brushable");
            getDustedMethod = brushableClass.getMethod("getDusted");
            setDustedMethod = brushableClass.getMethod("setDusted", int.class);
            brushableSupported = true;
            plugin.getLogger().info("Brushable block support enabled (dusted property)");
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            brushableSupported = false;
            plugin.getLogger().info("Brushable blocks not supported in this version");
        }
    }

    public void checkBrushableProperties(Player player, Block block) {
        if (!brushableSupported) return;

        Material blockType = block.getType();
        if (blockType != Material.SUSPICIOUS_SAND && blockType != Material.SUSPICIOUS_GRAVEL) {
            return;
        }

        BlockData blockData = block.getBlockData();
        if (!brushableClass.isInstance(blockData)) {
            return;
        }

        try {
            Object beforeClone = blockData.clone();

            SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
                Block updatedBlock = block.getLocation().getBlock();
                BlockData updatedBlockData = updatedBlock.getBlockData();

                if (!brushableClass.isInstance(updatedBlockData)) {
                    return;
                }

                try {
                    int beforeDusted = (int) getDustedMethod.invoke(beforeClone);
                    int afterDusted = (int) getDustedMethod.invoke(updatedBlockData);

                    if (beforeDusted != afterDusted && !player.hasPermission(PropertyPermissions.DUSTED)) {
                        setDustedMethod.invoke(updatedBlockData, beforeDusted);
                        updatedBlock.setBlockData(updatedBlockData);
                        player.sendMessage("§cYou don't have permission to change dusted level.");
                    }
                } catch (Exception e) {
                    plugin.getLogger().warning("Error checking dusted state: " + e.getMessage());
                }
            }, 1L);

        } catch (Exception e) {
            plugin.getLogger().warning("Error cloning brushable block data: " + e.getMessage());
        }
    }
}