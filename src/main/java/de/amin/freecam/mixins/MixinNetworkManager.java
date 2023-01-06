package de.amin.freecam.mixins;

import de.amin.freecam.FreecamMod;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {

    /*
    Cancel outgoing packets that could tell a server that you are using freecam
     */
    @Inject(at = @At("HEAD"), method = "sendPacket(Lnet/minecraft/network/Packet;)V", cancellable = true)
    public void onSendPacket(Packet inPacket, CallbackInfo ci) {
        if (FreecamMod.isEnabled) {
            if (inPacket instanceof C07PacketPlayerDigging
                    || inPacket instanceof C0APacketAnimation
                    || inPacket instanceof C02PacketUseEntity) {
                ci.cancel();
            }
        }
    }

}
