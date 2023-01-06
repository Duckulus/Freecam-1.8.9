package de.amin.freecam.mixins;

import de.amin.freecam.FreecamMod;
import de.amin.freecam.Wrapper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLivingBase.class)
public class MixinEntityLivingBase {

    @Inject(method = "attackEntityFrom", at = @At("HEAD"))
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (FreecamMod.isEnabled && this.equals(Wrapper.player()))
            FreecamMod.getInstance().disableFreecam();
    }

}
