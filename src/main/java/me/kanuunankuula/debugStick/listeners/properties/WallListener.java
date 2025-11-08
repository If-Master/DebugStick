package me.kanuunankuula.debugStick.listeners.properties;

import me.kanuunankuula.debugStick.DebugStick;
import me.kanuunankuula.debugStick.permissions.PropertyPermissions;
import me.kanuunankuula.debugStick.util.SchedulerUtil;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Wall;
import org.bukkit.entity.Player;

public class WallListener {
    private final DebugStick plugin;

    public WallListener(DebugStick plugin) {
        this.plugin = plugin;
    }

    public void checkWallProperties(Player player, Block block) {
        BlockData blockData = block.getBlockData();

        if (!(blockData instanceof Wall)) return;

        Wall beforeclone = (Wall) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Wall) {
                Wall after = (Wall) updatedBlock.getBlockData();

                for (org.bukkit.block.BlockFace face : new org.bukkit.block.BlockFace[]{
                        BlockFace.NORTH,
                        BlockFace.SOUTH,
                        BlockFace.EAST,
                        BlockFace.WEST

                }) {
                    if (!beforeclone.getHeight(face).equals(after.getHeight(face))) {
                        String permissionNode;
                        switch (face) {
                            case NORTH:
                                permissionNode = PropertyPermissions.NORTH;
                                break;
                            case SOUTH:
                                permissionNode = PropertyPermissions.SOUTH;
                                break;
                            case EAST:
                                permissionNode = PropertyPermissions.EAST;
                                break;
                            case WEST:
                                permissionNode = PropertyPermissions.WEST;
                                break;
                            default:
                                continue;

                        }
                        if (!player.hasPermission(permissionNode)) {
                            after.setHeight(face, beforeclone.getHeight(face));
                            updatedBlock.setBlockData(after);
                            player.sendMessage("§cYou don't have permission to change " + face.name().toLowerCase() + " wall connection.");
                        }
                    }
                }
                if (beforeclone.isUp() != after.isUp() && !player.hasPermission(PropertyPermissions.UP)) {
                    after.setUp(beforeclone.isUp());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change wall up connection");
                }
            }
        }, 1L);
    }
}
