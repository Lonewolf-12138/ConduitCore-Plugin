package me.lonewolf.conduitcoreplugin.network;

import lombok.Getter;
import me.lonewolf.conduitcoreplugin.ConduitCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author Lonewolf_12138(QQ1090001011)
 * @date 2023/5/18 17:18
 * @description TODO
 */

public class NetworkManager {

    @Getter
    private final String channelId = "conduit:conduit";

    private final ConduitCore conduitCore;

    public NetworkManager(ConduitCore conduitCore) {
        this.conduitCore = conduitCore;
        Bukkit.getMessenger().registerOutgoingPluginChannel(ConduitCore.getInstance(), channelId);
    }

    public void sendPacket(Player player, byte[] array) {
        player.sendPluginMessage(this.conduitCore, channelId, array);
    }

}
