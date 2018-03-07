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


    private String Prefix = Main.configs().getMessagesConfig().getString("Gamemode-prefix");

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("gm")) {
                if (!p.hasPermission("core.gamemode")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission to change your gamemode!"));
                    return true;
                }
                if (args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cUse /gm <gamemode>"));
                    return true;
                }
                if (args[0].equals("0") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")) {
                    setGamemode(p, GameMode.SURVIVAL);
                    return true;
                } else if (args[0].equals("1") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) {
                    setGamemode(p, GameMode.CREATIVE);
                    return true;
                } else if (args[0].equals("2") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a")) {
                    setGamemode(p, GameMode.ADVENTURE);
                    return true;
                } else if (args[0].equals("3") || args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp")) {
                    setGamemode(p, GameMode.SPECTATOR);
                    return true;
                }
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cWrong arguments. Use /gm <0|1|2|3>"));
                return true;
            }

            else if(cmd.getName().equalsIgnoreCase("gms")) {
                if (!p.hasPermission("core.gamemode")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission to change your gamemode!"));
                    return true;
                }
                setGamemode(p, GameMode.SURVIVAL);
                return true;
            }

            else if(cmd.getName().equalsIgnoreCase("gmc")) {
                if (!p.hasPermission("core.gamemode")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission to change your gamemode!"));
                    return true;
                }
                setGamemode(p, GameMode.CREATIVE);
                return true;
            }

            else if(cmd.getName().equalsIgnoreCase("gma")) {
                if (!p.hasPermission("core.gamemode")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission to change your gamemode!"));
                    return true;
                }
                setGamemode(p, GameMode.ADVENTURE);
                return true;
            }

            else if(cmd.getName().equalsIgnoreCase("gmsp")) {
                if (!p.hasPermission("core.gamemode")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission to change your gamemode!"));
                    return true;
                }
                setGamemode(p, GameMode.SPECTATOR);
                return true;
            }


        } else {
            Bukkit.getLogger().severe("You can only run this command as player!");
            return true;
        }
        return false;
    }

    public void setGamemode(Player p, GameMode gameMode) {
        String message = Main.configs().getMessagesConfig().getString("Gamemode");
        message = message.replace("{gamemode}", gameMode.toString().toLowerCase());
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + message));
        p.setGameMode(gameMode);

    }
}
