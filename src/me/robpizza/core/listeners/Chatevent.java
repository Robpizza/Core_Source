package me.robpizza.core.listeners;

import me.robpizza.core.commands.Ignore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Chatevent implements Listener {
    HashMap<Player, ArrayList<Player>> ignore = Ignore.getInstance().getIgnore();
    @EventHandler
    public void onPlayerchat(AsyncPlayerChatEvent e) {
        Player sender = e.getPlayer();
        Set<Player> r = e.getRecipients();
        for(Player pls : Bukkit.getServer().getOnlinePlayers()) {
            if(!ignore.containsKey(pls)) return;
            if(ignore.get(pls).contains(sender)) {
                r.remove(pls);
            }
        }
    }

    @EventHandler
    public void addColorToChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();

        if(p.hasPermission("core.chat.color")) {
            e.setMessage(ChatColor.translateAlternateColorCodes('&', message));
        } else {
            e.setMessage(message);
        }
    }
}
