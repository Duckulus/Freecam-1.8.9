import de.amin.freecam.FreecamMod;
import de.amin.freecam.events.RenderHand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = FreecamMod.MOD_ID, name = FreecamMod.MOD_NAME, version = FreecamMod.MOD_VERSION, clientSideOnly = true)
public class FreecamForgeMod {

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        FreecamMod.getInstance().init();
        MinecraftForge.EVENT_BUS.register(FreecamMod.getInstance());
        MinecraftForge.EVENT_BUS.register(new RenderHand());
    }

}
