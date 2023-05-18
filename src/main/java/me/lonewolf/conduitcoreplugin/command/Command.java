package me.lonewolf.conduitcoreplugin.command;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.lonewolf.conduitcoreplugin.ConduitCore;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author Lonewolf_12138(QQ1090001011)
 * @date 2023/5/18 17:45
 * @description TODO
 */

public class Command  implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
//        ConduitCore instance = ConduitCore.getInstance();
//        instance.getPacketThreadExecutor().sendPacket((Player) commandSender, new Runnable() {
//            @Override
//            public void run() {
//                ByteBuf byteBuf = Unpooled.buffer();
//                byteBuf.writeByte(10);
//                byteBuf.writeByte(9);
//                byte[] data = new byte[byteBuf.readableBytes()];
//                byteBuf.readBytes(data);
//                instance.getNetworkManager().sendPacket((Player) commandSender, data);
//                byteBuf.release();
//            }
//        });
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        return null;
    }

}
