package me.robpizza.core.listeners;

import me.robpizza.Main;
import me.robpizza.core.plugin.Api;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Blockbreak implements Listener {
    Api Listener = Api.getInstance();
    @EventHandler
    public void onBlockbreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if(!p.hasPermission("core.bypass") || !p.isOp() || Api.getInstance().isVanish(p)) {
            if (p.getLocation().getWorld().getName().equals(Main.configs().getCoreConfig().getString("Protected-world"))) {
                e.setCancelled(true);
                return;
            }
        }
        return;
    }
}
