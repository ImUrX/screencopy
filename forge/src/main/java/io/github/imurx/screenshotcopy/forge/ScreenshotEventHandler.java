package io.github.imurx.screenshotcopy.forge;
import io.github.imurx.screenshotcopy.ScreencopyConfig;
import io.github.imurx.screenshotcopy.ScreenshotCopy;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.text.TranslatableText;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenshotEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ScreenshotCopy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ScreenshotEventHandler {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onScreenshot(ScreenshotEvent ev) {
        if(ev.getScreenshotFile().exists()) return;
        try {
            ScreenshotCopy.copyScreenshot(ev.getImage());
            if(!AutoConfig.getConfigHolder(ScreencopyConfig.class).getConfig().saveScreenshot) {
                ev.setResultMessage(new TranslatableText("screencopy.success"));
                ev.setCanceled(true);
            }
        } catch(Exception ex) {
            ev.setResultMessage(new TranslatableText("screencopy.failure", ex.toString()));
            if(!AutoConfig.getConfigHolder(ScreencopyConfig.class).getConfig().saveScreenshot) ev.setCanceled(true);
        }
    }
}
