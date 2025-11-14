package me.kanuunankuula.debugStick.listeners.properties;

import me.kanuunankuula.debugStick.DebugStick;
import me.kanuunankuula.debugStick.permissions.PropertyPermissions;
import me.kanuunankuula.debugStick.util.SchedulerUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Grindstone;
import org.bukkit.block.data.type.TechnicalPiston;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class OtherProperties {

    private final DebugStick plugin;

    private Class<?> creakingHeartClass;
    private Method getNaturalMethod;
    private Method setNaturalMethod;
    private Method getCreakingMethod;
    private Method setCreakingMethod;

    private Class<?> wildflowerClass;
    private Method wildflowerGetFlowerAmountMethod;
    private Method wildflowerSetFlowerAmountMethod;

    private Class<?> leafLitterClass;
    private Method getSegmentAmountMethod;
    private Method setSegmentAmountMethod;


    public OtherProperties(DebugStick plugin) {
        this.plugin = plugin;
        initializeReflection();
    }

    private void initializeReflection() {
        try {
            creakingHeartClass = Class.forName("org.bukkit.block.data.type.CreakingHeart");
            getNaturalMethod = creakingHeartClass.getMethod("isNatural");
            setNaturalMethod = creakingHeartClass.getMethod("setNatural", boolean.class);

            try {
                getCreakingMethod = creakingHeartClass.getMethod("getCreaking");
                setCreakingMethod = creakingHeartClass.getMethod("setCreaking", getCreakingMethod.getReturnType());
            } catch (NoSuchMethodException e) {
                getCreakingMethod = null;
                setCreakingMethod = null;
            }

            plugin.getLogger().info("Creaking Heart support enabled");
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            plugin.getLogger().info("Creaking Heart not supported in this version");
        }

        try {
            wildflowerClass = Class.forName("org.bukkit.block.data.type.Wildflower");
            wildflowerGetFlowerAmountMethod = wildflowerClass.getMethod("getFlowerAmount");
            wildflowerSetFlowerAmountMethod = wildflowerClass.getMethod("setFlowerAmount", int.class);
            plugin.getLogger().info("Wildflower support enabled");
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            try {
                wildflowerClass = Class.forName("org.bukkit.block.data.type.Wildflowers");
                wildflowerGetFlowerAmountMethod = wildflowerClass.getMethod("getFlowerAmount");
                wildflowerSetFlowerAmountMethod = wildflowerClass.getMethod("setFlowerAmount", int.class);
                plugin.getLogger().info("Wildflowers support enabled");
            } catch (ClassNotFoundException | NoSuchMethodException ex) {
                plugin.getLogger().info("Wildflower/Wildflowers not supported in this version");
            }
        }

        try {
            leafLitterClass = Class.forName("org.bukkit.block.data.type.LeafLitter");
            getSegmentAmountMethod = leafLitterClass.getMethod("getSegmentAmount");
            setSegmentAmountMethod = leafLitterClass.getMethod("setSegmentAmount", int.class);
            plugin.getLogger().info("Leaf Litter support enabled");
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            plugin.getLogger().info("Leaf Litter not supported in this version");
        }
    }

    public void checkMissingProperties(Player player, Block block) {
        BlockData blockData = block.getBlockData();

        checkGrindstonePermission(player, block, blockData);
        checkPistonHeadPermission(player, block, blockData);
        checkCreakingHeartPermission(player, block, blockData);
        checkWildflowerPermission(player, block, blockData);
        checkLeafLitterPermission(player, block, blockData);
    }

    private void checkGrindstonePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Grindstone)) return;

        Grindstone beforeClone = (Grindstone) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Grindstone) {
                Grindstone after = (Grindstone) updatedBlock.getBlockData();

                if (!beforeClone.getAttachedFace().equals(after.getAttachedFace()) &&
                        !player.hasPermission(PropertyPermissions.FACE)) {
                    after.setAttachedFace(beforeClone.getAttachedFace());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change grindstone face.");
                }
            }
        }, 1L);
    }

    private void checkPistonHeadPermission(Player player, Block block, BlockData blockData) {
        Material type = block.getType();
        if (type != Material.PISTON_HEAD && type != Material.MOVING_PISTON) return;

        if (!(blockData instanceof TechnicalPiston)) return;

        TechnicalPiston beforeClone = (TechnicalPiston) blockData.clone();
        String beforeDataString = blockData.getAsString();

        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof TechnicalPiston) {
                TechnicalPiston after = (TechnicalPiston) updatedBlock.getBlockData();
                String afterDataString = after.getAsString();

                if (!beforeClone.getType().equals(after.getType()) &&
                        !player.hasPermission(PropertyPermissions.TYPE)) {
                    after.setType(beforeClone.getType());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change piston head type.");
                    return;
                }

                boolean beforeHasShort = beforeDataString.contains("short=true");
                boolean afterHasShort = afterDataString.contains("short=true");

                if (beforeHasShort != afterHasShort &&
                        !player.hasPermission(PropertyPermissions.SHORT)) {
                    try {
                        BlockData revertedData = org.bukkit.Bukkit.createBlockData(beforeDataString);
                        updatedBlock.setBlockData(revertedData);
                        player.sendMessage("§cYou don't have permission to change piston short state.");
                    } catch (Exception e) {
                        plugin.getLogger().warning("Failed to revert piston short state: " + e.getMessage());
                    }
                }
            }
        }, 1L);
    }

    private void checkCreakingHeartPermission(Player player, Block block, BlockData blockData) {
        if (creakingHeartClass == null || !creakingHeartClass.isInstance(blockData)) return;
        if (getNaturalMethod == null || setNaturalMethod == null) return;

        try {
            Object beforeClone = blockData.clone();
            SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
                Block updatedBlock = block.getLocation().getBlock();
                BlockData updatedBlockData = updatedBlock.getBlockData();

                if (!creakingHeartClass.isInstance(updatedBlockData)) return;

                try {
                    boolean beforeNatural = (boolean) getNaturalMethod.invoke(beforeClone);
                    boolean afterNatural = (boolean) getNaturalMethod.invoke(updatedBlockData);

                    if (beforeNatural != afterNatural &&
                            !player.hasPermission(PropertyPermissions.NATURAL)) {
                        setNaturalMethod.invoke(updatedBlockData, beforeNatural);
                        updatedBlock.setBlockData((BlockData) updatedBlockData);
                        player.sendMessage("§cYou don't have permission to change natural state.");
                        return;
                    }

                    if (getCreakingMethod != null && setCreakingMethod != null) {
                        try {
                            Object beforeCreaking = getCreakingMethod.invoke(beforeClone);
                            Object afterCreaking = getCreakingMethod.invoke(updatedBlockData);

                            if (!beforeCreaking.equals(afterCreaking) &&
                                    !player.hasPermission(PropertyPermissions.CREAKING)) {
                                setCreakingMethod.invoke(updatedBlockData, beforeCreaking);
                                updatedBlock.setBlockData((BlockData) updatedBlockData);
                                player.sendMessage("§cYou don't have permission to change creaking heart state.");
                            }
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e) {
                    plugin.getLogger().warning("Error checking creaking heart properties: " + e.getMessage());
                }
            }, 1L);
        } catch (Exception e) {
            plugin.getLogger().warning("Error cloning creaking heart data: " + e.getMessage());
        }
    }

    private void checkWildflowerPermission(Player player, Block block, BlockData blockData) {
        if (wildflowerClass == null || !wildflowerClass.isInstance(blockData)) return;
        if (wildflowerGetFlowerAmountMethod == null || wildflowerSetFlowerAmountMethod == null) return;

        try {
            int beforeAmount = (int) wildflowerGetFlowerAmountMethod.invoke(blockData);

            SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
                Block updatedBlock = block.getLocation().getBlock();
                BlockData updatedBlockData = updatedBlock.getBlockData();

                if (!wildflowerClass.isInstance(updatedBlockData)) return;

                try {
                    int afterAmount = (int) wildflowerGetFlowerAmountMethod.invoke(updatedBlockData);

                    if (beforeAmount != afterAmount &&
                            !player.hasPermission(PropertyPermissions.FLOWER_AMOUNT)) {
                        wildflowerSetFlowerAmountMethod.invoke(updatedBlockData, beforeAmount);
                        updatedBlock.setBlockData((BlockData) updatedBlockData);
                        player.sendMessage("§cYou don't have permission to change flower amount.");
                    }
                } catch (Exception e) {
                    plugin.getLogger().warning("Error checking wildflower flower_amount property: " + e.getMessage());
                }
            }, 1L);
        } catch (Exception e) {
            plugin.getLogger().warning("Error processing wildflower data: " + e.getMessage());
        }
    }

    private void checkLeafLitterPermission(Player player, Block block, BlockData blockData) {
        if (leafLitterClass == null || !leafLitterClass.isInstance(blockData)) return;

        try {
            Object beforeClone = blockData.clone();
            SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
                Block updatedBlock = block.getLocation().getBlock();
                BlockData updatedBlockData = updatedBlock.getBlockData();

                if (!leafLitterClass.isInstance(updatedBlockData)) return;

                try {
                    int beforeAmount = (int) getSegmentAmountMethod.invoke(beforeClone);
                    int afterAmount = (int) getSegmentAmountMethod.invoke(updatedBlockData);

                    if (beforeAmount != afterAmount &&
                            !player.hasPermission(PropertyPermissions.SEGMENT_AMOUNT)) {
                        setSegmentAmountMethod.invoke(updatedBlockData, beforeAmount);
                        updatedBlock.setBlockData((BlockData) updatedBlockData);
                        player.sendMessage("§cYou don't have permission to change segment amount.");
                    }
                } catch (Exception e) {
                    plugin.getLogger().warning("Error checking leaf litter properties: " + e.getMessage());
                }
            }, 1L);
        } catch (Exception e) {
            plugin.getLogger().warning("Error cloning leaf litter data: " + e.getMessage());
        }
    }
}