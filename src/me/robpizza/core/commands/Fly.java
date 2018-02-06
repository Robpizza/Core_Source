package me.robpizza.core.commands;

import me.robpizza.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Fly implements CommandExecutor {
    public Fly() {}
    static Fly instance = new Fly();

    public static Fly getInstance() {
        return instance;
    }

    public static ArrayList<UUID> fly = new ArrayList<UUID>();

    String Prefix = Main.configs().getMessagesConfig().getString("Fly-prefix");
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("fly")) {
                if (!p.hasPermission("core.fly")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission for the fly command!"));
                    return true;
                }
                if (args.length == 0) {
                    if (fly.contains(p.getUniqueId())) {
                        setFly(p, false);
                        return true;
                    }
                    setFly(p, true);
                    return true;
                } else if(args.length == 1 && args.length < 2) {
                    if (p.hasPermission("core.fly.other")) {
                        Player t = Bukkit.getServer().getPlayer(args[0]);
                        if (t == null) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "&cCould not find player &f " + args[0] + "&c!"));
                            return true;
                        }
                        if (fly.contains(t.getUniqueId())) {
                            setFly(t, false);
                            return true;
                        }
                        setFly(t, true);
                        return true;
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cPlease use /fly [player]"));
                    return true;
                }
            }
        } else {
            Bukkit.getLogger().severe("You can only run this command as player!");
            return true;
        }
        return false;
    }

    public void setFly(Player p, Boolean b) {
        if (!b) {
            fly.remove(p.getUniqueId());
            p.setFlying(false);
            p.setAllowFlight(false);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&fFly is &cdisabled!"));
            return;
        }
        fly.add(p.getUniqueId());
        p.setAllowFlight(true);
        p.setFlying(true);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&fFly is &aenabled!"));
    }

    public static ArrayList<UUID> getFly() {
        return fly;
    }
}
