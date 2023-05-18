package me.lonewolf.conduitcoreplugin.network;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author Lonewolf_12138(QQ1090001011)
 * @date 2023/5/18 16:42
 * @description TODO
 */

public class PacketThreadExecutor {

    private final ThreadPoolExecutor executor;

    private final Map<UUID, Queue<Runnable>> playerPackets = new ConcurrentHashMap<>();

    private final NetworkManager networkManager;

    public PacketThreadExecutor(NetworkManager networkManager) {
        this.networkManager = networkManager;
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("ConduitCore-sendPacketPool-%d").build();
        this.executor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
        this.executor.allowCoreThreadTimeOut(false);
    }

    public void onRegisterChannel(Player player){
        UUID uniqueId = player.getUniqueId();
        this.playerPackets.put(uniqueId, new ConcurrentLinkedQueue<>());
    }

    public void onPlayQuit(Player player){
        UUID uniqueId = player.getUniqueId();
        this.playerPackets.remove(uniqueId);
    }

    public void sendPacket(Player player, IPacket packet){
        UUID uniqueId = player.getUniqueId();
        if (this.playerPackets.get(uniqueId) == null) {
            return;
        }
        this.executor.execute(new PacketRunnable(this.networkManager, uniqueId, packet));
    }

    public void shutdown(){
        this.executor.shutdown();
    }

}
