package me.robpizza.core.plugin;

import me.robpizza.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class Configs {
    Configs() {}
    static Configs instance = new Configs();

    public static Configs getInstance() {
        return instance;
    }





    Plugin p;
    FileConfiguration config, locations, messages, holograms;
    File cfile, lfile, mfile, hfile;

    public  FileConfiguration getCoreConfig() {
        return this.config;
    }

    public FileConfiguration getLocConfig() {
        return this.locations;
    }

    public FileConfiguration getMessagesConfig() {
        return this.messages;
    }

    public FileConfiguration getHologramsConfig() {
        return this.holograms;
    }

    public void saveLocConfig() {
        try {
            locations.save(lfile);
        } catch (IOException ex) {
            p.getLogger().log(Level.SEVERE, "Could not save config to " + lfile, ex);
        }
    }

    public void saveMessagesConfig() {
        try {
            messages.save(mfile);
        } catch (IOException ex) {
            p.getLogger().log(Level.SEVERE, "Could not save config to " + mfile, ex);
        }
    }

    public void saveHologramsConfig() {
        try {
            holograms.save(hfile);
        } catch (IOException ex) {
            p.getLogger().log(Level.SEVERE, "Could not save config to " + hfile, ex);
        }
    }

    public void reloadCoreConfigs() {
        config = new YamlConfiguration();
        locations = new YamlConfiguration();
        messages = new YamlConfiguration();
        holograms = new YamlConfiguration();
        try {
            config.load(cfile);
            locations.load(lfile);
            messages.load(mfile);
            holograms.load(hfile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void setup(Plugin p) {
        cfile = new File(p.getDataFolder(), "config.yml");
        lfile = new File(p.getDataFolder(), "warps.yml");
        mfile = new File(p.getDataFolder(), "messages.yml");
        hfile = new File(p.getDataFolder(), "holograms.yml");

        if(!cfile.exists()) {
            cfile.getParentFile().mkdirs();
            p.saveResource("config.yml", false);
            p.getConfig().options().copyDefaults(true);
            p.saveDefaultConfig();
        }
        if(!lfile.exists()) {
            lfile.getParentFile().mkdirs();
            p.saveResource("warps.yml", false);
        }
        if(!mfile.exists()) {
            mfile.getParentFile().mkdirs();
            p.saveResource("messages.yml", false);
        }
        if(!hfile.exists()) {
            hfile.getParentFile().mkdirs();
            p.saveResource("holograms.yml", false);
        }

        config = new YamlConfiguration();
        locations = new YamlConfiguration();
        messages = new YamlConfiguration();
        holograms = new YamlConfiguration();
        try {
            config.load(cfile);
            locations.load(lfile);
            messages.load(mfile);
            holograms.load(hfile);
        } catch (IOException ex) {
            p.getLogger().log(Level.SEVERE, "Could not load config files ", ex);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
