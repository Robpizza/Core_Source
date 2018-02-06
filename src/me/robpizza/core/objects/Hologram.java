package me.robpizza.core.objects;

import com.sun.istack.internal.NotNull;
import me.robpizza.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;

public class Hologram {

    String name;
    List<String> lines = new ArrayList<String>();
    Location loc;
    private FileConfiguration config = Main.configs().getHologramsConfig();

    public Hologram() { }

    public void setName(String name) {
        this.name = name;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public void addLine(String line) {
        this.lines.add(line);
    }

    public String getName() {
        return name;
    }

    public List<String> getLines() {
        return lines;
    }




    public Location getLoc(@NotNull String name) {
        if(name == null || name == "") {
            Bukkit.getLogger().log(Level.WARNING, "[CORE] Error make sure you devined name!");
            return null;
        }

        if(!config.getConfigurationSection("holograms").contains(name)) {
            Bukkit.getLogger().log(Level.WARNING, "[CORE] Error while getting location of hologram: " + name + " make sure it exists!");
            return null;
        }
        return new Location(Bukkit.getWorld(config.get("holograms." + name + ".world").toString()), (Double)config.get("holograms." + name + ".x"), (Double) config.get("holograms." + name + ".y"), (Double) config.get("holograms." + name + ".z"));
    }



    //Make sure
    //NAME, LOCATION, LINES
    //Are set!
    public void create() {
        if(name == null || lines == null || loc == null || name == "") {
            Bukkit.getLogger().log(Level.WARNING, "[CORE] Error while creating the hologram make sure to set the name, lines and the location before creating it!");
            return;
        }
        for (int i = 0; i < lines.size(); i++) {
            if (i >= 9) {
                Bukkit.getLogger().log(Level.WARNING, "[CORE] Hologram " + name + " has more than 9 lines!");
                Bukkit.getLogger().log(Level.WARNING, "[CORE] CoreHolograms can only show up to 9 lines.");
                break;
            }

            Location location = new Location(loc.getWorld(), loc.getX(), loc.getY() - 0.25 * i, loc.getZ());
            ArmorStand armorStand = (ArmorStand) loc.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            armorStand.setGravity(false);
            armorStand.setCanPickupItems(false);
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', lines.get(i).replace("\"", "")));
            armorStand.setCustomNameVisible(true);
            armorStand.setVisible(false);
        }
        config.set("holograms." + name + ".world", loc.getWorld().getName());
        config.set("holograms." + name + ".x", loc.getX());
        config.set("holograms." + name + ".y", loc.getY());
        config.set("holograms." + name + ".z", loc.getZ());
        config.set("holograms." + name + ".lines", lines);
        Main.configs().saveHologramsConfig();
        return;
    }

    //Make sure
    //NAME, LOCATION
    //Are set!
    public void remove() {
        if(name == null || loc == null || name == "") {
            Bukkit.getLogger().log(Level.WARNING, "[CORE] Error while removing the hologram make sure to set the name and the location before removing a hologram!");
            return;
        }
        for (Entity entity : getNearbyntities(loc, (int) 2.5)) {
            if(entity.getType() == EntityType.ARMOR_STAND) {
                entity.remove();
            }
        }
        config.set("holograms." + name, null);
        Main.configs().saveHologramsConfig();
    }

    public void refresh() {
        for (String name : config.getConfigurationSection("holograms").getKeys(false)) {
            Location location = getLoc(name);
            for (Entity entity : getNearbyntities(location, (int) 2.5)) {
                entity.remove();
            }
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
            @Override
            public void run() {
                try {
                    Main.configs().reloadCoreConfigs();
                    FileConfiguration config = Main.configs().getHologramsConfig();
                    if(config.getConfigurationSection("holograms").getKeys(false).isEmpty()) {
                        Bukkit.getLogger().log(Level.WARNING, "[CORE] Can't refresh holograms. No holograms found!");
                        return;
                    }
                    for (String hologram : config.getConfigurationSection("holograms").getKeys(false)) {
                        lines.clear();
                        for (String line : config.getStringList("holograms." + hologram + ".lines")) {
                            addLine(line);
                        }
                        loc = getLoc(hologram);
                        name = hologram;
                        create();
                    }
                } catch (NullPointerException ignored) {
                    ignored.printStackTrace();
                }
            }
        }, 20 * 2);
    }



    private Entity[] getNearbyntities(Location l, int radius) {
        int chunkRadius = radius < 16 ? 1 : (radius - (radius % 16)) / 16;
        HashSet<Entity> radiusEntities = new HashSet<Entity>();

        for (int chX = 0 - chunkRadius; chX <= chunkRadius; chX++) {
            for (int chZ = 0 - chunkRadius; chZ <= chunkRadius; chZ++) {
                int x = (int) l.getX(), y = (int) l.getY(), z = (int) l.getZ();
                for (Entity e : new Location(l.getWorld(), x + (chX * 16), y, z + (chZ * 16)).getChunk().getEntities()) {
                    if(e.getLocation().distance(l) <= radius && e.getLocation().getBlock() != l.getBlock()) {
                        radiusEntities.add(e);
                    }
                }
            }
        }
        return radiusEntities.toArray(new Entity[radiusEntities.size()]);
    }
}
