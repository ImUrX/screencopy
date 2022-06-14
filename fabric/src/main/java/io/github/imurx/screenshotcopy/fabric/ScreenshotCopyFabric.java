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

        if(FabricLoader.getInstance().isModLoaded("fabrishot")) initFabrishot();

        ClientLifecycleEvents.CLIENT_STOPPING.register((_client) -> ScreenshotCopy.stop());
    }

    private void initFabrishot() {
        FramebufferCaptureCallback.EVENT.register((dimension, byteBuffer) -> {
            byte[] array = new byte[dimension.height() * dimension.width() * 4];
            //im sure there is a better way but no idea
            int offset = 0;
            for (int i = 0; i < byteBuffer.capacity(); i++) {
                if(i % 3 == 0 && i != 0) {
                    array[i+offset] = -1;
                    offset++;
                }
                array[offset+i] = byteBuffer.get(i);
            }
            try {
                ScreenshotCopy.copyScreenshot(dimension.width(), dimension.height(), array);
                MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.translatable("text.screencopy.success"));
            } catch(Exception ex) {
                MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.translatable("text.screencopy.failure", ex.toString()));
            }

        });
    }
}
