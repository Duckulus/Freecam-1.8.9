package de.amin.freecam.mixins;

import de.amin.freecam.FreecamMod;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {
    @Inject(
            at = @At("HEAD"),
            method = "sendChatMessage",
            cancellable = true
    )
    public void onSendChatMessage(String msg, CallbackInfo ci) {
        if (FreecamMod.getInstance().commandHandler.handleMessage(msg)) {
            ci.cancel();
        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "isCurrentViewEntity",
            cancellable = true
    )
    public void onIsCamera(CallbackInfoReturnable<Boolean> cir) {
        if (FreecamMod.isEnabled) cir.setReturnValue(true);
    }

    @Inject(method = "damageEntity", at = @At("HEAD"))
    private void onDamage(DamageSource damageSrc, float damageAmount, CallbackInfo ci) {
        if (FreecamMod.isEnabled)
            FreecamMod.getInstance().disableFreecam();
    }

}
