package me.robpizza.core.commands;

import me.robpizza.Main;
import me.robpizza.core.objects.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HologramCmd implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            String Prefix = ChatColor.translateAlternateColorCodes('&', (Main.configs().getMessagesConfig().getString("Holograms-prefix") + " "));

            if (cmd.getName().equalsIgnoreCase("hologram")) {
                if (!p.hasPermission("core.hologram")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "&cYou dont have the permission to create holograms!"));
                    return true;
                }

                Main.configs().reloadCoreConfigs();
                FileConfiguration config = Main.configs().getHologramsConfig();

                if (args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "&cUse /hologram create"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "&cUse /hologram remove"));
                    return true;
                }

                if(args[0].equalsIgnoreCase("create")) {
                    if(args.length < 2) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "&cUse /hologram create <name> <text>"));
                        return true;
                    }
                    List<String> lines = new ArrayList<>();
                    StringBuilder str = new StringBuilder("");
                    int arguments = 0;
                    for (String msg : args) {
                        if(msg.equals(args[0]) || msg.equals(args[1])) continue;
                        if(arguments == 9) break;
                        if(msg.startsWith("\"")) {
                            str.append(msg + " ");
                            continue;
                        }
                        if(msg.endsWith("\"")) {
                            str.append(msg);
                            String string = str.toString();
                            lines.add(string);
                            str.setLength(0);
                            continue;
                        }

                        if(!(str.toString().equals(""))) {
                            str.append(msg + " ");
                            continue;
                        }
                        lines.add(msg);
                        arguments++;
                    }

                    if(config.contains("holograms." + args[1])) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "&cHologram &f" + args[1] + " &calready exist!"));
                        return true;
                    }


                    Hologram hologram = new Hologram();
                    hologram.setName(args[1]);
                    hologram.setLoc(p.getLocation());
                    hologram.setLines(lines);
                    hologram.create();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "&aHologram &f" + args[1] + " &asuccessfully created!"));
                    return true;
                } else if(args[0].equalsIgnoreCase("remove")) {
                    if(args.length < 2) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "&cUse /hologram remove <name>"));
                        return true;
                    }

                    if(!config.contains("holograms." + args[1])) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "&cHologram &f" + args[1] + " &cdoes not exist!"));
                        return true;
                    }

                    Hologram hologram = new Hologram();
                    hologram.setName(args[1]);
                    hologram.setLoc(hologram.getLoc(args[1]));
                    hologram.remove();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "&aHologram &f" + args[1] + " &asuccessfully removed!"));
                    return true;
                } else if(args[0].equalsIgnoreCase("refresh")) {
                    Hologram hologram = new Hologram();
                    hologram.refresh();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "&aHolograms refreshed!"));
                    return true;
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + "&cUse /hologram create <name> <text>"));
                    return true;
                }
            }
        } else {
            Bukkit.getLogger().severe("You can only run this command as player!");
            return true;
        }
        return false;
    }
}
