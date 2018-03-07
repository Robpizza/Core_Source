package me.robpizza.core.objects;

import me.robpizza.core.commands.Fly;
import me.robpizza.core.commands.Vanish;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;
import java.util.UUID;

public class CPlayer {

    private Player p;


    public CPlayer(Player player) {
        p = player;
    }

    public CPlayer(UUID uuid) {
        p = Bukkit.getPlayer(uuid);
    }



    public String getDisplayName() {
        return p.getDisplayName();
    }

    public String getName() {
        return p.getName();
    }

    public InetSocketAddress getAddress() {
        return p.getAddress();
    }

    public Player getPlayer() {
        return p;
    }

    public UUID getUuid() {
        return p.getUniqueId();
    }

    public boolean isVanish() {
        return Vanish.getInstance().getVanish().contains(p.getUniqueId());
    }

    public boolean isFlying() {
        return Fly.getInstance().getFly().contains(p.getUniqueId());
    }

    public void setVanish() {
        Vanish.getInstance().setVanish(p, true);
    }

    public void removeVanish() {
        Vanish.getInstance().setVanish(p, false);
    }

    public void setFlying() {
        Fly.getInstance().setFly(p, true);
    }

    public void removeFly(Player p) {
        Fly.getInstance().setFly(p, false);
    }
}
