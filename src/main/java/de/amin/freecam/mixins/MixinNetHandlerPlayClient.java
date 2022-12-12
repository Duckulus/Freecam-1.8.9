package de.amin.freecam.mixins;

import de.amin.freecam.FreecamMod;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S40PacketDisconnect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {

    @Inject(
            at = @At("HEAD"),
            method = "handleDisconnect"
    )
    public void onDisconnect(S40PacketDisconnect packetIn, CallbackInfo ci) {
        if(FreecamMod.isEnabled) FreecamMod.getInstance().disableFreecam();
    }

}
