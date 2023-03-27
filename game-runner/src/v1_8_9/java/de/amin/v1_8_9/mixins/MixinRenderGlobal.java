package de.amin.v1_8_9.mixins;

import de.amin.v1_8_9.freecam.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RenderGlobal.class)
public class MixinRenderGlobal {

    /*
    This mixin is needed to render the player while in Freecam
     */
    @Redirect(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Minecraft;getRenderViewEntity()Lnet/minecraft/entity/Entity;",
                    ordinal = 1
            ),
            method = "renderEntities"
    )
    public Entity getRenderViewEntity(Minecraft instance) {
        return Wrapper.player();

    }

}
