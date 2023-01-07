package de.amin.freecam.mixins;

import de.amin.freecam.FreecamMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends Entity {

    public MixinEntityPlayer(World worldIn) {
        super(worldIn);
    }

    @Redirect(
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;isSpectator()Z"),
            method = "onUpdate"
    )
    public boolean onUpdate(EntityPlayer instance) {
        return instance.isSpectator() || FreecamMod.isEnabled && instance.equals(FreecamMod.getInstance().freecam);
    }


}
