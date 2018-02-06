package me.robpizza.core.plugin;

import me.robpizza.core.commands.Broadcast;
import me.robpizza.core.commands.Fly;
import me.robpizza.core.commands.Vanish;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Api {

    public Api() {}
    static Api instance = new Api();

    public static Api getInstance() {
        return instance;
    }




    public boolean isVanish(Player p) {
        return Vanish.getInstance().getVanish().contains(p.getUniqueId());
    }

    public boolean isFly(Player p) {
        return Fly.getInstance().getFly().contains(p.getUniqueId());
    }

    public void setVanish(Player p) {
        Vanish.getInstance().setVanish(p, true);
        return;
    }

    public void removeVanish(Player p) {
        Vanish.getInstance().setVanish(p, false);
        return;
    }

    public void setFly(Player p) {
        Fly.getInstance().setFly(p, true);
        return;
    }

    public void removeFly(Player p) {
        Fly.getInstance().setFly(p, false);
    }
}
