package me.kanuunankuula.debugStick.listeners.properties;

import me.kanuunankuula.debugStick.DebugStick;
import me.kanuunankuula.debugStick.permissions.PropertyPermissions;
import me.kanuunankuula.debugStick.util.SchedulerUtil;
import org.bukkit.block.Block;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.type.*;
import org.bukkit.block.data.Lightable;
import org.bukkit.entity.Player;

public class RedstoneListener {

    private final DebugStick plugin;

    public RedstoneListener(DebugStick plugin) {
        this.plugin = plugin;
    }

    public void checkRedstoneProperties(Player player, Block block) {
        BlockData blockData = block.getBlockData();

        checkPowerablePermission(player, block, blockData);
        checkAnaloguePowerablePermission(player, block, blockData);
        checkRepeaterPermission(player, block, blockData);
        checkCommandBlockPermission(player, block, blockData);
        checkDaylightDetectorPermission(player, block, blockData);
        checkPistonPermission(player, block, blockData);
        checkRedstoneLampPermission(player, block, blockData);
        checkRedstoneWirePermission(player, block, blockData);
    }

    private void checkPowerablePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Powerable)) return;

        Powerable beforeClone = (Powerable) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Powerable) {
                Powerable after = (Powerable) updatedBlock.getBlockData();

                if (beforeClone.isPowered() != after.isPowered() &&
                        !player.hasPermission(PropertyPermissions.POWERED)) {
                    after.setPowered(beforeClone.isPowered());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle powered state.");
                }
            }
        }, 1L);
    }

    private void checkAnaloguePowerablePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof AnaloguePowerable)) return;

        AnaloguePowerable beforeClone = (AnaloguePowerable) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof AnaloguePowerable) {
                AnaloguePowerable after = (AnaloguePowerable) updatedBlock.getBlockData();

                if (beforeClone.getPower() != after.getPower() &&
                        !player.hasPermission(PropertyPermissions.POWERED)) {
                    after.setPower(beforeClone.getPower());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change power level.");
                }
            }
        }, 1L);
    }

    private void checkRepeaterPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Repeater)) return;

        Repeater beforeClone = (Repeater) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Repeater) {
                Repeater after = (Repeater) updatedBlock.getBlockData();

                if (beforeClone.isLocked() != after.isLocked() &&
                        !player.hasPermission(PropertyPermissions.POWERED)) {
                    after.setLocked(beforeClone.isLocked());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle repeater locked state.");
                }
            }
        }, 1L);
    }

    private void checkCommandBlockPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof CommandBlock)) return;

        CommandBlock beforeClone = (CommandBlock) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof CommandBlock) {
                CommandBlock after = (CommandBlock) updatedBlock.getBlockData();

                if (beforeClone.isConditional() != after.isConditional() &&
                        !player.hasPermission(PropertyPermissions.CONDITIONAL)) {
                    after.setConditional(beforeClone.isConditional());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle conditional state.");
                }
            }
        }, 1L);
    }

    private void checkDaylightDetectorPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof DaylightDetector)) return;

        DaylightDetector beforeClone = (DaylightDetector) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof DaylightDetector) {
                DaylightDetector after = (DaylightDetector) updatedBlock.getBlockData();

                if (beforeClone.isInverted() != after.isInverted() &&
                        !player.hasPermission(PropertyPermissions.INVERTED)) {
                    after.setInverted(beforeClone.isInverted());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle inverted state.");
                }
            }
        }, 1L);
    }

    private void checkPistonPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Piston)) return;

        Piston beforeClone = (Piston) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Piston) {
                Piston after = (Piston) updatedBlock.getBlockData();

                if (beforeClone.isExtended() != after.isExtended() &&
                        !player.hasPermission(PropertyPermissions.EXTENDED)) {
                    after.setExtended(beforeClone.isExtended());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle piston extended state.");
                }
            }
        }, 1L);
    }

    private void checkRedstoneLampPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Lightable)) return;
        if (!block.getType().name().contains("REDSTONE_LAMP")) return;

        Lightable beforeClone = (Lightable) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Lightable) {
                Lightable after = (Lightable) updatedBlock.getBlockData();

                if (beforeClone.isLit() != after.isLit() &&
                        !player.hasPermission(PropertyPermissions.LIT)) {
                    after.setLit(beforeClone.isLit());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle redstone lamp state.");
                }
            }
        }, 1L);
    }

    private void checkRedstoneWirePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof RedstoneWire)) return;

        RedstoneWire beforeClone = (RedstoneWire) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof RedstoneWire) {
                RedstoneWire after = (RedstoneWire) updatedBlock.getBlockData();

                for (org.bukkit.block.BlockFace face : new org.bukkit.block.BlockFace[]{
                        org.bukkit.block.BlockFace.NORTH, org.bukkit.block.BlockFace.SOUTH,
                        org.bukkit.block.BlockFace.EAST, org.bukkit.block.BlockFace.WEST}) {

                    if (!beforeClone.getFace(face).equals(after.getFace(face))) {
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
                            after.setFace(face, beforeClone.getFace(face));
                            updatedBlock.setBlockData(after);
                            player.sendMessage("§cYou don't have permission to change " + face.name().toLowerCase() + " redstone connection.");
                            return;
                        }
                    }
                }
            }
        }, 1L);
    }
}