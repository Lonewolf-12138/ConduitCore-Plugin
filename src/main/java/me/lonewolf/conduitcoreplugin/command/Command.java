package me.lonewolf.conduitcoreplugin.command;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.lonewolf.conduitcoreplugin.ConduitCore;
import me.lonewolf.conduitcoreplugin.network.packet.BuffPacket;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lonewolf_12138(QQ1090001011)
 * @date 2023/5/18 17:45
 * @description TODO
 */

public class Command  implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if(!commandSender.hasPermission("conduitcore.command.use")){
            return false;
        }
        if (strings != null && strings.length != 0) {
            Player player = Bukkit.getPlayer(strings[0]);
            List<BuffPacket.Buff> buffs = new ArrayList<>();
            buffs.add(new BuffPacket.Buff("利刃", "攻击力", BuffPacket.BuffType.DIRECT_ADDITION, 7, 20));
            buffs.add(new BuffPacket.Buff("利刃", "暴击率", BuffPacket.BuffType.PERCENTAGE_ADDITION, 15, 10));
            buffs.add(new BuffPacket.Buff("迅捷", "移动速度", BuffPacket.BuffType.PERCENTAGE_ADDITION, 30, 25));
            buffs.add(new BuffPacket.Buff("失重", "移动速度", BuffPacket.BuffType.PERCENTAGE_ADDITION, -15, 15));
            BuffPacket buffPacket = new BuffPacket(buffs, true);
            ConduitCore.getInstance().getPacketThreadExecutor().sendPacket(player, buffPacket);
        }
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
