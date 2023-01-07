package de.amin.freecam;

import de.amin.freecam.commands.CommandHandler;
import de.amin.freecam.commands.impl.FreeCamCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class FreecamMod {

    public static Minecraft mc;
    public static boolean isEnabled = false;
    public CommandHandler commandHandler;
    public Freecam freecam;

    KeyBinding freecamBinding = new KeyBinding("Toggle Freecam", Keyboard.KEY_X, "freecam");

    public static final String MOD_NAME = "Freecam";
    public static final String MOD_ID = "freecam";
    public static final String MOD_VERSION = "1.0";


    private static final FreecamMod instance = new FreecamMod();

    public void init() {
        mc = Minecraft.getMinecraft();
        commandHandler = new CommandHandler();
        commandHandler.registerCommand("freecam", new FreeCamCommand());

        ClientRegistry.registerKeyBinding(freecamBinding);
    }

    @SubscribeEvent
    public void onEvent(InputEvent.KeyInputEvent event) {
        if (freecamBinding.isPressed()) {
            toggleFreecam();
        }
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
        freecam.setPositionAndRotation(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
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
        Wrapper.mc().getRenderManager().livingPlayer = Wrapper.player();
        System.out.println("Freecam off");
        Wrapper.sendActionbar("Freecam disabled", 30);
    }

    public static FreecamMod getInstance() {
        return instance;
    }

}
