package io.github.imurx.screenshotcopy.neoforge;

import io.github.imurx.screenshotcopy.ScreencopyConfig;
import io.github.imurx.screenshotcopy.ScreenshotCopy;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenshotEvent;

@EventBusSubscriber(modid = ScreenshotCopy.MOD_ID, value = Dist.CLIENT)
public class ScreenshotEventHandler {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onScreenshot(ScreenshotEvent ev) {
        var config = AutoConfig.getConfigHolder(ScreencopyConfig.class).getConfig();
        if(ev.getScreenshotFile().exists() || !config.copyScreenshot) return;

        try {
            ScreenshotCopy.copyScreenshot(ev.getImage());
            if(!config.saveScreenshot) {
                ev.setResultMessage(Component.translatable("text.screencopy.success"));
            } else if(config.messageOnCopy) {
                Minecraft.getInstance().gui.hud.getChat().addClientSystemMessage(Component.translatable("text.screencopy.success"));
            }
        } catch(Exception ex) {
            ev.setResultMessage(Component.translatable("text.screencopy.failure", ex.toString()));
        } finally {
            if(!config.saveScreenshot) ev.setCanceled(true);
        }
    }
}
