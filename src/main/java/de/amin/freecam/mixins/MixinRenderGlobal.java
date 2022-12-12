package de.amin.freecam.mixins;

import de.amin.freecam.FreecamMod;
import de.amin.freecam.Wrapper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderGlobal.class)
public class MixinRenderGlobal {

    @Inject(
            at = @At("HEAD"),
            method = "renderEntities"
    )
    public void onRender(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci) {
        if (FreecamMod.isEnabled) {
            Wrapper.mc().getRenderManager().renderEntitySimple(Wrapper.player(), partialTicks);
        }
    }

}
