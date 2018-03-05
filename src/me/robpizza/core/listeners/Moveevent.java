package me.robpizza.core.listeners;

import me.robpizza.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Moveevent implements Listener {
    @EventHandler
    public void voidHit(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(Main.configs().getCoreConfig().getBoolean("VoidTeleport")) {
            if(p.getLocation().getY() <= Main.configs().getCoreConfig().getInt("LowestY")) {
                p.performCommand("spawn");
            }
        }
    }
}
