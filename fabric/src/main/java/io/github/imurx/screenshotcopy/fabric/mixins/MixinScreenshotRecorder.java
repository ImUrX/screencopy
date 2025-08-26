package io.github.imurx.screenshotcopy.fabric.mixins;

import io.github.imurx.screenshotcopy.ScreencopyConfig;
import io.github.imurx.screenshotcopy.ScreenshotCopy;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.function.Consumer;

@Mixin(ScreenshotRecorder.class)
public abstract class MixinScreenshotRecorder {
    @Inject(at = @At("HEAD"), method = "method_68157", cancellable = true)
    private static void onInnerScreenshot(File file, String string, Consumer<Text> messageReceiver, NativeImage image, CallbackInfo ci) {
        var config = AutoConfig.getConfigHolder(ScreencopyConfig.class).getConfig();
        if(!config.copyScreenshot) return;

        try {
            ScreenshotCopy.copyScreenshot(image);
            if(!config.saveScreenshot || config.messageOnCopy) {
                messageReceiver.accept(Text.translatable("text.screencopy.success"));
            }
        } catch(Exception ex) {
            messageReceiver.accept(Text.translatable("text.screencopy.failure", ex.toString()));
        } finally {
            if(!config.saveScreenshot) ci.cancel();
        }
    }
}
