package me.robpizza;

import me.robpizza.core.commands.*;
import me.robpizza.core.listeners.*;
import me.robpizza.core.plugin.Configs;
import me.robpizza.core.plugin.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Main extends JavaPlugin {
    public static Plugin instance;

    public void onEnable() {
        instance = this;
        configs().setup(this);
        registerCommands();
        registerListeners();
        Metrics metrics = new Metrics(this);



        Bukkit.getLogger().log(Level.INFO, "");
        Bukkit.getLogger().log(Level.INFO, "[CORE] Listeners registered succesful!");
        Bukkit.getLogger().log(Level.INFO, "[CORE] Commands registered successful!");
        Bukkit.getLogger().log(Level.INFO, "[CORE] Please leave a rating on the Spigot page!");
        Bukkit.getLogger().log(Level.INFO, "[CORE] https://www.spigotmc.org/resources/core.32613/");
        Bukkit.getLogger().log(Level.INFO, "");
    }

    public void onDisable() {


    }


    public Plugin getInstance() {
        return instance;
    }

    public static Configs configs() {
        return Configs.getInstance();
    }


    private void registerCommands() {
        this.getCommand("broadcast").setExecutor(new Broadcast());
        this.getCommand("clearchat").setExecutor(new Clearchat(this));
        this.getCommand("core").setExecutor(new Corecommand());
        this.getCommand("fly").setExecutor(new Fly());
        this.getCommand("gm").setExecutor(new Gamemode());
        this.getCommand("ignore").setExecutor(new Ignore());
        this.getCommand("msg").setExecutor(new Msg());
        this.getCommand("spawn").setExecutor(new Spawn());
        this.getCommand("setspawn").setExecutor(new Spawn());
        this.getCommand("time").setExecutor(new Time());
        this.getCommand("tp").setExecutor(new Teleport());
        this.getCommand("tpa").setExecutor(new Tpa());
        this.getCommand("tpaccept").setExecutor(new Tpa());
        this.getCommand("tpdeny").setExecutor(new Tpa());
        this.getCommand("vanish").setExecutor(new Vanish());
        this.getCommand("warp").setExecutor(new Warp());
        this.getCommand("setwarp").setExecutor(new Warp());
        this.getCommand("delwarp").setExecutor(new Warp());
        this.getCommand("support").setExecutor(new Support());
        this.getCommand("hologram").setExecutor(new HologramCmd());
    }

    private void registerListeners() {
        Bukkit.getServer().getPluginManager().registerEvents(new Blockbreak(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Blockplace(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Chatevent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Commandpreprocess(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Death(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Entitydamage(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Joinevent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Leaveevent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Moveevent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Pingevent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SignListener(), this);
    }
}
