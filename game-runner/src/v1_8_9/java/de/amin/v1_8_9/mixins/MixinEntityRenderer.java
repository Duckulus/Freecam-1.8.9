package de.amin.v1_8_9.mixins;

import de.amin.v1_8_9.freecam.FreecamMod;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

    /*
     * Do not draw block outlines while in freecam to indicate that you are not able to place blocks
     */
    @Inject(
            at = @At("HEAD"),
            method = "isDrawBlockOutline",
            cancellable = true
    )
    public void onDrawBlockOutline(CallbackInfoReturnable<Boolean> cir) {
        if(FreecamMod.isEnabled) cir.setReturnValue(false);
    }

    @Inject(
            at = @At("HEAD"),
            method = "renderHand",
            cancellable = true
    )
    public void onRenderHand(float partialTicks, int xOffset, CallbackInfo ci) {
        if(FreecamMod.isEnabled && !FreecamMod.getInstance().getConfig().isShowHand()) {
            ci.cancel();
        }
    }

}
