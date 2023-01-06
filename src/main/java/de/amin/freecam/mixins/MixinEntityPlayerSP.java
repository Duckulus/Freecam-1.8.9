package de.amin.freecam.mixins;

import de.amin.freecam.FreecamMod;
import de.amin.freecam.Wrapper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends EntityLivingBase {
    public MixinEntityPlayerSP(World worldIn) {
        super(worldIn);
    }

    @Inject(at = @At("HEAD"), method = "sendChatMessage", cancellable = true)
    public void onSendChatMessage(String msg, CallbackInfo ci) {
        if (FreecamMod.getInstance().commandHandler.handleMessage(msg)) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "isCurrentViewEntity", cancellable = true)
    public void onIsCamera(CallbackInfoReturnable<Boolean> cir) {
        if (FreecamMod.isEnabled && this.equals(FreecamMod.getInstance().freecam)) cir.setReturnValue(true);
    }

    /*
     This is mixin is needed to keep sending Player Packets when the viewEntity is set to FreeCam
     */
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isCurrentViewEntity()Z"), method = "onUpdateWalkingPlayer")
    public boolean onIsViewEntity(EntityPlayerSP instance) {
        return this.equals(Wrapper.player());
    }

}
