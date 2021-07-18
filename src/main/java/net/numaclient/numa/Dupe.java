package net.numaclient.numa;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;

public class Dupe {
    Timer timer = new Timer();
    MinecraftClient mc = MinecraftClient.getInstance();
    boolean shouldPlace = false;
    int delay;
    public void onCommand(String message) {
        String[] args = message.replace(".dupe ", "").split(" ");
        try {
            timer.reset();
            shouldPlace = true;
            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                delay = Integer.parseInt(args[1]);
                mc.player.swingHand(Hand.MAIN_HAND);
                mc.player.swingHand(Hand.OFF_HAND);
            }
        } catch (Exception e) {
            mc.player.sendMessage(Text.of("Invalid Args Provided"), false);
        }
    }


    public void onTick() {
        if (!shouldPlace) return;
        if (timer.passed(delay)) {
            mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, (BlockHitResult) mc.player.raycast(40, mc.getTickDelta(), false));
            timer.reset();
            shouldPlace = false;
        }
    }
}
