package de.amin.v1_8_9.mixins;

import de.amin.v1_8_9.freecam.FreecamMod;
import de.amin.v1_8_9.freecam.Wrapper;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Entity.class)
public abstract class MixinEntity {

    @Shadow public abstract boolean equals(Object p_equals_1_);

    /*
     This mixin is needed to rotate the freecam
     */
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
