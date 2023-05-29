package me.lonewolf.conduitcoreplugin.network.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.lonewolf.conduitcoreplugin.network.IPacket;
import net.minecraft.network.FriendlyByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Lonewolf_12138(QQ1090001011)
 * @date 2023/5/20 15:32
 * @description TODO
 */

public class SkillCoolDownPacket implements IPacket {

    private static final byte id = 11;

    /**
     * 是否清除以前所有buff的记录
     */
    public boolean clearPreviousSkillCoolDown;

    public List<SkillCoolDownPacket.SkillCoolDown> skillCoolDowns;

    public SkillCoolDownPacket(List<SkillCoolDown> skillCoolDowns, boolean clearPreviousSkillCoolDown) {
        this.clearPreviousSkillCoolDown = clearPreviousSkillCoolDown;
        this.skillCoolDowns = skillCoolDowns;
    }

    @Override
    public byte[] array() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeByte(id);
        byteBuf.writeBoolean(this.clearPreviousSkillCoolDown);
        int size = skillCoolDowns.size();
        byteBuf.writeInt(size);
        for (SkillCoolDown skillCoolDown : skillCoolDowns) {
            byte[] skill = skillCoolDown.name.getBytes(StandardCharsets.UTF_8);
            byteBuf.writeInt(skill.length);
            byteBuf.writeBytes(skill);
            byteBuf.writeLong(skillCoolDown.coolDownTime);
        }
        // 重置读指针
        byteBuf.resetReaderIndex();
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        byteBuf.release();
        return data;
    }

    @Override
    public void write(FriendlyByteBuf var1) {

    }

    public static class SkillCoolDown {

        /**
         * 技能名字
         */
        public String name;

        /**
         * 冷却 tick
         */
        public long coolDownTime;

        public SkillCoolDown(String name, long coolDownTime) {
            this.name = name;
            this.coolDownTime = coolDownTime;
        }

    }

}
