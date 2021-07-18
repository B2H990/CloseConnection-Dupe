package net.numaclient.numa.mixin;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.numaclient.numa.Dupe;
import net.numaclient.numa.Numa;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    MinecraftClient mc = MinecraftClient.getInstance();

    @Inject(method = "send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V", at = @At("HEAD"), cancellable = true)
    public void send(Packet<?> packet, GenericFutureListener<? extends Future<? super Void>> callback, CallbackInfo ci) {
        if (packet instanceof ChatMessageC2SPacket) {
            String message = ((ChatMessageC2SPacket) packet).getChatMessage();
            if (message.startsWith(".dupe")) {
                Numa.INSTANCE.dupe.onCommand(message);
                ci.cancel();
            }
        }
    }
}
