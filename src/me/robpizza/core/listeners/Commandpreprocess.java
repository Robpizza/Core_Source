package me.robpizza.core.listeners;

import me.robpizza.Main;
import me.robpizza.core.plugin.Api;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;


public class Commandpreprocess implements Listener {
    Plugin plugin;

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if(Api.getInstance().isVanish(p)) {
            List<String> cmds = Main.configs().getCoreConfig().getStringList("BlockedCommands");
            for(String command : cmds) {
                if(e.getMessage().equalsIgnoreCase("/" + command)) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lERROR &8&l>> &cYou can't use this command while you're in vanish!"));
                    e.setCancelled(true);
                    return;
                }
            }
            return;
        }
        return;
    }
}
