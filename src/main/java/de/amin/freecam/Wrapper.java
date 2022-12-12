package de.amin.freecam;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;

public class Wrapper {

    public static Minecraft mc() {
        return Minecraft.getMinecraft();
    }

    public static EntityPlayerSP player() {
        return mc().thePlayer;
    }

    public static void sendMessage(String message) {
        mc().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(message));
    }
}
