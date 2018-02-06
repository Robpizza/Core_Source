package me.robpizza.core.listeners;

import me.robpizza.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Entitydamage implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if(e.getEntity().getLocation().getWorld().getName().equals(Main.configs().getCoreConfig().getString("Protected-world"))) {
            if(e.getEntity() instanceof Player) {
                if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    e.setCancelled(true);
                }
                return;
            }
            return;
        }
    }
}
