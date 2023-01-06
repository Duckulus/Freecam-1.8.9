package de.amin.freecam.mixins;

import de.amin.freecam.FreecamMod;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {

    /*
       Disable Freecam when joining a world or respawning
     */
    @Inject(
            at = @At("TAIL"),
            method = "handleSpawnPosition"
    )
    public void onJoin(S05PacketSpawnPosition packetIn, CallbackInfo ci) {
        if (FreecamMod.isEnabled) FreecamMod.getInstance().disableFreecam();
    }

}
