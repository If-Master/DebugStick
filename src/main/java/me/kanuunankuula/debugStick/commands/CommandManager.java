package me.kanuunankuula.debugStick.commands;

import me.kanuunankuula.debugStick.DebugStick;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {

    private final DebugStick plugin;

    public CommandManager(DebugStick plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("debugstick")) {
            return handleDebugStickCommand(sender);
        }
        else if (command.getName().equalsIgnoreCase("cds")) {
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                return handleReloadCommand(sender);
            }
            return handleCdsCommand(sender);
        }

        return false;
    }

    private boolean handleReloadCommand(CommandSender sender) {
        if (!sender.hasPermission("debugstick.reload")) {
            sender.sendMessage("§cYou do not have permission to reload the configuration.");
            return true;
        }

        try {
            plugin.getConfigManager().loadConfig();
            sender.sendMessage("§aConfiguration reloaded successfully!");
        } catch (Exception e) {
            sender.sendMessage("§cFailed to reload configuration: " + e.getMessage());
            plugin.getLogger().severe("Error reloading config: " + e.getMessage());
        }

        return true;
    }

    private boolean handleDebugStickCommand(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command can only be run by a player.");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("debugstick.command")) {
            player.sendMessage("§cYou do not have permission to use this command.");
            return true;
        }

        ItemStack debugStick = new ItemStack(Material.DEBUG_STICK);
        player.getInventory().addItem(debugStick);

        if (plugin.getConfigManager().showDebugStickGivenMessage()) {
            player.sendMessage("§aYou have been given a Debug Stick!");
        }
        return true;
    }

    private boolean handleCdsCommand(CommandSender sender) {
        if (!sender.hasPermission("debugstick.help")) {
            sender.sendMessage("§cYou do not have permission to use this command.");
            return true;
        }

        sender.sendMessage("§6§l=== Debug Stick ===");
//        sender.sendMessage("§6Usage Tutorial: §9https://youtu.be/zX7voYvH4mw");
        sender.sendMessage("");
        sender.sendMessage("§e/debugstick §7- Gives you the custom debug stick.");
        sender.sendMessage("§e/cds §7- Shows this help menu.");
        sender.sendMessage("\n§6§l| Usage Tutorial |");
        sender.sendMessage("§eLeft click (Attack/Destroy button) [L2] §7- to cycle  ");
        sender.sendMessage("§eRight click (Interaction button) [R2] §7- to change values  ");
        if (!(sender.hasPermission("debugstick.viewfilter"))) {
            sender.sendMessage("\n§7Filter Mode: §e" + plugin.getConfigManager().getFilterMode());
        }


//        sender.sendMessage("");
//        sender.sendMessage("§6§lPermissions:");
//        sender.sendMessage("§7Use §e/cds permissions §7for a full list of block property permissions.");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("cds") && args.length == 1) {
            if (sender.hasPermission("debugstick.reload")) {
                return Arrays.asList("reload");
            }
        }
        return new ArrayList<>();
    }

}