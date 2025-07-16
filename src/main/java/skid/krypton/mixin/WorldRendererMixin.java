package skid.krypton.mixin;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.world.LightType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import skid.krypton.Main;
import skid.krypton.module.modules.render.Fullbright;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    @ModifyVariable(
            method = "getLightmapCoordinates(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;)I",
            at = @At(value = "STORE"),
            ordinal = 0
    )
    private static int modifySkyLight(int skyLight) {
        Fullbright fullbright = (Fullbright) Main.getKrypton().getModuleManager().getModuleByClass(Fullbright.class);
        if (fullbright != null && fullbright.isEnabled()) {
            return Math.max(fullbright.getLuminance(LightType.SKY), skyLight);
        }
        return skyLight;
    }

    @ModifyVariable(
            method = "getLightmapCoordinates(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;)I",
            at = @At(value = "STORE"),
            ordinal = 1
    )
    private static int modifyBlockLight(int blockLight) {
        Fullbright fullbright = (Fullbright) Main.getKrypton().getModuleManager().getModuleByClass(Fullbright.class);
        if (fullbright != null && fullbright.isEnabled()) {
            return Math.max(fullbright.getLuminance(LightType.BLOCK), blockLight);
        }
        return blockLight;
    }
}