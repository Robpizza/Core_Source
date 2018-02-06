package me.robpizza.core.commands;

import me.robpizza.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Support implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            if(cmd.getName().equalsIgnoreCase("support")) {
                Player p = (Player) sender;
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&l############################"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&l#      &8&lCore-Support"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&l#&7&m---------------------------"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&l#"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&l# &7Core Wiki"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&l# &5https://github.com/Robpizza/Core/wiki"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&l#"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&l# &7Core Issues?"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&l# &5https://github.com/Robpizza/Core/issues"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&l#"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&l############################"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
                return true;
            }
        } else {
            Bukkit.getLogger().severe("Please run this command as ingame player!");
            return true;
        }
        return false;
    }
}
