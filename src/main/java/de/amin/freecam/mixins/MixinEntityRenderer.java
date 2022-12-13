package de.amin.freecam.mixins;

import de.amin.freecam.FreecamMod;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

    @Inject(
            at = @At("HEAD"),
            method = "isDrawBlockOutline",
            cancellable = true
    )
    public void onDrawBlockOutline(CallbackInfoReturnable<Boolean> cir) {
        if(FreecamMod.isEnabled) cir.setReturnValue(false);
    }

}
