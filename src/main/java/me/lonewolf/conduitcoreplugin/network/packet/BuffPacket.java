package me.lonewolf.conduitcoreplugin.network.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import lombok.Getter;
import me.lonewolf.conduitcoreplugin.network.IPacket;
import net.minecraft.network.FriendlyByteBuf;
import org.bukkit.World;
import org.json.simple.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Lonewolf_12138(QQ1090001011)
 * @date 2023/5/18 21:33
 * @description TODO
 */

public class BuffPacket implements IPacket {

    private static final byte id = 10;

    /**
     * 是否清除以前所有buff的记录
     */
    public boolean clearPreviousEffects;

    public List<Buff> buffs;

    public BuffPacket(List<Buff> buffs, boolean clearPreviousEffects) {
        this.buffs = buffs;
        this.clearPreviousEffects = clearPreviousEffects;
    }

    @Override
    public byte[] array() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeByte(id);
        byteBuf.writeBoolean(this.clearPreviousEffects);
        int size = this.buffs.size();
        byteBuf.writeInt(size);
        for (Buff buff : buffs) {
            byte[] nameBytes = buff.name.getBytes(StandardCharsets.UTF_8);
            byteBuf.writeInt(nameBytes.length);
            byteBuf.writeBytes(nameBytes);

            byte[] attributeBytes = buff.attribute.getBytes(StandardCharsets.UTF_8);
            byteBuf.writeInt(attributeBytes.length);
            byteBuf.writeBytes(attributeBytes);

            byte[] typeBytes = buff.type.getType().getBytes(StandardCharsets.UTF_8);
            byteBuf.writeInt(typeBytes.length);
            byteBuf.writeBytes(typeBytes);

            byteBuf.writeInt(buff.value);
            byteBuf.writeLong(buff.duration);
        }
        // 重置读指针
        byteBuf.resetReaderIndex();
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        byteBuf.release();

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("clearPreviousEffects", this.clearPreviousEffects);
//        JSONObject buffsJsonObject = new JSONObject();
//        for (Buff buff : this.buffs) {
//            Object buffObject = buffsJsonObject.get(buff.name);
//            if(buffObject == null) {
//                buffObject = new JSONObject();
//                buffsJsonObject.put(buff.name, buffObject);
//            }
//            JSONObject buffJsonObject = (JSONObject) buffObject;
//            JSONObject thisBuff = new JSONObject();
//            buffJsonObject.put(buff.attribute, thisBuff);
//            thisBuff.put("type", buff.type.getType());
//            thisBuff.put("value", buff.value);
//            thisBuff.put("duration", buff.duration);
//        }
//        jsonObject.put("buffs", buffsJsonObject);
//        byte[] jsonBytes = jsonObject.toJSONString().getBytes(CharsetUtil.UTF_8);
//        ByteBuf byteBuf = Unpooled.buffer();
//        byteBuf.writeByte(id);
//        byteBuf.writeBytes(jsonBytes);
//        byte[] data = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(data);
//        byteBuf.release();
        return data;
    }

    @Override
    public void write(FriendlyByteBuf var1) {

    }

    public static class Buff{

        /**
         * buff的名称
         */
        public String name;
        /**
         * 属性
         */
        public String attribute;
        /**
         * 类型
         */
        public BuffType type;
        /**
         * 数值
         */
        public int value;
        /**
         * 持续时间 tick
         */
        public long duration;

        public Buff(String name, String attribute, BuffType type, int value, long duration) {
            this.name = name;
            this.attribute = attribute;
            this.type = type;
            this.value = value;
            this.duration = duration;
        }
    }

    public static enum BuffType{
        /**
         * 直接相加属性
         */
        DIRECT_ADDITION("DIRECT_ADDITION"),
        /**
         * 百分比相乘属性
         */
        PERCENTAGE_ADDITION("PERCENTAGE_ADDITION");

        @Getter
        private final String type;

        BuffType(String type) {
            this.type = type;
        }
    }

}
