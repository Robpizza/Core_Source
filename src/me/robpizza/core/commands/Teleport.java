package me.robpizza.core.commands;

import me.robpizza.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            String Prefix = ChatColor.translateAlternateColorCodes('&',Main.configs().getMessagesConfig().getString("Teleport-prefix") + " ");
            if (cmd.getName().equalsIgnoreCase("tp")) {
                if (!p.hasPermission("core.tp")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission for /tp"));
                    return true;
                }

                if (args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cUse /tp <player> [target]"));
                    return true;
                }
                if (args.length == 1) {
                    Player t = Bukkit.getServer().getPlayer(args[0]);
                    if (t == null) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&f" + args[0] + " &c not found!"));
                        return true;
                    }
                    if (t == p) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou cant teleport to yourself!"));
                        return true;
                    }
                    p.teleport(t);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&fYou teleported to " + t.getName()));
                    return true;
                }
                if (args.length == 2) {
                    Player t = Bukkit.getServer().getPlayer(args[0]);
                    Player t2 = Bukkit.getServer().getPlayer(args[1]);
                    if (t == null) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&f" + args[0] + " &cnot found!"));
                        return true;
                    }
                    if (t2 == null) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&f" + args[1] + " &cnot found!"));
                        return true;
                    }
                    if (t == t2) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou cant teleport to yourself!"));
                        return true;
                    }

                    t.teleport(t2);
                    t.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&fYou teleported " + t.getName() + " to &7" + t2.getName()));
                    t2.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + t.getName() + "&f teleported to you."));
                    return true;
                }
            }
        } else {
            Bukkit.getLogger().severe("You can only send this command as player!");
            return true;
        }
        return false;
    }
}
