package me.kanuunankuula.debugStick.listeners.properties;

import me.kanuunankuula.debugStick.DebugStick;
import me.kanuunankuula.debugStick.permissions.PropertyPermissions;
import me.kanuunankuula.debugStick.util.SchedulerUtil;
import org.bukkit.block.Block;
import org.bukkit.block.data.*;
import org.bukkit.entity.Player;

public class BasicBlockListener {

    private final DebugStick plugin;

    public BasicBlockListener(DebugStick plugin) {
        this.plugin = plugin;
    }

    public void checkBasicProperties(Player player, Block block) {
        BlockData blockData = block.getBlockData();

        checkWaterloggedPermission(player, block, blockData);
        checkDirectionalPermission(player, block, blockData);
        checkOrientablePermission(player, block, blockData);
        checkOpenablePermission(player, block, blockData);
        checkBisectedPermission(player, block, blockData);
        checkRotatablePermission(player, block, blockData);
        checkLightablePermission(player, block, blockData);
        checkAgeablePermission(player, block, blockData);
        checkLevelledPermission(player, block, blockData);
        checkAttachablePermission(player, block, blockData);
        checkHangingPermission(player, block, blockData);
        checkSnowyPermission(player, block, blockData);
    }

    private void checkWaterloggedPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Waterlogged)) return;

        Waterlogged beforeClone = (Waterlogged) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Waterlogged) {
                Waterlogged after = (Waterlogged) updatedBlock.getBlockData();

                if (beforeClone.isWaterlogged() != after.isWaterlogged() &&
                        !player.hasPermission(PropertyPermissions.WATERLOGGED)) {
                    after.setWaterlogged(beforeClone.isWaterlogged());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle waterlogging.");
                }
            }
        }, 1L);
    }

    private void checkDirectionalPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Directional)) return;

        Directional beforeClone = (Directional) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Directional) {
                Directional after = (Directional) updatedBlock.getBlockData();

                if (!beforeClone.getFacing().equals(after.getFacing()) &&
                        !player.hasPermission(PropertyPermissions.FACING)) {
                    after.setFacing(beforeClone.getFacing());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change facing direction.");
                }
            }
        }, 1L);
    }

    private void checkOrientablePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Orientable)) return;

        Orientable beforeClone = (Orientable) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Orientable) {
                Orientable after = (Orientable) updatedBlock.getBlockData();

                if (!beforeClone.getAxis().equals(after.getAxis()) &&
                        !player.hasPermission(PropertyPermissions.AXIS)) {
                    after.setAxis(beforeClone.getAxis());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change block axis.");
                }
            }
        }, 1L);
    }

    private void checkOpenablePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Openable)) return;

        Openable beforeClone = (Openable) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Openable) {
                Openable after = (Openable) updatedBlock.getBlockData();

                if (beforeClone.isOpen() != after.isOpen() &&
                        !player.hasPermission(PropertyPermissions.OPEN)) {
                    after.setOpen(beforeClone.isOpen());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle open state.");
                }
            }
        }, 1L);
    }

    private void checkBisectedPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Bisected)) return;

        Bisected beforeClone = (Bisected) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Bisected) {
                Bisected after = (Bisected) updatedBlock.getBlockData();

                if (!beforeClone.getHalf().equals(after.getHalf()) &&
                        !player.hasPermission(PropertyPermissions.HALF)) {
                    after.setHalf(beforeClone.getHalf());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change block half.");
                }
            }
        }, 1L);
    }

    private void checkRotatablePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Rotatable)) return;

        Rotatable beforeClone = (Rotatable) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Rotatable) {
                Rotatable after = (Rotatable) updatedBlock.getBlockData();

                if (!beforeClone.getRotation().equals(after.getRotation()) &&
                        !player.hasPermission(PropertyPermissions.ROTATION)) {
                    after.setRotation(beforeClone.getRotation());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change block rotation.");
                }
            }
        }, 1L);
    }

    private void checkLightablePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Lightable)) return;

        Lightable beforeClone = (Lightable) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Lightable) {
                Lightable after = (Lightable) updatedBlock.getBlockData();

                if (beforeClone.isLit() != after.isLit() &&
                        !player.hasPermission(PropertyPermissions.LIT)) {
                    after.setLit(beforeClone.isLit());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle lit state.");
                }
            }
        }, 1L);
    }

    private void checkAgeablePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Ageable)) return;

        Ageable beforeClone = (Ageable) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Ageable) {
                Ageable after = (Ageable) updatedBlock.getBlockData();

                if (beforeClone.getAge() != after.getAge() &&
                        !player.hasPermission(PropertyPermissions.AGE)) {
                    after.setAge(beforeClone.getAge());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change block age.");
                }
            }
        }, 1L);
    }

    private void checkLevelledPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Levelled)) return;

        Levelled beforeClone = (Levelled) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Levelled) {
                Levelled after = (Levelled) updatedBlock.getBlockData();

                if (beforeClone.getLevel() != after.getLevel() &&
                        !player.hasPermission(PropertyPermissions.LEVEL)) {
                    after.setLevel(beforeClone.getLevel());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change block level.");
                }
            }
        }, 1L);
    }

    private void checkAttachablePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Attachable)) return;

        Attachable beforeClone = (Attachable) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Attachable) {
                Attachable after = (Attachable) updatedBlock.getBlockData();

                if (beforeClone.isAttached() != after.isAttached() &&
                        !player.hasPermission(PropertyPermissions.ATTACHED)) {
                    after.setAttached(beforeClone.isAttached());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle attached state.");
                }
            }
        }, 1L);
    }

    private void checkHangingPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Hangable)) return;

        Hangable beforeClone = (Hangable) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Hangable) {
                Hangable after = (Hangable) updatedBlock.getBlockData();

                if (beforeClone.isHanging() != after.isHanging() &&
                        !player.hasPermission(PropertyPermissions.HANGING)) {
                    after.setHanging(beforeClone.isHanging());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle hanging state.");
                }
            }
        }, 1L);
    }

    private void checkSnowyPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Snowable)) return;

        Snowable beforeClone = (Snowable) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Snowable) {
                Snowable after = (Snowable) updatedBlock.getBlockData();

                if (beforeClone.isSnowy() != after.isSnowy() &&
                        !player.hasPermission(PropertyPermissions.SNOWY)) {
                    after.setSnowy(beforeClone.isSnowy());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle snowy state.");
                }
            }
        }, 1L);
    }
}