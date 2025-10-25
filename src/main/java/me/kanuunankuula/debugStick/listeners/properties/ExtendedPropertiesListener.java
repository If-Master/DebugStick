package me.kanuunankuula.debugStick.listeners.properties;

import me.kanuunankuula.debugStick.DebugStick;
import me.kanuunankuula.debugStick.permissions.PropertyPermissions;
import me.kanuunankuula.debugStick.util.SchedulerUtil;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.block.data.Rail;
import org.bukkit.block.data.type.*;
import org.bukkit.entity.Player;

public class ExtendedPropertiesListener {

    private final DebugStick plugin;

    public ExtendedPropertiesListener(DebugStick plugin) {
        this.plugin = plugin;
    }

    public void checkExtendedProperties(Player player, Block block) {
        BlockData blockData = block.getBlockData();

        checkHingePermission(player, block, blockData);
        checkShapePermission(player, block, blockData);
        checkStairsShapePermission(player, block, blockData);
        checkTypePermission(player, block, blockData);
        checkDistancePermission(player, block, blockData);
        checkPartPermission(player, block, blockData);
        checkBitesPermission(player, block, blockData);
        checkChargesPermission(player, block, blockData);
        checkModePermission(player, block, blockData);
        checkNotePermission(player, block, blockData);
        checkPicklesPermission(player, block, blockData);
        checkSlabTypePermission(player, block, blockData);
        checkStagePermission(player, block, blockData);
        checkEggsPermission(player, block, blockData);
        checkHatchPermission(player, block, blockData);
        checkDelayPermission(player, block, blockData);
        checkMultipleFacingPermission(player, block, blockData);
        checkDragPermission(player, block, blockData);
        checkCanSummonPermission(player, block, blockData);
        checkInstrumentPermission(player, block, blockData);
        checkTriggeredPermission(player, block, blockData);
        checkEnabledPermission(player, block, blockData);
        checkMoisturePermission(player, block, blockData);
        checkFacePermission(player, block, blockData);
    }

    private void checkHingePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Door)) return;

        Door beforeClone = (Door) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Door) {
                Door after = (Door) updatedBlock.getBlockData();

                if (!beforeClone.getHinge().equals(after.getHinge()) &&
                        !player.hasPermission(PropertyPermissions.HINGE)) {
                    after.setHinge(beforeClone.getHinge());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change hinge position.");
                }
            }
        }, 1L);
    }

    private void checkShapePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Rail)) return;

        Rail beforeClone = (Rail) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Rail) {
                Rail after = (Rail) updatedBlock.getBlockData();

                if (!beforeClone.getShape().equals(after.getShape()) &&
                        !player.hasPermission(PropertyPermissions.SHAPE)) {
                    after.setShape(beforeClone.getShape());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change rail shape.");
                }
            }
        }, 1L);
    }

    private void checkStairsShapePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Stairs)) return;

        Stairs beforeClone = (Stairs) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Stairs) {
                Stairs after = (Stairs) updatedBlock.getBlockData();

                if (!beforeClone.getShape().equals(after.getShape()) &&
                        !player.hasPermission(PropertyPermissions.SHAPE)) {
                    after.setShape(beforeClone.getShape());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change stairs shape.");
                }
            }
        }, 1L);
    }

    private void checkTypePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Slab)) return;

        Slab beforeClone = (Slab) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Slab) {
                Slab after = (Slab) updatedBlock.getBlockData();

                if (!beforeClone.getType().equals(after.getType()) &&
                        !player.hasPermission(PropertyPermissions.TYPE)) {
                    after.setType(beforeClone.getType());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change slab type.");
                }
            }
        }, 1L);
    }

    private void checkDistancePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Leaves)) return;

        Leaves beforeClone = (Leaves) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Leaves) {
                Leaves after = (Leaves) updatedBlock.getBlockData();

                if (beforeClone.getDistance() != after.getDistance() &&
                        !player.hasPermission(PropertyPermissions.DISTANCE)) {
                    after.setDistance(beforeClone.getDistance());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change distance.");
                }
            }
        }, 1L);
    }

    private void checkPartPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Bed)) return;

        Bed beforeClone = (Bed) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Bed) {
                Bed after = (Bed) updatedBlock.getBlockData();

                if (!beforeClone.getPart().equals(after.getPart()) &&
                        !player.hasPermission(PropertyPermissions.PART)) {
                    after.setPart(beforeClone.getPart());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change bed part.");
                }
            }
        }, 1L);
    }

    private void checkBitesPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Cake)) return;

        Cake beforeClone = (Cake) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Cake) {
                Cake after = (Cake) updatedBlock.getBlockData();

                if (beforeClone.getBites() != after.getBites() &&
                        !player.hasPermission(PropertyPermissions.BITES)) {
                    after.setBites(beforeClone.getBites());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change cake bites.");
                }
            }
        }, 1L);
    }

    private void checkChargesPermission(Player player, Block block, BlockData blockData) {
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

    private void checkModePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Comparator)) return;

        Comparator beforeClone = (Comparator) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Comparator) {
                Comparator after = (Comparator) updatedBlock.getBlockData();

                if (!beforeClone.getMode().equals(after.getMode()) &&
                        !player.hasPermission(PropertyPermissions.MODE)) {
                    after.setMode(beforeClone.getMode());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change comparator mode.");
                }
            }
        }, 1L);
    }

    private void checkNotePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof NoteBlock)) return;

        NoteBlock beforeClone = (NoteBlock) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof NoteBlock) {
                NoteBlock after = (NoteBlock) updatedBlock.getBlockData();

                if (!beforeClone.getNote().equals(after.getNote()) &&
                        !player.hasPermission(PropertyPermissions.NOTE)) {
                    after.setNote(beforeClone.getNote());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change note block pitch.");
                }
            }
        }, 1L);
    }

    private void checkPicklesPermission(Player player, Block block, BlockData blockData) {
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

    private void checkSlabTypePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Slab)) return;

        Slab beforeClone = (Slab) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Slab) {
                Slab after = (Slab) updatedBlock.getBlockData();

                if (!beforeClone.getType().equals(after.getType()) &&
                        !player.hasPermission(PropertyPermissions.SLAB_TYPE)) {
                    after.setType(beforeClone.getType());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change slab type.");
                }
            }
        }, 1L);
    }

    private void checkStagePermission(Player player, Block block, BlockData blockData) {
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

    private void checkEggsPermission(Player player, Block block, BlockData blockData) {
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
                }
            }
        }, 1L);
    }

    private void checkHatchPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof TurtleEgg)) return;

        TurtleEgg beforeClone = (TurtleEgg) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof TurtleEgg) {
                TurtleEgg after = (TurtleEgg) updatedBlock.getBlockData();

                if (beforeClone.getHatch() != after.getHatch() &&
                        !player.hasPermission(PropertyPermissions.HATCH)) {
                    after.setHatch(beforeClone.getHatch());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change turtle egg hatch stage.");
                }
            }
        }, 1L);
    }

    private void checkDelayPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Repeater)) return;

        Repeater beforeClone = (Repeater) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Repeater) {
                Repeater after = (Repeater) updatedBlock.getBlockData();

                if (beforeClone.getDelay() != after.getDelay() &&
                        !player.hasPermission(PropertyPermissions.DELAY)) {
                    after.setDelay(beforeClone.getDelay());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change repeater delay.");
                }
            }
        }, 1L);
    }

    private void checkMultipleFacingPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof MultipleFacing)) return;

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
                            player.sendMessage("§cYou don't have permission to change " + face.name().toLowerCase() + " connection.");
                            return;
                        }
                    }
                }
            }
        }, 1L);
    }

    private void checkDragPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof BubbleColumn)) return;

        BubbleColumn beforeClone = (BubbleColumn) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof BubbleColumn) {
                BubbleColumn after = (BubbleColumn) updatedBlock.getBlockData();

                if (beforeClone.isDrag() != after.isDrag() &&
                        !player.hasPermission(PropertyPermissions.DRAG)) {
                    after.setDrag(beforeClone.isDrag());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change bubble column drag.");
                }
            }
        }, 1L);
    }

    private void checkCanSummonPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof SculkShrieker)) return;

        SculkShrieker beforeClone = (SculkShrieker) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof SculkShrieker) {
                SculkShrieker after = (SculkShrieker) updatedBlock.getBlockData();

                if (beforeClone.isCanSummon() != after.isCanSummon() &&
                        !player.hasPermission(PropertyPermissions.CAN_SUMMON)) {
                    after.setCanSummon(beforeClone.isCanSummon());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change can_summon state.");
                }
            }
        }, 1L);
    }

    private void checkInstrumentPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof NoteBlock)) return;

        NoteBlock beforeClone = (NoteBlock) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof NoteBlock) {
                NoteBlock after = (NoteBlock) updatedBlock.getBlockData();

                if (!beforeClone.getInstrument().equals(after.getInstrument()) &&
                        !player.hasPermission(PropertyPermissions.INSTRUMENT)) {
                    after.setInstrument(beforeClone.getInstrument());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change note block instrument.");
                }
            }
        }, 1L);
    }

    private void checkTriggeredPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Dispenser)) return;

        Dispenser beforeClone = (Dispenser) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Dispenser) {
                Dispenser after = (Dispenser) updatedBlock.getBlockData();

                if (beforeClone.isTriggered() != after.isTriggered() &&
                        !player.hasPermission(PropertyPermissions.TRIGGERED)) {
                    after.setTriggered(beforeClone.isTriggered());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change triggered state.");
                }
            }
        }, 1L);
    }

    private void checkEnabledPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Hopper)) return;

        Hopper beforeClone = (Hopper) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Hopper) {
                Hopper after = (Hopper) updatedBlock.getBlockData();

                if (beforeClone.isEnabled() != after.isEnabled() &&
                        !player.hasPermission(PropertyPermissions.ENABLED)) {
                    after.setEnabled(beforeClone.isEnabled());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change enabled state.");
                }
            }
        }, 1L);
    }

    private void checkMoisturePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Farmland)) return;

        Farmland beforeClone = (Farmland) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Farmland) {
                Farmland after = (Farmland) updatedBlock.getBlockData();

                if (beforeClone.getMoisture() != after.getMoisture() &&
                        !player.hasPermission(PropertyPermissions.MOISTURE)) {
                    after.setMoisture(beforeClone.getMoisture());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change farmland moisture.");
                }
            }
        }, 1L);
    }

    private void checkFacePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Switch)) return;

        Switch beforeClone = (Switch) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Switch) {
                Switch after = (Switch) updatedBlock.getBlockData();

                if (!beforeClone.getAttachedFace().equals(after.getAttachedFace()) &&
                        !player.hasPermission(PropertyPermissions.FACE)) {
                    after.setAttachedFace(beforeClone.getAttachedFace());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change button face.");
                }
            }
        }, 1L);
    }
}