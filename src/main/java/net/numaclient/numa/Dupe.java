package net.numaclient.numa;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;

import java.util.Objects;

public class Dupe {
    Timer timer = new Timer();
    MinecraftClient mc = MinecraftClient.getInstance();
    boolean shouldPlace = false;
    int delay;

    public void onCommand(String message) {
        String[] args = message.replace(".dupe ", "").split(" ");
        if (args[0].contains("swing")) {
            try {
                timer.reset();
                shouldPlace = true;
                delay = Integer.parseInt(args[2]);
                for (int i = 0; i < Integer.parseInt(args[1]); i++) {
                    if (mc.player == null || mc.world == null) return;
                    mc.player.swingHand(Hand.MAIN_HAND);
                    mc.player.swingHand(Hand.OFF_HAND);
                }
            } catch (Exception e) {
                if (mc.player != null) mc.player.sendMessage(Text.of("Invalid Args Provided"), false);
            }
        }
        if (args[0].contains("message")) {
            try {
                timer.reset();
                shouldPlace = true;
                delay = Integer.parseInt(args[1]);
                Objects.requireNonNull(mc.getNetworkHandler()).sendPacket(new ChatMessageC2SPacket(" "));
            } catch (Exception e) {
                if (mc.player != null) mc.player.sendMessage(Text.of("Invalid Args Provided"), false);
            }
        }
    }


    public void onTick() {
        if (mc.world == null || mc.player == null || mc.interactionManager == null) return;
            if (!shouldPlace) return;
        if (timer.passed(delay)) {
            mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, (BlockHitResult) mc.player.raycast(40, mc.getTickDelta(), false));
            timer.reset();
            shouldPlace = false;
        }
    }
}
