package me.robpizza.core.objects;

import me.robpizza.core.commands.Fly;
import me.robpizza.core.commands.Vanish;
import net.minecraft.server.v1_8_R3.ExceptionPlayerNotFound;
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

    /**
     *
     * @deprecated Do not use this Method use 'CPlayer(UUID)' or 'CPlayer(PLAYER)' instead!
     */
    @Deprecated
    public CPlayer(String name) throws ExceptionPlayerNotFound {
        if(Bukkit.getPlayer(name) != null) {
            p = Bukkit.getPlayer(name);
        } else {
            throw new ExceptionPlayerNotFound("Player " + name + " not found!");
        }
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
