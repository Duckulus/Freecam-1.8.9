package de.amin.v1_8_9.freecam;

import de.amin.freecam.FreecamConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.world.WorldSettings;
import org.lwjgl.input.Keyboard;

public class FreecamMod {

  public static Minecraft mc;
  public static boolean isEnabled = false;
  public Freecam freecam;
  private final FreecamConfig config = new FreecamConfig();

  KeyBinding freecamBinding = new KeyBinding("Toggle Freecam", Keyboard.KEY_X, "freecam");

  public static final String MOD_NAME = "Freecam";
  public static final String MOD_ID = "freecam";
  public static final String MOD_VERSION = "1.0";


  private static final FreecamMod instance = new FreecamMod();

  public void init() {
    mc = Minecraft.getMinecraft();
  }


  public void toggleFreecam() {
    if (!isEnabled) {
      enableFreecam();
    } else {
      disableFreecam();
    }
  }

  public void enableFreecam() {
    isEnabled = true;
    freecam = new Freecam();
    freecam.spawn();
    freecam.setPositionAndRotation(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ,
        mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
    freecam.inventory.mainInventory = Wrapper.player().inventory.mainInventory;
    freecam.movementInput = new MovementInputFromOptions(mc.gameSettings);
    mc.thePlayer.movementInput = new MovementInput();
    freecam.setGameType(WorldSettings.GameType.SPECTATOR);
    mc.setRenderViewEntity(freecam);
    mc.gameSettings.thirdPersonView = 0;
    Wrapper.player().moveForward = 0;
    Wrapper.player().moveStrafing = 0;
    Wrapper.player().setJumping(false);

    System.out.println("Freecam on");
    Wrapper.sendActionbar("Freecam enabled", 30);
  }

  public void disableFreecam() {
    isEnabled = false;
    freecam.despawn();
    mc.thePlayer.movementInput = freecam.movementInput;
    mc.setRenderViewEntity(mc.thePlayer);
    mc.renderGlobal.loadRenderers(); // reloads chunks to render players surroundings
    Wrapper.mc().getRenderManager().livingPlayer = Wrapper.player();
    System.out.println("Freecam off");
    Wrapper.sendActionbar("Freecam disabled", 30);
  }

  public static FreecamMod getInstance() {
    return instance;
  }

  public FreecamConfig getConfig() {
    return config;
  }
}
