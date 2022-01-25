package io.github.imurx.screenshotcopy.fabric;


import io.github.imurx.screenshotcopy.ScreencopyConfig;
import io.github.imurx.screenshotcopy.ScreenshotCopy;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class ScreenshotCopyFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AutoConfig.register(ScreencopyConfig.class, Toml4jConfigSerializer::new);
        ScreenshotCopy.init();
    }
}
