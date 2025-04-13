package com.jatc251.ActionBarDisplayX;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionBarDisplayX extends JavaPlugin {

    private String actionBarMessage;
    private String version = "v1.0.0";

    @Override
    public void onEnable() {
        if (!getConfig().getBoolean("enabled", true)) {
            getLogger().info("ActionBarDisplayX " + version + " disabled due to config.");
            return;
        }
        saveDefaultConfig();
        loadMessage();
        startActionBarTask();
        getLogger().info("ActionBarDisplayX " + version + " enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("ActionBarDisplayX " + version + " disabled.");
    }

    private void loadMessage() {
        actionBarMessage = ChatColor.translateAlternateColorCodes('&', getConfig().getString("message", ""));
    }

    private void startActionBarTask() {
        final SimpleDateFormat formatter = new SimpleDateFormat(getConfig().getString("timestamp-format", "dd.MM.yyyy 'at' HH:mm:ss z"));
        Long period = getConfig().getLong("period", 20);
        Long delay = getConfig().getLong("delay", 0);
        new BukkitRunnable() {
            @Override
            public void run() {
                String timeStamp = formatter.format(new Date());
                String combinedMessage;
                boolean appendTimeStamp = Boolean.parseBoolean(getConfig().getString("append-timestamp", ""));
                if (appendTimeStamp) {
                    combinedMessage = actionBarMessage + timeStamp;
                } else {
                    combinedMessage = actionBarMessage;
                }
                TextComponent component = new TextComponent(combinedMessage);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
                }
            }
        }.runTaskTimer(this, delay, period);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("reloadactionbar")) {
            reloadConfig();
            loadMessage();
            sender.sendMessage(ChatColor.GREEN + "ActionBarDisplayX reloaded");
            return true;
        }
        return false;
    }
}
