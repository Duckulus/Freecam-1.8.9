package de.amin.freecam;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

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

    public static void sendActionbar(String message, int duration, boolean rainbow) {
        GuiIngame gui = mc().ingameGUI;
        ObfuscationReflectionHelper.setPrivateValue(GuiIngame.class, gui, message, "field_73838_g"); // recordPlaying
        ObfuscationReflectionHelper.setPrivateValue(GuiIngame.class, gui, duration, "field_73845_h"); // recordPlayingUpFor
        ObfuscationReflectionHelper.setPrivateValue(GuiIngame.class, gui, rainbow, "field_73844_j"); // recordIsPlaying
    }

    public static void sendActionbar(String message, int duration) {
        sendActionbar(message, duration, false);
    }
}
