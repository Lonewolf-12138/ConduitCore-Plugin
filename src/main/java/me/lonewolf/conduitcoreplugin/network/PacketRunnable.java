package me.lonewolf.conduitcoreplugin.network;

import me.lonewolf.conduitcoreplugin.ConduitCore;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Lonewolf_12138(QQ1090001011)
 * @date 2023/5/18 18:31
 * @description TODO
 */

public class PacketRunnable implements Runnable{

    private final NetworkManager networkManager;

    private final UUID uuid;

    private final IPacket packet;

    public PacketRunnable(NetworkManager networkManager, UUID uuid, IPacket packet) {
        this.networkManager = networkManager;
        this.uuid = uuid;
        this.packet = packet;
    }

    @Override
    public void run() {
        byte[] array = packet.array();
        Player player = Bukkit.getOfflinePlayer(uuid).getPlayer();
        if (player != null) {
            this.networkManager.sendPacket(player, array);
        }
    }

}
