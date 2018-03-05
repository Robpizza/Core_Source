package me.robpizza.core.listeners;

import me.robpizza.Main;
import me.robpizza.core.objects.CPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Blockbreak implements Listener {
    @EventHandler
    public void onBlockbreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        CPlayer cPlayer = new CPlayer(p);
        if(Main.configs().getCoreConfig().getBoolean("Enabled")) {
            if (!p.hasPermission("core.bypass") || !p.isOp() || cPlayer.isVanish()) {
                if (p.getLocation().getWorld().getName().equals(Main.configs().getCoreConfig().getString("Protected-world"))) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
