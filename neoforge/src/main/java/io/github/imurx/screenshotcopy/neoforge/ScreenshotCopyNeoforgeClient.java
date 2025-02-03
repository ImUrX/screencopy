package io.github.imurx.screenshotcopy.neoforge;

import io.github.imurx.screenshotcopy.ScreencopyConfig;
import io.github.imurx.screenshotcopy.ScreenshotCopy;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = ScreenshotCopy.MOD_ID, dist = Dist.CLIENT)
public final class ScreenshotCopyNeoforgeClient {
    public ScreenshotCopyNeoforgeClient() {
        AutoConfig.register(ScreencopyConfig.class, Toml4jConfigSerializer::new);
        ScreenshotCopy.init();

        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (client, parent) ->
                AutoConfig.getConfigScreen(ScreencopyConfig.class, parent).get()
        );
    }
}
