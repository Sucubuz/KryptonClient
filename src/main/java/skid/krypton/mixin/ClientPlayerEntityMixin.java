package skid.krypton.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import skid.krypton.event.events.PostMotionEvent;
import skid.krypton.event.events.PreMotionEvent;
import skid.krypton.event.events.SendMovementPacketsEvent;
import skid.krypton.event.events.StartTickEvent;
import skid.krypton.manager.EventManager;

@Mixin({ClientPlayerEntity.class})
public class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {
    @Shadow
    @Final
    protected MinecraftClient client;

    public ClientPlayerEntityMixin(final ClientWorld world, final GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = {"sendMovementPackets"}, at = {@At("HEAD")})
    private void onSendMovementPackets(final CallbackInfo ci) {
        EventManager.b(new SendMovementPacketsEvent());
    }

    @Inject(method = {"tick"}, at = {@At("HEAD")})
    private void onPlayerTick(final CallbackInfo ci) {
        EventManager.b(new StartTickEvent());
    }

    @Inject(at = @At("HEAD"), method = "sendMovementPackets()V")
    private void onSendMovementPacketsHEAD(CallbackInfo ci)
    {
        EventManager.b(new PreMotionEvent());
    }

    @Inject(at = @At("TAIL"), method = "sendMovementPackets()V")
    private void onSendMovementPacketsTAIL(CallbackInfo ci)
    {
        EventManager.b(new PostMotionEvent());
    }
}