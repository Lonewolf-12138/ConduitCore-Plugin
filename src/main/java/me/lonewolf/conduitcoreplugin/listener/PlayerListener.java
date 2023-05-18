package me.lonewolf.conduitcoreplugin.listener;

import me.lonewolf.conduitcoreplugin.ConduitCore;
import me.lonewolf.conduitcoreplugin.network.PacketThreadExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;

/**
 * @author Lonewolf_12138(QQ1090001011)
 * @date 2023/5/18 17:06
 * @description TODO
 */

public class PlayerListener implements Listener {

    private final ConduitCore conduitCore;

    private final PacketThreadExecutor packetThreadExecutor;

    public PlayerListener(ConduitCore conduitCore, PacketThreadExecutor packetThreadExecutor) {
        this.packetThreadExecutor = packetThreadExecutor;
        this.conduitCore = conduitCore;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent e){
        this.packetThreadExecutor.onPlayQuit(e.getPlayer());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerRegisterChannel(PlayerRegisterChannelEvent e){
        String channel = e.getChannel();
        if (channel.equals(this.conduitCore.getNetworkManager().getChannelId())) {
            this.packetThreadExecutor.onRegisterChannel(e.getPlayer());
        }
    }

}
