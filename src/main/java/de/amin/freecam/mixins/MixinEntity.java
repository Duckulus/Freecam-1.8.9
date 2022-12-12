package de.amin.freecam.mixins;

import de.amin.freecam.FreecamMod;
import de.amin.freecam.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Entity.class)
public abstract class MixinEntity {

    @Shadow public abstract boolean equals(Object p_equals_1_);

    @Inject(
            at = @At("HEAD"),
            method = "setAngles",
            cancellable = true
    )
    public void onRotationChange(float x, float y, CallbackInfo ci) {
        if(FreecamMod.isEnabled && this.equals(Wrapper.player())) {
            FreecamMod.getInstance().freecam.setAngles(x,y);
            ci.cancel();
        }
    }

}
