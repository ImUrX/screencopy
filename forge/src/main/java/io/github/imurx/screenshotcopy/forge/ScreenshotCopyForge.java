package io.github.imurx.screenshotcopy.forge;

import io.github.imurx.screenshotcopy.ScreencopyConfig;
import io.github.imurx.screenshotcopy.ScreenshotCopy;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(ScreenshotCopy.MOD_ID)
public class ScreenshotCopyForge {
    public ScreenshotCopyForge() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> "Client-side mod", (a, b) -> b));
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> this::client);
    }

    public void client() {
        AutoConfig.register(ScreencopyConfig.class, Toml4jConfigSerializer::new);
        ScreenshotCopy.init();

        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) ->
                AutoConfig.getConfigScreen(ScreencopyConfig.class, parent).get()
        ));
    }
}
