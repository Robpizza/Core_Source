package me.robpizza.core.commands;

import me.robpizza.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class Corecommand implements CommandExecutor {
    Plugin pl;


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            String Prefix = ChatColor.translateAlternateColorCodes('&',Main.configs().getMessagesConfig().getString("Core-prefix") + " ");
            if(cmd.getName().equalsIgnoreCase("core")) {
                if(args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cPlease use /core help"));
                    return true;
                }
                if(args[0].equalsIgnoreCase("help")) {
                    List<String> help = Main.configs().getMessagesConfig().getStringList("Core-Help");
                    for(String msg : help) {
                        msg = msg.replace("{player}", p.getName());
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',msg));
                    }
                    return true;
                } else if(args[0].equalsIgnoreCase("reload")) {
                    if(p.hasPermission("core.reload")) {
                        Main.configs().reloadCoreConfigs();
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&aCore configs reloaded successful!"));
                        for(Player op : Bukkit.getServer().getOnlinePlayers()) {
                            op.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&f " + p.getName() + " &areloaded the Core configs!"));
                        }
                        return true;
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission to reload the Core configs!"));
                        return true;
                    }
                }
            }
        } else {
            Bukkit.getLogger().severe("You can only run this command as player!");
            return true;
        }
        return false;
    }
}
