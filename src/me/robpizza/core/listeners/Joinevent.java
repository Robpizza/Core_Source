package me.robpizza.core.listeners;

import me.robpizza.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Calendar;
import java.util.List;

public class Joinevent implements Listener {

    @EventHandler
    public void joinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();



        if (Main.configs().getCoreConfig().getBoolean("JoinLeaveMessage")) {
            String JoinMessage = Main.configs().getMessagesConfig().getString("JoinMessage");
            JoinMessage = JoinMessage.replace("{player}", p.getName());
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', JoinMessage));
        }


        for (int i = 1; i < 100; i++) {
            p.sendMessage("");
        }
        long time = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        List<String> joinMotd = Main.configs().getMessagesConfig().getStringList("Welcome-motd");
        for (String m : joinMotd) {
            m = m.replace("{time}", Calendar.HOUR_OF_DAY + ":" + cal.get(Calendar.MINUTE));
            m = m.replace("{player}", p.getName());
            m = m.replace("{world}", p.getLocation().getWorld().getName());
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
        }
        p.sendMessage("");
        p.performCommand("spawn");
    }
}
