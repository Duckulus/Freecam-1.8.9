package de.amin.freecam;

import de.amin.freecam.commands.CommandHandler;
import de.amin.freecam.commands.impl.FreeCamCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = FreecamMod.MOD_ID, name = FreecamMod.MOD_NAME, version = FreecamMod.MOD_VERSION, clientSideOnly = true)
public class FreecamMod {

    public static Minecraft mc;
    public static boolean isEnabled = false;
    public CommandHandler commandHandler;
    public Freecam freecam;

    public static final String MOD_NAME = "Freecam";
    public static final String MOD_ID = "freecam";
    public static final String MOD_VERSION = "1.0";


    @Mod.Instance(FreecamMod.MOD_ID)
    private static FreecamMod instance;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        mc = Minecraft.getMinecraft();
        commandHandler = new CommandHandler();
        commandHandler.registerCommand("freecam", new FreeCamCommand());
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
        System.out.println("Freecam on");
        Wrapper.sendMessage("Freecam enabled");
    }

    public void disableFreecam() {
        isEnabled = false;
        mc.thePlayer.noClip = false;
        freecam.despawn();
        mc.thePlayer.movementInput = freecam.movementInput;
        mc.setRenderViewEntity(mc.thePlayer);
        System.out.println("Freecam off");
        Wrapper.sendMessage("Freecam disabled");
    }

    public static FreecamMod getInstance() {
        return instance;
    }

}
