package de.amin.freecam.mixins;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
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
        if(p instanceof C03PacketPlayer.C05PacketPlayerLook) {
            System.out.println("Looking to " + ((C03PacketPlayer.C05PacketPlayerLook) p).getYaw() + " " + ((C03PacketPlayer.C05PacketPlayerLook) p).getPitch());
        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "sendPacket(Lnet/minecraft/network/Packet;)V"
    )
    public void onSendPacket(Packet inPacket, CallbackInfo ci) {
        if (inPacket instanceof C03PacketPlayer) {
//            System.out.println("Before: " + ((C03PacketPlayer) inPacket).isMoving());
//            ((C03PacketPlayer) inPacket).setMoving(false);
//            System.out.println("After: " + ((C03PacketPlayer) inPacket).isMoving());
        }
    }

}
