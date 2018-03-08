package me.robpizza.core.listeners;

import me.robpizza.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;

public class Pingevent implements Listener {
    @EventHandler
    public void onServerPing(ServerListPingEvent e) {
        String motd = ChatColor.translateAlternateColorCodes('&', Main.configs().getCoreConfig().getString("SystemMotd"));
        motd = motd.replace("{version}", "Stable-1.4.6");
        e.setMotd(ChatColor.translateAlternateColorCodes('&', motd));
    }
}
