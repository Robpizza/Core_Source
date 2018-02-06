package me.robpizza.core.commands;

import me.robpizza.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Warp implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String Commandlabel, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            String Prefix = ChatColor.translateAlternateColorCodes('&',Main.configs().getMessagesConfig().getString("Warp-prefix") + " ");
            if(cmd.getName().equalsIgnoreCase("warp")) {
                if(args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cPlease enter a warp name."));
                    return true;
                }
                if(args[0].equalsIgnoreCase("list")) {
                    String warps = "" + Main.configs().getLocConfig().getConfigurationSection("Warps").getKeys(false);
                    warps = warps.replace("[", "");
                    warps = warps.replace("]", ", ");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&fAvailable warps are: &a" + warps));
                    return true;
                }
                if(Main.configs().getLocConfig().getString("Warps." + args[0]) == null) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cWarp not found, &7/warp list!"));
                    return true;
                }
                World w = Bukkit.getServer().getWorld(Main.configs().getLocConfig().getString("Warps." + args[0] + ".world"));
                double x = Main.configs().getLocConfig().getDouble("Warps." + args[0] + ".x");
                double y = Main.configs().getLocConfig().getDouble("Warps." + args[0] + ".y");
                double z = Main.configs().getLocConfig().getDouble("Warps." + args[0] + ".z");
                float pitch = Main.configs().getLocConfig().getInt("Warps." + args[0] + ".pitch");
                float yaw = Main.configs().getLocConfig().getInt("Warps." + args[0] + ".yaw");
                p.teleport(new Location(w, x, y, z, yaw, pitch));
                return true;
            }
            if (cmd.getName().equalsIgnoreCase("setwarp")) {
                if(!p.hasPermission("core.setwarp")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission for /setwarp"));
                    return true;
                }
                if(args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cPlease enter warp name!"));
                    return true;
                }
                Main.configs().getLocConfig().set("Warps." + args[0] + ".world", p.getLocation().getWorld().getName());
                Main.configs().getLocConfig().set("Warps." + args[0] + ".x", p.getLocation().getX());
                Main.configs().getLocConfig().set("Warps." + args[0] + ".y", p.getLocation().getY());
                Main.configs().getLocConfig().set("Warps." + args[0] + ".z", p.getLocation().getZ());
                Main.configs().getLocConfig().set("Warps." + args[0] + ".pitch", p.getLocation().getPitch());
                Main.configs().getLocConfig().set("Warps." + args[0] + ".yaw", p.getLocation().getYaw());
                Main.configs().saveLocConfig();
                Main.configs().saveLocConfig();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "Warp &f" + args[0] + " set!"));
                return true;
            }
            if(cmd.getName().equalsIgnoreCase("delwarp")) {
                if (!p.hasPermission("core.delwarp")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission for /delwarp"));
                    return true;
                }
                if (args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cPlease enter warp name!"));
                    return true;
                }
                Main.configs().getLocConfig().set("Warps." + args[0], null);
                Main.configs().saveLocConfig();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&aWarp &f" + args[0] + " &aremoved!"));
                return true;
            }
        } else {
            Bukkit.getLogger().severe("You can only run this command as a player!");
            return true;
        }
        return false;
    }
}
