package de.amin.v1_8_9.mixins;

import de.amin.v1_8_9.freecam.FreecamMod;
import de.amin.v1_8_9.freecam.Wrapper;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase {

    /*
      This mixin disables the client-side swing item animation while in Freecam
     */
    @Inject(method = "swingItem", at = @At("HEAD"), cancellable = true)
    public void onSwing(CallbackInfo ci) {
        if (FreecamMod.isEnabled && this.equals(Wrapper.mc().thePlayer)) {
            ci.cancel();
        }
    }

}
