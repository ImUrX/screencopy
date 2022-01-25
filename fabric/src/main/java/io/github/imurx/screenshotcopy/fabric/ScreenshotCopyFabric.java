package io.github.imurx.screenshotcopy.fabric;


import io.github.imurx.screenshotcopy.ScreenshotCopy;
import net.fabricmc.api.ClientModInitializer;

public class ScreenshotCopyFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenshotCopy.init();
    }
}
