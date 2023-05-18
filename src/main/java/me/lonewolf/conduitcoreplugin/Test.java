package me.lonewolf.conduitcoreplugin;

import me.lonewolf.conduitcoreplugin.network.packet.BuffPacket;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lonewolf_12138(QQ1090001011)
 * @date 2023/5/18 22:10
 * @description TODO
 */

public class Test {

    public static boolean clearPreviousEffects = true;

    public static List<BuffPacket.Buff> buffs = new ArrayList<>();

    public static void main(String[] args) {
        buffs.add(new BuffPacket.Buff("利刃", "攻击力", BuffPacket.BuffType.DIRECT_ADDITION, 1, 10));
        buffs.add(new BuffPacket.Buff("利刃", "暴击率", BuffPacket.BuffType.PERCENTAGE_ADDITION, 1, 20));
        buffs.add(new BuffPacket.Buff("迅捷", "移动速度", BuffPacket.BuffType.DIRECT_ADDITION, 10, 10));
        buffs.add(new BuffPacket.Buff("重甲", "移动速度", BuffPacket.BuffType.DIRECT_ADDITION, -5, 30));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clearPreviousEffects", clearPreviousEffects);
        JSONObject buffsJsonObject = new JSONObject();
        jsonObject.put("buffs", buffsJsonObject);
        for (BuffPacket.Buff buff : buffs) {
            Object buffObject = buffsJsonObject.get(buff.name);
            if(buffObject == null) {
                buffObject = new JSONObject();
                buffsJsonObject.put(buff.name, buffObject);
            }
            JSONObject buffJsonObject = (JSONObject) buffObject;
            JSONObject thisBuff = new JSONObject();
            buffJsonObject.put(buff.attribute, thisBuff);
            thisBuff.put("type", buff.type.getType());
            thisBuff.put("value", buff.value);
            thisBuff.put("duration", buff.duration);
        }
        System.out.println(jsonObject);
    }

}
