package skid.krypton.mixin;

import net.caffeinemc.mods.sodium.client.model.light.data.LightDataAccess;
import net.minecraft.world.LightType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import skid.krypton.Main;
import skid.krypton.module.modules.render.Fullbright;

@Mixin(value = LightDataAccess.class, remap = false)
public abstract class SodiumLightDataAccessMixin {
    @ModifyVariable(method = "compute", at = @At(value = "STORE"), name = "sl")
    private int compute_assignSL(int sl) {
        Fullbright fb = (Fullbright) Main.getKrypton().getModuleManager().getModuleByClass(Fullbright.class);
        return fb != null && fb.isEnabled() ? Math.max(fb.getLuminance(LightType.SKY), sl) : sl;
    }

    @ModifyVariable(method = "compute", at = @At(value = "STORE"), name = "bl")
    private int compute_assignBL(int bl) {
        Fullbright fb = (Fullbright) Main.getKrypton().getModuleManager().getModuleByClass(Fullbright.class);
        return fb != null && fb.isEnabled() ? Math.max(fb.getLuminance(LightType.BLOCK), bl) : bl;
    }
}