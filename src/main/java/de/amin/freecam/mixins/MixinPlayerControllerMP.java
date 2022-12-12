package de.amin.freecam.mixins;

import de.amin.freecam.FreecamMod;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerControllerMP.class)
public class MixinPlayerControllerMP {

    @Inject(
            at = @At("HEAD"),
            method = "attackEntity",
            cancellable = true
    )
    public void onAttackEntity(EntityPlayer p_attackEntity_1_, Entity p_attackEntity_2_, CallbackInfo ci)  {
        if(FreecamMod.isEnabled) {
            ci.cancel();
        }
    }

}
