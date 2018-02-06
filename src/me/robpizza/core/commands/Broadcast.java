package me.robpizza.core.commands;

import me.robpizza.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Broadcast implements CommandExecutor {
    public Broadcast() {}
    static Broadcast instance = new Broadcast();
    public static Broadcast getInstance() {
        return instance;
    }

    private String Prefix = ChatColor.translateAlternateColorCodes('&', Main.configs().getMessagesConfig().getString("Broadcast-prefix") + " ");
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("broadcast")) {
                if (!p.hasPermission("core.broadcast")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission for /broadcast"));
                    return true;
                }
                if (args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',(Prefix + "Use /broadcast <message>")));
                    return true;
                }

                StringBuilder str = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    str.append(args[i] + " ");
                }
                String Msg = str.toString();
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',Prefix + Msg));
                return true;
            }

        } else {
            Bukkit.getLogger().severe("You can only run this command as player!");
            return true;
        }
        return false;
    }
}
