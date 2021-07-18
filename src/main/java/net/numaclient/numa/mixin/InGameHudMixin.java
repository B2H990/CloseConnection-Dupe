package net.numaclient.numa.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Inject(method = {"render(Lnet/minecraft/client/util/math/MatrixStack;F)V"}, at = {@At("HEAD")})
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        textRenderer.draw(matrices, "Dupe by A2H#6255", 1, 1, -1);
    }
}