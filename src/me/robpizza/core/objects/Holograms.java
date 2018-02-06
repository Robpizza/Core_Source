package me.robpizza.core.objects;

import com.sun.istack.internal.NotNull;
import me.robpizza.Main;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;

public class Holograms {
    /*

    Location location;

    public Holograms(Location location) {
        this.location = location;
    }

    public void createHologram(String name, List<String> lines) {
        for(int i = 0; i < lines.size(); i++) {
            Location loc = new Location(location.getWorld(), location.getX(), location.getY() - 0.25 * i, location.getZ());
            ArmorStand as = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            as.setGravity(false);
            as.setCanPickupItems(false);

            String tname = lines.get(i);
            tname = tname.replace("\"", "");
            as.setCustomName(ChatColor.translateAlternateColorCodes('&', tname));
            as.setCustomNameVisible(true);
            as.setVisible(false);
        }
        hologramsConfig.set("holograms." + name + ".world", location.getWorld().getName());
        hologramsConfig.set("holograms." + name + ".x", location.getX());
        hologramsConfig.set("holograms." + name + ".y", location.getY());
        hologramsConfig.set("holograms." + name + ".z", location.getZ());
        hologramsConfig.set("holograms." + name + ".lines", lines);
        Main.configs().saveHologramsConfig();
    }

    public void removeHologram(String name) {
        World world = Bukkit.getWorld(hologramsConfig.get("holograms." + name + ".world").toString());
        double x = (double) hologramsConfig.get("holograms." + name + ".x");
        double y = (double) hologramsConfig.get("holograms." + name + ".y");
        double z = (double) hologramsConfig.get("holograms." + name + ".z");
        Location loc = new Location(world, x, y, z);

        for(Entity entity : getNearbyntities(loc, (int) 2.5)) {
            if(!(entity.getType() == EntityType.ARMOR_STAND)) {
                return;
            }
            entity.remove();
        }
        Main.configs().getHologramsConfig().set("holograms." + name, null);
        Main.configs().saveHologramsConfig();
    }


    public void refresh() {
        Main.configs().reloadCoreConfigs();
        FileConfiguration config = Main.configs().getHologramsConfig();
        for (String name : config.getConfigurationSection("holograms").getKeys(false)) {
            World world = Bukkit.getWorld(config.get("holograms." + name + ".world").toString());
            double x = (double) config.get("holograms." + name + ".x");
            double y = (double) config.get("holograms." + name + ".y");
            double z = (double) config.get("holograms." + name + ".z");
            Location loc = new Location(world, x, y, z);

            for (Entity entity : getNearbyntities(loc, (int) 2.5)) {
                entity.remove();
            }
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
            @Override
            public void run() {
                try {
                    for (String name : config.getConfigurationSection("holograms").getKeys(false)) {
                        World world = Bukkit.getWorld(config.get("holograms." + name + ".world").toString());
                        double x = (double) config.get("holograms." + name + ".x");
                        double z = (double) config.get("holograms." + name + ".z");
                        List<String> lines = new ArrayList<String>();

                        for(String line : config.getStringList("holograms." + name + ".lines")) {
                            lines.add(line);
                        }

                        for(int i = 0; i < lines.size(); i++) {
                            if(i >= 9) {
                                Bukkit.getLogger().log(Level.WARNING, "[CORE] Hologram " + name + " has more than 9 lines!");
                                Bukkit.getLogger().log(Level.WARNING, "[CORE] CoreHolograms can only show up to 9 lines.");
                                break;
                            }
                            double y = (double) config.get("holograms." + name + ".y") - 0.25 * i;
                            Location loc = new Location(world, x, y, z);
                            ArmorStand as = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
                            as.setGravity(false);
                            as.setCanPickupItems(false);

                            String tname = lines.get(i);
                            tname = tname.replace("\"", "");
                            as.setCustomName(ChatColor.translateAlternateColorCodes('&', tname));
                            as.setCustomNameVisible(true);
                            as.setVisible(false);
                        }
                    }
                } catch (NullPointerException ignored) {}
            }
        }, 20*1);
    }



    private Entity[] getNearbyntities(Location l, int radius) {
        int chunkRadius = radius < 16 ? 1 : (radius - (radius % 16)) / 16;
        HashSet<Entity> radiusEntities = new HashSet<Entity>();

        for(int chX = 0 - chunkRadius; chX <= chunkRadius; chX++) {
            for(int chZ = 0 - chunkRadius; chZ <= chunkRadius; chZ++) {
                int x = (int) l.getX(), y = (int) l.getY(), z = (int) l.getZ();
                for(Entity e : new Location(l.getWorld(), x + (chX * 16), y, z + (chZ * 16)).getChunk().getEntities()) {
                    if(e.getLocation().distance(l) <= radius && e.getLocation().getBlock() != l.getBlock()) {
                        radiusEntities.add(e);
                    }
                }
            }
        }
        return radiusEntities.toArray(new Entity[radiusEntities.size()]);
    }
    */
}
