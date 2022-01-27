package io.github.imurx.screenshotcopy.fabric;


import io.github.imurx.screenshotcopy.ScreencopyConfig;
import io.github.imurx.screenshotcopy.ScreenshotCopy;
import me.ramidzkh.fabrishot.event.FramebufferCaptureCallback;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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
            ByteBuffer buffer = ByteBuffer.allocate(dimension.height * dimension.width * 4).order(ByteOrder.LITTLE_ENDIAN);
            //im sure there is a better way but no idea
            for (int i = 0; i < byteBuffer.position(); i+=3) {
                for(int j = 0; j < 3; j++) buffer.put(byteBuffer.get(j+i));
                buffer.put((byte) 255);
            }
            ScreenshotCopy.copyScreenshot(dimension.width, dimension.height, buffer.array());
        });
    }
}
