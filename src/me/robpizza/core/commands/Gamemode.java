package me.robpizza.core.commands;

import me.robpizza.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            String Prefix = Main.configs().getMessagesConfig().getString("Gamemode-prefix");
            if(cmd.getName().equalsIgnoreCase("gm")) {
                if (!p.hasPermission("core.gamemode")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission to change your gamemode!"));
                    return true;
                }
                if (args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cUse /gm <gamemode>"));
                    return true;
                }
                if (args[0].equals("0") || args[0].equalsIgnoreCase("survival")) {
                    String message = Main.configs().getMessagesConfig().getString("Gamemode");
                    message = message.replace("{gamemode}", "Survival");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + message));
                    p.setGameMode(GameMode.SURVIVAL);
                    return true;
                } else if (args[0].equals("1") || args[0].equalsIgnoreCase("creative")) {
                    String message = Main.configs().getMessagesConfig().getString("Gamemode");
                    message = message.replace("{gamemode}", "Creative");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + message));
                    p.setGameMode(GameMode.CREATIVE);
                    return true;
                } else if (args[0].equals("2") || args[0].equalsIgnoreCase("adventure")) {
                    String message = Main.configs().getMessagesConfig().getString("Gamemode");
                    message = message.replace("{gamemode}", "Adventure");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + message));
                    p.setGameMode(GameMode.ADVENTURE);
                    return true;
                } else if (args[0].equals("3") || args[0].equalsIgnoreCase("spectator")) {
                    String message = Main.configs().getMessagesConfig().getString("Gamemode");
                    message = message.replace("{gamemode}", "Spectator");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + message));
                    p.setGameMode(GameMode.SPECTATOR);
                    return true;
                }
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cWrong arguments. Use /gm <gamemode>"));
                    return true;
            }
        } else {
            Bukkit.getLogger().severe("You can only run this command as player!");
            return true;
        }
        return false;
    }
}
