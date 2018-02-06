package me.robpizza.core.listeners;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.util.List;

public class SignListener implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
            for (int i = 0; i < 4; i++) {
                String line = e.getLine(i);
                if (line != null && !line.equals("")) {
                    if (e.getPlayer().hasPermission("core.sign.color")) {
                        e.setLine(i, ChatColor.translateAlternateColorCodes('&', line));
                    } else {
                        e.setLine(i, line);
                }
            }
        }
    }
}
