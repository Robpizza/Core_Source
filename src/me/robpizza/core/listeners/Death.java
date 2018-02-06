package me.robpizza.core.listeners;

import me.robpizza.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if(Main.configs().getCoreConfig().getBoolean("InstandRespawn") == true) {
            Player p = e.getEntity().getPlayer();
            p.spigot().respawn();
            p.performCommand("spawn");
            return;
        }
        return;
    }
}
