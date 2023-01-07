package de.amin.freecam;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;

import java.util.UUID;

public class Freecam extends EntityPlayerSP {

    public static Minecraft mc = Minecraft.getMinecraft();

    public static NetHandlerPlayClient networkHandler = new NetHandlerPlayClient(mc, mc.currentScreen, mc.getNetHandler().getNetworkManager(), new GameProfile(UUID.randomUUID(), "Freecam")) {
        @Override
        public void addToSendQueue(Packet p_addToSendQueue_1_) {
        }
    };

    public Freecam() {
        super(mc, mc.theWorld, networkHandler, mc.thePlayer.getStatFileWriter());
        this.capabilities.allowFlying = true;
        this.capabilities.isFlying = true;
    }

    public void spawn() {
        worldObj.spawnEntityInWorld(this);
    }

    public void despawn() {
        worldObj.removeEntity(this);
    }

    @Override
    public boolean isInWater() {
        return false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.capabilities.setFlySpeed(FreecamMod.getInstance().getConfig().getFlySpeed());
        this.inventory.currentItem = Wrapper.player().inventory.currentItem;
        this.setHealth(Wrapper.player().getHealth());
    }
}
