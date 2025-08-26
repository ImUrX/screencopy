package io.github.imurx.screenshotcopy.fabric;


import io.github.imurx.screenshotcopy.ScreencopyConfig;
import io.github.imurx.screenshotcopy.ScreenshotCopy;
import me.ramidzkh.fabrishot.event.FramebufferCaptureCallback;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ScreenshotCopyFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AutoConfig.register(ScreencopyConfig.class, Toml4jConfigSerializer::new);
        ScreenshotCopy.init();

        try {
            if(FabricLoader.getInstance().isModLoaded("fabrishot")) initFabrishot();
        } catch(Exception e) {
            ScreenshotCopy.LOGGER.error("Failed to init Fabrishot compatibility", e);
        }

        ClientLifecycleEvents.CLIENT_STOPPING.register((_client) -> ScreenshotCopy.stop());
    }

    private void initFabrishot() {
        FramebufferCaptureCallback.EVENT.register((image) -> {
            var config = AutoConfig.getConfigHolder(ScreencopyConfig.class).getConfig();
            if(!config.copyScreenshot) return;

            try {
                ScreenshotCopy.copyScreenshot(image);
                if(config.messageOnCopy) {
                    MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.translatable("text.screencopy.success"));
                }
            } catch(Exception ex) {
                MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.translatable("text.screencopy.failure", ex.toString()));
            }

        });
    }
}
