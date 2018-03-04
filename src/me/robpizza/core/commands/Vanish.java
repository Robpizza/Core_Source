package me.robpizza.core.commands;

import me.robpizza.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Vanish implements CommandExecutor {
    public Vanish() {}
    static Vanish instance = new Vanish();
    public static Vanish getInstance() {
        return instance;
    }


    private ArrayList<UUID> vanish = new ArrayList<UUID>();
    private HashMap<UUID, Location> location = new HashMap<UUID, Location>();
    private HashMap<UUID, ItemStack[]> itemsaves = new HashMap<UUID, ItemStack[]>();
    private HashMap<UUID, ItemStack[]> armorsaves = new HashMap<UUID, ItemStack[]>();

    private String Prefix = ChatColor.translateAlternateColorCodes('&',Main.configs().getMessagesConfig().getString("Vanish-prefix") + " ");


    public boolean onCommand(CommandSender sender, Command cmd, String Commandlabel, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("vanish")) {
                if(!p.hasPermission("core.vanish")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cYou dont have the permission for /vanish"));
                    return true;
                }
                if(args.length == 0) {
                    if(vanish.contains(p.getUniqueId())) {
                        setVanish(p, false);
                        return true;
                    }
                    setVanish(p, true);
                    return true;
                }

                if(p.hasPermission("core.vanish.others")) {
                    Player t = Bukkit.getServer().getPlayer(args[0]);
                    if (t == null) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&ccould not find player &f" + args[0] + "&c!"));
                        return true;
                    }

                    if (vanish.contains(t.getUniqueId())) {
                        setVanish(t, false);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&fYou &cdisabled &fvanish for " + t.getName()));
                        return true;
                    }
                    setVanish(t, true);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&fYou &aenabled &fvanish for " + t.getName()));
                    return true;




                }
            }
        }
        return true;
    }

    public void setVanish(Player p, Boolean b) {
        if(!b) {
            Location loc = location.get(p.getUniqueId());
            p.teleport(loc);
            vanish.remove(p.getUniqueId());
            location.remove(p.getUniqueId());
            p.setFlying(false);
            p.setAllowFlight(false);
            p.getInventory().clear();
            p.getInventory().setContents(itemsaves.get(p.getUniqueId()));
            p.getInventory().setArmorContents(armorsaves.get(p.getUniqueId()));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&fvanish &cdisabled!"));
            for(Player OnlinePlayers : Bukkit.getServer().getOnlinePlayers()) {
                OnlinePlayers.showPlayer(p);
            }
            return;
        }
        location.put(p.getUniqueId(), p.getLocation());
        vanish.add(p.getUniqueId());
        itemsaves.put(p.getUniqueId(), p.getInventory().getContents());
        armorsaves.put(p.getUniqueId(), p.getEquipment().getArmorContents());
        p.getInventory().clear();
        p.getEquipment().clear();
        p.setAllowFlight(true);
        p.setFlying(true);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&fvanish &aenabled!"));
        for(Player OnlinePlayers : Bukkit.getServer().getOnlinePlayers()) {
            OnlinePlayers.hidePlayer(p);
        }
    }

    public ArrayList<UUID> getVanish() {
        return vanish;
    }






}
