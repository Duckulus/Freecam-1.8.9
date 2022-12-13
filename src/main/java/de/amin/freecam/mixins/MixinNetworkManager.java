package de.amin.freecam.mixins;

import de.amin.freecam.FreecamMod;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0APacketAnimation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {

    @Inject(
            at = @At("HEAD"),
            method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;)V"
    )
    public void onRead(ChannelHandlerContext p_channelRead0_1_, Packet p, CallbackInfo ci) {
//        if(p.getClass().getName().contains(".client"))
//            System.out.println("Received: " + p);
//        if(p instanceof C03PacketPlayer) {
//            System.out.println("ismoving: " + ((C03PacketPlayer) p).isMoving());
//        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "sendPacket(Lnet/minecraft/network/Packet;)V",
            cancellable = true)
    public void onSendPacket(Packet inPacket, CallbackInfo ci) {
        if (FreecamMod.isEnabled) {
            if (inPacket instanceof C07PacketPlayerDigging || inPacket instanceof C0APacketAnimation || inPacket instanceof C08PacketPlayerBlockPlacement) {
                ci.cancel();
            }
        }
    }

}
