package me.kanuunankuula.debugStick.listeners.properties;

import me.kanuunankuula.debugStick.DebugStick;
import me.kanuunankuula.debugStick.permissions.PropertyPermissions;
import me.kanuunankuula.debugStick.util.SchedulerUtil;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.*;
import org.bukkit.entity.Player;

public class SpecificBlockListener {

    private final DebugStick plugin;

    public SpecificBlockListener(DebugStick plugin) {
        this.plugin = plugin;
    }

    public void checkSpecificBlockProperties(Player player, Block block) {
        BlockData blockData = block.getBlockData();

        checkTripwirePermission(player, block, blockData);
        checkEndPortalFramePermission(player, block, blockData);
        checkJukeboxPermission(player, block, blockData);
        checkBrewingStandPermission(player, block, blockData);
        checkBedPermission(player, block, blockData);
        checkBellPermission(player, block, blockData);
        checkCampfirePermission(player, block, blockData);
        checkFencePermission(player, block, blockData);
        checkGatePermission(player, block, blockData);
        checkLeavesPermission(player, block, blockData);
        checkScaffoldingPermission(player, block, blockData);
        checkTNTPermission(player, block, blockData);
        checkChestPermission(player, block, blockData);
        checkSculkShriekerPermission(player, block, blockData);
    }

    private void checkTripwirePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Tripwire)) return;

        Tripwire beforeClone = (Tripwire) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {

            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Tripwire) {
                Tripwire after = (Tripwire) updatedBlock.getBlockData();

                if (beforeClone.isDisarmed() != after.isDisarmed() &&
                        !player.hasPermission(PropertyPermissions.DISARMED)) {
                    after.setDisarmed(beforeClone.isDisarmed());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle disarmed state.");
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

    private void checkJukeboxPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Jukebox)) return;

        Jukebox beforeClone = (Jukebox) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {

            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Jukebox) {
                Jukebox after = (Jukebox) updatedBlock.getBlockData();

                if (beforeClone.hasRecord() != after.hasRecord() &&
                        !player.hasPermission(PropertyPermissions.HAS_RECORD)) {
                    after.setHasRecord(beforeClone.hasRecord());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle record state.");
                }
            }
        }, 1L);
    }

    private void checkBrewingStandPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof BrewingStand)) return;

        BrewingStand beforeClone = (BrewingStand) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {

            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof BrewingStand) {
                BrewingStand after = (BrewingStand) updatedBlock.getBlockData();

                for (int i = 0; i < 3; i++) {
                    if (beforeClone.hasBottle(i) != after.hasBottle(i) &&
                            !player.hasPermission(PropertyPermissions.HAS_BOTTLE)) {
                        after.setBottle(i, beforeClone.hasBottle(i));
                        updatedBlock.setBlockData(after);
                        player.sendMessage("§cYou don't have permission to toggle bottle slots.");
                        return;
                    }
                }
            }
        }, 1L);
    }

    private void checkBedPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Bed)) return;

        Bed beforeClone = (Bed) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {

            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Bed) {
                Bed after = (Bed) updatedBlock.getBlockData();

                if (beforeClone.isOccupied() != after.isOccupied() &&
                        !player.hasPermission(PropertyPermissions.OCCUPIED)) {
                    after.setOccupied(beforeClone.isOccupied());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle occupied state.");
                }
            }
        }, 1L);
    }

    private void checkBellPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Bell)) return;

        Bell beforeClone = (Bell) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {

            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Bell) {
                Bell after = (Bell) updatedBlock.getBlockData();

                if (!beforeClone.getAttachment().equals(after.getAttachment()) &&
                        !player.hasPermission(PropertyPermissions.ATTACHMENT)) {
                    after.setAttachment(beforeClone.getAttachment());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change attachment type.");
                }
            }
        }, 1L);
    }

    private void checkCampfirePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Campfire)) return;

        Campfire beforeClone = (Campfire) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {

            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Campfire) {
                Campfire after = (Campfire) updatedBlock.getBlockData();

                if (beforeClone.isSignalFire() != after.isSignalFire() &&
                        !player.hasPermission(PropertyPermissions.SIGNAL_FIRE)) {
                    after.setSignalFire(beforeClone.isSignalFire());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle signal fire state.");
                }
            }
        }, 1L);
    }

    private void checkFencePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Fence)) return;

        Fence beforeClone = (Fence) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {

            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Fence) {
                Fence after = (Fence) updatedBlock.getBlockData();

                for (org.bukkit.block.BlockFace face : new org.bukkit.block.BlockFace[]{
                        org.bukkit.block.BlockFace.NORTH, org.bukkit.block.BlockFace.EAST,
                        org.bukkit.block.BlockFace.SOUTH, org.bukkit.block.BlockFace.WEST}) {
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
                            default:
                                continue;
                        }

                        if (!player.hasPermission(permissionNode)) {
                            after.setFace(face, beforeClone.hasFace(face));
                            updatedBlock.setBlockData(after);
                            player.sendMessage("§cYou don't have permission to change fence connections.");
                            return;
                        }
                    }
                }
            }
        }, 1L);
    }

    private void checkGatePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Gate)) return;

        Gate beforeClone = (Gate) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {

            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Gate) {
                Gate after = (Gate) updatedBlock.getBlockData();

                if (beforeClone.isInWall() != after.isInWall() &&
                        !player.hasPermission(PropertyPermissions.IN_WALL)) {
                    after.setInWall(beforeClone.isInWall());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle in-wall state.");
                }
            }
        }, 1L);
    }

    private void checkLeavesPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Leaves)) return;

        Leaves beforeClone = (Leaves) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {

            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Leaves) {
                Leaves after = (Leaves) updatedBlock.getBlockData();

                if (beforeClone.isPersistent() != after.isPersistent() &&
                        !player.hasPermission(PropertyPermissions.PERSISTENT)) {
                    after.setPersistent(beforeClone.isPersistent());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle persistent state.");
                }
            }
        }, 1L);
    }

    private void checkScaffoldingPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Scaffolding)) return;

        Scaffolding beforeClone = (Scaffolding) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {

            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Scaffolding) {
                Scaffolding after = (Scaffolding) updatedBlock.getBlockData();

                if (beforeClone.isBottom() != after.isBottom() &&
                        !player.hasPermission(PropertyPermissions.BOTTOM)) {
                    after.setBottom(beforeClone.isBottom());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle bottom state.");
                    return;
                }

                if (beforeClone.getDistance() != after.getDistance() &&
                        !player.hasPermission(PropertyPermissions.DISTANCE)) {
                    after.setDistance(beforeClone.getDistance());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change scaffolding distance.");
                }
            }
        }, 1L);
    }

    private void checkTNTPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof TNT)) return;

        TNT beforeClone = (TNT) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {

            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof TNT) {
                TNT after = (TNT) updatedBlock.getBlockData();

                if (beforeClone.isUnstable() != after.isUnstable() &&
                        !player.hasPermission(PropertyPermissions.UNSTABLE)) {
                    after.setUnstable(beforeClone.isUnstable());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle unstable state.");
                }
            }
        }, 1L);
    }

    private void checkChestPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Chest)) return;

        Chest beforeClone = (Chest) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {

            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Chest) {
                Chest after = (Chest) updatedBlock.getBlockData();

                if (!beforeClone.getType().equals(after.getType()) &&
                        !player.hasPermission(PropertyPermissions.TYPE)) {
                    after.setType(beforeClone.getType());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change chest type.");
                }
            }
        }, 1L);
    }

    private void checkSculkShriekerPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof SculkShrieker)) return;

        SculkShrieker beforeClone = (SculkShrieker) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {

            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof SculkShrieker) {
                SculkShrieker after = (SculkShrieker) updatedBlock.getBlockData();

                if (beforeClone.isShrieking() != after.isShrieking() &&
                        !player.hasPermission(PropertyPermissions.SHRIEKING)) {
                    after.setShrieking(beforeClone.isShrieking());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to toggle shrieking state.");
                }
            }
        }, 1L);
    }
}