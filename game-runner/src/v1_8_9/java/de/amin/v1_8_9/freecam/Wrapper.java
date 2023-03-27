package de.amin.v1_8_9.freecam;

import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiIngame;
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

  public static void sendActionbar(String message, int duration, boolean rainbow) {
    GuiIngame gui = mc().ingameGUI;
    setPrivateValue(GuiIngame.class, gui, message,
        "field_73838_g"); // recordPlaying
    setPrivateValue(GuiIngame.class, gui, duration,
        "field_73845_h"); // recordPlayingUpFor
    setPrivateValue(GuiIngame.class, gui, rainbow,
        "field_73844_j"); // recordIsPlaying
  }

  public static void sendActionbar(String message, int duration) {
    sendActionbar(message, duration, false);
  }

  private static <T, E> void setPrivateValue(Class<? super T> clazz, T instance, E value,
      String fieldname) {
    try {
      findField(clazz, fieldname).set(instance, value);
    } catch (IllegalAccessException ignored) {
    }
  }

  public static Field findField(Class<?> clazz, String... fieldNames) {
    Exception failed = null;
    for (String fieldName : fieldNames) {
      try {
        Field f = clazz.getDeclaredField(fieldName);
        f.setAccessible(true);
        return f;
      } catch (Exception e) {
        failed = e;
      }
    }
    return null;
  }
}
