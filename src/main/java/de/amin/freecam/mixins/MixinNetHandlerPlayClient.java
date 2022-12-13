package de.amin.freecam.mixins;

import de.amin.freecam.FreecamMod;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S01PacketJoinGame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {

    @Inject(
            at = @At("TAIL"),
            method = "handleJoinGame"
    )
    public void onJoin(S01PacketJoinGame packetIn, CallbackInfo ci) {
        if (FreecamMod.isEnabled) FreecamMod.getInstance().disableFreecam();
    }

}
