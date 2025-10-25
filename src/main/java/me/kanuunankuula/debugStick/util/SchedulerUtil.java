package me.kanuunankuula.debugStick.util;

import me.kanuunankuula.debugStick.DebugStick;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Method;

public class SchedulerUtil {

    private static boolean isFolia;
    private static Method getRegionSchedulerMethod;
    private static Method runDelayedMethod;

    static {
        try {
            Class.forName("io.papermc.paper.threadedregions.scheduler.RegionScheduler");
            isFolia = true;

            Class<?> serverClass = Bukkit.getServer().getClass();
            getRegionSchedulerMethod = serverClass.getMethod("getRegionScheduler");

            Class<?> regionSchedulerClass = Class.forName("io.papermc.paper.threadedregions.scheduler.RegionScheduler");
            runDelayedMethod = regionSchedulerClass.getMethod("runDelayed",
                    Plugin.class, Location.class, java.util.function.Consumer.class, long.class);

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            isFolia = false;
        }
    }

    public static void runDelayed(DebugStick plugin, Location location, Runnable task, long delay) {
        if (isFolia) {
            runDelayedFolia(plugin, location, task, delay);
        } else {
            runDelayedBukkit(plugin, task, delay);
        }
    }

    private static void runDelayedBukkit(DebugStick plugin, Runnable task, long delay) {
        Bukkit.getScheduler().runTaskLater(plugin, task, delay);
    }

    private static void runDelayedFolia(DebugStick plugin, Location location, Runnable task, long delay) {
        try {
            Object regionScheduler = getRegionSchedulerMethod.invoke(Bukkit.getServer());

            java.util.function.Consumer<Object> consumer = (scheduledTask) -> task.run();

            runDelayedMethod.invoke(regionScheduler, plugin, location, consumer, delay);
        } catch (Exception e) {
            plugin.getLogger().warning("Failed to use Folia scheduler, falling back to Bukkit scheduler");
            runDelayedBukkit(plugin, task, delay);
        }
    }

    public static boolean isFolia() {
        return isFolia;
    }
}