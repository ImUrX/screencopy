package io.github.imurx.screenshotcopy.forge;

import io.github.imurx.screenshotcopy.ScreencopyConfig;
import io.github.imurx.screenshotcopy.ScreenshotCopy;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraftforge.fml.common.Mod;
import io.github.imurx.arboard.Clipboard;

@Mod(ScreenshotCopy.MOD_ID)
public class ScreenshotCopyForge {
    public ScreenshotCopyForge() {
        var a = new Clipboard();
        a.close();
        AutoConfig.register(ScreencopyConfig.class, Toml4jConfigSerializer::new);
        ScreenshotCopy.init();
    }
}
