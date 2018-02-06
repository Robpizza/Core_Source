package me.robpizza.core.commands;

import me.robpizza.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Clearchat implements CommandExecutor {
    private Main pl;
    public  Clearchat(Main instance) {
        pl = instance;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            String Prefix = ChatColor.translateAlternateColorCodes('&', (Main.configs().getMessagesConfig().getString("Clearchat-prefix") + " "));
            if(cmd.getName().equalsIgnoreCase("clearchat")) {
                if(!p.hasPermission("core.clearchat")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission to clear the chat!"));
                    return true;
                }

                for(Player onlineP : pl.getServer().getOnlinePlayers()) {
                    for(int i = 1; i < 150; i++) {
                        onlineP.sendMessage("");
                    }

                    String msg = Main.configs().getMessagesConfig().getString("Clearchat-msg");
                    msg = msg.replace("{player}", p.getName());
                    onlineP.sendMessage(ChatColor.translateAlternateColorCodes('&',msg));
                    onlineP.sendMessage("");
                }
                return true;
            }

        } else {
            Bukkit.getLogger().severe("You can only run this command as player!");
            return true;
        }

        return false;
    }
}
