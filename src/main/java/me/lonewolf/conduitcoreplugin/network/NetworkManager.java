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
    private final String channelId = "conduit";

    /**
     * 只有修改以前包的时候才需要修改版本，添加新的包不需要修改版本
     */
    @Getter
    private final String channelVersion = "v1";

    @Getter
    private final String channel;

    private final ConduitCore conduitCore;

    public NetworkManager(ConduitCore conduitCore) {
        this.channel = this.channelId + this.channelVersion + ":" + this.channelId + this.channelVersion;
        this.conduitCore = conduitCore;
        Bukkit.getMessenger().registerOutgoingPluginChannel(ConduitCore.getInstance(), this.channel);
    }

    public void sendPacket(Player player, byte[] array) {
        player.sendPluginMessage(this.conduitCore, this.channel, array);
    }

}
