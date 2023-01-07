package de.amin.freecam.mixins;

import de.amin.freecam.FreecamMod;
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
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/client/ForgeHooksClient;renderFirstPersonHand(Lnet/minecraft/client/renderer/RenderGlobal;FI)Z"
            ),
            method = "renderWorldPass",
            cancellable = true
    )
    public void onRenderHand(int pass, float partialTicks, long finishTimeNano, CallbackInfo ci) {
        if(FreecamMod.isEnabled) {
            ci.cancel();
        }
    }

}
