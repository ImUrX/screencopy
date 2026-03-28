package io.github.imurx.screenshotcopy.fabric.mixins;

import com.mojang.blaze3d.platform.NativeImage;
import io.github.imurx.screenshotcopy.ScreencopyConfig;
import io.github.imurx.screenshotcopy.ScreenshotCopy;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.Screenshot;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.function.Consumer;

@Mixin(Screenshot.class)
public abstract class MixinScreenshotRecorder {
    @Inject(at = @At("HEAD"), method = "lambda$grab$1", cancellable = true)
    private static void onInnerScreenshot(NativeImage image, File file, Consumer<Component> callback, CallbackInfo ci) {
        var config = AutoConfig.getConfigHolder(ScreencopyConfig.class).getConfig();
        if(!config.copyScreenshot) return;

        try {
            ScreenshotCopy.copyScreenshot(image);
            if(!config.saveScreenshot || config.messageOnCopy) {
                callback.accept(Component.translatable("text.screencopy.success"));
            }
        } catch(Exception ex) {
            callback.accept(Component.translatable("text.screencopy.failure", ex.toString()));
        } finally {
            if(!config.saveScreenshot) ci.cancel();
        }
    }
}
