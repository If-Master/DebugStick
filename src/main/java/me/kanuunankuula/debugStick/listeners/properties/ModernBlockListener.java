package me.kanuunankuula.debugStick.listeners.properties;

import me.kanuunankuula.debugStick.DebugStick;
import me.kanuunankuula.debugStick.permissions.PropertyPermissions;
import me.kanuunankuula.debugStick.util.SchedulerUtil;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.*;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class ModernBlockListener {

    private final DebugStick plugin;

    public ModernBlockListener(DebugStick plugin) {
        this.plugin = plugin;
    }

    public void checkModernBlockProperties(Player player, Block block) {
        BlockData blockData = block.getBlockData();

        checkCandlesPermission(player, block, blockData);
        checkSculkSensorPermission(player, block, blockData);
        checkBrushablePermission(player, block, blockData);
        checkPointedDripstonePermission(player, block, blockData);
        checkCrafterPermission(player, block, blockData);
        checkTrialSpawnerPermission(player, block, blockData);
        checkVaultPermission(player, block, blockData);
        checkBigDripleafPermission(player, block, blockData);
        checkSnifferEggPermission(player, block, blockData);
        checkPinkPetalsPermission(player, block, blockData);
        checkPitcherCropPermission(player, block, blockData);
        checkTorchflowerCropPermission(player, block, blockData);
        checkBeehivePermission(player, block, blockData);
        checkSnowPermission(player, block, blockData);
        checkSweetBerryBushPermission(player, block, blockData);
        checkCaveVinesPermission(player, block, blockData);
        checkChiseledBookshelfPermission(player, block, blockData);
        checkLecternPermission(player, block, blockData);
        checkMangrovePermission(player, block, blockData);
        checkDriedGhastPermission(player, block, blockData);

    }

    private void checkCandlesPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Candle)) return;

        Candle beforeClone = (Candle) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Candle) {
                Candle after = (Candle) updatedBlock.getBlockData();

                if (beforeClone.getCandles() != after.getCandles() &&
                        !player.hasPermission(PropertyPermissions.CANDLES)) {
                    after.setCandles(beforeClone.getCandles());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change candle amount.");
                }
            }
        }, 1L);
    }

    private void checkSculkSensorPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof SculkSensor)) return;

        SculkSensor beforeClone = (SculkSensor) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof SculkSensor) {
                SculkSensor after = (SculkSensor) updatedBlock.getBlockData();

                try {
                    Method getPhaseMethod = SculkSensor.class.getMethod("getPhase");
                    Method setPhaseMethod = SculkSensor.class.getMethod("setPhase", getPhaseMethod.getReturnType());

                    Object beforePhase = getPhaseMethod.invoke(beforeClone);
                    Object afterPhase = getPhaseMethod.invoke(after);

                    if (!beforePhase.equals(afterPhase) &&
                            !player.hasPermission(PropertyPermissions.SCULK_SENSOR_PHASE)) {
                        setPhaseMethod.invoke(after, beforePhase);
                        updatedBlock.setBlockData(after);
                        player.sendMessage("§cYou don't have permission to change sculk sensor phase.");
                    }
                } catch (Exception e) {
                }
            }
        }, 1L);
    }

    private void checkBrushablePermission(Player player, Block block, BlockData blockData) {
        try {
            Class<?> brushableClass = Class.forName("org.bukkit.block.data.Brushable");
            if (!brushableClass.isInstance(blockData)) return;

            Object beforeClone = blockData.clone();
            SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
                Block updatedBlock = block.getLocation().getBlock();
                if (brushableClass.isInstance(updatedBlock.getBlockData())) {
                    Object after = updatedBlock.getBlockData();

                    try {
                        Method getDustedMethod = brushableClass.getMethod("getDustedLevel");
                        Method setDustedMethod = brushableClass.getMethod("setDustedLevel", int.class);

                        int beforeDusted = (int) getDustedMethod.invoke(beforeClone);
                        int afterDusted = (int) getDustedMethod.invoke(after);

                        if (beforeDusted != afterDusted &&
                                !player.hasPermission(PropertyPermissions.DUSTED)) {
                            setDustedMethod.invoke(after, beforeDusted);
                            updatedBlock.setBlockData((BlockData) after);
                            player.sendMessage("§cYou don't have permission to change dusted level.");
                        }
                    } catch (Exception e) {
                    }
                }
            }, 1L);
        } catch (ClassNotFoundException e) {
        }
    }

    private void checkPointedDripstonePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof PointedDripstone)) return;

        PointedDripstone beforeClone = (PointedDripstone) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof PointedDripstone) {
                PointedDripstone after = (PointedDripstone) updatedBlock.getBlockData();

                if (!beforeClone.getThickness().equals(after.getThickness()) &&
                        !player.hasPermission(PropertyPermissions.THICKNESS)) {
                    after.setThickness(beforeClone.getThickness());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change dripstone thickness.");
                    return;
                }

                if (!beforeClone.getVerticalDirection().equals(after.getVerticalDirection()) &&
                        !player.hasPermission(PropertyPermissions.VERTICAL_DIRECTION)) {
                    after.setVerticalDirection(beforeClone.getVerticalDirection());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change dripstone direction.");
                }
            }
        }, 1L);
    }

    private void checkCrafterPermission(Player player, Block block, BlockData blockData) {
        try {
            Class<?> crafterClass = Class.forName("org.bukkit.block.data.type.Crafter");
            if (!crafterClass.isInstance(blockData)) return;

            Object beforeClone = blockData.clone();
            SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
                Block updatedBlock = block.getLocation().getBlock();
                if (crafterClass.isInstance(updatedBlock.getBlockData())) {
                    Object after = updatedBlock.getBlockData();

                    try {
                        Method isCraftingMethod = crafterClass.getMethod("isCrafting");
                        Method setCraftingMethod = crafterClass.getMethod("setCrafting", boolean.class);
                        Method getOrientationMethod = crafterClass.getMethod("getOrientation");
                        Method setOrientationMethod = crafterClass.getMethod("setOrientation", getOrientationMethod.getReturnType());
                        Method isTriggeredMethod = crafterClass.getMethod("isTriggered");
                        Method setTriggeredMethod = crafterClass.getMethod("setTriggered", boolean.class);

                        boolean beforeCrafting = (boolean) isCraftingMethod.invoke(beforeClone);
                        boolean afterCrafting = (boolean) isCraftingMethod.invoke(after);

                        if (beforeCrafting != afterCrafting &&
                                !player.hasPermission(PropertyPermissions.CRAFTING)) {
                            setCraftingMethod.invoke(after, beforeCrafting);
                            updatedBlock.setBlockData((BlockData) after);
                            player.sendMessage("§cYou don't have permission to change crafting state.");
                            return;
                        }

                        Object beforeOrientation = getOrientationMethod.invoke(beforeClone);
                        Object afterOrientation = getOrientationMethod.invoke(after);

                        if (!beforeOrientation.equals(afterOrientation) &&
                                !player.hasPermission(PropertyPermissions.ORIENTATION)) {
                            setOrientationMethod.invoke(after, beforeOrientation);
                            updatedBlock.setBlockData((BlockData) after);
                            player.sendMessage("§cYou don't have permission to change crafter orientation.");
                            return;
                        }

                        boolean beforeTriggered = (boolean) isTriggeredMethod.invoke(beforeClone);
                        boolean afterTriggered = (boolean) isTriggeredMethod.invoke(after);

                        if (beforeTriggered != afterTriggered &&
                                !player.hasPermission(PropertyPermissions.TRIGGERED)) {
                            setTriggeredMethod.invoke(after, beforeTriggered);
                            updatedBlock.setBlockData((BlockData) after);
                            player.sendMessage("§cYou don't have permission to change crafter triggered state.");
                        }

                    } catch (Exception e) {
                    }
                }
            }, 1L);
        } catch (ClassNotFoundException e) {
        }
    }

    private void checkTrialSpawnerPermission(Player player, Block block, BlockData blockData) {
        try {
            Class<?> trialSpawnerClass = Class.forName("org.bukkit.block.data.type.TrialSpawner");
            if (!trialSpawnerClass.isInstance(blockData)) return;

            Object beforeClone = blockData.clone();
            SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
                Block updatedBlock = block.getLocation().getBlock();
                if (trialSpawnerClass.isInstance(updatedBlock.getBlockData())) {
                    Object after = updatedBlock.getBlockData();

                    try {
                        Method getStateMethod = trialSpawnerClass.getMethod("getTrialSpawnerState");
                        Method setStateMethod = trialSpawnerClass.getMethod("setTrialSpawnerState", getStateMethod.getReturnType());

                        Object beforeState = getStateMethod.invoke(beforeClone);
                        Object afterState = getStateMethod.invoke(after);

                        if (!beforeState.equals(afterState) &&
                                !player.hasPermission(PropertyPermissions.TRIAL_SPAWNER_STATE)) {
                            setStateMethod.invoke(after, beforeState);
                            updatedBlock.setBlockData((BlockData) after);
                            player.sendMessage("§cYou don't have permission to change trial spawner state.");
                            return;
                        }

                        try {
                            Method isOminousMethod = trialSpawnerClass.getMethod("isOminous");
                            Method setOminousMethod = trialSpawnerClass.getMethod("setOminous", boolean.class);

                            boolean beforeOminous = (boolean) isOminousMethod.invoke(beforeClone);
                            boolean afterOminous = (boolean) isOminousMethod.invoke(after);

                            if (beforeOminous != afterOminous &&
                                    !player.hasPermission(PropertyPermissions.OMINOUS)) {
                                setOminousMethod.invoke(after, beforeOminous);
                                updatedBlock.setBlockData((BlockData) after);
                                player.sendMessage("§cYou don't have permission to change trial spawner ominous state.");
                            }
                        } catch (NoSuchMethodException e) {
                        }
                    } catch (Exception e) {
                    }
                }
            }, 1L);
        } catch (ClassNotFoundException e) {
        }
    }

    private void checkVaultPermission(Player player, Block block, BlockData blockData) {
        try {
            Class<?> vaultClass = Class.forName("org.bukkit.block.data.type.Vault");
            if (!vaultClass.isInstance(blockData)) return;

            Object beforeClone = blockData.clone();
            SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
                Block updatedBlock = block.getLocation().getBlock();
                if (vaultClass.isInstance(updatedBlock.getBlockData())) {
                    Object after = updatedBlock.getBlockData();

                    try {
                        Method getStateMethod = vaultClass.getMethod("getVaultState");
                        Method setStateMethod = vaultClass.getMethod("setVaultState", getStateMethod.getReturnType());
                        Method isOminousMethod = vaultClass.getMethod("isOminous");
                        Method setOminousMethod = vaultClass.getMethod("setOminous", boolean.class);

                        Object beforeState = getStateMethod.invoke(beforeClone);
                        Object afterState = getStateMethod.invoke(after);

                        if (!beforeState.equals(afterState) &&
                                !player.hasPermission(PropertyPermissions.VAULT_STATE)) {
                            setStateMethod.invoke(after, beforeState);
                            updatedBlock.setBlockData((BlockData) after);
                            player.sendMessage("§cYou don't have permission to change vault state.");
                            return;
                        }

                        boolean beforeOminous = (boolean) isOminousMethod.invoke(beforeClone);
                        boolean afterOminous = (boolean) isOminousMethod.invoke(after);

                        if (beforeOminous != afterOminous &&
                                !player.hasPermission(PropertyPermissions.OMINOUS)) {
                            setOminousMethod.invoke(after, beforeOminous);
                            updatedBlock.setBlockData((BlockData) after);
                            player.sendMessage("§cYou don't have permission to change ominous state.");
                        }

                    } catch (Exception e) {
                    }
                }
            }, 1L);
        } catch (ClassNotFoundException e) {
        }
    }

    private void checkBigDripleafPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof BigDripleaf)) return;

        BigDripleaf beforeClone = (BigDripleaf) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof BigDripleaf) {
                BigDripleaf after = (BigDripleaf) updatedBlock.getBlockData();

                if (!beforeClone.getTilt().equals(after.getTilt()) &&
                        !player.hasPermission(PropertyPermissions.TILT)) {
                    after.setTilt(beforeClone.getTilt());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change big dripleaf tilt.");
                }
            }
        }, 1L);
    }

    private void checkSnifferEggPermission(Player player, Block block, BlockData blockData) {
        try {
            Class<?> snifferEggClass = Class.forName("org.bukkit.block.data.type.SnifferEgg");
            if (!snifferEggClass.isInstance(blockData)) return;

            Object beforeClone = blockData.clone();
            SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
                Block updatedBlock = block.getLocation().getBlock();
                if (snifferEggClass.isInstance(updatedBlock.getBlockData())) {
                    Object after = updatedBlock.getBlockData();

                    try {
                        Method getHatchMethod = snifferEggClass.getMethod("getHatch");
                        Method setHatchMethod = snifferEggClass.getMethod("setHatch", int.class);

                        int beforeHatch = (int) getHatchMethod.invoke(beforeClone);
                        int afterHatch = (int) getHatchMethod.invoke(after);

                        if (beforeHatch != afterHatch &&
                                !player.hasPermission(PropertyPermissions.HATCH)) {
                            setHatchMethod.invoke(after, beforeHatch);
                            updatedBlock.setBlockData((BlockData) after);
                            player.sendMessage("§cYou don't have permission to change sniffer egg hatch stage.");
                        }
                    } catch (Exception e) {
                    }
                }
            }, 1L);
        } catch (ClassNotFoundException e) {
        }
    }

    @SuppressWarnings("deprecation")
    private void checkPinkPetalsPermission(Player player, Block block, BlockData blockData) {
        try {
            Class<?> pinkPetalsClass = Class.forName("org.bukkit.block.data.type.PinkPetals");
            if (!pinkPetalsClass.isInstance(blockData)) return;

            Object beforeClone = blockData.clone();
            SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
                Block updatedBlock = block.getLocation().getBlock();
                if (pinkPetalsClass.isInstance(updatedBlock.getBlockData())) {
                    Object after = updatedBlock.getBlockData();

                    try {
                        Method getFlowerAmountMethod = pinkPetalsClass.getMethod("getFlowerAmount");
                        Method setFlowerAmountMethod = pinkPetalsClass.getMethod("setFlowerAmount", int.class);

                        int beforeAmount = (int) getFlowerAmountMethod.invoke(beforeClone);
                        int afterAmount = (int) getFlowerAmountMethod.invoke(after);

                        if (beforeAmount != afterAmount &&
                                !player.hasPermission(PropertyPermissions.FLOWER_AMOUNT)) {
                            setFlowerAmountMethod.invoke(after, beforeAmount);
                            updatedBlock.setBlockData((BlockData) after);
                            player.sendMessage("§cYou don't have permission to change pink petals amount.");
                        }
                    } catch (Exception e) {
                    }
                }
            }, 1L);
        } catch (ClassNotFoundException e) {
        }
    }

    private void checkPitcherCropPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof PitcherCrop)) return;

        PitcherCrop beforeClone = (PitcherCrop) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof PitcherCrop) {
                PitcherCrop after = (PitcherCrop) updatedBlock.getBlockData();

                if (beforeClone.getAge() != after.getAge() &&
                        !player.hasPermission(PropertyPermissions.AGE)) {
                    after.setAge(beforeClone.getAge());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change pitcher crop age.");
                }
            }
        }, 1L);
    }

    private void checkTorchflowerCropPermission(Player player, Block block, BlockData blockData) {
        try {
            Class<?> torchflowerClass = Class.forName("org.bukkit.block.data.type.TorchflowerCrop");
            if (!torchflowerClass.isInstance(blockData)) return;

            Object beforeClone = blockData.clone();
            SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
                Block updatedBlock = block.getLocation().getBlock();
                if (torchflowerClass.isInstance(updatedBlock.getBlockData())) {
                    Object after = updatedBlock.getBlockData();

                    try {
                        Method getAgeMethod = torchflowerClass.getMethod("getAge");
                        Method setAgeMethod = torchflowerClass.getMethod("setAge", int.class);

                        int beforeAge = (int) getAgeMethod.invoke(beforeClone);
                        int afterAge = (int) getAgeMethod.invoke(after);

                        if (beforeAge != afterAge &&
                                !player.hasPermission(PropertyPermissions.AGE)) {
                            setAgeMethod.invoke(after, beforeAge);
                            updatedBlock.setBlockData((BlockData) after);
                            player.sendMessage("§cYou don't have permission to change torchflower crop age.");
                        }
                    } catch (Exception e) {
                    }
                }
            }, 1L);
        } catch (ClassNotFoundException e) {
        }
    }

    private void checkBeehivePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Beehive)) return;

        Beehive beforeClone = (Beehive) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Beehive) {
                Beehive after = (Beehive) updatedBlock.getBlockData();

                if (beforeClone.getHoneyLevel() != after.getHoneyLevel() &&
                        !player.hasPermission(PropertyPermissions.HONEY_LEVEL)) {
                    after.setHoneyLevel(beforeClone.getHoneyLevel());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change honey level.");
                }
            }
        }, 1L);
    }

    private void checkSnowPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Snow)) return;

        Snow beforeClone = (Snow) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Snow) {
                Snow after = (Snow) updatedBlock.getBlockData();

                if (beforeClone.getLayers() != after.getLayers() &&
                        !player.hasPermission(PropertyPermissions.LAYERS)) {
                    after.setLayers(beforeClone.getLayers());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change snow layers.");
                }
            }
        }, 1L);
    }

    private void checkSweetBerryBushPermission(Player player, Block block, BlockData blockData) {
        try {
            Class<?> berryBushClass = Class.forName("org.bukkit.block.data.type.SweetBerryBush");
            if (!berryBushClass.isInstance(blockData)) return;

            Object beforeClone = blockData.clone();
            SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
                Block updatedBlock = block.getLocation().getBlock();
                if (berryBushClass.isInstance(updatedBlock.getBlockData())) {
                    Object after = updatedBlock.getBlockData();

                    try {
                        Method getAgeMethod = berryBushClass.getMethod("getAge");
                        Method setAgeMethod = berryBushClass.getMethod("setAge", int.class);

                        int beforeAge = (int) getAgeMethod.invoke(beforeClone);
                        int afterAge = (int) getAgeMethod.invoke(after);

                        if (beforeAge != afterAge &&
                                !player.hasPermission(PropertyPermissions.AGE)) {
                            setAgeMethod.invoke(after, beforeAge);
                            updatedBlock.setBlockData((BlockData) after);
                            player.sendMessage("§cYou don't have permission to change berry bush age.");
                        }
                    } catch (Exception e) {
                    }
                }
            }, 1L);
        } catch (ClassNotFoundException e) {
        }
    }

    @SuppressWarnings("deprecation")
    private void checkCaveVinesPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof CaveVines)) return;

        CaveVines beforeClone = (CaveVines) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof CaveVines) {
                CaveVines after = (CaveVines) updatedBlock.getBlockData();

                if (beforeClone.isBerries() != after.isBerries() &&
                        !player.hasPermission(PropertyPermissions.BERRIES)) {
                    after.setBerries(beforeClone.isBerries());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change cave vines berries.");
                }
            }
        }, 1L);
    }

    private void checkChiseledBookshelfPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof ChiseledBookshelf)) return;

        ChiseledBookshelf beforeClone = (ChiseledBookshelf) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof ChiseledBookshelf) {
                ChiseledBookshelf after = (ChiseledBookshelf) updatedBlock.getBlockData();

                try {
                    for (int i = 0; i < 6; i++) {
                        Method getSlotMethod = ChiseledBookshelf.class.getMethod("isSlotOccupied", int.class);
                        Method setSlotMethod = ChiseledBookshelf.class.getMethod("setSlotOccupied", int.class, boolean.class);

                        boolean beforeOccupied = (boolean) getSlotMethod.invoke(beforeClone, i);
                        boolean afterOccupied = (boolean) getSlotMethod.invoke(after, i);

                        if (beforeOccupied != afterOccupied &&
                                !player.hasPermission(PropertyPermissions.BOOKS_STORED)) {
                            setSlotMethod.invoke(after, i, beforeOccupied);
                            updatedBlock.setBlockData(after);
                            player.sendMessage("§cYou don't have permission to change bookshelf slots.");
                            return;
                        }
                    }
                } catch (Exception e) {
                }
            }
        }, 1L);
    }

    private void checkLecternPermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof Lectern)) return;

        Lectern beforeClone = (Lectern) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof Lectern) {
                Lectern after = (Lectern) updatedBlock.getBlockData();

                if (beforeClone.hasBook() != after.hasBook() &&
                        !player.hasPermission(PropertyPermissions.HAS_BOOK)) {
                    after.setHasBook(beforeClone.hasBook());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change lectern book state.");
                }
            }
        }, 1L);
    }

    private void checkMangrovePermission(Player player, Block block, BlockData blockData) {
        if (!(blockData instanceof MangrovePropagule)) return;

        MangrovePropagule beforeClone = (MangrovePropagule) blockData.clone();
        SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
            Block updatedBlock = block.getLocation().getBlock();
            if (updatedBlock.getBlockData() instanceof MangrovePropagule) {
                MangrovePropagule after = (MangrovePropagule) updatedBlock.getBlockData();

                if (beforeClone.getAge() != after.getAge() &&
                        !player.hasPermission(PropertyPermissions.AGE)) {
                    after.setAge(beforeClone.getAge());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change mangrove propagule age.");
                    return;
                }

                if (beforeClone.getStage() != after.getStage() &&
                        !player.hasPermission(PropertyPermissions.STAGE)) {
                    after.setStage(beforeClone.getStage());
                    updatedBlock.setBlockData(after);
                    player.sendMessage("§cYou don't have permission to change mangrove propagule stage.");
                }
            }
        }, 1L);
    }

    private void checkDriedGhastPermission(Player player, Block block, BlockData blockData) {
        if (!block.getType().name().equals("DRIED_GHAST")) return;

        try {
            Method getHydrationMethod = blockData.getClass().getMethod("getHydration");
            Method setHydrationMethod = blockData.getClass().getMethod("setHydration", int.class);

            Object beforeClone = blockData.clone();
            SchedulerUtil.runDelayed(plugin, block.getLocation(), () -> {
                Block updatedBlock = block.getLocation().getBlock();
                BlockData afterData = updatedBlock.getBlockData();

                try {
                    int beforeHydration = (int) getHydrationMethod.invoke(beforeClone);
                    int afterHydration = (int) getHydrationMethod.invoke(afterData);

                    if (beforeHydration != afterHydration &&
                            !player.hasPermission(PropertyPermissions.HYDRATION)) {
                        setHydrationMethod.invoke(afterData, beforeHydration);
                        updatedBlock.setBlockData(afterData);
                        player.sendMessage("§cYou don't have permission to change dried ghast hydration level.");
                    }
                } catch (Exception e) {
                }
            }, 1L);
        } catch (NoSuchMethodException e) {
        }
    }
}