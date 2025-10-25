package me.kanuunankuula.debugStick;

import me.kanuunankuula.debugStick.commands.CommandManager;
import me.kanuunankuula.debugStick.config.ConfigManager;
import me.kanuunankuula.debugStick.listeners.DebugStickListener;
import me.kanuunankuula.debugStick.util.SchedulerUtil;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;

public class DebugStick extends JavaPlugin {

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        printHeader();

        if (SchedulerUtil.isFolia()) {
            getLogger().info("§aFolia detected! Using region-based scheduling.");
        } else {
            getLogger().info("§aPaper/Spigot detected! Using standard scheduling.");
        }

        configManager = new ConfigManager(this);

        try {
            Permission debugStickPerm = new Permission("minecraft.debugstick.always",
                    "Allows using debug stick without OP",
                    PermissionDefault.TRUE);
            Bukkit.getPluginManager().addPermission(debugStickPerm);
            getLogger().info("§aRegistered minecraft.debugstick.always permission.");
        } catch (IllegalArgumentException e) {
            getLogger().info("§eminecraft.debugstick.always permission already registered.");
        }

        Bukkit.getPluginManager().registerEvents(new DebugStickListener(this), this);

        CommandManager commandManager = new CommandManager(this);
        getCommand("debugstick").setExecutor(commandManager);
        getCommand("cds").setExecutor(commandManager);

    }

    @Override
    public void onDisable() {
        try {
            Bukkit.getPluginManager().removePermission("minecraft.debugstick.always");
        } catch (Exception e) {
        }
        getLogger().info("§cDebugStick plugin has been disabled.");
        getLogger().info("§eThank you for using DebugStick!");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    private void printHeader() {
        getLogger().info("§8========================================");
        getLogger().info("§6      DebugStick Plugin v" + getDescription().getVersion());
        getLogger().info("§7      By Kanuunankuula [@If_Master]");
        getLogger().info("§8========================================");
    }

}