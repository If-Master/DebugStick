package me.kanuunankuula.debugStick.config;

import me.kanuunankuula.debugStick.DebugStick;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConfigManager {

    private final DebugStick plugin;
    private FileConfiguration config;

    private FilterMode filterMode;
    private Set<Material> blacklistedBlocks;
    private Set<Material> whitelistedBlocks;
    private boolean deleteDropped;
    private String blockedBlockMessage;
    private boolean showDebugStickGivenMessage;
    private boolean showDebugStickDroppedMessage;

    public ConfigManager(DebugStick plugin) {
        this.plugin = plugin;
        this.blacklistedBlocks = new HashSet<>();
        this.whitelistedBlocks = new HashSet<>();
        loadConfig();
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();

        deleteDropped = config.getBoolean("Delete-Dropped", true);

        String filterModeStr = config.getString("Filter-Mode", "NONE").toUpperCase();
        try {
            filterMode = FilterMode.valueOf(filterModeStr);
        } catch (IllegalArgumentException e) {
            plugin.getLogger().warning("Invalid Filter-Mode: " + filterModeStr + ". Using NONE.");
            filterMode = FilterMode.NONE;
        }

        blacklistedBlocks.clear();
        List<String> blacklist = config.getStringList("Blacklisted-Blocks");
        for (String blockName : blacklist) {
            try {
                Material material = Material.valueOf(blockName.toUpperCase());
                blacklistedBlocks.add(material);
            } catch (IllegalArgumentException e) {
                plugin.getLogger().warning("Invalid material in blacklist: " + blockName);
            }
        }

        whitelistedBlocks.clear();
        List<String> whitelist = config.getStringList("Whitelisted-Blocks");
        for (String blockName : whitelist) {
            try {
                Material material = Material.valueOf(blockName.toUpperCase());
                whitelistedBlocks.add(material);
            } catch (IllegalArgumentException e) {
                plugin.getLogger().warning("Invalid material in whitelist: " + blockName);
            }
        }

        blockedBlockMessage = config.getString("Blocked-Block-Message", "§cYou cannot modify this block with the debug stick!");
        showDebugStickGivenMessage = config.getBoolean("Messages.Debug-Stick-Given", true);
        showDebugStickDroppedMessage = config.getBoolean("Messages.Debug-Stick-Dropped", true);

        plugin.getLogger().info("Configuration loaded successfully!");
        plugin.getLogger().info("Filter Mode: " + filterMode);
        plugin.getLogger().info("Blacklisted blocks: " + blacklistedBlocks.size());
        plugin.getLogger().info("Whitelisted blocks: " + whitelistedBlocks.size());
    }

    public boolean isBlockAllowed(Material material) {
        switch (filterMode) {
            case BLACKLIST:
                return !blacklistedBlocks.contains(material);
            case WHITELIST:
                return whitelistedBlocks.contains(material);
            case NONE:
            default:
                return true;
        }
    }

    public boolean isDeleteDropped() {
        return deleteDropped;
    }

    public String getBlockedBlockMessage() {
        return blockedBlockMessage;
    }

    public FilterMode getFilterMode() {
        return filterMode;
    }

    public boolean showDebugStickGivenMessage() {
        return showDebugStickGivenMessage;
    }

    public boolean showDebugStickDroppedMessage() {
        return showDebugStickDroppedMessage;
    }

    public enum FilterMode {
        NONE,
        BLACKLIST,
        WHITELIST
    }
}