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

public class Spawn implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            String Prefix = ChatColor.translateAlternateColorCodes('&',Main.configs().getMessagesConfig().getString("Spawn-prefix"));
            if(cmd.getName().equalsIgnoreCase("spawn")) {
                if (Main.configs().getLocConfig().getConfigurationSection("Spawn") == null) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cThe Spawn has not yet been set!"));
                    return true;
                }
                World w = Bukkit.getServer().getWorld(Main.configs().getLocConfig().getString("Spawn.world"));
                double x = Main.configs().getLocConfig().getDouble("Spawn.x");
                double y = Main.configs().getLocConfig().getDouble("Spawn.y");
                double z = Main.configs().getLocConfig().getDouble("Spawn.z");
                float pitch = Main.configs().getLocConfig().getInt("Spawn.pitch");
                float yaw = Main.configs().getLocConfig().getInt("Spawn.yaw");
                p.teleport(new Location(w, x, y, z, yaw, pitch));
                return true;
            }

            if (cmd.getName().equalsIgnoreCase("setspawn")) {
                if(!p.hasPermission("core.setspawn")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission for /setspawn"));
                    return true;
                }
                Main.configs().getLocConfig().set("Spawn.world", p.getLocation().getWorld().getName());
                Main.configs().getLocConfig().set("Spawn.x", p.getLocation().getX());
                Main.configs().getLocConfig().set("Spawn.y", p.getLocation().getY());
                Main.configs().getLocConfig().set("Spawn.z", p.getLocation().getZ());
                Main.configs().getLocConfig().set("Spawn.pitch", p.getLocation().getPitch());
                Main.configs().getLocConfig().set("Spawn.yaw", p.getLocation().getYaw());
                Main.configs().saveLocConfig();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&aSpawn set!"));
                return true;
            }
        } else {
            Bukkit.getLogger().severe("You can only run this command as player!");
            return true;
        }
        return false;
    }
}
