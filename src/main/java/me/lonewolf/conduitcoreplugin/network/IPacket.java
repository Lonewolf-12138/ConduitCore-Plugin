package me.lonewolf.conduitcoreplugin.network;

import net.minecraft.network.FriendlyByteBuf;

public interface IPacket {

    /**
     * 转为byte[] 用于发送
     */
    byte[] array();

    /**
     * 处理
     * @param var1
     */
    void write(FriendlyByteBuf var1);

}
