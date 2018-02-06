package me.robpizza.core.commands;

import me.robpizza.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Ignore implements CommandExecutor {
    public Ignore() {}
    static Ignore instance = new Ignore();
    public static Ignore getInstance() {
        return instance;
    }

    public HashMap<Player, ArrayList<Player>> getIgnore() {
        return ignoreManager;
    }
    HashMap<Player, ArrayList<Player>> ignoreManager = new HashMap<Player, ArrayList<Player>>();
    HashMap<Player, ArrayList<Player>> ignore = getIgnore();


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            String Prefix = ChatColor.translateAlternateColorCodes('&', Main.configs().getMessagesConfig().getString("Ignore-prefix"));
            if(cmd.getName().equalsIgnoreCase("ignore")) {
                if(args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cPlease enter a player name!"));
                    return true;
                }
                Player t = Bukkit.getServer().getPlayer(args[0]);
                if(t == null) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cPlayer &f" + args[0] + "&c not found!"));
                    return true;
                }
                if(p == t) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou cant ignore yourself!"));
                    return true;
                }
                if(t.hasPermission("core.ignore.staff") || t.isOp()) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou cant ignore a staff member!"));
                    return true;
                }
                if(ignore.get(p) == null || !ignore.get(p).contains(t)) {
                    ArrayList<Player> al = new ArrayList<Player>();
                    al.add(t);
                    ignore.put(p, al);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou're now ignoring &f" + t.getName()));
                    return true;
                }
                if(ignore.get(p).contains(t)) {
                    ArrayList<Player> al = ignore.get(p);
                    al.remove(t);
                    ignore.remove(p, al);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&aYou're no longer ignoring &f" + t.getName()));
                    return true;
                }
            }
        } else {
            Bukkit.getLogger().severe("You can only run this command as player!");
            return true;
        }
        return false;
    }
}
