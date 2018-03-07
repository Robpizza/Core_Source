package me.robpizza.core.commands;

import me.robpizza.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Time implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String Commandlabel, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            String Prefix = ChatColor.translateAlternateColorCodes('&',Main.configs().getMessagesConfig().getString("Time-prefix") + " ");

            if(cmd.getName().equalsIgnoreCase("day")) {
                if(!p.hasPermission("core.time")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + " &cYou dont have the permission to change the time!"));
                    return true;
                }
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&fYou changed the time to day"));
                p.getWorld().setTime(6000);
                return true;
            }
            if(cmd.getName().equalsIgnoreCase("night")) {
                if(!p.hasPermission("core.time")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + " &cYou dont have the permission to change the time!"));
                    return true;
                }
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&fYou changed the time to night"));
                p.getWorld().setTime(18000);
                return true;
            }
        } else {
            Bukkit.getLogger().severe("You can only run this command as player!");
            return true;
        }
        return false;
    }
}
