package me.robpizza.core.commands;

import me.robpizza.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Tpa implements CommandExecutor {
    String Prefix = ChatColor.translateAlternateColorCodes('&',Main.configs().getMessagesConfig().getString("Tpa-prefix" ) + " ");
    HashMap<UUID,UUID> teleportrequest = new HashMap<UUID,UUID>();


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("tpa")) {
                if(!p.hasPermission("core.tpa")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission to request a teleport!"));
                    return true;
                }
                if(args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cPlease specify a player!"));
                    return true;
                }

                Player t = Bukkit.getServer().getPlayer(args[0]);
                if(t == null) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cPlayer &f" + args[0] + " &cnot found!"));
                    return true;
                }
                if(t == p) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "You cant teleport to yourself!"));
                    return true;
                }
                sendReqeust(p, t);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
                    public void run() {
                        killTask(t);
                    }
                }, Main.configs().getCoreConfig().getInt("tpa-timeout") * 20L);
            }
            if(cmd.getName().equalsIgnoreCase("tpaccept")) {
                if(!p.hasPermission("core.tpa")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission for /tpaccept!"));
                    return true;
                }
                requestBoolean(p, true);
                return true;
            }

            if(cmd.getName().equalsIgnoreCase("tpdeny")) {
                if(!p.hasPermission("core.tpa")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission for /tpaccept!"));
                    return true;
                }
                requestBoolean(p, false);
                return true;
            }
        } else {
            Bukkit.getLogger().severe("You can only run this command as player!");
            return true;
        }
        return false;
    }



    private void sendReqeust(Player p, Player r) {
        teleportrequest.put(r.getUniqueId(), p.getUniqueId());
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&fTeleport request send to " + r.getName()));
        r.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&f" + p.getName() + " wants to teleport to you."));
        r.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&a/tpaccept &for &c/tpdeny"));
    }

    private void requestBoolean(Player p, boolean r) {
        if(r) {
            if(teleportrequest.containsKey(p.getUniqueId())) {
                UUID uuid = teleportrequest.get(p.getUniqueId());
                Player t = Bukkit.getServer().getPlayer(uuid);

                t.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&fTeleporting to " + p.getName()));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&f" + t.getName() + " is teleporting to you."));
                t.teleport(p);
                teleportrequest.remove(p.getUniqueId());
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cThe teleport request expired."));
            }
        } else {
            if(teleportrequest.containsKey(p.getUniqueId())) {
                UUID uuid = teleportrequest.get(p.getUniqueId());
                Player target = Bukkit.getServer().getPlayer(uuid);

                target.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + p.getName() + " denied your teleport request."));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "Teleport request from " + target.getName() + " denied."));
                teleportrequest.remove(p.getUniqueId());
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cThe teleport request expired."));
            }
        }
    }

    private boolean killTask(Player p) {
        if(teleportrequest.containsKey(p.getUniqueId())) {

            UUID uuid = teleportrequest.get(p.getUniqueId());
            Player target = Bukkit.getServer().getPlayer(uuid);
            if(target != null) {
                target.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYour teleport request to " + p.getName() + " timed out."));
            }
            teleportrequest.remove(p.getUniqueId());
            return true;
        } else {
            return false;
        }
    }
}
