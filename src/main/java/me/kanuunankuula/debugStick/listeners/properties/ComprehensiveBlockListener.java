package me.kanuunankuula.debugStick.listeners.properties;

import me.kanuunankuula.debugStick.DebugStick;
import me.kanuunankuula.debugStick.permissions.PropertyPermissions;
import me.kanuunankuula.debugStick.util.SchedulerUtil;
import org.bukkit.block.Block;
import org.bukkit.block.data.*;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.*;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class ComprehensiveBlockListener {

    private final DebugStick plugin;

    public ComprehensiveBlockListener(DebugStick plugin) {
        this.plugin = plugin;
    }

    public void checkComprehensiveProperties(Player player, Block block) {
        BlockData blockData = block.getBlockData();

        checkLightPermission(player, block, blockData);
        checkGlowLichenPermission(player, block, blockData);
        checkSculkVeinPermission(player, block, blockData);
        checkAmethystClusterPermission(player, block, blockData);
        checkCopperBulbPermission(player, block, blockData);
        checkHeavyCorePermission(player, block, blockData);
        checkPowderSnowCauldronPermission(player, block, blockData);
        checkSaplingPermission(player, block, blockData);
        checkCocoaPermission(player, block, blockData);
        checkNetherWartPermission(player, block, blockData);
        checkFrostedIcePermission(player, block, blockData);
        checkSeaPicklePermission(player, block, blockData);
        checkTurtleEggPermission(player, block, blockData);
        checkChorusPlantPermission(player, block, blockData);
        checkRespawnAnchorPermission(player, block, blockData);
        checkStructureBlockPermission(player, block, blockData);
        checkEndPortalFramePermission(player, block, blockData);
    }
    private void checkLightPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Light)) return;

        Light beforeClone = (Light) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Light) {
                Light after = (Light) updatedBlock.getBlockData();

                if (beforeClone.getLevel() != after.getLevel() &&
                        !player.hasPermission(PropertyPermissions.LEVEL)) {
                    after.setLevel(beforeClone.getLevel());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change light level.");
                }
            }
        }, 1L);
    }

    private void checkGlowLichenPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof GlowLichen)) return;

        GlowLichen beforeClone = (GlowLichen) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof GlowLichen) {
                GlowLichen after = (GlowLichen) updatedBlock.getBlockData();

                for (org.bukkit.block.BlockFace face : after.getAllowedFaces()) {
                    if (beforeClone.hasFace(face) != after.hasFace(face)) {
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
                            case UP:
                                permissionNode = PropertyPermissions.UP;
                                break;
                            case DOWN:
                                permissionNode = PropertyPermissions.DOWN;
                                break;
                            default:
                                continue;
                        }

                        if (!player.hasPermission(permissionNode)) {
                            after.setFace(face, beforeClone.hasFace(face));
                            updatedBlock.setBlockData(after);
                            player.sendMessage("§cYou don't have permission to change glow lichen face.");
                            return;
                        }
                    }
                }

                if (beforeClone.isWaterlogged() != after.isWaterlogged() &&
                        !player.hasPermission(PropertyPermissions.WATERLOGGED)) {
                    after.setWaterlogged(beforeClone.isWaterlogged());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle waterlogged state.");
                }
            }
        }, 1L);
    }

    private void checkSculkVeinPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof SculkVein)) return;

        SculkVein beforeClone = (SculkVein) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof SculkVein) {
                SculkVein after = (SculkVein) updatedBlock.getBlockData();

                for (org.bukkit.block.BlockFace face : after.getAllowedFaces()) {
                    if (beforeClone.hasFace(face) != after.hasFace(face)) {
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
                            case UP:
                                permissionNode = PropertyPermissions.UP;
                                break;
                            case DOWN:
                                permissionNode = PropertyPermissions.DOWN;
                                break;
                            default:
                                continue;
                        }

                        if (!player.hasPermission(permissionNode)) {
                            after.setFace(face, beforeClone.hasFace(face));
                            updatedBlock.setBlockData(after);
                            player.sendMessage("§cYou don't have permission to change sculk vein face.");
                            return;
                        }
                    }
                }

                if (beforeClone.isWaterlogged() != after.isWaterlogged() &&
                        !player.hasPermission(PropertyPermissions.WATERLOGGED)) {
                    after.setWaterlogged(beforeClone.isWaterlogged());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle waterlogged state.");
                }
            }
        }, 1L);
    }

    private void checkAmethystClusterPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof AmethystCluster)) return;

        AmethystCluster beforeClone = (AmethystCluster) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof AmethystCluster) {
                AmethystCluster after = (AmethystCluster) updatedBlock.getBlockData();

                if (!beforeClone.getFacing().equals(after.getFacing()) &&
                        !player.hasPermission(PropertyPermissions.FACING)) {
                    after.setFacing(beforeClone.getFacing());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change facing direction.");
                    return;
                }

                if (beforeClone.isWaterlogged() != after.isWaterlogged() &&
                        !player.hasPermission(PropertyPermissions.WATERLOGGED)) {
                    after.setWaterlogged(beforeClone.isWaterlogged());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle waterlogged state.");
                }
            }
        }, 1L);
    }

    private void checkCopperBulbPermission(Player player, Block block, BlockData blockData) {
        try {
            Class<?> copperBulbClass = Class.forName("org.bukkit.block.data.type.CopperBulb");
            if (!copperBulbClass.isInstance(blockData)) return;

            Object beforeClone = blockData.clone();
            SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
                Block updatedBlock = block.getLocation().getBlock();
                if (copperBulbClass.isInstance(updatedBlock.getBlockData())) {
                    Object after = updatedBlock.getBlockData();

                    try {
                        Method isLitMethod = copperBulbClass.getMethod("isLit");
                        Method setLitMethod = copperBulbClass.getMethod("setLit", boolean.class);
                        Method isPoweredMethod = copperBulbClass.getMethod("isPowered");
                        Method setPoweredMethod = copperBulbClass.getMethod("setPowered", boolean.class);

                        boolean beforeLit = (boolean) isLitMethod.invoke(beforeClone);
                        boolean afterLit = (boolean) isLitMethod.invoke(after);

                        if (beforeLit != afterLit &&
                                !player.hasPermission(PropertyPermissions.LIT)) {
                            setLitMethod.invoke(after, beforeLit);
                            updatedBlock.setBlockData((BlockData) after);
                            player.sendMessage("§cYou don't have permission to toggle lit state.");
                            return;
                        }

                        boolean beforePowered = (boolean) isPoweredMethod.invoke(beforeClone);
                        boolean afterPowered = (boolean) isPoweredMethod.invoke(after);

                        if (beforePowered != afterPowered &&
                                !player.hasPermission(PropertyPermissions.POWERED)) {
                            setPoweredMethod.invoke(after, beforePowered);
                            updatedBlock.setBlockData((BlockData) after);
                            player.sendMessage("§cYou don't have permission to toggle powered state.");
                        }
                    } catch (Exception e) {
                    }
                }
            }, 1L);
        } catch (ClassNotFoundException e) {
        }
    }

    private void checkHeavyCorePermission(Player player, Block block, BlockData blockData) {
        try {
            Class<?> heavyCoreClass = Class.forName("org.bukkit.block.data.type.HeavyCore");
            if (!heavyCoreClass.isInstance(blockData)) return;

            Object beforeClone = blockData.clone();
            SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
                Block updatedBlock = block.getLocation().getBlock();
                if (heavyCoreClass.isInstance(updatedBlock.getBlockData())) {
                    Object after = updatedBlock.getBlockData();

                    try {
                        Method isWaterloggedMethod = heavyCoreClass.getMethod("isWaterlogged");
                        Method setWaterloggedMethod = heavyCoreClass.getMethod("setWaterlogged", boolean.class);

                        boolean beforeWaterlogged = (boolean) isWaterloggedMethod.invoke(beforeClone);
                        boolean afterWaterlogged = (boolean) isWaterloggedMethod.invoke(after);

                        if (beforeWaterlogged != afterWaterlogged &&
                                !player.hasPermission(PropertyPermissions.WATERLOGGED)) {
                            setWaterloggedMethod.invoke(after, beforeWaterlogged);
                            updatedBlock.setBlockData((BlockData) after);
                            player.sendMessage("§cYou don't have permission to toggle waterlogged state.");
                        }
                    } catch (Exception e) {
                    }
                }
            }, 1L);
        } catch (ClassNotFoundException e) {
        }
    }

    private void checkPowderSnowCauldronPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Levelled)) return;
        if (!block.getType().name().contains("POWDER_SNOW_CAULDRON")) return;

        Levelled beforeClone = (Levelled) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Levelled) {
                Levelled after = (Levelled) updatedBlock.getBlockData();

                if (beforeClone.getLevel() != after.getLevel() &&
                        !player.hasPermission(PropertyPermissions.LEVEL)) {
                    after.setLevel(beforeClone.getLevel());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change powder snow cauldron level.");
                }
            }
        }, 1L);
    }

    private void checkSaplingPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Sapling)) return;

        Sapling beforeClone = (Sapling) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Sapling) {
                Sapling after = (Sapling) updatedBlock.getBlockData();

                if (beforeClone.getStage() != after.getStage() &&
                        !player.hasPermission(PropertyPermissions.STAGE)) {
                    after.setStage(beforeClone.getStage());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change sapling stage.");
                }
            }
        }, 1L);
    }

    private void checkCocoaPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Cocoa)) return;

        Cocoa beforeClone = (Cocoa) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Cocoa) {
                Cocoa after = (Cocoa) updatedBlock.getBlockData();

                if (beforeClone.getAge() != after.getAge() &&
                        !player.hasPermission(PropertyPermissions.AGE)) {
                    after.setAge(beforeClone.getAge());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change cocoa age.");
                }
            }
        }, 1L);
    }

    private void checkNetherWartPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Ageable) || !block.getType().name().contains("NETHER_WART")) return;

        Ageable beforeClone = (Ageable) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Ageable) {
                Ageable after = (Ageable) updatedBlock.getBlockData();

                if (beforeClone.getAge() != after.getAge() &&
                        !player.hasPermission(PropertyPermissions.AGE)) {
                    after.setAge(beforeClone.getAge());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change nether wart age.");
                }
            }
        }, 1L);
    }

    private void checkFrostedIcePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Ageable) || !block.getType().name().contains("FROSTED_ICE")) return;

        Ageable beforeClone = (Ageable) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Ageable) {
                Ageable after = (Ageable) updatedBlock.getBlockData();

                if (beforeClone.getAge() != after.getAge() &&
                        !player.hasPermission(PropertyPermissions.AGE)) {
                    after.setAge(beforeClone.getAge());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change frosted ice age.");
                }
            }
        }, 1L);
    }

    private void checkSeaPicklePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof SeaPickle)) return;

        SeaPickle beforeClone = (SeaPickle) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof SeaPickle) {
                SeaPickle after = (SeaPickle) updatedBlock.getBlockData();

                if (beforeClone.getPickles() != after.getPickles() &&
                        !player.hasPermission(PropertyPermissions.PICKLES)) {
                    after.setPickles(beforeClone.getPickles());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change sea pickle count.");
                }
            }
        }, 1L);
    }

    private void checkTurtleEggPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof TurtleEgg)) return;

        TurtleEgg beforeClone = (TurtleEgg) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof TurtleEgg) {
                TurtleEgg after = (TurtleEgg) updatedBlock.getBlockData();

                if (beforeClone.getEggs() != after.getEggs() &&
                        !player.hasPermission(PropertyPermissions.EGGS)) {
                    after.setEggs(beforeClone.getEggs());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change turtle egg count.");
                    return;
                }

                if (beforeClone.getHatch() != after.getHatch() &&
                        !player.hasPermission(PropertyPermissions.HATCH)) {
                    after.setHatch(beforeClone.getHatch());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change turtle egg hatch stage.");
                }
            }
        }, 1L);
    }

    private void checkChorusPlantPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof MultipleFacing) || !block.getType().name().contains("CHORUS")) return;

        MultipleFacing beforeClone = (MultipleFacing) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof MultipleFacing) {
                MultipleFacing after = (MultipleFacing) updatedBlock.getBlockData();

                for (org.bukkit.block.BlockFace face : after.getAllowedFaces()) {
                    if (beforeClone.hasFace(face) != after.hasFace(face)) {
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
                            case UP:
                                permissionNode = PropertyPermissions.UP;
                                break;
                            case DOWN:
                                permissionNode = PropertyPermissions.DOWN;
                                break;
                            default:
                                continue;
                        }

                        if (!player.hasPermission(permissionNode)) {
                            after.setFace(face, beforeClone.hasFace(face));
                            updatedBlock.setBlockData(after);
                            player.sendMessage("§cYou don't have permission to change chorus plant connection.");
                            return;
                        }
                    }
                }
            }
        }, 1L);
    }

    private void checkRespawnAnchorPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof RespawnAnchor)) return;

        RespawnAnchor beforeClone = (RespawnAnchor) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof RespawnAnchor) {
                RespawnAnchor after = (RespawnAnchor) updatedBlock.getBlockData();

                if (beforeClone.getCharges() != after.getCharges() &&
                        !player.hasPermission(PropertyPermissions.CHARGES)) {
                    after.setCharges(beforeClone.getCharges());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change respawn anchor charges.");
                }
            }
        }, 1L);
    }

    private void checkStructureBlockPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof StructureBlock)) return;

        StructureBlock beforeClone = (StructureBlock) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof StructureBlock) {
                StructureBlock after = (StructureBlock) updatedBlock.getBlockData();

                if (!beforeClone.getMode().equals(after.getMode()) &&
                        !player.hasPermission(PropertyPermissions.MODE)) {
                    after.setMode(beforeClone.getMode());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change structure block mode.");
                }
            }
        }, 1L);
    }

    private void checkEndPortalFramePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof EndPortalFrame)) return;

        EndPortalFrame beforeClone = (EndPortalFrame) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof EndPortalFrame) {
                EndPortalFrame after = (EndPortalFrame) updatedBlock.getBlockData();

                if (beforeClone.hasEye() != after.hasEye() &&
                        !player.hasPermission(PropertyPermissions.EYE)) {
                    after.setEye(beforeClone.hasEye());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle eye state.");
                }
            }
        }, 1L);
    }
}