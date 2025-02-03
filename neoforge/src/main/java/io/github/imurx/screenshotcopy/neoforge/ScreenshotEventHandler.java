package io.github.imurx.screenshotcopy.neoforge;

import io.github.imurx.screenshotcopy.ScreencopyConfig;
import io.github.imurx.screenshotcopy.ScreenshotCopy;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.text.Text;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenshotEvent;

@EventBusSubscriber(modid = ScreenshotCopy.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ScreenshotEventHandler {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onScreenshot(ScreenshotEvent ev) {
        if(ev.getScreenshotFile().exists()) return;
        try {
            ScreenshotCopy.copyScreenshot(ev.getImage());
            if(!AutoConfig.getConfigHolder(ScreencopyConfig.class).getConfig().saveScreenshot) {
                ev.setResultMessage(Text.translatable("text.screencopy.success"));
                ev.setCanceled(true);
            }
        } catch(Exception ex) {
            ev.setResultMessage(Text.translatable("text.screencopy.failure", ex.toString()));
            if(!AutoConfig.getConfigHolder(ScreencopyConfig.class).getConfig().saveScreenshot) ev.setCanceled(true);
        }
    }
}
