package me.lonewolf.conduitcoreplugin;

import lombok.Getter;
import me.lonewolf.conduitcoreplugin.command.Command;
import me.lonewolf.conduitcoreplugin.listener.PlayerListener;
import me.lonewolf.conduitcoreplugin.network.NetworkManager;
import me.lonewolf.conduitcoreplugin.network.PacketThreadExecutor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ConduitCore extends JavaPlugin {

    @Getter
    private static ConduitCore instance;

    @Getter
    private PacketThreadExecutor packetThreadExecutor;

    @Getter
    private NetworkManager networkManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.networkManager = new NetworkManager(instance);
        this.packetThreadExecutor = new PacketThreadExecutor(this.networkManager);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(instance, this.packetThreadExecutor), this);
//        getCommand("conduitcore").setExecutor(new Command());
//        getCommand("conduitcore").setTabCompleter(new Command());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.packetThreadExecutor.shutdown();

    }
}
