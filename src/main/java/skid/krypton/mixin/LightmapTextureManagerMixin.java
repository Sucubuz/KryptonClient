package skid.krypton.mixin;

import skid.krypton.Main;
import skid.krypton.module.modules.render.Fullbright;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LightmapTextureManager.class)
public abstract class LightmapTextureManagerMixin {
    @ModifyArgs(
        method = "update",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/texture/NativeImage;setColor(III)V"
        )
    )
    private void onUpdateLightmap(Args args) {
        Fullbright fullbright = (Fullbright) Main.getKrypton().getModuleManager().getModuleByClass(Fullbright.class);
        if (fullbright != null && fullbright.isEnabled()) {
            args.set(2, 0xFFFFFFFF);
        }
    }
}