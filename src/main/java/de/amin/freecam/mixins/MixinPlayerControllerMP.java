package de.amin.freecam.mixins;

import de.amin.freecam.FreecamMod;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerControllerMP.class)
public class MixinPlayerControllerMP {

    @Inject(
            at = @At("HEAD"),
            method = "attackEntity",
            cancellable = true
    )
    public void onAttackEntity(EntityPlayer p_attackEntity_1_, Entity p_attackEntity_2_, CallbackInfo ci) {
        if (FreecamMod.isEnabled) {
            ci.cancel();
        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "interactWithEntitySendPacket",
            cancellable = true)
    public void onInteract(EntityPlayer playerIn, Entity targetEntity, CallbackInfoReturnable<Boolean> cir) {
        if (FreecamMod.isEnabled) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "onPlayerDamageBlock",
            cancellable = true
    )
    public void onAttackBlock(BlockPos posBlock, EnumFacing directionFacing, CallbackInfoReturnable<Boolean> cir) {
        if (FreecamMod.isEnabled) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "clickBlock",
            cancellable = true
    )
    public void onClickBlock(BlockPos posBlock, EnumFacing directionFacing, CallbackInfoReturnable<Boolean> cir) {
        if (FreecamMod.isEnabled) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "onPlayerRightClick",
            cancellable = true
    )
    public void onRightClickFreecam(EntityPlayerSP player, WorldClient worldIn, ItemStack heldStack, BlockPos hitPos, EnumFacing side, Vec3 hitVec, CallbackInfoReturnable<Boolean> cir) {
        if (FreecamMod.isEnabled) {
            cir.setReturnValue(false);
        }
    }

}
