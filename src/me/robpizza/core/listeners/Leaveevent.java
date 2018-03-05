package me.robpizza.core.listeners;

import me.robpizza.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leaveevent implements Listener {
    @EventHandler
    public void leaveEvent(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if(Main.configs().getCoreConfig().getBoolean("JoinLeaveMessage")) {
                String LeaveMessage = Main.configs().getMessagesConfig().getString("LeaveMessage");
                LeaveMessage = LeaveMessage.replace("{player}", p.getName());
                e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', LeaveMessage));
        }
    }
}
